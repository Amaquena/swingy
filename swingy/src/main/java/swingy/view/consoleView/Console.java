package swingy.view.consoleView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import swingy.controller.GameController;

public class Console {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private GameController gameController = null;

	public Console(GameController gameController) {
		this.gameController = gameController;
	}

	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public void mainMenu() {
		clearScreen();
		String msg = "\t\tWelcome to swingy!\n\n\t1- New Game\n\t2- Load Game\n";
		String line = "";

		try {
			System.out.println(msg);
			while (!line.equals("1") && !line.equals("2")) {
				System.out.print("Enter: ");
				line = br.readLine();
			}
			gameController.initializeGame(Integer.parseInt(line));
		} catch (IOException e) {
			System.err.println("Invalid input");
		}
	}

	public String getHeroName() {
		try {
			clearScreen();
			String heroName = null;
			System.out.println("\t\tHero Creation\n\n");
			System.out.println("\tWhat's your name?");
			System.out.print("Enter: ");
			heroName = br.readLine();
			return heroName;
		} catch (IOException e) {
			System.out.print("I/O error");
		}
		return null;
	}

	public String getHeroClass() {
		try {
			String heroClass = "";
			System.out.println("\tSelect Hero Class");
			System.out.println("\t1- Mage\n\t2- Knight\n\t3- Archer\n\t4- Samurai");
			while (!heroClass.equals("1") && !heroClass.equals("2") && !heroClass.equals("3") && !heroClass.equals("4")) {
				System.out.print("Enter: ");
				heroClass = br.readLine();
			}
			switch (heroClass) {
				case "1":
					return "mage";
				case "2":
					return "knight";
				case "3":
					return "archer";
				case "4":
					return "samurai";
			}
			return heroClass;
		} catch (IOException e) {
			System.out.print("I/O error");
		}
		return null;
	}
}
