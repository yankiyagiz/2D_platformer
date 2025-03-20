package Game;

import java.awt.BorderLayout;

import javax.swing.JFrame;


public class Game {

	public static void main(String[] args) {

		JFrame  window = new JFrame("Game");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		
		window.setLayout(new BorderLayout());
		window.add(new GamePanel(), BorderLayout.CENTER);
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		
		
	}

}
