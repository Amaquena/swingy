package swingy.model;

import swingy.model.heroes.GenerateHero;
import swingy.model.heroes.Hero;

public class Game {
	private Hero player = null;
	private char map[][] = null;
	private int mapSize;

	public void createPlayer(String heroName, String heroClass) {
		player = GenerateHero.newHero(heroName, heroClass);
	}

	public void createMap() {
		int level = player.getLevel();
		mapSize = (level - 1) * 5 + 10 - (level % 2);
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

	}
}
