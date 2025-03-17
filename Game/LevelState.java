package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import Characters.BlueEnemy;
import Characters.Enemies;
import Characters.OrangeEnemy;
import Characters.Player;
import Characters.RedEnemy;
import GameMechanics.LoadGame;
import GameMechanics.SaveGame;
import Objects.Block;
import Objects.Bullet;
import Objects.Coin;
import Objects.Door;
import Objects.Fire;
import Objects.FireAndWater;
import Objects.ShortBlock;
import Objects.Water;

public class LevelState extends GameState{
	
	private int tickCount = 1;
	
	public static Player player;
	
	public static int score = 0;
	
	private ArrayList<Bullet> bullet;
	
	public static ArrayList<Coin> coins;
	
	public static Enemies enemy[];
	
	private static ArrayList<Enemies> Spawner = new ArrayList<>();
	
//	private Enemies redEnemy1;
	
//	private Block b1, b2, b3;
	
	private Block[] blok;
	
	private ShortBlock[] shortBlok;
	
	private Door kapi;
	
	private Water[] su;
	
	private Fire[] ates;
	
	private static FireAndWater fw;
	
	public LevelState(GameStateManager gsm) {
		super (gsm);
	}
	
	
	public static void restartGame() {
	    player.restart(); 
	    for (Enemies enemy : enemy) {
	        enemy.restart(); 
	    }
	    
	    Spawner.clear();
	    
	    fw.restart();
	    
	    coins = new ArrayList<>();
	    coins.add(new Coin(110, 710));
		coins.add(new Coin(150, 710));
		coins.add(new Coin(190, 660));
		coins.add(new Coin(230, 610));
		coins.add(new Coin(270, 610));
		coins.add(new Coin(310, 610));
		coins.add(new Coin(350, 660));
		coins.add(new Coin(390, 710));
		coins.add(new Coin(430, 710));
		coins.add(new Coin(470, 710));
		coins.add(new Coin(510, 710));
		coins.add(new Coin(550, 710));
		coins.add(new Coin(590, 710));
		coins.add(new Coin(630, 710));
		coins.add(new Coin(670, 710));
		coins.add(new Coin(710, 710));
		coins.add(new Coin(750, 710));
		coins.add(new Coin(790, 710));
		coins.add(new Coin(830, 710));
		coins.add(new Coin(870, 710));
		coins.add(new Coin(910, 710));
		coins.add(new Coin(950, 710));
		coins.add(new Coin(950, 605));
		
		coins.add(new Coin(40, 515));
		coins.add(new Coin(80, 515));
		coins.add(new Coin(120, 515));
		coins.add(new Coin(160, 515));
		
		int k1 = 280;
		for (int i = 0; i < 13; i++) {
			coins.add(new Coin(k1, 315));
			k1+=40;
		}
		
		int k2 = 730;
		for (int i = 0; i < 8; i++) {
			coins.add(new Coin(k2, 165));
			k2+=40;
		}
	   
	}
	
	
	public void init() {
		
		player = new Player(40, 650, 35, 45); // x y width height
		
		kapi = new Door(20, 20);
		
		
		coins = new ArrayList<>();
		coins.add(new Coin(110, 710));
		coins.add(new Coin(150, 710));
		coins.add(new Coin(190, 660));
		coins.add(new Coin(230, 610));
		coins.add(new Coin(270, 610));
		coins.add(new Coin(310, 610));
		coins.add(new Coin(350, 660));
		coins.add(new Coin(390, 710));
		coins.add(new Coin(430, 710));
		coins.add(new Coin(470, 710));
		coins.add(new Coin(510, 710));
		coins.add(new Coin(550, 710));
		coins.add(new Coin(590, 710));
		coins.add(new Coin(630, 710));
		coins.add(new Coin(670, 710));
		coins.add(new Coin(710, 710));
		coins.add(new Coin(750, 710));
		coins.add(new Coin(790, 710));
		coins.add(new Coin(830, 710));
		coins.add(new Coin(870, 710));
		coins.add(new Coin(910, 710));
		coins.add(new Coin(950, 710));
		coins.add(new Coin(950, 605));
		
		
		coins.add(new Coin(40, 515));
		coins.add(new Coin(80, 515));
		coins.add(new Coin(120, 515));
		coins.add(new Coin(160, 515));
		
		int k1 = 280;
		for (int i = 0; i < 13; i++) {
			coins.add(new Coin(k1, 315));
			k1+=40;
		}
		
		int k2 = 730;
		for (int i = 0; i < 8; i++) {
			coins.add(new Coin(k2, 165));
			k2+=40;
		}
		
		
		enemy = new Enemies[3];
		enemy[0] = new RedEnemy(900, 690, 45, 55, 5, 0, GamePanel.Width, false);	// x y width height health leftBound rightBound
		enemy[1] = new BlueEnemy(400, 690, 45, 55, 5, 0, GamePanel.Width, true);
		enemy[2] = new OrangeEnemy(400, 500, 40, 50, 5, 0, 800, false);
		
		
		blok = new Block[7];
		blok[0] = new Block(0, 745);
		blok[1] = new Block(-400, 550);
		blok[2] = new Block(250, 350);
		blok[3] = new Block(900, 640);
		blok[4] = new Block(-700, 100);
		blok[5] = new Block(-1100, 430);
		blok[6] = new Block(700, 200);
		
		
		
		shortBlok = new ShortBlock[1];
//		shortBlok[0] = new ShortBlock(70, 430);
		
		su = new Water[1];
		su[0] = new Water(200, 745);

		
		ates = new Fire[1];
		ates[0] = new Fire(600, 745);

		
		fw = new FireAndWater(200, 550);
		
	}

	
	public void tick() {
		
		tickCount++;
		spawnEnemy();
		
		if(Player.Saved) {
			SaveGame.saveGame(player, enemy, coins, score);
		}
		
		if(!Player.pause ) {
			
			player.tick();
			for (int i = 0; i < enemy.length; i++) {
				enemy[i].tick();
			}
			
			for (int i = 0; i < Spawner.size(); i++) {
				Spawner.get(i).tick();
			}
			player.tick(kapi);
			player.tickE(Spawner);
//			player.tick(shortBlok);
			player.tick(blok);
//			player.tick(shortBlok);
			player.tick(su);
			player.tick(ates);
			player.tick(enemy);
			player.tick(fw);
			player.tick(coins);
		}
	}

	
	public void draw(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.Width, GamePanel.Height);
		
		kapi.draw(g);
		
		player.draw(g);
		
//		for (int i = 0; i < coins.size(); i++) {
//			coins.get(i).draw(g);
//		}
		
		for (int i = 0; i < enemy.length; i++) {
			enemy[i].draw(g);
		}	
		
		for (int i = 0; i < Spawner.size(); i++) {
			Spawner.get(i).draw(g);
		}
		
		for (int i = 0; i< blok.length; i++) {
			blok[i].draw(g);
		}
		
//		for (int i = 0; i < shortBlok.length; i++) {
//			shortBlok[i].draw(g);
//		}

		for (int i = 0; i< su.length; i++) {
			su[i].draw(g);
		}
		
		for (int i = 0; i < ates.length; i++) {
			ates[i].draw(g);
			
		}
		
		for (int i = 0; i < coins.size(); i++) {
			coins.get(i).draw(g);
		}
		
		fw.draw(g);
			
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.PLAIN,32));
		g.drawString("Score: " + score, 800, 100);
		
		if(Player.pause) {
			g.setColor(new Color(0, 0, 0, 150));
	        g.fillRect(0, 0, GamePanel.Width, GamePanel.Height);

	        g.setColor(Color.WHITE);
	        g.setFont(new Font("Arial", Font.BOLD, 32));
	        String pausedMessage = "Game is Paused";
	        int messageWidth = g.getFontMetrics().stringWidth(pausedMessage);
	        int messageHeight = g.getFontMetrics().getHeight();
	        int x = (GamePanel.Width - messageWidth) / 2;
	        int y = (GamePanel.Height - messageHeight) / 2;
	        g.drawString(pausedMessage, x, y);
				    
		}
		
		if(Player.Saved) {
			g.setColor(new Color(0, 0, 0, 0));
	        g.fillRect(0, 0, GamePanel.Width, GamePanel.Height);

	        g.setColor(Color.WHITE);
	        g.setFont(new Font("Arial", Font.BOLD, 32));
	        String pausedMessage = "Game is Saved";
	        int messageWidth = g.getFontMetrics().stringWidth(pausedMessage);
	        int messageHeight = g.getFontMetrics().getHeight();
	        int x = (GamePanel.Width - messageWidth) / 2;
	        int y = (GamePanel.Height - messageHeight) / 2 + 50;
	        g.drawString(pausedMessage, x, y);
		}
		
		if(Player.isFinished) {
			g.setColor(Color.PINK);
	        g.fillRect(0, 0, GamePanel.Width, GamePanel.Height);

	        g.setColor(Color.WHITE);
	        g.setFont(new Font("Arial", Font.BOLD, 32));
	        String pausedMessage = "Congrutulations!!! Your score: " + score;
	        int messageWidth = g.getFontMetrics().stringWidth(pausedMessage);
	        int messageHeight = g.getFontMetrics().getHeight();
	        int x = (GamePanel.Width - messageWidth) / 2;
	        int y = (GamePanel.Height - messageHeight) / 2 + 50;
	        g.drawString(pausedMessage, x, y);
		}
		
	}

	public void keyPressed(int k) {
		
		player.keyPressed(k);
		
		if(k == KeyEvent.VK_K) {
			
			SaveGame.saveGame(player, enemy, coins, k);
			
			Player.Saved = true;
			
			
//			GameStateManager gsm = LoadGame.loadGame();
//	        if (gsm != null) {
//	            gsm.init(); // Oyun durumunu başlat
//	            gsm.start(); // Oyunu başlat
//	        } else {
//	            System.out.println("Oyun yüklenemedi.");
//	        }
		}
		
		if(k == KeyEvent.VK_X) {
			LoadGame.loadGame();
		}
		
	}

	
	public void keyReleased(int k) {
		
		player.keyReleased(k);
		
	}
	
//	enemy[0] = new RedEnemy(900, 405, 45, 55, 5, 250, GamePanel.Width, false);
	
	public void spawnEnemy() {
		
		Random random = new Random();

		if (tickCount % 700 == 0) {

			int randomEnemy = random.nextInt(2);
			
			if (randomEnemy == 0) {
				Spawner.add(new RedEnemy(900, 295, 45, 55, 5, 250, GamePanel.Width, false));
			}
			
			if (randomEnemy == 1) {
				Spawner.add(new BlueEnemy(900, 295, 45, 55, 5, 250, GamePanel.Width, false));
			}
	        
			
	        
	        }
	}
}


