package GameFiles;

import java.util.ArrayList;

public class Hand {
    public enum Status {
        Won,
        Lost,
        Push,
        Blackjack,
        Bust
    }
    public boolean complete = false;
    public boolean doubled = false;
    public boolean doubleAble = true;
    public boolean active = false;
    public ArrayList<Card> cards;
    public Status status;
    public double bet;
    public int aces;
    public int total;
    public int cardsInHand;
    public Hand(){
        cards = new ArrayList<>();
        aces = 0;
        total = 0;
    }
    public Hand(double bet){
        this.bet = bet;
        cards = new ArrayList<>();
        aces = 0;
        total = 0;
    }
    public Hand(Card card, double bet) {
        this.bet = bet;
        cards = new ArrayList();
        cards.add(card);
        aces = 0;
        total = 0;
    }
}
