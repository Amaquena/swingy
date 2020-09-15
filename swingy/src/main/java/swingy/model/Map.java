package swingy.model;

import java.util.concurrent.ThreadLocalRandom;

public class Map {
	private char map[][] = null;
	private int mapSize;

	public void generateMap(int playerLevel) {
		mapSize = (playerLevel - 1) * 5 + 10 - (playerLevel % 2);
		map = new char[mapSize][mapSize];
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				map[i][j] = 'O';
			}
		}
	}

	public char[][] getMap() {
		return map;
	}
	
	public int getMapSize() {
		return mapSize;
	}

	public void addPlayerToMap() {
		map[(int) Math.ceil(mapSize / 2)][(int) Math.ceil(mapSize / 2)] = 'P';
	}

	public void addEnemiesToMap() {
		int enemyCount = (int) Math.floor((mapSize * mapSize) / 6);
		int enemyPlacement = ThreadLocalRandom.current().nextInt(1, enemyCount + 1);
		int k = 0;

		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				if (k == enemyPlacement && map[i][j] != 'P') {
					map[i][j] = 'E';
					enemyPlacement = ThreadLocalRandom.current().nextInt(1, enemyCount + 1);
					k = 0;
				} else {
					k++;
				}
			}
		}
	}

	public char investigatePosition(String direction) {
		char mapPoint = 'X';
		int[] playerLocation = findPlayerPosition();
		int i = playerLocation[0];
		int j = playerLocation[1];

		// System.out.println("Player Position:\ni: " + playerLocation[0] + "\nj: " + playerLocation[1]);
		if (direction.equalsIgnoreCase("north")) {
			if (i - 1 >= 0) {
				mapPoint = map[i - 1][j];
			}
		} else if (direction.equalsIgnoreCase("south")) {
			if (i + 1 < mapSize) {
				mapPoint = map[i + 1][j];
			}
		} else if (direction.equalsIgnoreCase("east")) {
			if (j + 1 < mapSize) {
				mapPoint = map[i][j + 1];
			}
		} else if (direction.equalsIgnoreCase("west")) {
			if (j - 1 >= 0) {
				mapPoint = map[i][j - 1];
			}
		}

		if (mapPoint == 'O') {
			updateMap(playerLocation, direction);
		}
		return mapPoint;
	}

	private int[] findPlayerPosition() {
		int loc[] = new int[2];

		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				if (map[i][j] == 'P') {
					loc[0] = i;
					loc[1] = j;
					break ;
				}
			}
		}
		return loc;
	}

	public void updateMap(int[] playerLocation, String direction) {
		int i = playerLocation[0];
		int j = playerLocation[1];

		map[i][j] = 'O';
		if (direction.equalsIgnoreCase("north")) {
				map[i - 1][j] = 'P';
		} else if (direction.equalsIgnoreCase("south")) {
				map[i + 1][j] = 'P';
		} else if (direction.equalsIgnoreCase("east")) {
				map[i][j + 1] = 'P';
		} else if (direction.equalsIgnoreCase("west")) {
				map[i][j - 1] = 'P';
		}
	}

}
