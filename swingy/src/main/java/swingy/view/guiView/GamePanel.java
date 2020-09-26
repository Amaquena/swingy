package swingy.view.guiView;

import java.awt.event.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import swingy.controller.GameController;
import swingy.model.Map;
import swingy.model.heroes.Hero;

public class GamePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private GameController gameController;
	private Gui gui;
	private Hero player;
	private Map map;
	private JTextArea outPutTextArea;
	private JButton northButton;
	private JButton southButton;
	private JButton eastButton;
	private JButton westButton;
	private JMenu menu;
	private JMenuBar menuBar;
	private JMenuItem saveMenuItem;
	private JMenuItem quitMenuItem;
	private JScrollPane scroll;
	private JPanel middlePanel;

	public GamePanel(GameController gameController, Gui gui) {
		outPutTextArea = new JTextArea(22, 40);
		northButton = new JButton("North");
		southButton = new JButton("South");
		eastButton = new JButton("East");
		westButton = new JButton("West");
		menu = new JMenu("Menu");
		menuBar = new JMenuBar();
		saveMenuItem = new JMenuItem("Save");
		quitMenuItem = new JMenuItem("Quit");
		middlePanel = new JPanel();
		this.gui = gui;
		this.gameController = gameController;
		player = gameController.getHero();
		map = gameController.getMap();
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		middlePanel.setBorder( new TitledBorder ( new EtchedBorder (), "Display Area" ) );
		outPutTextArea.setEditable(false);
		scroll = new JScrollPane(outPutTextArea);
		scroll.setVerticalScrollBarPolicy( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		middlePanel.add(scroll);
		setOutputInstructions();
		display();

		menu.add(saveMenuItem);
		menu.add(quitMenuItem);
		menuBar.add(menu);

		add(menuBar);
		add(middlePanel);
		add(northButton);
		add(southButton);
		add(eastButton);
		add(westButton);

		setUpActionListeners();
	}

	private void setUpActionListeners() {
		northButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameController.gameLoop("north", null, 0, null);
				display();
			}
		});
		southButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameController.gameLoop("south", null, 0, null);
				display();
			}
		});
		westButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameController.gameLoop("west", null, 0, null);
				display();
			}
		});
		eastButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameController.gameLoop("east", null, 0, null);
				display();
			}
		});
		saveMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameController.gameLoop("save", null, 0, null);
			}
		});
		quitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameController.gameLoop("save", null, 0, null);
				JOptionPane.showMessageDialog(GamePanel.this, "GoodBye", "Bye", JOptionPane.INFORMATION_MESSAGE);
				gui.quit();
			}
		});
	}

	private void setOutputHeroStats() {
		String heroStates = "";

		heroStates = "Name: " + player.getName() + "\n";
		heroStates = heroStates + "Class: " + player.getHeroClass() + "\n";
		heroStates = heroStates + "Hp: " + player.getHp() + "\n";
		heroStates = heroStates + "Attack: " + player.getAttack() + "\n";
		heroStates = heroStates + "Defense: " + player.getDefense() + "\n";
		heroStates = heroStates + "Xp: " + player.getXp() + "\n";
		heroStates = heroStates + "Level: " + player.getLevel() + "\n";
		heroStates = heroStates + "Helm: " + player.getHelm().getName() + "(" + player.getHelm().getHealth()
				+ " to hp)\n";
		heroStates = heroStates + "Weapon: " + player.getWeapon().getName() + "(" + player.getWeapon().getAttack()
				+ " to attack)\n";
		heroStates = heroStates + "Armor: " + player.getArmor().getName() + "(" + player.getArmor().getDefense()
				+ " to defense)\n\n";

		outPutTextArea.setText(outPutTextArea.getText() + heroStates);
	}

	private void setOutputMap() {
		char[][] map = this.map.getMap();
		int mapSize = this.map.getMapSize();
		String mapOutput = "";

		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				if (map[i][j] == 'P') {
					mapOutput = mapOutput + map[i][j] + " ";
				} else if (map[i][j] == 'E') {
					mapOutput = mapOutput + map[i][j] + " ";
				} else {
					mapOutput = mapOutput + map[i][j] + " ";
				}
			}
			mapOutput = mapOutput + "\n";
		}

		outPutTextArea.setText(outPutTextArea.getText() + mapOutput);
	}

	private void setOutputInstructions() {
		String instructions = null;

		instructions = "Welcome to Swingy, a text-based RPG that's loads of \"fun\".\n";
		instructions = instructions
				+ "You'll be placed in the center of a grid where you'll have to try and reach either of the edges and win.\n";
		instructions = instructions
				+ "You can move \"north\", \"south\", \"east\" or \"west\" and will encounter enemies along the way.\n";
		instructions = instructions
				+ "Enemies will be places randomly on the map and will have a chance to drop loot and give you xp after being defeated.\n";
		instructions = instructions
				+ "The map size is determined by your level.\n";
		instructions = instructions + "Good Luck!.\n";

		JOptionPane.showMessageDialog(GamePanel.this, instructions, "Instructions", JOptionPane.INFORMATION_MESSAGE);
	}

	private void display() {
		outPutTextArea.setText("");
		setOutputHeroStats();
		setOutputMap();
	}

	public void setGameValues(char[][] map, int mapSize, Hero player) {
		this.map.setMap(map);
		this.map.setMapSize(mapSize);
		this.player = player;
	}

	public void outOfBounds() {
		String msg = "Well Done! You've managed to have fallen off and win.\nFlat earth confirmed.\n";
		msg = msg + "Do you want to continue playing?";
		int n = JOptionPane.showConfirmDialog(GamePanel.this, msg, "So Do You?", JOptionPane.YES_NO_OPTION);
		if (n == 1) {
			JOptionPane.showMessageDialog(GamePanel.this, "GoodBye", "Bye", JOptionPane.INFORMATION_MESSAGE);
			gameController.gameLoop("save", null, 0, null);
			gui.quit();
		} else {
			gameController.gameLoop("yes", null, 0, null);
		}
	}

	public void runOrFightMessage(String enemy) {
		String msg = "You've encounted a " + enemy;
		Object[] options = { "Fight!", "Run!" };
		int n = JOptionPane.showOptionDialog(GamePanel.this, msg, "Run Or Fight?",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, // do not use a custom Icon
				options, // the titles of buttons
				options[0]); // default button title
		if (n == 0) {
			gameController.gameLoop("fight", null, 0, null);
		} else if (n == 1) {
			gameController.gameLoop("run", null, 0, null);
		}
	}

	public void fleeMessage() {
		String msg = "You've ran away, chicken! you find yourself at the samePlace. What now?";
		JOptionPane.showMessageDialog(GamePanel.this, msg, "Fleeing", JOptionPane.INFORMATION_MESSAGE);
	}

	public void combatMessage(String message) {
		// outPutTextArea.setText("");
		// outPutTextArea.setText(message);
		JOptionPane.showMessageDialog(GamePanel.this, message, "Combat", JOptionPane.INFORMATION_MESSAGE);
	}

	public void dropOrEquip(String item) {
		String msg = "The enemy dropped " + item + ".";
		Object[] options = { "Equip", "Drop" };
		int n = JOptionPane.showOptionDialog(GamePanel.this, msg, "Item Drop",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, // do not use a custom Icon
				options, // the titles of buttons
				options[0]); // default button title
		if (n == 0) {
			gameController.gameLoop("equip", null, 0, null);
		} else if (n == 1) {
			gameController.gameLoop("drop", null, 0, null);
		}
	}

	public void levelUp() {
		JOptionPane.showMessageDialog(GamePanel.this, "LEVEL UP!", "Level Message", JOptionPane.INFORMATION_MESSAGE);
	}
}
