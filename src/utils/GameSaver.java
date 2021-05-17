package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.TETOSOFT.graphics.Sprite;
import com.TETOSOFT.tilegame.MapLoader;
import com.TETOSOFT.tilegame.TileMap;
import com.TETOSOFT.tilegame.TileMapDrawer;

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
