package Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Block extends Rectangle{
	
	private static final long serialVersionUID = 1L;
	
	public static final int blockSize = 64;
	
	public Block(int x, int y) {
		
		setBounds(x, y, 1200, 30);
		
	}
	
	public void draw(Graphics g) {
		
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
//		g.setColor(Color.BLACK);
		
	}

}
