package Characters;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class OrangeEnemy extends Enemies{
	
	private static final long serialVersionUID = 1L;

	int redSpeed = 4;
	
	boolean movingRight;
	
	private int leftBoundary;
	private int rightBoundary;

	public OrangeEnemy(int x, int y, int width, int height,  int health, int leftBoundary, int rightBoundary, boolean movingRight) {
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
			x += 6;
		}
		else {
			x -= 6;
		}
		
	}

	
	public void draw(Graphics g) {
		
		
			g.setColor(Color.ORANGE);
			g.fillRect(x, y, width, height);
			
			
		
		
	}

}
