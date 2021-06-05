package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.utils.GameLoader;
import main.java.utils.GameSaver;

public class TestGameLoader {
	private FileWriter fileWriter;
	
	@Before
	public void intSaveFile() {
		File file = new File(GameSaver.saveFile);
		try {
			//create save file if it doesn't exist
			file.createNewFile();
			this.fileWriter = new FileWriter(GameSaver.saveFile);
			this.fileWriter.write(
					"- 13,5\n"
					+"- 20,9\n"
					+ "MAP: 1\n"
					+ "COINS: 5\n"
					+ "LIVES: 2\n"
					+ "PLAYER: 16,9"
			);
			this.fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

	@Test 
	public void testCheck() {
		GameLoader gameLoader = new GameLoader();
		assertTrue(gameLoader.check());
	}
	
	@Test
	public void testLoad() throws IOException {
		ArrayList<Integer> expectedRemovedSpritesX = new ArrayList<>();
		expectedRemovedSpritesX.add(13); expectedRemovedSpritesX.add(20);
		ArrayList<Integer> expectedRemovedSpritesY = new ArrayList<>();
		expectedRemovedSpritesY.add(5); expectedRemovedSpritesY.add(9);
		GameLoader gameLoader = new GameLoader();
		gameLoader.load();
		assertEquals(gameLoader.getMap(), 1);
		assertEquals(gameLoader.getCollectedStars(), 5);
		assertEquals(gameLoader.getNumLives(), 2);
		assertEquals(gameLoader.getPlayerX(), 16);
		assertEquals(gameLoader.getPlayerY(), 9);
		assertEquals(gameLoader.getRemovedSpritesX(), expectedRemovedSpritesX);
		assertEquals(gameLoader.getRemovedSpritesY(), expectedRemovedSpritesY);
	}
	
	
	
	
	
}
