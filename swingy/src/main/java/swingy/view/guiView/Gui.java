package swingy.view.guiView;

import javax.swing.*;

import swingy.controller.GameController;
import swingy.model.heroes.Hero;

public class Gui extends JFrame {
	private MainMenuPanel mainMenuPanel;
	private CharacterCreationPanel characterCreationPanel;
	private GamePanel gamePanel = null;
	private static final long serialVersionUID = 1L;
	private GameController gameController;

	public Gui(GameController gameController) {
		super("Swingy");

		this.gameController = gameController;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(500, 550);
		setLocationRelativeTo(null);
		mainMenu();
		setVisible(true);
	}

	public void mainMenu() {
		mainMenuPanel = new MainMenuPanel(this);
		setContentPane(mainMenuPanel);
	}

	public void characterCreation() {
		characterCreationPanel = new CharacterCreationPanel(gameController, this);
		remove(mainMenuPanel);
		setContentPane(characterCreationPanel);
		revalidate();
		repaint();
	}

	public void startGame() {
		gamePanel = new GamePanel(gameController, this);

		if (getContentPane() == characterCreationPanel) {
			remove(characterCreationPanel);
		}
		setContentPane(gamePanel);
		revalidate();
		repaint();
	}

	public void updateGame(char[][] map, int MapSize, Hero player) {
		if (gamePanel == null) {
			gamePanel = new GamePanel(gameController, this);
			remove(mainMenuPanel);
			setContentPane(gamePanel);
		}
		gamePanel.setGameValues(map, MapSize, player);
	}

	public void outOfBoundsMessage() {
		gamePanel.outOfBounds();
	}

	public void quit() {
		dispose();
	}

	public void runOrFight(String enemy) {
		gamePanel.runOrFightMessage(enemy);
	}

	public void flee() {
		gamePanel.fleeMessage();
	}

	public void combat(String outputMessage) {
		gamePanel.combatMessage(outputMessage);
	}

	public void itemDropChoice(String item) {
		gamePanel.dropOrEquip(item);
	}

	public void levelUpMessage() {
		gamePanel.levelUp();
	}

	public void loadGame() {
		gameController.gameLoop("load", null, 0, null);
	}

	public void heroCheckFailedOutput() {
		String msg = "Failed to load hero, jumping to character creation.";
		JOptionPane.showMessageDialog(this, msg, "Corrupt saved file", JOptionPane.ERROR_MESSAGE);
		characterCreation();
	}

	// public void loadedDeadCharacter() {
	// 	JOptionPane.showMessageDialog(this, "You loaded a dead Hero", "Uhm, Oops!", JOptionPane.ERROR_MESSAGE);
	// 	characterCreation();
	// }
}