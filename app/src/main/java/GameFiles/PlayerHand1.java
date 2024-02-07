package GameFiles;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PlayerHand1 {
    public enum Status {
        Won,
        Lost,
        Push,
        Blackjack,
        Bust
    }
    public boolean complete = false;
    public ArrayList<Card> cards;
    public Hand.Status status;
    public double bet;
    public int aces;
    public int total;
    public ArrayList<ImageView> handImages;
    public TextView handTotal;
    PlayerHand1(){

    }
}
