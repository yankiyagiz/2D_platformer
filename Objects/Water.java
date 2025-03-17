package Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Water extends Rectangle{
		
		private static final long serialVersionUID = 1L;
		
		public static final int blockSize = 64;
		
		public Water(int x, int y) {
			
			setBounds(x, y, 140, 30);
			
		}
		
		public void draw(Graphics g) {
			
			g.setColor(Color.CYAN);
			g.fillRect(x, y, width, height);
//			g.setColor(Color.BLACK);
			
		}

}
