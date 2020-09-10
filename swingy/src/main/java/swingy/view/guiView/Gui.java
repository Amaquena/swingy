package swingy.view.guiView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.*;

public final class Gui {
	private JFrame frame = null;
	private JPanel panel = new JPanel();

	// Main Menu layout
	private JButton newGameButton;
	private JButton loadGameButton;
	private JButton helpButton;
	private JLabel headerLabel;
	private GroupLayout layout;

	public Gui() {
		initComponents();
		mainMenu();
	}

	public void initComponents() {
		frame = new JFrame("Swingy");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		// Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		// this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 -
		// this.getSize().height / 2);
		frame.setVisible(true);
	}

	public void mainMenu() {
		newGameButton = new JButton("New Game");
		loadGameButton = new JButton("Load Game");
		helpButton = new JButton("Help");

		headerLabel = new JLabel("SWINGY");

		layout = new GroupLayout(panel);
		panel.setLayout(layout);

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addComponent(headerLabel)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(newGameButton))
					.addComponent(loadGameButton));

		layout.setVerticalGroup(layout
				.createSequentialGroup()
				.addComponent(headerLabel)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(newGameButton)
					.addComponent(loadGameButton)));
		// headerLabel.setHorizontalAlignment(SwingConstants.LEFT);
		// headerLabel.setFont(new Font("Impact", Font.HANGING_BASELINE, 140));

		// panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		// panel.removeAll();
		// panel.add(headerLabel, BorderLayout.NORTH);
		panel.setBackground(Color.CYAN);
		// panel.add(newGameButton);
		// panel.add(loadGameButton);

		frame.add(panel);
	}
}
