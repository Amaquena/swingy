package swingy.view.consoleView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import swingy.controller.GameController;

public class Console {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

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
			System.out.println("\n\tSelect Hero Class");
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

	public void displayMap(char[][] map, int mapSize) {
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				if (map[i][j] == 'P') {
					System.out.print(ANSI_CYAN + map[i][j] + ANSI_RESET + " ");	
				} else {
					System.out.print(map[i][j] + " ");
				}
			}
			System.out.println();
		}
	}
}
