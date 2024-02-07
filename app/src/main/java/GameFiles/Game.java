package GameFiles;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import com.example.sbj.R;

import java.util.ArrayList;

import static GameFiles.Deck.populateCardBack;
import static GameFiles.mainFunctions.UpdateDealer;
import static GameFiles.mainFunctions.addToDealerDisplay;
import static GameFiles.mainFunctions.addToPlayerDisplay;
import static com.example.sbj.MainActivity.dealerCardCount;
import static com.example.sbj.MainActivity.hand1Count;
import static com.example.sbj.MainActivity.playerCardCount;
import static com.example.sbj.MainActivity.playerHands;
import static com.example.sbj.MainActivity.playerTotalViews;

public class Game {
    public int cardCount;
    public Deck deck;
    public Player player;
    public Dealer dealer;
    public Context context;
    public int decks;
    int handCount;
    public static int backChoice = 4;
    public boolean endHand;
    public static ArrayList<Bitmap> backgrounds;
    private enum Payouts{
        Push,
        Win,
        Loss,
        Blackjack
    }
    public Game(Context context, int decks){
        player = new Player(1000);
        dealer = new Dealer();
        this.context = context;
        populateCardBack(context);
        this.decks = decks;
        deck = new Deck(context,decks,backChoice);
        cardCount = 0;
        populateBackgrounds(context);
    }
    public void newGame(Game game){
        deck = new Deck(context, 1,backChoice);
        player = game.player;
        dealer = new Dealer();
        cardCount = 0;
    }
    public void Deal(){
        player.bankRoll -= player.bet;
        handCount = 0;
        Hand newHand = new Hand(player.bet);
        player.handsList.add(newHand);
        for (int i = 0; i< 2; i++){
            AddToPlayersHand(0);
            AddToDealersHand();
        }
        player.handsList.get(0).active = true;
        CountHand(player.handsList.get(0));
        hand1Count.setText(Integer.toString(CountHand(player.handsList.get(0))));
        UpdateDealer(dealer.hand.cards.get(1).value);
    }
    public int CountHand(Hand hand) {
        hand.total = 0;
        for (Card c : hand.cards) {
            if (c.name == Card.Name.Ace) {
                hand.aces++;
            }
            hand.total += c.value;
        }
        if (hand.aces >= 1 && hand.total <= 11) {
            hand.total += 10;
        }
        if (hand.total > 21) {
            hand.status = Hand.Status.Bust;
        }
        return hand.total;
    }
    public int CountHand(int activeHand) {
        player.handsList.get(activeHand).total = 0;
        for (Card c : player.handsList.get(activeHand).cards) {
            if (c.name == Card.Name.Ace) {
                player.handsList.get(activeHand).aces++;
            }
            player.handsList.get(activeHand).total += c.value;
        }
        if (player.handsList.get(activeHand).aces >= 1 && player.handsList.get(activeHand).total <= 11) {
            player.handsList.get(activeHand).total += 10;
        }
        if (player.handsList.get(activeHand).total > 21) {
            player.handsList.get(activeHand).status = Hand.Status.Bust;
        }
        if (player.handsList.get(activeHand).total == 21) {
            player.handsList.get(activeHand).complete = true;
        }
        return player.handsList.get(activeHand).total;
    }
    public void DealerFunction(){
        while (dealer.hand.total <17){
            AddToDealersHand();
            UpdateDealer(CountHand(dealer.hand));
        }
    }
    public void Payout(Payouts payout, Hand hand){
        if (hand.doubled ==true) {
            if (payout == Payouts.Win) {
                player.bankRoll = player.bankRoll + (hand.bet * 4);
            } else if (payout == Payouts.Loss) {
                player.bankRoll = player.bankRoll - hand.bet;
            }
        }
        else {
            if (payout == Payouts.Blackjack) {
                player.bankRoll = player.bankRoll + hand.bet+ ((hand.bet * 3) / 2);
            } else if (payout == Payouts.Loss) {
            } else if (payout == Payouts.Push) {
                player.bankRoll = player.bankRoll + player.bet;
            } else if (payout == Payouts.Win) {
                player.bankRoll = player.bankRoll + (hand.bet * 2);
            }
        }
    }
    public void WinCheck(){
        CountHand(dealer.hand);
        for(Hand hand :player.handsList){
            CountHand(hand);
            if (hand.status != hand.status.Blackjack) {
                if (hand.total > 21) {
                    Payout(Payouts.Loss, hand);
                    hand.status = Hand.Status.Bust;
                } else if (dealer.hand.total > 21 && hand.total <= 21) {
                    Payout(Payouts.Win, hand);
                    hand.status = Hand.Status.Won;
                } else if (hand.total == dealer.hand.total) {
                    Payout(Payouts.Push, hand);
                    hand.status = Hand.Status.Push;
                } else if (hand.total > dealer.hand.total) {
                    Payout(Payouts.Win, hand);
                    hand.status = Hand.Status.Won;
                } else {
                    Payout(Payouts.Loss, hand);
                    hand.status = Hand.Status.Lost;
                }
            }
        }
        if (player.insurance==true && dealer.blackjack==false){
            player.bankRoll-= (player.bet/2);
        }
    }
    public void Reset(){
        player.handsList.clear();
        dealer = new Dealer();
        playerCardCount = 0;
        dealerCardCount = 0;
    }
    public boolean FirstDealBlackjackCheck() {
        CountHand(player.handsList.get(0));
        CountHand(dealer.hand);
        if (dealer.hand.total == 21 && player.handsList.get(0).total == 21) {
            Payout(Payouts.Push, player.handsList.get(0));
            player.handsList.get(0).status = Hand.Status.Push;
            dealer.blackjack = true;
            return true;
        } else if (dealer.hand.total == 21) {
            Payout(Payouts.Loss, player.handsList.get(0));
            player.handsList.get(0).status = Hand.Status.Lost;
            dealer.blackjack = true;
            return true;
        } else if (player.handsList.get(0).total == 21) {
            Payout(Payouts.Blackjack, player.handsList.get(0));
            player.handsList.get(0).status = Hand.Status.Blackjack;
            return true;
        } else
            return false;
    }
    public void SplitHand(int activeHand) {
        Hand newHand = new Hand(player.handsList.get(activeHand).cards.get(1), player.bet);

        player.handsList.add(newHand);
        int hands = player.handsList.size() - 1;
        addToPlayerDisplay(context, newHand.cards.get(0), hands, 0);
        player.handsList.get(activeHand).cards.remove(1);
        playerHands.get(activeHand).get(1).setVisibility(View.INVISIBLE);
        AddToPlayersHand(activeHand);
        playerTotalViews.get(activeHand).setText(Integer.toString(CountHand(activeHand)));
        AddToPlayersHand(hands);
        playerTotalViews.get(hands).setText(Integer.toString(CountHand(hands)));
        if (player.handsList.get(hands).total == 21) {
            player.handsList.get(hands).complete = true;
        }
        player.bankRoll -= player.bet;

    }
    public void AddToPlayersHand(int activeHand){

        player.handsList.get(activeHand).cards.add(deck.playingDeck.get(cardCount));
        addToPlayerDisplay(context, deck.playingDeck.get(cardCount), activeHand,player.handsList.get(activeHand).cards.size()-1);
        cardCount++;

    }
    private void AddToDealersHand(){
        dealer.hand.cards.add(deck.playingDeck.get(cardCount));
        addToDealerDisplay(deck.playingDeck.get(cardCount));
        cardCount++;
    }
    public static void populateBackgrounds(Context current){
        backgrounds = new ArrayList<>();
        Bitmap back = BitmapFactory.decodeResource(current.getResources(), R.drawable.blue_felt);
        Bitmap back2 = BitmapFactory.decodeResource(current.getResources(), R.drawable.green_felt);
        Bitmap back3 = BitmapFactory.decodeResource(current.getResources(), R.drawable.green_background2);
        backgrounds.add(back);
        backgrounds.add(back2);
        backgrounds.add(back3);
    }
}
