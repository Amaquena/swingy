package swingy.view;

import javax.swing.*;

public class Gui extends Output implements Viewable {
	JFrame frame = null;
	JLabel label = null;

	public Gui() {
		initComponents();
	}

	public void initComponents() {
		frame = new JFrame("Swingy");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(500, 500);
		frame.setLayout(null);
		label = new JLabel("<html>" + super.welcome().replaceAll("\n", "<br>").replaceAll("\t", " ") + "<html>");
		label.setBounds(10, 10, 50, 200);
		frame.setVisible(true);
	}

	public void welcomeMsg() {
		frame.add(label);
		System.out.println(super.welcome());
	}

}
