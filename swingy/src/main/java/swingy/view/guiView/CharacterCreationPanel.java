package swingy.view.guiView;

import java.awt.*;
import java.awt.event.*;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import swingy.controller.GameController;

public class CharacterCreationPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private GameController gameController;
	private Gui gui;
	protected JButton confirm;
	private JLabel headerLabel;
	private JLabel heroLabel;
	private JTextField nameTextField;
	private JRadioButton mage;
	private JRadioButton archer;
	private JRadioButton knight;
	private JRadioButton samurai;

	public CharacterCreationPanel(GameController gameController, Gui gui) {
		headerLabel = new JLabel("Character creation");
		heroLabel = new JLabel("Select a hero");
		mage = new JRadioButton("Mage");
		archer = new JRadioButton("Archer");
		knight = new JRadioButton("Knight");
		samurai = new JRadioButton("Samurai");
		confirm = new JButton("confirm");
		nameTextField = new JTextField("Enter your name");
		this.gui = gui;
		this.gameController = gameController;

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		ButtonGroup heroGroup = new ButtonGroup();
		heroGroup.add(mage);
		heroGroup.add(archer);
		heroGroup.add(knight);
		heroGroup.add(samurai);

		add(headerLabel);
		add(nameTextField);
		add(heroLabel);
		add(mage);
		add(archer);
		add(knight);
		add(samurai);
		add(confirm);

		setUpActionListeners();
	}

	private void setUpActionListeners() {
		nameTextField.setForeground(Color.GRAY);
		nameTextField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (nameTextField.getText().equals("Enter your name")) {
					nameTextField.setText("");
					nameTextField.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (nameTextField.getText().isEmpty()) {
					nameTextField.setForeground(Color.GRAY);
					nameTextField.setText("Enter your name");
				}
			}
		});

		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameTextField.getText();
				String heroClass = null;
				if (archer.isSelected()) {
					heroClass = "archer";
				} else if (mage.isSelected()) {
					heroClass = "mage";
				} else if (knight.isSelected()) {
					heroClass = "knight";
				} else if (samurai.isSelected()) {
					heroClass = "samurai";
				} else {
					JOptionPane.showMessageDialog(CharacterCreationPanel.this, "Select Hero Class", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				if (!checkNameIsValid(name)) {
					JOptionPane.showMessageDialog(CharacterCreationPanel.this, "Name not Valid", "Error",
							JOptionPane.ERROR_MESSAGE);

				}
				if (heroClass != null && checkNameIsValid(name)) {
					gameController.characterCreation(name, heroClass);
					gui.startGame();
				}
			}
		});

	}

	private boolean checkNameIsValid(String name) {
		if ((name.length() < 2 || name.length() > 10) || (name == null) || (name.equals(""))
				|| !name.matches("^[a-zA-Z0-9]*$")) {
			return false;
		}
		return true;
	}

}
