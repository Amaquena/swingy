package swingy.view.consoleView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import swingy.controller.GameController;
import swingy.model.heroes.Hero;

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

	private void pressEnterToContinue() {
		System.out.println("Press Enter key to continue...");
		try {
			System.in.read();
		} catch (Exception e) {
		}
	}

	private static String capitalize(String str) {
		if (str == null)
			return str;
		return str.substring(0, 1).toUpperCase() + str.substring(1);
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

	public String getHeroType() {
		try {
			String heroClass = "";
			System.out.println("\n\tSelect Hero Class");
			System.out.println("\t1- Mage\n\t2- Knight\n\t3- Archer\n\t4- Samurai");
			while (!heroClass.equals("1") && !heroClass.equals("2") && !heroClass.equals("3")
					&& !heroClass.equals("4")) {
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
				} else if (map[i][j] == 'E') {
					System.out.print(ANSI_RED + map[i][j] + ANSI_RESET + " ");
				} else {
					System.out.print(map[i][j] + " ");
				}
			}
			System.out.println();
		}
	}

	public void displayPlayerStats(Hero player) {
		// clearScreen();
		System.out.println("Name: " + player.getName());
		System.out.println("Class: " + capitalize(player.getHeroClass()));
		System.out.println("Hp: " + ANSI_GREEN + player.getHp() + ANSI_RESET);
		System.out.println("Attack: " + ANSI_YELLOW + player.getAttack() + ANSI_RESET);
		System.out.println("Defense: " + ANSI_PURPLE + player.getDefense() + ANSI_RESET);
		System.out.println("XP: " + player.getXp());
		System.out.println("Level: " + player.getLevel());
		System.out.println("\nHelm: " + ANSI_GREEN + player.getHelm().getName() + ANSI_RESET + " + (" + ANSI_GREEN
				+ player.getHelm().getHealth() + ANSI_RESET + " to hp)");
		System.out.println("weapon: " + ANSI_YELLOW + player.getWeapon().getName() + ANSI_RESET + " + (" + ANSI_YELLOW
				+ player.getWeapon().getAttack() + ANSI_RESET + " to attack)");
		System.out.println("Armor: " + ANSI_PURPLE + player.getArmor().getName() + ANSI_RESET + " + (" + ANSI_PURPLE
				+ player.getArmor().getDefense() + ANSI_RESET + " to defense)");
		System.out.println();
	}

	public void displayInstructions() {
		clearScreen();
		System.out.println("Welcome to Swingy, a text-based RPG that's loads of \"fun\".");
		System.out.println(
				"You'll be placed in the center of a grid where you'll have to try and reach either of the edges.");
		System.out.println(
				"You can move \"north\", \"south\", \"east\" or \"west\" and will encounter enemies along the way.");
		System.out.println(
				"Enemies will be places randomly on the map and will have a chance to drop loot and give you xp after being defeated.");
		System.out.println(
				"The map size is determined by your level and too complete the game you'll have to reach level 6.");
		System.out.println("Good Luck!.");
		pressEnterToContinue();
	}

	public String moveCharacter() {
		System.out.println("Type \"north\", \"south\", \"east\" or \"west\" to move your character. Or \"save\" to save your character.");

		try {
			String command = "";
			while (!command.equalsIgnoreCase("north") && !command.equalsIgnoreCase("south")
					&& !command.equalsIgnoreCase("east") && !command.equalsIgnoreCase("west")
					&& !command.equalsIgnoreCase("save")) {
				command = br.readLine();
			}
			return command;
		} catch (IOException e) {
			System.err.println("I/O error");
		}
		return null;
	}

	public String outOfBoundsMessage() {
		System.out.println("Well Done! You've managed to have fallen off and win.\nFlat earth confirmed.");
		System.out.println("Do you want to continue playing?");

		try {
			String command = "";
			while (!command.equalsIgnoreCase("yes") && !command.equalsIgnoreCase("no")) {
				command = br.readLine();
			}
			return command;
		} catch (IOException e) {
			System.err.println("I/O error");
		}
		return null;
	}

	public String runOrFight(String enemy) {
		System.out.println("You've encounted a " + enemy + ", do you \"run\" or \"fight\"?");

		try {

			String command = "";
			while (!command.equalsIgnoreCase("run") && !command.equalsIgnoreCase("fight")) {
				command = br.readLine();
			}
			return command;
		} catch (IOException e) {
			System.err.println("I/O error");
		}
		return null;
	}

	public void combatMessage(String output) {
		System.out.println(output);
		pressEnterToContinue();
	}

	public void fleeMessage() {
		System.out.println("You've ran away, chicken! you find yourself at the samePlace. What now?");
		pressEnterToContinue();
	}

	public String dropOrEquip(String item) {
		System.out.println("The enemy dropped " + ANSI_CYAN + item + ANSI_RESET + ". Do you want to \"equip\" or \"drop\"?");

		try {
			String command = "";
			while (!command.equalsIgnoreCase("equip") && !command.equalsIgnoreCase("drop")) {
				command = br.readLine();
			}
			return command;
		} catch (IOException e) {
			System.err.println("I/O error");
		}
		return null;
	}

	public void quitMessage() {
		System.out.println(ANSI_BLUE + "\n\nGoodbye!!"+ANSI_RESET);
		System.exit(0);
	}

	public void levelUpMessage() {
		System.out.println(ANSI_BLUE+ "LEVEL UP!" +ANSI_RESET);
		pressEnterToContinue();
	}

	public void loadedDeadCharacter() {
		System.out.println("Sorry, character already met his/her fate.");
		System.out.println("Jumping to character creation.");
		pressEnterToContinue();
	}
}
