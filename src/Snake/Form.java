package Snake;

import java.awt.Color;

import javax.swing.JFrame;

public class Form extends JFrame {
	public Form() {
		setTitle("Snake");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setBackground(Color.black);
		add(new Board(this));
		setVisible(true);
	}
}
