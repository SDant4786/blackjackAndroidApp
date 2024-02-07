package GameFiles;

import java.util.ArrayList;

public class Player {
    public double bankRoll;
    public double bet;
    public Hand hand;
    public static ArrayList<Hand> handsList;
    public int cardsInHand;
    public boolean insurance;
    public Player(int startingCash){
        handsList = new ArrayList<>();
        bankRoll = startingCash;
        bet = 0;
    }
}
