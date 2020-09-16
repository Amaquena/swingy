package swingy.model;

import swingy.model.heroes.GenerateHero;
import swingy.model.heroes.Hero;
import swingy.model.villains.GenerateVillain;
import swingy.model.villains.Villain;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import swingy.controller.GameController;

public class Game {
	private static Map map = null;
	private static Hero player = null;
	private static Gamble dice = null;
	private static GameController gameController;
	private static String enemies[] = { "orc", "skeleton", "zombie", "goblin" };
	private static Villain enemy = null;

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

		if (command.equalsIgnoreCase("north") || command.equalsIgnoreCase("south") || command.equalsIgnoreCase("east")
				|| command.equalsIgnoreCase("west")) {
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
		Game.enemy = GenerateVillain.newVillain(enemy);

		gameController.initiateFight(Game.enemy.getName());
	}

	public void fight() {
		gameController.combat("You've engaged combat, good luck!");

		gameController.combat("Rolling for initiative..");
		dice.roll();
		int heroInitiative = dice.getTripleDiceTotal() / 3;
		dice.roll();
		int villainInitiative = dice.getTripleDiceTotal() / 3;
		System.out.println("HeroInit: "+heroInitiative+"\tVillainInit: "+villainInitiative);

		if (heroInitiative >= villainInitiative) {
			gameController.combat("You've won initiative step.");
		} else if (villainInitiative > heroInitiative) {
			gameController.combat("Enemy won initiative step.");
		}

		engangeCombat(heroInitiative, villainInitiative);
	}

	private void engangeCombat(int heroInit, int villainInit) {
		int damage = 0;
		boolean firstHit = false;

		if (heroInit >= villainInit) {
			// TODO: hero attack first
			// TODO: add Artifact to damage calculation
			gameController.combat("You land the first attack.");
			firstHit = true;
			do {
				if (!firstHit) {
					gameController.combat("You come swinging.");
				}
				damage = player.getAttack();
				System.out.println("Game.engangeCombat()1");
				enemy.takeDamage(damage);
				System.out.println("Game.engangeCombat()2");
				gameController
						.combat(enemy.getName() + " took " + damage + " damage. It has " + enemy.getHp() + " hp left.");
				if (enemy.getHp() <= 0) {
					break;
				}
				gameController.combat("Enemy attacks");
				damage = enemy.getAttack();
				player.takeDamage(damage);
				gameController.combat("You took " + damage + " damage. You have " + player.getHp() + " hp left.");
				if (player.getHp() <= 0) {
					break;
				}
			} while (player.getHp() > 0 && enemy.getHp() > 0);
		} else if (villainInit > heroInit) {
			// TODO: villain attack first
			// TODO: add Artifact to defense calculation (subtract from enemy attack)
			gameController.combat(enemy.getName() + " lands the first attack.");
			firstHit = true;
			do {
				if (!firstHit) {
					gameController.combat(enemy.getName() + " comes swinging.");
				}
				damage = enemy.getAttack();
				player.takeDamage(damage);
				gameController.combat("You took " + damage + " damage. You have " + player.getHp() + " hp left.");
				if (player.getHp() <= 0) {
					break;
				}
				gameController.combat("You attack");
				damage = player.getAttack();
				enemy.takeDamage(damage);
				gameController
						.combat(enemy.getName() + " took " + damage + " damage. It has " + enemy.getHp() + " hp left.");
				if (enemy.getHp() <= 0) {
					break;
				}
			} while (player.getHp() > 0 && enemy.getHp() > 0);
		}

		if (player.getHp() <= 0) {
			gameController.combat("You DIED GAME OVER.");
			System.exit(1);
		}
		if (enemy.getHp() <= 0) {
			gameController.combat("Enemy defeated.");
		}
	}

	public void run() {
		boolean chance = dice.getChance();
		if (chance) {
			gameController.flee();
			gameController.gameLoop(map.getMap(), map.getMapSize(), player);
		} else {
			fight();
			gameController.gameLoop(map.getMap(), map.getMapSize(), player);
		}
	}

}
