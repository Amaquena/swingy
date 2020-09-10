package swingy.controller;

import javax.swing.SwingUtilities;

import swingy.model.heroes.GenerateHero;
import swingy.model.heroes.Hero;
import swingy.view.consoleView.Console;
import swingy.view.guiView.Gui;

public class GameController {
	private Console console = null;
	private Hero player = null;

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

		}
	}

	public void characterCreation() {
		String heroName = console.getHeroName();
		String heroClass = console.getHeroClass();
		player = GenerateHero.newHero(heroName, heroClass);
	}
}