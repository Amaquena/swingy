package swingy;

import swingy.view.*;

public class App {
	public static void main(String[] args) {
		
		try {

			// TODO: Set up controller to handle views and models
			if (args[0].equals("console")) {
				Console console = new Console();
				console.welcomeMsg();

			} else if (args[0].equals("gui")) {
				System.out.println("GIU component will be avaible soon.");
				System.exit(0);
			} else {
				System.out.println("Use \"console\" or \"gui\" as an argument.");
				System.exit(0);
			}
			
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Use \"console\" or \"gui\" as an argument.");
		}
	}
}
