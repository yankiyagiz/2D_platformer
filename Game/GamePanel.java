package Game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import Characters.Player;

public class GamePanel extends JPanel implements Runnable, KeyListener{
	
	private static final long serialVersionUID = 1L;
	
	
	public static final int Width = 1000;
	public static final int Height = 800;
	
	private Thread thread;
	private boolean isRunning = false;
	
	private int FPS = 60;
	private long targetTime = 1000/60;
	
	private GameStateManager gsm;
	
	public GamePanel() {
		setPreferredSize(new Dimension(Width, Height));
		
		addKeyListener(this);
		setFocusable(true);
		
//		this.gsm = initialGSM;
		
		start();
	}
	
	private void start() {
		
		isRunning = true;
		thread = new Thread(this);
		thread.start();
		
		
	}
	
	public void run() {
		
		long start, elapsed, wait;
		
		gsm = new GameStateManager();
		
		while (isRunning) {
			
			
			
			start = System.nanoTime();
			
			
			tick();
			repaint();
			
			elapsed = System.nanoTime()-start;
			wait = targetTime - elapsed / 1000000;
			
			if (wait < 0) {
				wait=5;
			}
			
			try {
				Thread.sleep(wait);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void tick() {
		gsm.tick();
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		g.clearRect(0, 0, Width, Height);
		
//		g.drawRect(10, 10, 10, 10);
		
		gsm.draw(g);
	}

	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		gsm.keyPressed(e.getKeyCode());
		
	}

	public void keyReleased(KeyEvent e) {
		gsm.keyReleased(e.getKeyCode());
		
	}

	
	

}
