package swingy.view.consoleView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public Console() {

	}

	public void mainMenu() {
		String msg = "\t\tWelcome to swingy!\n\n\t1- New Game\n\t2- Load Game\n";
		String line;

		try {
			line = br.readLine();
			System.out.println(msg);
			System.out.println(line);
		} catch (IOException e) {
			System.err.println("Invalid input");
		}
	}
}
