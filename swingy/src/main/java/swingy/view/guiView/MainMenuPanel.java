package swingy.view.guiView;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.BoxLayout;

import java.awt.*;
import java.awt.event.*;

public class MainMenuPanel extends JPanel {
	private Gui gui;
	private static final long serialVersionUID = 1L;
	protected JButton newGameButton;
	private JButton loadGameButton;
	private JLabel headerLabel;

	public MainMenuPanel(Gui gui) {
		headerLabel = new JLabel("SWINGY");
		newGameButton = new JButton("New Game");
		loadGameButton = new JButton("Load Game");
		this.gui = gui;

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		add(headerLabel);
		add(newGameButton);
		add(loadGameButton);
		
		setUpActionlisteners();
	}

	private void setUpActionlisteners() {
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.characterCreation();
			}
		});
		loadGameButton.addActionListener((new ActionListener() {
			public void actionPerformed(ActionEvent e ) {
				//TODO: loadgame actionlistener
				gui.loadGame();
				// gui.startGame();
			}
		}));
	}
}
