package swingy.model;

import swingy.model.heroes.GenerateHero;
import swingy.model.heroes.Hero;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import swingy.controller.GameController;

public class Game {
	private static Map map = null;
	private Hero player = null;
	private ArrayList<String> enemies = null;
	private GameController gameController;

	public Game(GameController gameController) {
		this.gameController = gameController;
		map = new Map();
		enemies = new ArrayList<>(Arrays.asList("orc", "skeleton", "zombie", "goblin"));
	}

	public void createPlayer(String heroName, String heroClass) {
		player = GenerateHero.newHero(heroName, heroClass);
	}

	public Hero getPlayer() {
		return player;
	}

	public void createMap() {
		try {
			System.out.println("generating map...");
			// TimeUnit.SECONDS.sleep(3);
			map.generateMap(player.getLevel());
			map.addPlayerToMap();
			System.out.println("placing player...");
			TimeUnit.SECONDS.sleep(1);
			map.addEnemiesToMap();
			System.out.println("generating enemies...");
			// TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			System.out.println("system sleep error.");
		}
		gameController.startGame(map.getMap(), map.getMapSize(), player);
	}

	public void handleCommand(String direction) {
		char newPosition = '\0';

		if (direction.equalsIgnoreCase("north") || direction.equalsIgnoreCase("south") ||
				direction.equalsIgnoreCase("east") || direction.equalsIgnoreCase("west")) {
					newPosition = map.investigatePosition(direction);
		}

		if (newPosition == 'X') {
			gameController.outOfbounds();
		} else if (newPosition == 'E') {
			// TODO: engangeEnemy();
			System.out.println("battle Mode");
		}
	}

}
