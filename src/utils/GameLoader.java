package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GameLoader {
	
	private int numLives;
	private int collectedStars;
	private int map;
	private int playerX;
	private int playerY;
	private ArrayList<Integer> removedSpritesX = new ArrayList<>();
	private ArrayList<Integer> removedSpritesY = new ArrayList<>();
	
	/**
	 * Checks if there is a saved game
	 */
	public boolean check() {
		BufferedReader reader;
		try {
			reader = new BufferedReader( new FileReader(GameSaver.saveFile));
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			return false;
		}
		String line;
		try {
			line = reader.readLine();
			reader.close();
			// file is empty
	        if (line == null) {
	            return false;
	        }
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Loads the saved data into the instance variables 
	 */
	public void load() throws IOException{
        BufferedReader reader = new BufferedReader( new FileReader(GameSaver.saveFile));
		String line;
		while (true) {
			line = reader.readLine();
	        // no more lines to read
	        if (line == null) {
	        	reader.close();
	            break;
	        }

	            if (line.startsWith("- ")) {
	            	removedSpritesX.add(Integer.parseInt(line.substring(2).split(",")[0]));
	            	removedSpritesY.add(Integer.parseInt(line.substring(2).split(",")[1]));
	            }
	            
	            if (line.startsWith("MAP: ")) {
	            	map = Integer.parseInt(line.substring(5));
	            }
	            
	            if (line.startsWith("COINS: ")) {
	            	collectedStars = Integer.parseInt(line.substring(7));
	            }
	            
	            if (line.startsWith("LIVES: ")) {
	            	numLives = Integer.parseInt(line.substring(7));
	            }
	            
	            if (line.startsWith("PLAYER: ")) {
	            	playerX = Integer.parseInt(line.substring(8).split(",")[0]);
	            	playerY = Integer.parseInt(line.substring(8).split(",")[1]);
	            }      
		}
	}

	public int getNumLives() {
		return numLives;
	}

	public int getCollectedStars() {
		return collectedStars;
	}

	public int getMap() {
		return map;
	}

	public int getPlayerX() {
		return playerX;
	}

	public int getPlayerY() {
		return playerY;
	}

	public ArrayList<Integer> getRemovedSpritesX() {
		return removedSpritesX;
	}

	public ArrayList<Integer> getRemovedSpritesY() {
		return removedSpritesY;
	}
}
