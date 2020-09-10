package swingy.controller;

import javax.swing.SwingUtilities;

import swingy.view.consoleView.Console;
import swingy.view.guiView.Gui;

public class GameController {

	public GameController (int interfaceType) {
		if (interfaceType == 1) {
			System.out.println("console View");
			new Console();
		} else if (interfaceType == 2) {
			System.out.println("gui View");
			SwingUtilities.invokeLater(new Runnable(){
				public void run() {
					new Gui();
				}
			});
		}

		initializeGame();
	}

	public void initializeGame() {

	}
}