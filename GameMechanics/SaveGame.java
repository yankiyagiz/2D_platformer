package GameMechanics;

import java.io.*;
import java.util.ArrayList;

import Characters.Enemies;
import Characters.Player;
import Objects.Coin;

public class SaveGame {

    public static void saveGame(Player player, Enemies enemy[], ArrayList<Coin> coins, int score) {
    	
    	Player.pause = true;
    	
        try {
            FileOutputStream fileOut = new FileOutputStream("saveGame.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
           
            out.writeObject(player);
            out.writeObject(enemy);
            out.writeObject(coins);
            out.writeInt(score);
           
            out.close();
            fileOut.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
