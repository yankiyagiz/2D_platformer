package Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;

public class Coin extends Rectangle implements Serializable{
	
	public Coin(int x, int y) {
		setBounds(x, y , 6, 6);
	}
	
	public void tick() {
		
	}
	
	public void draw(Graphics g) {
		
		g.setColor(Color.GREEN);
		g.fillRect(x, y, 9, 9);
		
		
	}

}
