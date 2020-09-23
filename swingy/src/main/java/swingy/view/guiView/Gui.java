package swingy.view.guiView;

import javax.swing.*;

import swingy.controller.GameController;

public class Gui extends JFrame {
	private MainMenuPanel mainMenuPanel;
	private CharacterCreationPanel characterCreationPanel;
	private GamePanel gamePanel;
	private static final long serialVersionUID = 1L;
	private GameController gameController;

	public Gui(GameController gameController) {
		super("Swingy");

		this.gameController = gameController;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(500, 500);
		setLocationRelativeTo(null);
		mainMenu();
		setVisible(true);
	}

	public void mainMenu() {
		mainMenuPanel = new MainMenuPanel(this);
		setContentPane(mainMenuPanel);
	}

	public void characterCreation() {
		characterCreationPanel = new CharacterCreationPanel(this);
		remove(mainMenuPanel);
		setContentPane(characterCreationPanel);
		revalidate();
		repaint();
	}

	public void startGame() {
		gamePanel = new GamePanel(this);
		remove(characterCreationPanel);
		setContentPane(gamePanel);
		revalidate();
		repaint();
	}
}
