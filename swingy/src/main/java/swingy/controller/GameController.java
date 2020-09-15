package swingy.controller;

import javax.swing.SwingUtilities;

import swingy.model.Game;
import swingy.model.heroes.Hero;
import swingy.view.consoleView.Console;
// import swingy.view.guiView.Gui;

public class GameController {
	private static Console console = null;
	private static Game game = null;

	public GameController (int interfaceType) {
		if (interfaceType == 1) {
			console = new Console(this);
		} else if (interfaceType == 2) {
			SwingUtilities.invokeLater(new Runnable(){
				public void run() {
					// new Gui();
				}
			});
		}
		if (interfaceType == 1) {
			console.mainMenu();
		}
	}

	public void initializeGame(int mainMenuCommand) {
		game = new Game(this);
		if (mainMenuCommand == 1) {
			characterCreation();
		} else if (mainMenuCommand == 2) {
			//TODO: LoadGame information from a textfile
		}
		mapCreation();
	}

	public void characterCreation() {
		String heroName = console.getHeroName();
		String heroClass = console.getHeroType();
		game.createPlayer(heroName, heroClass);
	}

	public void mapCreation() {
		game.createMap();
	}

	public void startGame(char[][] map, int mapSize, Hero player) {
		console.displayInstructions();
		console.displayPlayerStats(player);
		console.displayMap(map, mapSize);

		String userCommand = null;
		while (player.getLevel() < 6) {
			userCommand = console.moveCharacter();
			game.handleCommand(userCommand);
			console.displayMap(map, mapSize);
		}
	}

	public void outOfbounds() {
		console.outOfBoundsMessage();
		mapCreation();
	}
}