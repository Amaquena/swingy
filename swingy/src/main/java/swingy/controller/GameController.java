package swingy.controller;

import javax.swing.SwingUtilities;

import swingy.model.Game;
import swingy.model.Map;
import swingy.model.heroes.Hero;
import swingy.view.consoleView.Console;
import swingy.view.guiView.Gui;

public class GameController {
	private static Console console = null;
	private static Game game = null;
	private static Gui gui = null;
	private static int interfaceType;

	public GameController(int interfaceType) {
		GameController.interfaceType = interfaceType;
		game = new Game(this);
		if (interfaceType == 1) {
			console = new Console(this);
		} else if (interfaceType == 2) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					gui = new Gui(GameController.this);
				}
			});
		}
		if (interfaceType == 1) {
			console.mainMenu();
		}
	}

	public void initializeGame(int mainMenuCommand) {
		if (mainMenuCommand == 1) {
			characterCreation();
		} else if (mainMenuCommand == 2) {
			game.load(interfaceType);
		}
		mapCreation();
	}

	public void characterCreation() {
		String heroName = console.getHeroName();
		String heroClass = console.getHeroType();
		game.createPlayer(heroName, heroClass);
	}

	public void characterCreation(String name, String heroClass) {
		game.createPlayer(name, heroClass);
		game.createMapGui();
	}

	public void mapCreation() {
		game.createMap();
	}

	public void startGame(char[][] map, int mapSize, Hero player) {
		console.displayInstructions();
		console.displayPlayerStats(player);
		console.displayMap(map, mapSize);

		String userCommand = null;
		userCommand = console.moveCharacter();
		game.handleCommand(userCommand);
	}

	public void gameLoop(String command, char[][] map, int mapSize, Hero player) {
		if (command.equals("reset")) {
			gui.updateGame(map, mapSize, player);
		} else {
			game.handleCommandGui(command);
		}
	}
	
	public void gameLoop(char[][] map, int mapSize, Hero player) {
		String userCommand = null;

		console.displayPlayerStats(player);		
		console.displayMap(map, mapSize);
		userCommand = console.moveCharacter();
		game.handleCommand(userCommand);
	}

	public void outOfbounds() {
		if (interfaceType == 1) {
			String userCommand = console.outOfBoundsMessage();
			game.handleCommand(userCommand);
		} else {
			gui.outOfBoundsMessage();
		}
	}

	public void initiateFight(String enemy) {
		if (interfaceType == 1) {
			String command = console.runOrFight(enemy);
			game.handleCommand(command);
		} else {
			gui.runOrFight(enemy);
		}
	}

	public void combat(String output) {
		if (interfaceType == 1) {
			console.combatMessage(output);
		} else {
			gui.combat(output);
		}
	}

	public void itemDropChoice(String item) {
		if (interfaceType == 1) {
			String userCommand = null;
			userCommand = console.dropOrEquip(item);
			game.handleCommand(userCommand);
		} else {
			gui.itemDropChoice(item);
		}
	}

	public void flee() {
		if (interfaceType == 1) {
			console.fleeMessage();
		} else {
			gui.flee();
		}
	}

	public void quit() {
		console.quitMessage();
	}

	public void levelUp() {
		if (interfaceType == 1) {
			console.levelUpMessage();
		} else {
			gui.levelUpMessage();
		}
	}

	public void loadDeadGuy() {
		if (interfaceType == 1) {
			console.loadedDeadCharacter();
		} else {
			// gui.loadedDeadCharacter();
		}
	}

	public Hero getHero() {
		return game.getPlayer();
	}

	public Map getMap() {
		return game.getMap();
	}

	public void heroCheck(String condition) {
		if (condition.equals("fail")) {
			gui.heroCheckFailedOutput();
		} else if (condition.equals("pass")) {
			gui.startGame();
		}
	}
}