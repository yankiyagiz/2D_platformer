package Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Fire extends Rectangle{
	
	private static final long serialVersionUID = 1L;
	
	public static final int blockSize = 64;
	
	public Fire(int x, int y) {
		
		setBounds(x, y, 140, 30);
		
	}
	
	public void draw(Graphics g) {
		
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
//		g.setColor(Color.BLACK);
		
	}


}
