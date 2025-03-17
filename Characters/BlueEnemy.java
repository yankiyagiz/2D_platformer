package Characters;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import Game.GamePanel;

public class BlueEnemy extends Enemies{
	
int redSpeed = 4;
	
	boolean movingRight;
	
	private int leftBoundary;
	private int rightBoundary;

	public BlueEnemy(int x, int y, int width, int height,  int health, int leftBoundary, int rightBoundary, boolean movingRight) {
		super(x, y, width, height, health);
		this.leftBoundary = leftBoundary;
		this.rightBoundary = rightBoundary;
		this.movingRight = movingRight;
		
	}
	
	public void init() {
		
		
	}

	
	public void tick() {
		
		if(health <= 0) {
			setBounds(-100,-100,0,0);
		}
		
		if (movingRight && x >= rightBoundary - width) {
			
			movingRight = false;
		}
		
		else if(!movingRight && x <= leftBoundary) {
			
			movingRight = true;
		}
		
		if(movingRight) {
			x += 4;
		}
		else {
			x -= 4;
		}
		
	}

	
	public void draw(Graphics g) {
		
		if(health <= 0) {
			return;
		}
		else {
			g.setColor(Color.CYAN);
			g.fillRect(x, y, width, height);
			
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.PLAIN,30));
			g.drawString(health + "", x + width/3 , y + 2*height/3 );
		}
		
	}
}
