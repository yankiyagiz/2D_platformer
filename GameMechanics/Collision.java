package GameMechanics;

import java.awt.Point;

import Characters.Enemies;
import Characters.Player;
import Objects.Block;
import Objects.Bullet;
import Objects.Coin;
import Objects.Door;
import Objects.Fire;
import Objects.FireAndWater;
import Objects.ShortBlock;
import Objects.Water;

public class Collision {

	public static boolean playerBlock(Point p, Block b) {
		
		return b.contains(p);
		
	}
	
	public static boolean playerShortBlock(Point p, ShortBlock b) {
		
		return b.contains(p);
		
	}
	
	public static boolean playerWater(Point p, Water w) {
		
		return w.contains(p);
		
	}
	
	public static boolean playerFire(Point p, Fire f) {
		
		return f.contains(p);
		
	}

	public static boolean playerEnemy(Point p, Enemies e) {
		
		return e.contains(p);
		
	}
	
	public static boolean bulletEnemy(Enemies e, Bullet b) {
		
		return e.contains(b);
					
	}
	
	public static boolean fireWaterPlayer(Point p, FireAndWater fw) {
		
		return fw.contains(p);
		
	}
	
	public static boolean coinPlayer( Player p, Coin c) {
		
//		return c.contains(p);		
		return p.intersects(c);
	}
	
	public static boolean playerDoor(Player p, Door d) {
		return d.contains(p);
	}

	
	
	
	
}
