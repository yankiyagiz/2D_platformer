package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import GameMechanics.LoadGame;

public class MenuState extends GameState{

	private String [] options = {"Start", "Help", "Quit"};
	private int currentSelection = 0;
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
		
	}

	
	public void init() {}

	
	public void tick() {}

	
	public void draw(Graphics g) {
		
		g.setColor(new Color(250, 175, 200));
		g.fillRect(0, 0, GamePanel.Width, GamePanel.Height);
		
		for (int i = 0; i<options.length; i++) {
			
			if(i == currentSelection) {
				g.setColor(Color.BLACK);
			}
			else {
				g.setColor(Color.WHITE);
			}
			
			g.setFont(new Font("Arial", Font.PLAIN,72));
			g.drawString(options[i], GamePanel.Width/ 2 - 70, 200 + i * 200);
			
			
		}
		
	}

	
	public void keyPressed(int k) {
		
		if(k == KeyEvent.VK_DOWN) {
			currentSelection++;
			
			if (currentSelection >= options.length) {
				currentSelection = 0;
			}
			
		}
		else if (k == KeyEvent.VK_UP) {
			currentSelection--;
			
			if (currentSelection < 0) {
				currentSelection = options.length;
			}
		}
		
		if (k == KeyEvent.VK_ENTER) {
			if (currentSelection == 0) {
//				gsm.states.push(new LevelState(gsm));
				gsm.states.push(LoadGame.loadGame());
			}
			
			else if (currentSelection == 1) {
				//help
			}
			
			else if (currentSelection == 2) {
				System.exit(0);
			}
		}
		
	}

	
	public void keyReleased(int k) {
		
		
	}

}
