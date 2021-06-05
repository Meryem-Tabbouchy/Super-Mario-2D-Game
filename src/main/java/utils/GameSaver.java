package main.java.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import main.java.com.TETOSOFT.graphics.Sprite;
import main.java.com.TETOSOFT.tilegame.MapLoader;
import main.java.com.TETOSOFT.tilegame.TileMap;
import main.java.com.TETOSOFT.tilegame.TileMapDrawer;


public class GameSaver {
	protected static final String saveFile = "savePoint.txt";
	private FileWriter fileWriter;
	
	public GameSaver() {
		File file = new File(saveFile);
		try {
			//create save file if it doesn't exist
			file.createNewFile();
			this.fileWriter = new FileWriter(saveFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public GameSaver(GameLoader gameLoader) {
		File file = new File(saveFile);
		try {
			//create save file if it doesn't exist
			file.createNewFile();
			this.fileWriter = new FileWriter(saveFile);
			//
			ArrayList<Integer> spritesToIgnoreY = gameLoader.getRemovedSpritesY();
	    	ArrayList<Integer> spritesToIgnoreX = gameLoader.getRemovedSpritesX();
	    	for(int i = 0; i < spritesToIgnoreY.size(); i++) {
	    		fileWriter.write("- " + spritesToIgnoreX.get(i) + "," + spritesToIgnoreY.get(i) + "\n");
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveDeletedSpritePosition(Sprite sprite) {
		try {
			fileWriter.write("- " + sprite.getTileX() + "," + sprite.getTileY() + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Saves the player's last position, the score, the level and the lives
	 */
	public void saveGame(MapLoader mapLoader, int collectedStars, int numLives, TileMap map) {
		if(numLives > 0) {
			saveCurrentMap(mapLoader.currentMap);
			saveScore(collectedStars);
			saveLives(numLives);
			savePlayerPosition(map.getPlayer().getX(), map.getPlayer().getY());
			closeWriter();
		}
		else {
			// the player failed => nothing to save
			closeWriter();
			try {
				this.fileWriter = new FileWriter(saveFile);
				fileWriter.write("");
				fileWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void saveScore(int collectedCoins) {
		try {
			fileWriter.write("COINS: " + collectedCoins + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void saveLives(int lives) {
		try {
			fileWriter.write("LIVES: " + lives + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void savePlayerPosition(float x, float y) {
		try {
			fileWriter.write("PLAYER: " + TileMapDrawer.pixelsToTiles(x) + "," + TileMapDrawer.pixelsToTiles(y));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void saveCurrentMap(int currentMap) {
		try {
			fileWriter.write("MAP: " + currentMap + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeWriter() {
		try {
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}