package swingy.view;

import javax.swing.*;

public class Gui {
	JFrame frame = null;

	public Gui() {
		initComponents();
	}

	public void initComponents() {
		frame = new JFrame("My first GUI.");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}

	public String display() {
		return "Hello World";
	}

}
