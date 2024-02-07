package GameFiles;

import android.graphics.Bitmap;

public class Card {
    public enum Suit {
        Clubs,
        Diamonds,
        Hearts,
        Spades
    }
    public enum Name {
        Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, King, Queen
    }
    public Suit suit;
    public Name name;
    public int value;
    public Bitmap front;
    public Bitmap back;

    public Card(Suit suit, Name name, int value, Bitmap front, Bitmap back){
        this.suit = suit;
        this.name = name;
        this.value = value;
        this.front = front;
        this.back = back;
    }
}
