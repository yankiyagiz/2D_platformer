package Characters;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Enemies extends Rectangle {
	
	private static final long serialVersionUID = 1L;
	int health;
	protected int initialX; 
    protected int initialY; 
    protected int initialHealth;
    protected int initialWidth;
    protected int initialHeight;
	
	public Enemies (int x , int y, int width, int height, int health) {
		setBounds(x, y, width, height);
		this.health = health;
		initialX = x;
	    initialY = y;
	    initialHealth = health;
	    initialWidth = width;
	    initialHeight = height;
		
	}
	
	public void restart() {
		
		width = initialWidth;
		height = initialHeight;
        x = initialX; 
        y = initialY; 
        health = initialHealth; 
    }
	
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public abstract void init();
	public abstract void tick();
	public abstract void draw (Graphics g);
	

}
