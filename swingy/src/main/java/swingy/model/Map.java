package swingy.model;

import java.util.concurrent.ThreadLocalRandom;

public class Map {
	private char map[][];
	private int mapSize;
	private String direction;
	private int[] location;

	public void generateMap(int playerLevel) {
		map = null;
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

	public void addEnemiesToMap(int playerLevel) {
		int enemyCount = mapSize / 2;
		int enemyPlacement = ThreadLocalRandom.current().nextInt(1, enemyCount + 1);
		int k = 0;

		if (playerLevel >= 5) {
			enemyCount = mapSize / 6;
		} else if (playerLevel >= 7 && playerLevel <= 9) {
			enemyCount = mapSize / 6;
		} else if (playerLevel > 9) {
			enemyCount = mapSize / 10;
		}
		
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				if (k == enemyPlacement && map[i][j] != 'P') {
					map[i][j] = 'E';
					enemyPlacement = ThreadLocalRandom.current().nextInt(1, enemyCount + 1);
					k = 0;
				} else if (k > enemyPlacement) {
					k = 0;
				} else {
					k++;
				}
			}
		}
	}

	public char investigatePosition(String direction) {
		char mapPoint = 'X';
		this.direction = direction;
		this.location = findPlayerPosition();
		int i = this.location[0];
		int j = this.location[1];

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
			updateMap();
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

	public void updateMap() {
		int i = location[0];
		int j = location[1];

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
