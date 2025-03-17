package GameMechanics;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import Characters.Enemies;
import Characters.Player;
import Game.GameState;
import Game.GameStateManager;
import Game.LevelState;
import Objects.Coin;

public class LoadGame {

//	private GameStateManager gsm ;
	
	public static LevelState loadGame() {
		
		Player.pause = true;
		
        try {
            FileInputStream fileIn = new FileInputStream("saveGame.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            
            Player player = (Player) in.readObject();
            Enemies[] enemy = (Enemies[]) in.readObject();
            ArrayList<Coin> coins = (ArrayList<Coin>) in.readObject();
            int score = in.readInt();
            
            in.close();
            fileIn.close();
            
            GameStateManager gsm = new GameStateManager();
            LevelState ls = new LevelState(gsm);
            
            LevelState.player = player;
            LevelState.enemy = enemy;
            LevelState.coins = coins;
            LevelState.score = score;
            
            return ls; // Oyun durumunu içeren bir nesne döndürülebilir.
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
