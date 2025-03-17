package Characters;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


import Game.GamePanel;
import Game.LevelState;
import GameMechanics.Collision;
import GameMechanics.SaveGame;
import Objects.Block;
import Objects.Bullet;
import Objects.Coin;
import Objects.Door;
import Objects.Fire;
import Objects.FireAndWater;
import Objects.ShortBlock;
import Objects.Water;

public class Player extends Rectangle{
	
	
	
	public static boolean Saved;
		
	public static boolean pause = false;
	
	private static final long serialVersionUID = 1L;
	
	public static boolean isFinished;
	
	public static boolean isDead;
	
	private boolean right, left, jump, fall;
	
	public boolean red = true , blue = false, magenta = false;
	
	boolean facingRight;
	
	private double jumpSpeed = 6;
	private double currentJumpSpeed = jumpSpeed;
	
	private double fallSpeed = 6;
	private double currentFallSpeed = 0.1;
	
	public static ArrayList<Bullet> bullets;
	
	int k = GamePanel.WIDTH;
	
	int initialX = 40;
	int initialY = 650;
	
	private long lastFireTime = 0; 

	private static final long fireDelay = 250; 
	
	public boolean getRed() {
		return red;
	}
	
	
	public Player (int x , int y, int width, int height) {
		
		setBounds(x, y, width, height);
		bullets = new ArrayList<>();
	}
	
	public void tick(Door d) {
		
		if(Collision.playerDoor(LevelState.player, d)) {
			isFinished = true;
		}
		
	}
	
	public void tick(Block[] b) {
		
		int w = width;
		int h = height;
		
		boolean onGround = false;
						
		for (int i = 0; i < b.length; i++) {
			
			if (Collision.playerBlock(new Point(x, y + h), b[i]) ||
					Collision.playerBlock(new Point(x + w, y + h), b[i])) {
				
				fall = false;
				onGround = true;				
			}			
			
			if(Collision.playerBlock(new Point (x + w, y), b[i])||
					Collision.playerBlock(new Point (x , y), b[i])) {
					 
				jump = false;
				fall = true;
			}
			
			if (!onGround){				
				fall = true;				
			}
			
			if(Collision.playerBlock(new Point(x + w, y + h/4), b[i]) ||
					Collision.playerBlock(new Point(x + w, y + h/2), b[i])||
					Collision.playerBlock(new Point(x + w, y + 3*h/4), b[i]) ){
				right = false;
				fall = true;
			}
			
			if(Collision.playerBlock(new Point(x, y + h/4), b[i]) ||
					Collision.playerBlock(new Point(x , y + h/2), b[i])||
					Collision.playerBlock(new Point(x , y + 3*h/4), b[i]) ){
				left = false;
				fall = true;
			}
		}
	}
	
	public void tick(ShortBlock[] s) {
		
		int w = width;
		int h = height;
		
		boolean onGround = false;
						
		for (int i = 0; i < s.length; i++) {
			
			if (Collision.playerShortBlock(new Point(x, y + h), s[i]) ||
					Collision.playerShortBlock(new Point(x + w, y + h), s[i])) {
				
				fall = false;
				onGround = true;				
			}			
			
			if(Collision.playerShortBlock(new Point (x + w, y), s[i])||
					Collision.playerShortBlock(new Point (x , y), s[i])) {
					 
				jump = false;
				fall = true;
			}
			
			if (!onGround){				
				fall = true;				
			}
			
			if(Collision.playerShortBlock(new Point(x + w, y + h/4), s[i]) ||
					Collision.playerShortBlock(new Point(x + w, y + h/2), s[i])||
					Collision.playerShortBlock(new Point(x + w, y + 3*h/4), s[i]) ){
				right = false;
				fall = true;
			}
			
			if(Collision.playerShortBlock(new Point(x, y + h/4), s[i]) ||
					Collision.playerShortBlock(new Point(x , y + h/2), s[i])||
					Collision.playerShortBlock(new Point(x , y + 3*h/4), s[i]) ){
				left = false;
				fall = true;
			}
		}
	}
	
	public void tick(Water[] w) {
		
		int wi = width;
		int he = height;
		
		for(int i = 0; i < w.length; i++) {
			
			if (Collision.playerWater(new Point(x , y + he), w[i]) ||
					Collision.playerWater(new Point(x + wi , y + he), w[i])) {
				
				if(!blue) {
					LevelState.restartGame();
				}
			}
		}
	}
	
	public void tick(Fire[] f) {
		
		int wi = width;
		int he = height;
		
		for(int i = 0; i < f.length; i++) {
			
			if (Collision.playerFire(new Point(x , y + he), f[i]) ||
					Collision.playerFire(new Point(x + wi , y + he), f[i])) {
				
				if(!red) {
					LevelState.restartGame();
				}
			}
		}
	}
	
	public void tick(Enemies[] e) {
		
		int w = width;
		int h = height;
		
		boolean enRed = false, enBlue = false;
		
		for (int i = 0; i < e.length; i++) {
			
			for (int j = 0; j < bullets.size(); j++) {
			
				if(Collision.bulletEnemy(e[i], new Bullet(bullets.get(j).x, bullets.get(j).y, facingRight))) {
					
					if(e[i] instanceof OrangeEnemy){
						bullets.get(j).setBounds(-100,-100,0,0);
					}
					
					if(e[i] instanceof RedEnemy && red){
						e[i].setHealth(e[i].getHealth()+ 1);
						bullets.get(j).setBounds(-100,-100,0,0);
						
						if(e[i].getHealth() <= 0)							
							e[i].setBounds(-100,-100,0,0);
					}
					
					if (e[i] instanceof RedEnemy && !red){
						e[i].setHealth(e[i].getHealth() - 1);
						bullets.get(j).setBounds(-100,-100,0,0);
						
						if(e[i].getHealth() <= 0)	{
							LevelState.score += 5; 
							e[i].setBounds(-100,-100,0,0);
						}
						
					}
					
					if(e[i] instanceof BlueEnemy && blue) {
						e[i].setHealth(e[i].getHealth()+ 1);
						bullets.get(j).setBounds(-100,-100,0,0);
						
						if(e[i].getHealth() <= 0)							
							e[i].setBounds(-100,-100,0,0);
					}
					if(e[i] instanceof BlueEnemy && !blue) {
						e[i].setHealth(e[i].getHealth() - 1);
						bullets.get(j).setBounds(-100,-100,0,0);
						
						if(e[i].getHealth() <= 0)	{
							LevelState.score += 5; 
							e[i].setBounds(-100,-100,0,0);
						}
					}
				}
			}	
		}
		
		for (int i = 0; i < e.length; i++) {
						
			if (Collision.playerEnemy(new Point (x+w , y), e[i]) ||
					Collision.playerEnemy(new Point (x+w , y+h), e[i]) ||
					Collision.playerEnemy(new Point (x , y+h), e[i]) ||
					Collision.playerEnemy(new Point (x , y), e[i])) {
				
				LevelState.restartGame();
			}			
		}		
	}
	
	public void tickE(ArrayList<Enemies> e) {
		
		int w = width;
		int h = height;
		
		boolean enRed = false, enBlue = false;
		
		for (int i = 0; i < e.size(); i++) {
			
			for (int j = 0; j < bullets.size(); j++) {
			
				if(Collision.bulletEnemy(e.get(i), new Bullet(bullets.get(j).x, bullets.get(j).y, facingRight))) {
					
					if(e.get(i) instanceof OrangeEnemy){
						bullets.get(j).setBounds(-100,-100,0,0);
					}
					
					if(e.get(i) instanceof RedEnemy && red){
						e.get(i).setHealth(e.get(i).getHealth()+ 1);
						bullets.get(j).setBounds(-100,-100,0,0);
						
						if(e.get(i).getHealth() <= 0)							
							e.get(i).setBounds(-100,-100,0,0);
					}
					
					if (e.get(i) instanceof RedEnemy && !red){
						e.get(i).setHealth(e.get(i).getHealth() - 1);
						bullets.get(j).setBounds(-100,-100,0,0);
						
						if(e.get(i).getHealth() <= 0)	{
							LevelState.score += 5; 
							e.get(i).setBounds(-100,-100,0,0);
						}
						
					}
					
					if(e.get(i) instanceof BlueEnemy && blue) {
						e.get(i).setHealth(e.get(i).getHealth()+ 1);
						bullets.get(j).setBounds(-100,-100,0,0);
						
						if(e.get(i).getHealth() <= 0)							
							e.get(i).setBounds(-100,-100,0,0);
					}
					if(e.get(i) instanceof BlueEnemy && !blue) {
						e.get(i).setHealth(e.get(i).getHealth() - 1);
						bullets.get(j).setBounds(-100,-100,0,0);
						
						if(e.get(i).getHealth() <= 0)	{
							LevelState.score += 5; 
							e.get(i).setBounds(-100,-100,0,0);
						}
					}
				}
			}	
		}
		
		for (int i = 0; i < e.size(); i++) {
						
			if (Collision.playerEnemy(new Point (x+w , y), e.get(i)) ||
					Collision.playerEnemy(new Point (x+w , y+h), e.get(i)) ||
					Collision.playerEnemy(new Point (x , y+h), e.get(i)) ||
					Collision.playerEnemy(new Point (x , y), e.get(i))){
				
				LevelState.restartGame();
			}			
		}		
	}
	
	public void tick(FireAndWater fw) {
		
		int w = width;
		int h = height;
		
		if((Collision.fireWaterPlayer(new Point (x , y + h), fw) && !blue && FireAndWater.color != Color.RED) ||
				Collision.fireWaterPlayer(new Point (x + w , y + h), fw) && !blue && FireAndWater.color != Color.RED) {
			
			LevelState.restartGame();
		}
		
		if((Collision.fireWaterPlayer(new Point (x , y + h), fw) && !red && FireAndWater.color == Color.RED) ||
				Collision.fireWaterPlayer(new Point (x + w , y + h), fw) && !red && FireAndWater.color == Color.RED) {
			
			LevelState.restartGame();
		}
	}
	
	public void tick(ArrayList<Coin> c) {
		
		for (int i = 0; i < c.size(); i++) {
			if(Collision.coinPlayer( LevelState.player, c.get(i))) {
				LevelState.score ++;
				c.remove(i);
			}
		}		
	}
	
	public void tick() {
		
		int w = width;
		int h = height;
		
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).tick();
		}
				
		if(right) {
			x+=4;
		}
		
		if(left) {
			x-=4;
		}
		
		if(jump) {
			
			y-= currentJumpSpeed;
			
			if (!magenta)
				currentJumpSpeed -= .1;
			else
				currentJumpSpeed -= .05;
			
			if(currentJumpSpeed <= 0) {
				currentJumpSpeed = jumpSpeed;
				jump = false;
				fall = true;
			}
		}
		if(fall) {
			
//			if (y == 700) {
//				fall = false;
//			}
			
			y+= currentFallSpeed;
			
//			currentFallSpeed += 0.1;
			
			if(currentFallSpeed < fallSpeed) {
				currentFallSpeed += .1;
			}
		}		
		if(!fall) {
			currentFallSpeed = .1;
		}		
	}
	
	public void draw (Graphics g) {
		
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).draw(g);
		}
		
		g.setColor(Color.RED);
		
				
		if(red == true)
			g.setColor(Color.RED);
		if(blue == true)
			g.setColor(Color.CYAN);
		if(magenta == true)
			g.setColor(Color.MAGENTA);
		
		g.fillRect(x, y, width, height);
	}
	
	public void keyPressed(int k) {
		
//		if(k == KeyEvent.VK_Z) {
//			Saved = true;
//			SaveGame.saveGame(player, null, k);
//		}
		
		if(k == KeyEvent.VK_P) {
			Player.pause = !Player.pause;
			Player.Saved = false;
		}
		
		//MOVEMENT
		if(k == KeyEvent.VK_D) {
			right = true;
			facingRight = true;
		}
		
		if(k == KeyEvent.VK_A) {
			left = true;
			facingRight = false;
		}
		
		if(k == KeyEvent.VK_W) 
			jump = true;
		
		if (k == KeyEvent.VK_SPACE) {
			if (!magenta) {
				fireBullet();
			}
	    }
		//COLOR
		if(k == KeyEvent.VK_1) {
			red = true;
			blue = false;
			magenta = false;
		}	
			
		if(k == KeyEvent.VK_2) {
			blue = true;
			red = false;
			magenta = false;
		}
		
		if(k == KeyEvent.VK_3) {
			magenta = true;
			red = false;
			blue = false;
		}
	}
	
	public void keyReleased(int k) {
		
		if(k == KeyEvent.VK_D) 
			right = false;
		if(k == KeyEvent.VK_A) 
			left = false;
	}
	
	public void restart() {
		x = initialX;
	    y = initialY;
	    
	    right = false;
	    left = false;
	    jump = false;
	    fall = false;
	    red = true;
	    blue = false;
	    magenta = false;
	    facingRight = true;
	    
	    bullets.clear();
	    
	    LevelState.score = 0;
		
	}
	
	private void fireBullet() {
		long currentTime = System.currentTimeMillis();
		
		if (currentTime - lastFireTime > fireDelay) {
	        Bullet bullet = new Bullet(x + width / 2, y + height / 2, facingRight);
	        bullets.add(bullet);
	        lastFireTime = currentTime;
		}
    }
}
