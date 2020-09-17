package swingy.model;

import swingy.model.artifacts.GenerateArtifact;
import swingy.model.artifacts.armors.Armor;
import swingy.model.artifacts.helms.Helm;
import swingy.model.artifacts.weapons.Weapon;
import swingy.model.heroes.GenerateHero;
import swingy.model.heroes.Hero;
import swingy.model.villains.GenerateVillain;
import swingy.model.villains.Villain;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import swingy.controller.GameController;

public class Game {
	private static Map map = null;
	private static Hero player = null;
	private static Villain enemy = null;
	private static Gamble dice = null;
	private static GameController gameController;
	private static String artifacts[] = { "armor", "weapon", "helm" };
	private static String enemies[] = { "orc", "skeleton", "zombie", "goblin" };
	private static String weapons[] = { "Crimson Blade", "Demonic Sword", "Enma", "Green Arrow", "Staff of Zeus",
			"Trident", "lightning Bolt", "Rainbow Rod" };
	private static String armors[] = { "Ancient Set", "Cloth", "Diamond Armor", "Dragon Scales", "Kimono", "Shitagi",
			"Silk Robe", "Unholy Drawers" };
	private static String helms[] = { "Cap", "Eye Patch", "Farseer Mask", "Headband", "Helm of Destiny",
			"I can't believe it's Not bacon", "Iron Helmet", "Magic Hat" };
	private static String item;
	private static List<String> arrayListWeapons = Arrays.asList(weapons);
	private static List<String> arrayListArmor = Arrays.asList(armors);
	private static List<String> arrayListHelm = Arrays.asList(helms);

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
		} else if (command.equalsIgnoreCase("equip")) {
			equipItem();
			map.updateMap();
		} else if (command.equalsIgnoreCase("drop")) {
			map.updateMap();
		} else if (command.equalsIgnoreCase("yes")) {
			// yes is from GameControler.outOfbounds
			reCreateMap();
		} else if (command.equalsIgnoreCase("no")) {
			gameController.quit();
		}

		// 'X'-> outOfBounds; 'E'-> enemy
		if (newPosition == 'X') {
			gameController.outOfbounds();
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
		System.out.println("HeroInit: " + heroInitiative + "\tVillainInit: " + villainInitiative);

		if (heroInitiative >= villainInitiative) {
			gameController.combat("You've won initiative step.");
		} else if (villainInitiative > heroInitiative) {
			gameController.combat("Enemy won initiative step.");
		}

		engangeCombat(heroInitiative, villainInitiative);
	}

	private void engangeCombat(int heroInit, int villainInit) {
		int damage = 0;
		boolean firstHit = true;

		if (heroInit >= villainInit) {
			// TODO: hero attack first
			// TODO: add Artifact to damage calculation
			gameController.combat("You land the first attack.");
			do {
				if (!firstHit) {
					gameController.combat("You come swinging.");
				}
				firstHit = false;
				damage = player.getAttack();
				enemy.takeDamage(damage);
				gameController
						.combat(enemy.getName() + " took " + damage + " damage. It has " + enemy.getHp() + " hp left.");
				if (enemy.getHp() <= 0) {
					break;
				}
				gameController.combat("Enemy attacks");
				damage = (enemy.getAttack() - player.getDefense());
				if (damage > 0) {
					player.takeDamage(damage);
				} else if (damage < 0) {
					damage = 0;
				}
				gameController.combat("You took " + damage + " damage. You have " + player.getHp() + " hp left.");
				if (player.getHp() <= 0) {
					break;
				}
			} while (player.getHp() > 0 && enemy.getHp() > 0);
		} else if (villainInit > heroInit) {
			// TODO: villain attack first
			// TODO: add Artifact to defense calculation (subtract from enemy attack)
			gameController.combat(enemy.getName() + " lands the first attack.");
			do {
				if (!firstHit) {
					gameController.combat(enemy.getName() + " comes swinging.");
				}
				firstHit = false;
				damage = (enemy.getAttack() - player.getDefense());
				if (damage > 0) {
					player.takeDamage(damage);
				} else if (damage < 0) {
					damage = 0;
				}
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
			dropLoot();
		}
	}

	private void dropLoot() {
		boolean chance = dice.getChance();
		// TODO: place chance in if statemenet
		if (true) {
			dice.roll();
			int artifactRoll = dice.getThreeSideDie();
			int itemRoll = dice.getEightSideDie();
			System.out.println("ArtifactRoll: " + artifactRoll + "\t itemRoll: " + itemRoll);

			String artifact = artifacts[artifactRoll];
			switch (artifact) {
				case "weapon":
					Game.item = weapons[itemRoll];
					gameController.itemDropChoice(item);
					break;
				case "armor":
					Game.item = armors[itemRoll];
					gameController.itemDropChoice(item);
					break;
				case "helm":
					Game.item = helms[itemRoll];
					gameController.itemDropChoice(item);
					break;

				default:
					break;
			}
		}
	}

	private void equipItem() {
		System.out.println("Item: " + Game.item.toLowerCase());
		if (arrayListWeapons.contains(Game.item)) {
			Weapon weapon = GenerateArtifact.newWeapon(item.toLowerCase());
			player.equipWeapon(weapon);
		}
		if (arrayListArmor.contains(Game.item)) {
			Armor armor = GenerateArtifact.newArmor(item.toLowerCase());
			player.equipArmor(armor);
		}
		if (arrayListHelm.contains(Game.item)) {
			Helm helm = GenerateArtifact.newHelm(item.toLowerCase());
			player.equipHelm(helm);
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
