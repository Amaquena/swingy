package swingy.model;

import swingy.model.heroes.GenerateHero;
import swingy.model.heroes.Hero;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import swingy.controller.GameController;

public class Game {
	private static Map map = null;
	private static Hero player = null;
	private static Gamble dice = null;
	private static GameController gameController;
	private static String enemies[] = {"orc", "skeleton", "zombie", "goblin"};

	public Game(GameController gameController) {
		Game.gameController = gameController;
		map = new Map();
		dice = new Gamble();
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

	public void reCreateMap() {
		try {
			System.out.println("recreating map...");
			TimeUnit.SECONDS.sleep(3);
			map.generateMap(player.getLevel());
			map.addPlayerToMap();
			System.out.println("placing player...");
			// TimeUnit.SECONDS.sleep(1);
			map.addEnemiesToMap();
			System.out.println("generating enemies...");
			// TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			System.out.println("system sleep error.");
		}
		gameController.gameLoop(map.getMap(), map.getMapSize(), player);
	}

	public void handleCommand(String command) {
		char newPosition = '\0';

		if (command.equalsIgnoreCase("north") || command.equalsIgnoreCase("south") ||
				command.equalsIgnoreCase("east") || command.equalsIgnoreCase("west")) {
					newPosition = map.investigatePosition(command);
		} else if (command.equalsIgnoreCase("run")) {
			run();
		} else if (command.equalsIgnoreCase("fight")) {
			fight();
		}
		// 'X'-> outOfBounds; 'E'-> enemy
		if (newPosition == 'X') {
			gameController.outOfbounds();
			reCreateMap();
		} else if (newPosition == 'E') {
			System.out.println("battle Mode");
			engangeEnemy();
		}
		gameController.gameLoop(map.getMap(), map.getMapSize(), player);
	}

	public void engangeEnemy() {
		int enemySelection = new Random().nextInt(4);
		String enemy = enemies[enemySelection];

		gameController.initiateFight(enemy);
	}

	public void fight() {
		// dice.roll();
		// int heroInitiative = dice.getTripleDiceTotal() / 3;
		// int villainInitiative = dice.getTripleDiceTotal() / 3;
	}

	public void run() {
		boolean chance = dice.getChance();
		if (chance) {
			gameController.flee();
			gameController.gameLoop(map.getMap(), map.getMapSize(), player);
		} else {
			gameController.combat();
			gameController.gameLoop(map.getMap(), map.getMapSize(), player);
		}
	}
	
}
