package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import main.java.com.TETOSOFT.graphics.Animation;
import main.java.com.TETOSOFT.graphics.Sprite;
import main.java.com.TETOSOFT.tilegame.TileMapDrawer;
import main.java.utils.GameSaver;

public class TestGameSaver {
	@Test
	public void testSaveFileCreation() {
		GameSaver gameSaver = new GameSaver();
		File file = new File(GameSaver.saveFile);
		assertTrue(file.exists());
	}
	
	@Test
	public void testSaveScore() throws IOException {
		GameSaver gameSaver = new GameSaver();
		gameSaver.saveScore(10);
		gameSaver.closeWriter();
		BufferedReader reader = new BufferedReader( new FileReader(GameSaver.saveFile));
		assertEquals(reader.readLine(), "COINS: 10");
		reader.close();
	}
	
	@Test
	public void testSaveLives() throws IOException {
		GameSaver gameSaver = new GameSaver();
		gameSaver.saveLives(2);
		gameSaver.closeWriter();
		BufferedReader reader = new BufferedReader( new FileReader(GameSaver.saveFile));
		assertEquals(reader.readLine(), "LIVES: 2");
		reader.close();
	}
	
	@Test
	public void testSaveMap() throws IOException {
		GameSaver gameSaver = new GameSaver();
		gameSaver.saveCurrentMap(2);
		gameSaver.closeWriter();
		BufferedReader reader = new BufferedReader( new FileReader(GameSaver.saveFile));
		assertEquals(reader.readLine(), "MAP: 2");
		reader.close();
	}
	
	@Test
	public void testSavePlayerPosition() throws IOException {
		GameSaver gameSaver = new GameSaver();
		gameSaver.savePlayerPosition(19, 3);
		gameSaver.closeWriter();
		BufferedReader reader = new BufferedReader( new FileReader(GameSaver.saveFile));
		assertEquals(reader.readLine(), "PLAYER: " + TileMapDrawer.pixelsToTiles(19) + "," + TileMapDrawer.pixelsToTiles(3));
		reader.close();
	}
	
	@Test
	public void testSaveDeletedSprite() throws IOException {
		Sprite sprite = new Sprite(null);
		sprite.setTileX(5); sprite.setTileY(4);
		GameSaver gameSaver = new GameSaver();
		gameSaver.saveDeletedSpritePosition(sprite);
		gameSaver.closeWriter();
		BufferedReader reader = new BufferedReader( new FileReader(GameSaver.saveFile));
		assertEquals(reader.readLine(), "- 5,4");
		reader.close();
	}

}
