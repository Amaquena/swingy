package swingy.controller;

import javax.swing.SwingUtilities;

import swingy.model.Game;
import swingy.view.consoleView.Console;
import swingy.view.guiView.Gui;

public class GameController {
	private Console console = null;
	private Game game = new Game();

	public GameController (int interfaceType) {
		if (interfaceType == 1) {
			console = new Console(this);
		} else if (interfaceType == 2) {
			SwingUtilities.invokeLater(new Runnable(){
				public void run() {
					new Gui();
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
			//TODO: LoadGame information from a textfile
		}
		game.createMap();
		game.addPlayerToMap();
		game.addEnemiesToMap();
		console.displayMap(game.getMap(), game.getMapSize());
	}

	public void characterCreation() {
		String heroName = console.getHeroName();
		String heroClass = console.getHeroClass();
		game.createPlayer(heroName, heroClass);
	}
}