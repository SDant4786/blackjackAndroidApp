package GameFiles;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;

import com.example.sbj.R;

import static com.example.sbj.MainActivity.acceptBetBtn;
import static com.example.sbj.MainActivity.activeHand;
import static com.example.sbj.MainActivity.bet1;
import static com.example.sbj.MainActivity.bet100;
import static com.example.sbj.MainActivity.bet10k;
import static com.example.sbj.MainActivity.bet1k;
import static com.example.sbj.MainActivity.bet25;
import static com.example.sbj.MainActivity.bet5;
import static com.example.sbj.MainActivity.bet500;
import static com.example.sbj.MainActivity.bet5k;
import static com.example.sbj.MainActivity.betBTNPopup;
import static com.example.sbj.MainActivity.betView;
import static com.example.sbj.MainActivity.betViews;
import static com.example.sbj.MainActivity.blackjackView;
import static com.example.sbj.MainActivity.cardBack;
import static com.example.sbj.MainActivity.dealBTN;
import static com.example.sbj.MainActivity.dealerCardCount;
import static com.example.sbj.MainActivity.dealerHand;
import static com.example.sbj.MainActivity.dealerHandCount;
import static com.example.sbj.MainActivity.doubleBTN;
import static com.example.sbj.MainActivity.endHandStatus;
import static com.example.sbj.MainActivity.format;
import static com.example.sbj.MainActivity.hitBTN;
import static com.example.sbj.MainActivity.indicators;
import static com.example.sbj.MainActivity.mInterstitialAd;
import static com.example.sbj.MainActivity.playerCash;
import static com.example.sbj.MainActivity.playerHands;
import static com.example.sbj.MainActivity.playerTotalViews;
import static com.example.sbj.MainActivity.repeatBetBTN;
import static com.example.sbj.MainActivity.splitBTN;
import static com.example.sbj.MainActivity.standBTN;

public class mainFunctions {
    private static boolean split;

    public static void addToPlayerDisplay(final Context context, final Card card, int activeHand, int pos){


        playerHands.get(activeHand).get(pos).setImageBitmap(card.front);
            final ObjectAnimator oa1 = ObjectAnimator.ofFloat(cardBack, "scaleX", 1f, 0f);
            oa1.setDuration(1000);
            final ObjectAnimator oa2 = ObjectAnimator.ofFloat(cardBack, "scaleX", 0f, 1f);
            oa2.setDuration(1000);
            oa1.setInterpolator(new DecelerateInterpolator());
            oa2.setInterpolator(new AccelerateDecelerateInterpolator());
            oa1.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    cardBack.setImageBitmap(card.front);
                    oa2.start();
                }
            });
            oa1.start();

    playerHands.get(activeHand).get(pos).setVisibility(View.VISIBLE);
    }


    public static void addToDealerDisplay(Card card) {
        if (dealerCardCount == 0) {
            dealerHand.get(0).setImageBitmap(card.back);
            dealerHand.get(0).setVisibility(View.VISIBLE);
            dealerCardCount++;
        } else {
            dealerHand.get(dealerCardCount).setImageBitmap(card.front);
            dealerHand.get(dealerCardCount).setVisibility(View.VISIBLE);
            dealerCardCount++;
        }
    }

    public static void Reset(Game game){
        game.Reset();
        activeHand = 0;
        blackjackView.setVisibility(View.INVISIBLE);
        split = false;
        for (int i = 0; i < 10; i++){
            for (int j = 0; j<5; j++){
                playerHands.get(j).get(i).setVisibility(View.INVISIBLE);
            }
        }
        for (int i = 0; i < 5; i ++){
            playerTotalViews.get(i).setText("");
        }
        for (int i = 0; i< 10; i++){
            dealerHand.get(i).setVisibility(View.INVISIBLE);
        }
        dealerHandCount.setText("");
        for (int i = 0; i< 5; i++){
            endHandStatus.get(i).setText("");
            endHandStatus.get(i).setVisibility(View.INVISIBLE);
        }
        for (int i = 0; i< 5; i++){
            betViews.get(i).setText("");
        }
        betView.setText("Bet:"+format.format(game.player.bet));

    }



    public static void UpdateDealer(int total){
        if (total ==1){
            total +=10;
        }
        dealerHandCount.setText(Integer.toString(total));
    }

    public static String endHandUpdate(Hand hand){
        if (hand.status == Hand.Status.Won){
            return "WIN";
        }
        if (hand.status == Hand.Status.Lost){
            return "LOST";
        }
        if (hand.status == Hand.Status.Push){
            return"PUSH";
        }
        if (hand.status == Hand.Status.Blackjack){
            blackjackView.setVisibility(View.VISIBLE);
        }
        if (hand.status == Hand.Status.Bust){
            return "BUST";
        }
        return "";
    }
    public static void chipBTNEnabler(Game game){
        if ((game.player.bankRoll- game.player.bet)<10000 ){
            bet10k.setEnabled(false);
        }
        if ((game.player.bankRoll- game.player.bet)<5000 ){
            bet5k.setEnabled(false);
        }
        if ((game.player.bankRoll- game.player.bet)<1000 ){
            bet1k.setEnabled(false);
        }
        if ((game.player.bankRoll- game.player.bet)<500 ){
            bet500.setEnabled(false);
        }
        if ((game.player.bankRoll- game.player.bet)<100 ){
            bet100.setEnabled(false);
        }
        if ((game.player.bankRoll- game.player.bet)<25 ){
            bet25.setEnabled(false);
        }
        if ((game.player.bankRoll- game.player.bet)<5 ){
            bet5.setEnabled(false);
        }
        if ((game.player.bankRoll- game.player.bet)<1 ){
            bet1.setEnabled(true);
        }
    }
    public static void chipDisabler(){
        bet1.setEnabled(false);
        bet5.setEnabled(false);
        bet25.setEnabled(false);
        bet100.setEnabled(false);
        bet500.setEnabled(false);
        bet1k.setEnabled(false);
        bet5k.setEnabled(false);
        bet10k.setEnabled(false);

    }
    public static void resetChipBTNS(){
        bet10k.setEnabled(true);
        bet5k.setEnabled(true);
        bet1k.setEnabled(true);
        bet500.setEnabled(true);
        bet100.setEnabled(true);
        bet25.setEnabled(true);
        bet5.setEnabled(true);
        bet1.setEnabled(true);
    }
    public static void cycleToNextActive(Game game){
        indicators.get(activeHand).setVisibility(View.INVISIBLE);
        while(game.player.handsList.get(activeHand).complete ==true && activeHand!=0){
            activeHand--;
        }
        indicators.get(activeHand).setVisibility(View.VISIBLE);
    }
    public static void doubleSplitActiveCheck(Game game){
        if ((game.player.bankRoll - game.player.bet) <0){
            doubleBTN.setEnabled(false);
            doubleBTN.setVisibility(View.INVISIBLE);
            splitBTN.setEnabled(false);
            splitBTN.setVisibility(View.INVISIBLE);
        }
        else {
            if (game.player.handsList.get(activeHand).cards.get(0) == game.player.handsList.get(activeHand).cards.get(1)) {
                splitBTN.setEnabled(true);
                splitBTN.setVisibility(View.VISIBLE);
            }
            if (game.player.handsList.get(activeHand).doubleAble == true){
                doubleBTN.setEnabled(true);
                doubleBTN.setVisibility(View.VISIBLE);
            }
            else{
                doubleBTN.setEnabled(false);
                doubleBTN.setVisibility(View.INVISIBLE);
            }
        }
    }
    public static void betSetter(Game game, double bet){
        game.player.bet += bet;
        betView.setText("Bet:"+format.format(game.player.bet));
        chipBTNEnabler(game);
        acceptBetBtn.setEnabled(true);
        acceptBetBtn.setVisibility(View.VISIBLE);
    }

    public static void EndHand(Game game){
        UpdateDealer(game.CountHand(game.dealer.hand));
        game.DealerFunction();
        if (game.dealer.blackjack == true && game.player.insurance==true){
            game.player.bankRoll += game.player.bet + (game.player.bet/2);
        }
        else {
            game.WinCheck();
            for (int i = 0; i < game.player.handsList.size(); i++) {
                endHandStatus.get(i).setText(endHandUpdate(game.player.handsList.get(i)));
                if (endHandUpdate(game.player.handsList.get(i))=="WIN"){
                    endHandStatus.get(i).setTextColor(Color.GREEN);
                }
                if (endHandUpdate(game.player.handsList.get(i))=="LOST"){
                    endHandStatus.get(i).setTextColor(Color.RED);
                }
                if (endHandUpdate(game.player.handsList.get(i))=="PUSH"){
                    endHandStatus.get(i).setTextColor(Color.YELLOW);
                }
                if (endHandUpdate(game.player.handsList.get(i))=="BUST"){
                    endHandStatus.get(i).setTextColor(Color.RED);
                }
                endHandStatus.get(i).setVisibility(View.VISIBLE);
            }
            dealerHand.get(0).setImageBitmap(game.dealer.hand.cards.get(0).front);
            dealBTN.setVisibility(View.VISIBLE);
            playerCash.setText(format.format(game.player.bankRoll));
            if (game.player.bankRoll <= 0) {

                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                game.player.bankRoll += 1000;
            }
        }
        playerCash.setText(format.format(game.player.bankRoll));
        dealBTN.setVisibility(View.INVISIBLE);
        hitBTN.setVisibility(View.INVISIBLE);
        standBTN.setVisibility(View.INVISIBLE);
        doubleBTN.setVisibility(View.INVISIBLE);
        splitBTN.setVisibility(View.INVISIBLE);

        betBTNPopup.setVisibility(View.VISIBLE);
        if ((game.player.bankRoll - game.player.bet) < 0) {
            repeatBetBTN.setEnabled(false);
            repeatBetBTN.setVisibility(View.INVISIBLE);
        }
        if (game.cardCount > (game.deck.playingDeck.size() * .8)) {
            game.newGame(game);
        }
    }
}
