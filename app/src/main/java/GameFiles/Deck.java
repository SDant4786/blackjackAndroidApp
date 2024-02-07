package GameFiles;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.sbj.R;
import java.util.ArrayList;
import java.util.Random;
public class Deck {
    public Card[] cardsInDeck;
    public ArrayList<Card> playingDeck;
    public static ArrayList<Bitmap> cardBacks;
    public Deck(Context current, int numberOfDecks, int backPos) {
        cardsInDeck = new Card[52];
        Bitmap back = cardBacks.get(backPos);
        cardsInDeck[0] = new Card(Card.Suit.Clubs, Card.Name.Ace, 1, BitmapFactory.decodeResource(current.getResources(), R.drawable.club_ace), back);
        cardsInDeck[1] = new Card(Card.Suit.Clubs, Card.Name.Two, 2, BitmapFactory.decodeResource(current.getResources(), R.drawable.club_two), back);
        cardsInDeck[2] = new Card(Card.Suit.Clubs, Card.Name.Three, 3, BitmapFactory.decodeResource(current.getResources(), R.drawable.club_three), back);
        cardsInDeck[3] = new Card(Card.Suit.Clubs, Card.Name.Four, 4, BitmapFactory.decodeResource(current.getResources(), R.drawable.club_four), back);
        cardsInDeck[4] = new Card(Card.Suit.Clubs, Card.Name.Five, 5, BitmapFactory.decodeResource(current.getResources(), R.drawable.club_five), back);
        cardsInDeck[5] = new Card(Card.Suit.Clubs, Card.Name.Six, 6, BitmapFactory.decodeResource(current.getResources(), R.drawable.club_six), back);
        cardsInDeck[6] = new Card(Card.Suit.Clubs, Card.Name.Seven, 7, BitmapFactory.decodeResource(current.getResources(), R.drawable.club_seven), back);
        cardsInDeck[7] = new Card(Card.Suit.Clubs, Card.Name.Eight, 8, BitmapFactory.decodeResource(current.getResources(), R.drawable.club_eight), back);
        cardsInDeck[8] = new Card(Card.Suit.Clubs, Card.Name.Nine, 9, BitmapFactory.decodeResource(current.getResources(), R.drawable.club_nine), back);
        cardsInDeck[9] = new Card(Card.Suit.Clubs, Card.Name.Ten, 10, BitmapFactory.decodeResource(current.getResources(), R.drawable.club_ten), back);
        cardsInDeck[10] = new Card(Card.Suit.Clubs, Card.Name.Jack, 10, BitmapFactory.decodeResource(current.getResources(), R.drawable.club_jack), back);
        cardsInDeck[11] = new Card(Card.Suit.Clubs, Card.Name.Queen, 10, BitmapFactory.decodeResource(current.getResources(), R.drawable.club_queen), back);
        cardsInDeck[12] = new Card(Card.Suit.Clubs, Card.Name.King, 10, BitmapFactory.decodeResource(current.getResources(), R.drawable.club_king), back);

        cardsInDeck[13] = new Card(Card.Suit.Diamonds, Card.Name.Ace, 1, BitmapFactory.decodeResource(current.getResources(), R.drawable.diamonds_ace), back);
        cardsInDeck[14] = new Card(Card.Suit.Diamonds, Card.Name.Two, 2, BitmapFactory.decodeResource(current.getResources(), R.drawable.diamonds_two), back);
        cardsInDeck[15] = new Card(Card.Suit.Diamonds, Card.Name.Three, 3, BitmapFactory.decodeResource(current.getResources(), R.drawable.diamonds_three), back);
        cardsInDeck[16] = new Card(Card.Suit.Diamonds, Card.Name.Four, 4, BitmapFactory.decodeResource(current.getResources(), R.drawable.diamonds_four), back);
        cardsInDeck[17] = new Card(Card.Suit.Diamonds, Card.Name.Five, 5, BitmapFactory.decodeResource(current.getResources(), R.drawable.diamonds_five), back);
        cardsInDeck[18] = new Card(Card.Suit.Diamonds, Card.Name.Six, 6, BitmapFactory.decodeResource(current.getResources(), R.drawable.diamonds_six), back);
        cardsInDeck[19] = new Card(Card.Suit.Diamonds, Card.Name.Seven, 7, BitmapFactory.decodeResource(current.getResources(), R.drawable.diamonds_seven), back);
        cardsInDeck[20] = new Card(Card.Suit.Diamonds, Card.Name.Eight, 8, BitmapFactory.decodeResource(current.getResources(), R.drawable.diamonds_eight), back);
        cardsInDeck[21] = new Card(Card.Suit.Diamonds, Card.Name.Nine, 9, BitmapFactory.decodeResource(current.getResources(), R.drawable.diamonds_nine), back);
        cardsInDeck[22] = new Card(Card.Suit.Diamonds, Card.Name.Ten, 10, BitmapFactory.decodeResource(current.getResources(), R.drawable.diamonds_ten), back);
        cardsInDeck[23] = new Card(Card.Suit.Diamonds, Card.Name.Jack, 10, BitmapFactory.decodeResource(current.getResources(), R.drawable.diamonds_jack), back);
        cardsInDeck[24] = new Card(Card.Suit.Diamonds, Card.Name.Queen, 10, BitmapFactory.decodeResource(current.getResources(), R.drawable.diamonds_queen), back);
        cardsInDeck[25] = new Card(Card.Suit.Diamonds, Card.Name.King, 10, BitmapFactory.decodeResource(current.getResources(), R.drawable.diamonds_king), back);

        cardsInDeck[26] = new Card(Card.Suit.Hearts, Card.Name.Ace, 1, BitmapFactory.decodeResource(current.getResources(), R.drawable.hearts_ace), back);
        cardsInDeck[27] = new Card(Card.Suit.Hearts, Card.Name.Two, 2, BitmapFactory.decodeResource(current.getResources(), R.drawable.hearts_two), back);
        cardsInDeck[28] = new Card(Card.Suit.Hearts, Card.Name.Three, 3, BitmapFactory.decodeResource(current.getResources(), R.drawable.hearts_three), back);
        cardsInDeck[29] = new Card(Card.Suit.Hearts, Card.Name.Four, 4, BitmapFactory.decodeResource(current.getResources(), R.drawable.hearts_four), back);
        cardsInDeck[30] = new Card(Card.Suit.Hearts, Card.Name.Five, 5, BitmapFactory.decodeResource(current.getResources(), R.drawable.hearts_five), back);
        cardsInDeck[31] = new Card(Card.Suit.Hearts, Card.Name.Six, 6, BitmapFactory.decodeResource(current.getResources(), R.drawable.hearts_six), back);
        cardsInDeck[32] = new Card(Card.Suit.Hearts, Card.Name.Seven, 7, BitmapFactory.decodeResource(current.getResources(), R.drawable.hearts_seven), back);
        cardsInDeck[33] = new Card(Card.Suit.Hearts, Card.Name.Eight, 8, BitmapFactory.decodeResource(current.getResources(), R.drawable.hearts_eight), back);
        cardsInDeck[34] = new Card(Card.Suit.Hearts, Card.Name.Nine, 9, BitmapFactory.decodeResource(current.getResources(), R.drawable.hearts_nine), back);
        cardsInDeck[35] = new Card(Card.Suit.Hearts, Card.Name.Ten, 10, BitmapFactory.decodeResource(current.getResources(), R.drawable.hearts_ten), back);
        cardsInDeck[36] = new Card(Card.Suit.Hearts, Card.Name.Jack, 10, BitmapFactory.decodeResource(current.getResources(), R.drawable.hearts_jack), back);
        cardsInDeck[37] = new Card(Card.Suit.Hearts, Card.Name.Queen, 10, BitmapFactory.decodeResource(current.getResources(), R.drawable.hearts_queen), back);
        cardsInDeck[38] = new Card(Card.Suit.Hearts, Card.Name.King, 10, BitmapFactory.decodeResource(current.getResources(), R.drawable.hearts_king), back);

        cardsInDeck[39] = new Card(Card.Suit.Spades, Card.Name.Ace, 1, BitmapFactory.decodeResource(current.getResources(), R.drawable.spades_ace), back);
        cardsInDeck[40] = new Card(Card.Suit.Spades, Card.Name.Two, 2, BitmapFactory.decodeResource(current.getResources(), R.drawable.spades_two), back);
        cardsInDeck[41] = new Card(Card.Suit.Spades, Card.Name.Three, 3, BitmapFactory.decodeResource(current.getResources(), R.drawable.spades_three), back);
        cardsInDeck[42] = new Card(Card.Suit.Spades, Card.Name.Four, 4, BitmapFactory.decodeResource(current.getResources(), R.drawable.spades_four), back);
        cardsInDeck[43] = new Card(Card.Suit.Spades, Card.Name.Five, 5, BitmapFactory.decodeResource(current.getResources(), R.drawable.spades_five), back);
        cardsInDeck[44] = new Card(Card.Suit.Spades, Card.Name.Six, 6, BitmapFactory.decodeResource(current.getResources(), R.drawable.spades_six), back);
        cardsInDeck[45] = new Card(Card.Suit.Spades, Card.Name.Seven, 7, BitmapFactory.decodeResource(current.getResources(), R.drawable.spades_seven), back);
        cardsInDeck[46] = new Card(Card.Suit.Spades, Card.Name.Eight, 8, BitmapFactory.decodeResource(current.getResources(), R.drawable.spades_eight), back);
        cardsInDeck[47] = new Card(Card.Suit.Spades, Card.Name.Nine, 9, BitmapFactory.decodeResource(current.getResources(), R.drawable.spades_nine), back);
        cardsInDeck[48] = new Card(Card.Suit.Spades, Card.Name.Ten, 10, BitmapFactory.decodeResource(current.getResources(), R.drawable.spades_ten), back);
        cardsInDeck[49] = new Card(Card.Suit.Spades, Card.Name.Jack, 10, BitmapFactory.decodeResource(current.getResources(), R.drawable.spades_jack), back);
        cardsInDeck[50] = new Card(Card.Suit.Spades, Card.Name.Queen, 10, BitmapFactory.decodeResource(current.getResources(), R.drawable.spades_queen), back);
        cardsInDeck[51] = new Card(Card.Suit.Spades, Card.Name.King, 10, BitmapFactory.decodeResource(current.getResources(), R.drawable.spades_king), back);

        playingDeck = new ArrayList<>();
        for (int i = 0; i < numberOfDecks; i++) {
            for (int j = 0; j < 52; j++) {
                playingDeck.add(cardsInDeck[j]);
            }
        }
        ShuffleDeck(this);
    }
    public static void ShuffleDeck(Deck deck){
        Random rand = new Random();
        for (int j = 0; j<7; j++){
            ArrayList<Card> shuffled = new ArrayList<>();
            for(int i = deck.playingDeck.size(); i> 0; i--){
                int pos = rand.nextInt(i);
                shuffled.add( deck.playingDeck.get(pos));
                deck.playingDeck.set(pos,deck.playingDeck.get(i-1));
            }
            deck.playingDeck = shuffled;
        }
    }
    public static void populateCardBack(Context current){
        cardBacks = new ArrayList<>();
        Bitmap back = BitmapFactory.decodeResource(current.getResources(), R.drawable.red_back);
        Bitmap back2 = BitmapFactory.decodeResource(current.getResources(), R.drawable.blue_back);
        Bitmap back3 = BitmapFactory.decodeResource(current.getResources(), R.drawable.gray_back);
        Bitmap back4 = BitmapFactory.decodeResource(current.getResources(), R.drawable.green_back);
        Bitmap back5 = BitmapFactory.decodeResource(current.getResources(), R.drawable.purple_back);
        Bitmap back6 = BitmapFactory.decodeResource(current.getResources(), R.drawable.yellow_back);
        cardBacks.add(back);
        cardBacks.add(back2);
        cardBacks.add(back3);
        cardBacks.add(back4);
        cardBacks.add(back5);
        cardBacks.add(back6);
    }
}
