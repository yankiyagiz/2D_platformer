package Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import Characters.Player;

public class FireAndWater extends Rectangle {
    private static final long serialVersionUID = 1L;
    public static final int blockSize = 64;
    public static Color color;
    private Timer timer;
    boolean isFire;
    
    
    public FireAndWater(int x, int y) {
        setBounds(x, y, 500, 30);
        color = Color.RED; // Başlangıçta kırmızı renkte
        timer = new Timer();
        timer.schedule(new ChangeColorTask(), 2000); // 5 saniye sonra renk değiştir
    }
    
    public static Color getColor() {
    	return color;
    }

    public void restart() {
      color = Color.RED;
    }

    
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    // Renk değiştirme işlemi
    private class ChangeColorTask extends TimerTask {
        public void run() {
        	
//        	if(!Player.pause) {
	            if (color == Color.RED) {
	                color = Color.CYAN; // Eğer kırmızıysa maviye çevir
	                isFire= false;
	            } else {
	                color = Color.RED; // Değilse kırmızıya çevir
	                isFire = true;
	            }
	            // Renk değiştirildikten sonra tekrar zamanlayıcıyı başlat
	            timer.schedule(new ChangeColorTask(), 2000); // 5 saniye sonra renk değiştir
//        	}
//        	else {
//        		if(isFire) {
//        			color = Color.RED;
//        		}
//        		if(!isFire) {
//        			color = Color.CYAN;
//        		}
//        		
//        	}
        }
    }
}
