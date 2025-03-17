package Objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import Characters.Enemies;
import Characters.Player;
import Characters.RedEnemy;
import Game.LevelState;
import GameMechanics.Collision;

public class Bullet extends Rectangle{

	private static final long serialVersionUID = 1L;
	
	boolean facingRight, facingLeft, shoot;
	
//	boolean movingRight = true;

	
	public Bullet(int x, int y, boolean facingRight) {
		
		setBounds(x, y, 20, 8);
		this.facingRight = facingRight;
		
	}
	
	public void fire(int x, int y, boolean facingRight) {
	    this.x = x;
	    this.y = y;
	    this.facingRight = facingRight; 
	}
	
	public void init() {
		
		
	}
	
	
	public void tick(Enemies[] e) {
		
		
		
		for (int i = 0; i < e.length; i++) {
			
			Player p = new Player(0,0,0,0);
			
			if(Collision.bulletEnemy(e[i], new Bullet(x, y, facingRight))) {
				
				
				if(e[i] instanceof RedEnemy && p.getRed() ){
					e[i].setHealth(e[i].getHealth()+ 1);
					setBounds(-100,-100,0,0);
				}
				
				else {
					e[i].setHealth(e[i].getHealth() - 1);
					setBounds(-100,-100,0,0);
					
				}
				
			}
			
			
		}
		
	}

	
	public void tick() {
		if (facingRight) {
			x += 10;
		}
		
		else {
			x -= 10;			
		}
	}

	
	public void draw(Graphics g) {
		
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, 20, 8);
			
		
		
	}
	
	public void keyPressed(int k) {
		
		if(k == KeyEvent.VK_D) {
			
			facingLeft = false;
			facingRight = true;
			
		}
		
		if(k == KeyEvent.VK_A) {
			facingRight = false;
			facingLeft = true;
		}
		
		if (k == KeyEvent.VK_SPACE) {
			shoot = true;
		}
			
		
	}
//	public void keyReleased(int k) {
//		
//		if (k == KeyEvent.VK_SPACE) {
//			shoot = false;
//		}
//		
//	}

}
