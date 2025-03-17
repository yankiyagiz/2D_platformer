package Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Door extends Rectangle{
	
	private static final long serialVersionUID = 1L;
	
	public static final int blockSize = 64;
	
	public Door(int x, int y) {
		
		setBounds(x, y, 80, 80);
		
	}
	
	public void draw(Graphics g) {
		
		g.setColor(Color.PINK);
		g.fillRect(x, y, width, height);
//		g.setColor(Color.BLACK);
		
	}

}