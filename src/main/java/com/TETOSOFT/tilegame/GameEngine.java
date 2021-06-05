package main.java.com.TETOSOFT.tilegame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import main.java.com.TETOSOFT.graphics.Sprite;
import main.java.com.TETOSOFT.input.GameAction;
import main.java.com.TETOSOFT.input.InputManager;
import main.java.com.TETOSOFT.test.GameCore;
import main.java.com.TETOSOFT.tilegame.sprites.Creature;
import main.java.com.TETOSOFT.tilegame.sprites.Player;
import main.java.com.TETOSOFT.tilegame.sprites.PowerUp;
import main.java.utils.GameLoader;
import main.java.utils.GameSaver;

/**
 * GameManager manages all parts of the game.
 */
public class GameEngine extends GameCore 
{
    
    public static void main(String[] args) 
    {
        new GameEngine().run();
    }
    
    public static final float GRAVITY = 0.002f;
    
    private Point pointCache = new Point();
    private TileMap map;
    private MapLoader mapLoader;
    private InputManager inputManager;
    private TileMapDrawer drawer;
    
    private GameAction moveLeft;
    private GameAction moveRight;
    private GameAction jump;
    private GameAction exit;
    private int collectedStars=0;
    private int numLives=6;
    
    private GameSaver gameSaver;
    private GameLoader gameLoader = null;
    
    private Clip clip1, clip2;
    
    public GameEngine() {
    	super();
    }
    
    public GameEngine(GameLoader gameLoader) {
    	super();
    	this.gameLoader = gameLoader;
    }
    
    public void init() {
        super.init();
        
        // start resource manager
        mapLoader = new MapLoader(screen.getFullScreenWindow().getGraphicsConfiguration());
        
        if(gameLoader != null) {
        		try {
        			//load the game from the save file
					gameLoader.load();
					numLives = gameLoader.getNumLives();
					collectedStars = gameLoader.getCollectedStars();
					mapLoader.setCurrentMap(gameLoader.getMap() - 1);
					//set up the game saver
			        gameSaver = new GameSaver(gameLoader);
				} catch (IOException e) {
					e.printStackTrace();
				}
        }
        	
        // set up input manager
        initInput();
        
        // load resources
        drawer = new TileMapDrawer();
        drawer.setBackground(mapLoader.loadImage("background.jpg"));
        
        // load first map
        map = mapLoader.loadNextMap(gameLoader);
        
        //set up the game saver
        if(gameSaver == null)
        	gameSaver = new GameSaver();
		
	//play background sound
	clip1 = sound("sounds/super_mario.wav", clip1);
        playSound(clip1);
        loopSound(clip1);
    }
    
    
    /**
     * Closes any resurces used by the GameManager.
     */
    public void stop() {
        super.stop();
        //save score, lives, level and player's position 
        gameSaver.saveGame(mapLoader, collectedStars, numLives, map);
	//stop background sound
	stopSound(clip1);
    }
     
    private void initInput() {
        moveLeft = new GameAction("moveLeft");
        moveRight = new GameAction("moveRight");
        jump = new GameAction("jump", GameAction.DETECT_INITAL_PRESS_ONLY);
        exit = new GameAction("exit",GameAction.DETECT_INITAL_PRESS_ONLY);
        
        inputManager = new InputManager(screen.getFullScreenWindow());
        inputManager.setCursor(InputManager.INVISIBLE_CURSOR);
        
        inputManager.mapToKey(moveLeft, KeyEvent.VK_LEFT);
        inputManager.mapToKey(moveRight, KeyEvent.VK_RIGHT);
	//making up touch instead if escape for jump
        // inputManager.mapToKey(jump, KeyEvent.VK_SPACE);
        inputManager.mapToKey(jump, KeyEvent.VK_UP);
        inputManager.mapToKey(exit, KeyEvent.VK_ESCAPE);
    }
    
    
    private void checkInput(long elapsedTime) 
    {
        
        if (exit.isPressed()) {
            stop();
        }
        
        Player player = (Player)map.getPlayer();
        if (player.isAlive()) 
        {
            float velocityX = 0;
            if (moveLeft.isPressed()) 
            {
                velocityX-=player.getMaxSpeed();
            }
            if (moveRight.isPressed()) {
                velocityX+=player.getMaxSpeed();
            }
            if (jump.isPressed()) {
                player.jump(false);
            }
            player.setVelocityX(velocityX);
        }
        
    }
    
    
    public void draw(Graphics2D g) {
        
        drawer.draw(g, map, screen.getWidth(), screen.getHeight());
        System.out.println(screen.getWidth() + " " + screen.getHeight());
        g.setColor(Color.WHITE);
        g.drawString("Press ESC for EXIT.",screen.getWidth()*10/800,screen.getHeight()*20/600);
        g.setColor(Color.GREEN);
        g.drawString("Coins: "+collectedStars,screen.getWidth()*300.0f/800, screen.getHeight()*20/600);
        g.setColor(Color.YELLOW);
        g.drawString("Lives: "+(numLives),screen.getWidth()*500.0f/800, screen.getHeight()*20/600);
        g.setColor(Color.WHITE);
        g.drawString("Home: "+mapLoader.currentMap,screen.getWidth()*700.0f/800, screen.getHeight()*20/600);
        
    }
    
    
    /**
     * Gets the current map.
     */
    public TileMap getMap() {
        return map;
    }
    
    /**
     * Gets the tile that a Sprites collides with. Only the
     * Sprite's X or Y should be changed, not both. Returns null
     * if no collision is detected.
     */
    public Point getTileCollision(Sprite sprite, float newX, float newY) 
    {
        float fromX = Math.min(sprite.getX(), newX);
        float fromY = Math.min(sprite.getY(), newY);
        float toX = Math.max(sprite.getX(), newX);
        float toY = Math.max(sprite.getY(), newY);
        
        // get the tile locations
        int fromTileX = TileMapDrawer.pixelsToTiles(fromX);
        int fromTileY = TileMapDrawer.pixelsToTiles(fromY);
        int toTileX = TileMapDrawer.pixelsToTiles(
                toX + sprite.getWidth() - 1);
        int toTileY = TileMapDrawer.pixelsToTiles(
                toY + sprite.getHeight() - 1);
        
        // check each tile for a collision
        for (int x=fromTileX; x<=toTileX; x++) {
            for (int y=fromTileY; y<=toTileY; y++) {
                if (x < 0 || x >= map.getWidth() ||
                        map.getTile(x, y) != null) {
                    // collision found, return the tile
                    pointCache.setLocation(x, y);
                    return pointCache;
                }
            }
        }
        
        // no collision found
        return null;
    }
    
    
    /**
     * Checks if two Sprites collide with one another. Returns
     * false if the two Sprites are the same. Returns false if
     * one of the Sprites is a Creature that is not alive.
     */
    public boolean isCollision(Sprite s1, Sprite s2) {
        // if the Sprites are the same, return false
        if (s1 == s2) {
            return false;
        }
        
        // if one of the Sprites is a dead Creature, return false
        if (s1 instanceof Creature && !((Creature)s1).isAlive()) {
            return false;
        }
        if (s2 instanceof Creature && !((Creature)s2).isAlive()) {
            return false;
        }
        
        // get the pixel location of the Sprites
        int s1x = Math.round(s1.getX());
        int s1y = Math.round(s1.getY());
        int s2x = Math.round(s2.getX());
        int s2y = Math.round(s2.getY());
        
        // check if the two sprites' boundaries intersect
        return (s1x < s2x + s2.getWidth() &&
                s2x < s1x + s1.getWidth() &&
                s1y < s2y + s2.getHeight() &&
                s2y < s1y + s1.getHeight());
    }
    
    
    /**
     * Gets the Sprite that collides with the specified Sprite,
     * or null if no Sprite collides with the specified Sprite.
     */
    public Sprite getSpriteCollision(Sprite sprite) {
        
        // run through the list of Sprites
        Iterator<Sprite> i = map.getSprites();
        while (i.hasNext()) {
            Sprite otherSprite = (Sprite)i.next();
            if (isCollision(sprite, otherSprite)) {
                // collision found, return the Sprite
                return otherSprite;
            }
        }
        
        // no collision found
        return null;
    }
    
    
    /**
     * Updates Animation, position, and velocity of all Sprites
     * in the current map.
     */
    public void update(long elapsedTime) {
        Creature player = (Creature)map.getPlayer();
        
        
        // player is dead!  
        if (player.getState() == Creature.STATE_DEAD) {
        	//restart saving
        	gameSaver.closeWriter();
        	gameSaver = new GameSaver();
        	//start map over
            map = mapLoader.reloadMap(null);
            return;
        }
        
        // get keyboard/mouse input
        checkInput(elapsedTime);
        
        // update player
        updateCreature(player, elapsedTime);
        player.update(elapsedTime);
        
        // update other sprites
        Iterator<Sprite> i = map.getSprites();
        while (i.hasNext()) {
            Sprite sprite = (Sprite)i.next();
            if (sprite instanceof Creature) {
                Creature creature = (Creature)sprite;
                if (creature.getState() == Creature.STATE_DEAD) {
                    i.remove();
                    //save the creature's position
                    gameSaver.saveDeletedSpritePosition(creature);
                } else {
                    updateCreature(creature, elapsedTime);
                }
            }
            // normal update
            sprite.update(elapsedTime);
        }
    }
    
    
    /**
     * Updates the creature, applying gravity for creatures that
     * aren't flying, and checks collisions.
     */
    private void updateCreature(Creature creature,
            long elapsedTime) {
        
        // apply gravity
        if (!creature.isFlying()) {
            creature.setVelocityY(creature.getVelocityY() +
                    GRAVITY * elapsedTime);
        }
        
        // change x
        float dx = creature.getVelocityX();
        float oldX = creature.getX();
        float newX = oldX + dx * elapsedTime;
        Point tile =
                getTileCollision(creature, newX, creature.getY());
        if (tile == null) {
            creature.setX(newX);
        } else {
            // line up with the tile boundary
            if (dx > 0) {
                creature.setX(
                        TileMapDrawer.tilesToPixels(tile.x) -
                        creature.getWidth());
            } else if (dx < 0) {
                creature.setX(
                        TileMapDrawer.tilesToPixels(tile.x + 1));
            }
            creature.collideHorizontal();
        }
        if (creature instanceof Player) {
            checkPlayerCollision((Player)creature, false);
        }
        
        // change y
        float dy = creature.getVelocityY();
        float oldY = creature.getY();
        float newY = oldY + dy * elapsedTime;
        tile = getTileCollision(creature, creature.getX(), newY);
        if (tile == null) {
            creature.setY(newY);
        } else {
            // line up with the tile boundary
            if (dy > 0) {
                creature.setY(
                        TileMapDrawer.tilesToPixels(tile.y) -
                        creature.getHeight());
            } else if (dy < 0) {
                creature.setY(
                        TileMapDrawer.tilesToPixels(tile.y + 1));
            }
            creature.collideVertical();
        }
        if (creature instanceof Player) {
            boolean canKill = (oldY < creature.getY());
            checkPlayerCollision((Player)creature, canKill);
        }
        
    }
    
    
    /**
     * Checks for Player collision with other Sprites. If
     * canKill is true, collisions with Creatures will kill
     * them.
     */
    public void checkPlayerCollision(Player player,
            boolean canKill) {
        if (!player.isAlive()) {
            return;
        }
        
        // check for player collision with other sprites
        Sprite collisionSprite = getSpriteCollision(player);
        if (collisionSprite instanceof PowerUp) {
            acquirePowerUp((PowerUp)collisionSprite);
        } else if (collisionSprite instanceof Creature) {
            Creature badguy = (Creature)collisionSprite;
            if (canKill) {
                // kill the badguy and make player bounce
                badguy.setState(Creature.STATE_DYING);
                player.setY(badguy.getY() - player.getHeight());
                player.jump(true);
            } else {
                // player dies!
                player.setState(Creature.STATE_DYING);
		
		stopSound(clip1);
                clip2 = sound("sounds/Mario Dead _ Sound Effects.wav",clip2);
                playSound(clip2);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
		
                numLives--;
		
		if(numLives!=0) {
                	clip1 = sound("sounds/super_mario.wav", clip1);
                    playSound(clip1);
                    loopSound(clip1);
                }
		
                if(numLives==0) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    stop();
                }
            }
        }
    }
    
    
    /**
     * Gives the player the speicifed power up and removes it
     * from the map.
     */
    public void acquirePowerUp(PowerUp powerUp) {
        // remove it from the map
        map.removeSprite(powerUp);
        //save the powerUp's position
        gameSaver.saveDeletedSpritePosition(powerUp);
        
        if (powerUp instanceof PowerUp.Star) {
            // do something here, like give the player points
            collectedStars++;
            if(collectedStars==100) 
            {
                numLives++;
                collectedStars=0;
            }
            
        } else if (powerUp instanceof PowerUp.Music) {
            // change the music
            
        } else if (powerUp instanceof PowerUp.Goal) {
            // advance to next map
	    // reset lives to 6 for next map
	    numLives=6;
            map = mapLoader.loadNextMap(null);
	    
//            stopSound(clip1);
//            clip2 = sound("sounds/Super Mario Bros. Music - Level Complete.wav",clip2);
//            playSound(clip2);
//            
//            
//            playSound(clip1);
//            loopSound(clip1);
//            
	    
            //restart saving
        	gameSaver.closeWriter();
        	gameSaver = new GameSaver();
        }
    }
    
    /**
     * Handles playing, stoping, and looping of sounds for the game.
     */
    public Clip sound(String fileName, Clip clip) {
    	// specify the sound to play
        // (assuming the sound can be played by the audio system)
        // from a wave File
    	try {
            File file = new File(fileName);
            if (file.exists()) {
            	AudioInputStream audios = AudioSystem.getAudioInputStream(file);
            	// load the sound into memory (a Clip)
            	clip = AudioSystem.getClip();
                clip.open(audios);
            }else {
                throw new RuntimeException("Sound: file not found: " + fileName);
            }
        }
        catch (MalformedURLException e) {
            throw new RuntimeException("Sound: Malformed URL: " + e);
        }
        catch (UnsupportedAudioFileException e) {
            throw new RuntimeException("Sound: Unsupported Audio File: " + e);
        }
        catch (IOException e) {
            throw new RuntimeException("Sound: Input/Output Error: " + e);
        }
        catch (LineUnavailableException e) {
            throw new RuntimeException("Sound: Line Unavailable: " + e);
        }
    	
    	return clip;
    }
    
    // play, stop, loop the sound clip
    public void playSound(Clip clip){
        clip.setFramePosition(0);  // Must always rewind!
        clip.start();
    }
    public void loopSound(Clip clip){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stopSound(Clip clip){
        clip.stop();
    }
      
}
