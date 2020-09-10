package swingy;

import swingy.controller.GameController;

public class App {
	public static void main(String[] args) {
		byte interfaceType = 0;

		try {
			if (args[0].equalsIgnoreCase("console")) {
				interfaceType = 1;
			} else if (args[0].equalsIgnoreCase("gui")) {
				interfaceType = 2;
			} else {
				System.err.println("Use \"console\" or \"gui\" as an argument.");
				System.exit(0);
			}
			new GameController(interfaceType);
			
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Use \"console\" or \"gui\" as an argument.");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
