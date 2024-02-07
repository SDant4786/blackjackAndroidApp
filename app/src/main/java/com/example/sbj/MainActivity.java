package com.example.sbj;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.text.NumberFormat;
import java.util.ArrayList;

import GameFiles.Card;
import GameFiles.Deck;
import GameFiles.Game;
import GameFiles.Hand;

import static GameFiles.Deck.cardBacks;
import static GameFiles.Game.backChoice;
import static GameFiles.mainFunctions.EndHand;
import static GameFiles.mainFunctions.Reset;
import static GameFiles.mainFunctions.betSetter;
import static GameFiles.mainFunctions.chipBTNEnabler;
import static GameFiles.mainFunctions.chipDisabler;
import static GameFiles.mainFunctions.cycleToNextActive;
import static GameFiles.mainFunctions.doubleSplitActiveCheck;
import static GameFiles.mainFunctions.resetChipBTNS;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<ArrayList<ImageView>> playerHands;
    public static ArrayList<ImageView> indicators;
    public static ArrayList<TextView> playerTotalViews;
    public static ArrayList<TextView> endHandStatus;
    public static ArrayList<TextView> betViews;
    public static int playerCardCount;
    public static ArrayList<ImageView> dealerHand;
    public static int dealerCardCount;
    public static Button hitBTN, splitBTN, doubleBTN, standBTN, dealBTN;
    public static Button bet1, bet5, bet25, bet100, bet500, bet1k, bet5k, bet10k;
    public static Button repeatBetBTN, newBetBtn, acceptBetBtn, resetBetBtn;
    public static ConstraintLayout betPopup, betBTNPopup, mainDisplay, insrancePopUp;
    public static TextView hand1Count, dealerHandCount, playerBet, playerCash, blackjackView, betView,
            mainScreenBet, mainBetView, betView2, betView3, betView4, betView5;
    public static int activeHand;
    public static boolean split;
    public static ImageView cardBack, settingsBTN, helpBTN, closeBTN, mainScreen;

    public static ConstraintLayout dealerLayout;
    private AdView topAd, bottomAd;
    public static InterstitialAd mInterstitialAd;
    public static NumberFormat format;
    MediaPlayer mediaPlayer;
    public static boolean insCheck;
    public static LinearLayout deckBacks, backgrounds;
    public static CheckBox sound, fx;

    @Override
    protected void onPause() {
        super.onPause();
        //mediaPlayer.stop();
        //mediaPlayer.release();
    }

    @Override
    public void onResume() {
        super.onResume();
        //mediaPlayer.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Game game = new Game(this,6);
        boolean firstLoad = true;
        //mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.game_music2);
        //mediaPlayer.start();
        format = NumberFormat.getCurrencyInstance();
        cardBack = (ImageView) findViewById(R.id.backOfCards);

        deckBacks = findViewById(R.id.deckLinearView);
        backgrounds = findViewById(R.id.backgroundChoiceViewer);
        insrancePopUp = (ConstraintLayout) findViewById(R.id.insurancePopUp);
        insCheck = false;
        mainScreen = findViewById(R.id.backGroundImage);
        closeBTN = (ImageView) findViewById(R.id.close);
        settingsBTN =  findViewById(R.id.settingsBTN);
        helpBTN = findViewById(R.id.helpBTN);
        bet1 = (Button) findViewById(R.id.betOneBTN);
        bet5 = (Button) findViewById(R.id.betFiveBTN);
        bet25 = (Button) findViewById(R.id.bet25BTN);
        bet100 = (Button) findViewById(R.id.bet100BTN);
        bet500 = (Button) findViewById(R.id.bet500BTN);
        bet1k = (Button) findViewById(R.id.bet1kBTN);
        bet5k = (Button) findViewById(R.id.bet5kBTN);
        bet10k = (Button) findViewById(R.id.bet10KBTN);
        repeatBetBTN = (Button) findViewById(R.id.repeatBetBTN);
        newBetBtn = (Button) findViewById(R.id.newBetBTN);
        betPopup = (ConstraintLayout) findViewById(R.id.betPopup);
        betBTNPopup = (ConstraintLayout) findViewById(R.id.betPrompt);
        acceptBetBtn = (Button) findViewById(R.id.acceptBTN);
        resetBetBtn = (Button) findViewById(R.id.resetBetBTn);
        betView = (TextView) findViewById(R.id.betView);
        mainScreenBet = (TextView) findViewById(R.id.mainScreenBet);
        mainDisplay = (ConstraintLayout) findViewById(R.id.mainButtonLayout);
        mainBetView = (TextView) findViewById(R.id.betView2);
        final ConstraintLayout chipContainer = (ConstraintLayout) findViewById(R.id.constraintLayout3);

        playerTotalViews = new ArrayList<>();
        playerHands = new ArrayList<>();
        endHandStatus = new ArrayList<>();
        indicators = new ArrayList<>();
        dealerLayout = (ConstraintLayout) findViewById(R.id.dealerHandContainer);
        betViews = new ArrayList<>();


        hitBTN = findViewById(R.id.hitBTN);
        splitBTN = findViewById(R.id.splitBTN);
        splitBTN.setEnabled(false);
        doubleBTN = findViewById(R.id.doubleBTN);
        standBTN = findViewById(R.id.standBTN);
        dealBTN = findViewById(R.id.dealBTN);
        topAd = findViewById(R.id.topAd);
        bottomAd = findViewById(R.id.bottomAd);
        MobileAds.initialize(this, "cca-app-pub-3940256099942544/6300978111");
        blackjackView = (TextView) findViewById(R.id.blackjackView);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                AdRequest adRequest = new AdRequest.Builder().build();
                mInterstitialAd.loadAd(adRequest);
            }
        });

        hand1Count = (TextView) findViewById(R.id.totalView);
        dealerHandCount = (TextView) findViewById(R.id.dealertotalView);
        dealerHandCount = (TextView) findViewById(R.id.dealertotalView);
        playerCash = findViewById(R.id.cashView);

        populatePlayerCardArray();
        populateDealerCardArray();
        playerCardCount = 0;
        dealerCardCount = 0;
        activeHand = 0;

        hitBTN.setEnabled(false);
        standBTN.setEnabled(false);
        doubleBTN.setEnabled(false);
        splitBTN.setEnabled(false);
        dealBTN.setEnabled(false);
        dealBTN.setVisibility(View.INVISIBLE);
        hitBTN.setVisibility(View.INVISIBLE);
        standBTN.setVisibility(View.INVISIBLE);
        doubleBTN.setVisibility(View.INVISIBLE);
        splitBTN.setVisibility(View.INVISIBLE);
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.temp);


        betBTNPopup.setVisibility(View.VISIBLE);
        if (firstLoad == true) {
            repeatBetBTN.setVisibility(View.INVISIBLE);
            chipDisabler();
            firstLoad = false;
            //dialog.show();
        }
        settingsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateScrollViews(game,getApplicationContext());

                ConstraintLayout layout = findViewById(R.id.settingsPopUp);
                layout.setVisibility(View.VISIBLE);
            }
        });
        closeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConstraintLayout layout = findViewById(R.id.settingsPopUp);
                layout.setVisibility(View.INVISIBLE);
            }
        });

        cardBack.setImageBitmap(cardBacks.get(backChoice));
        playerCash.setText(format.format(game.player.bankRoll));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        AdRequest adRequest = new AdRequest.Builder().build();
        AdRequest adRequest2 = new AdRequest.Builder().build();
        topAd.loadAd(adRequest);
        bottomAd.loadAd(adRequest2);

        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setContentView(R.layout.temp);
        alertDialog.setMessage("Insurance?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                game.player.insurance = true;
                game.player.bankRoll -= (game.player.bet / 2);
                playerCash.setText(format.format(game.player.bankRoll));
                if (game.FirstDealBlackjackCheck() == true) {
                    game.player.handsList.get(activeHand).complete = true;
                    game.player.handsList.get(activeHand).active = false;
                    EndHand(game);
                } else
                    dealFuncion(game);


                alertDialog.dismiss();

            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (game.FirstDealBlackjackCheck() == true) {
                    game.player.handsList.get(activeHand).complete = true;
                    game.player.handsList.get(activeHand).active = false;
                    EndHand(game);
                } else
                    dealFuncion(game);
                alertDialog.dismiss();
            }
        });
        repeatBetBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dealBTN.setEnabled(true);
                betPopup.setVisibility(View.INVISIBLE);
                betPopup.setEnabled(false);
                betBTNPopup.setVisibility(View.INVISIBLE);
                dealBTN.setVisibility(View.VISIBLE);
                mainDisplay.setVisibility(View.VISIBLE);
            }
        });
        newBetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.player.bet = 0;
                betView.setText("Bet:" + format.format(0));

                dealBTN.setEnabled(false);
                hitBTN.setEnabled(false);
                standBTN.setEnabled(false);
                splitBTN.setEnabled(false);
                doubleBTN.setEnabled(false);
                acceptBetBtn.setVisibility(View.INVISIBLE);
                betBTNPopup.setVisibility(View.INVISIBLE);
                splitBTN.setVisibility(View.INVISIBLE);


                betPopup.setVisibility(View.VISIBLE);
                resetChipBTNS();
                chipBTNEnabler(game);
                acceptBetBtn.setEnabled(false);
            }
        });
        resetBetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.player.bet = 0;
                betView.setText("Bet:" + format.format(game.player.bet));
                resetChipBTNS();
                chipBTNEnabler(game);
                acceptBetBtn.setEnabled(false);
                acceptBetBtn.setVisibility(View.INVISIBLE);
            }
        });
        acceptBetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dealBTN.setEnabled(true);
                betPopup.setVisibility(View.INVISIBLE);
                betPopup.setEnabled(false);
                dealBTN.setVisibility(View.VISIBLE);
                resetChipBTNS();
                chipDisabler();
                repeatBetBTN.setEnabled(true);
                repeatBetBTN.setVisibility(View.VISIBLE);
            }
        });
        bet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(game.player.bankRoll>=1) {
                    betSetter(game, 1);
                }else if (game.player.bankRoll<1) {
                    betSetter(game, game.player.bankRoll);
                }
            }
        });
        bet5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betSetter(game, 5);
            }
        });
        bet25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betSetter(game, 25);
            }
        });
        bet100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betSetter(game, 100);
            }
        });
        bet500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betSetter(game, 500);
            }
        });
        bet1k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betSetter(game, 1000);
            }
        });
        bet5k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betSetter(game, 5000);
            }
        });
        bet10k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betSetter(game, 10000);

            }
        });
        dealBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reset(game);
                ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) dealerLayout.getLayoutParams();
                params.verticalBias = .3f;
                dealerLayout.setLayoutParams(params);
                game.Deal();
                indicators.get(activeHand).setVisibility(View.VISIBLE);
                game.CountHand(activeHand);
                if (game.dealer.hand.cards.get(1).name == Card.Name.Ace && game.player.bankRoll - (game.player.bet / 2) >= 0
                        && game.player.handsList.get(0).total != 21) {
                    alertDialog.show();
                } else
                    dealFuncion(game);
            }
        });

        hitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView temp = findViewById(R.id.handStatus1);
                game.AddToPlayersHand(activeHand);
                splitBTN.setEnabled(false);
                splitBTN.setVisibility(View.INVISIBLE);
                playerTotalViews.get(activeHand).setText(Integer.toString(game.CountHand(activeHand)));
                game.player.handsList.get(activeHand).doubleAble = false;
                if (game.player.handsList.get(activeHand).status == Hand.Status.Bust) {
                    game.player.handsList.get(activeHand).complete = true;
                    game.player.handsList.get(activeHand).active = false;
                    endHandStatus.get(activeHand).setText("BUST");
                    endHandStatus.get(activeHand).setTextColor(Color.RED);
                    endHandStatus.get(activeHand).setVisibility(View.VISIBLE);
                }

                if (game.player.handsList.get(activeHand).total == 21) {
                    game.player.handsList.get(activeHand).complete = true;
                    game.player.handsList.get(activeHand).active = false;
                }
                if (game.player.handsList.get(activeHand).complete == true) {
                    if (split == false && activeHand == 0) {
                        EndHand(game);
                    } else if (split == true && activeHand == 0) {
                        EndHand(game);
                    } else {
                        cycleToNextActive(game);
                    }

                }
                doubleSplitActiveCheck(game);
            }

        });
        standBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.player.handsList.get(activeHand).doubleAble = false;
                game.player.handsList.get(activeHand).complete = true;
                game.player.handsList.get(activeHand).active = false;

                if (split == false || activeHand == 0) {

                    EndHand(game);
                } else {
                    cycleToNextActive(game);
                    doubleSplitActiveCheck(game);
                }

            }
        });
        doubleBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.AddToPlayersHand(activeHand);
                game.player.handsList.get(activeHand).doubleAble = false;
                game.player.handsList.get(activeHand).complete = true;
                playerTotalViews.get(activeHand).setText(Integer.toString(game.CountHand(activeHand)));
                game.player.handsList.get(activeHand).doubled = true;
                if (split == false || activeHand == 0) {
                    EndHand(game);
                } else {
                    cycleToNextActive(game);
                }
                doubleSplitActiveCheck(game);
                playerCash.setText(format.format(game.player.bankRoll - game.player.bet));
                betViews.get(activeHand).setText("Bet:" + format.format(game.player.bet * 2));
            }
        });
        splitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                split = true;
                game.SplitHand(activeHand);
                indicators.get(activeHand).setVisibility(View.INVISIBLE);
                activeHand = game.player.handsList.size() - 1;
                indicators.get(activeHand).setVisibility(View.VISIBLE);
                betViews.get(activeHand).setText("Bet:" + format.format(game.player.bet));
                if (game.player.handsList.get(activeHand).cards.get(0).name == game.player.handsList.get(activeHand).cards.get(1).name) {
                    splitBTN.setEnabled(true);
                    splitBTN.setVisibility(View.VISIBLE);
                } else {
                    splitBTN.setEnabled(false);
                    splitBTN.setVisibility(View.INVISIBLE);
                }
                if (game.player.handsList.size() == 5) {
                    splitBTN.setEnabled(false);
                    splitBTN.setVisibility(View.INVISIBLE);
                }
                if (game.player.handsList.size() > 2) {
                    ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) dealerLayout.getLayoutParams();
                    params.verticalBias = .07f;
                    dealerLayout.setLayoutParams(params);
                }
                if ((game.player.bankRoll - game.player.bet) < 0) {
                    doubleBTN.setEnabled(false);
                    doubleBTN.setVisibility(View.INVISIBLE);
                    splitBTN.setEnabled(false);
                    splitBTN.setVisibility(View.INVISIBLE);
                }
                playerCash.setText(format.format(game.player.bankRoll));
            }
        });
    }


    public static void dealFuncion(Game game) {
        if (game.FirstDealBlackjackCheck() == true) {
            game.player.handsList.get(activeHand).complete = true;
            game.player.handsList.get(activeHand).active = false;
            EndHand(game);
        } else {

            if (game.player.handsList.get(activeHand).cards.get(0).name == game.player.handsList.get(activeHand).cards.get(1).name) {
                if ((game.player.bankRoll - game.player.bet) < 0) {
                    splitBTN.setEnabled(false);
                    splitBTN.setVisibility(View.INVISIBLE);
                } else {
                    splitBTN.setEnabled(true);
                    splitBTN.setVisibility(View.VISIBLE);
                }
            }
            playerCash.setText(format.format(game.player.bankRoll));
            dealBTN.setEnabled(false);
            dealBTN.setVisibility(View.INVISIBLE);
            hand1Count.setText(Integer.toString(game.CountHand(activeHand)));
            hitBTN.setVisibility(View.VISIBLE);
            standBTN.setVisibility(View.VISIBLE);
            doubleBTN.setVisibility(View.VISIBLE);

            hitBTN.setEnabled(true);
            standBTN.setEnabled(true);
            if ((game.player.bankRoll - game.player.bet) < 0) {
                doubleBTN.setEnabled(false);
                doubleBTN.setVisibility(View.INVISIBLE);
            } else {
                doubleBTN.setEnabled(true);
                doubleBTN.setVisibility(View.VISIBLE);
            }
        }
    }

    private void populatePlayerCardArray() {

        ArrayList<ImageView> tempPlayerHand = new ArrayList<>();
        ImageView one = findViewById(R.id.card1View);
        ImageView two = findViewById(R.id.card2View);
        ImageView three = findViewById(R.id.card3View);
        ImageView four = findViewById(R.id.card4View);
        ImageView five = findViewById(R.id.card5View);
        ImageView six = findViewById(R.id.card6View);
        ImageView seven = findViewById(R.id.card7View);
        ImageView eight = findViewById(R.id.card8View);
        ImageView nine = findViewById(R.id.card9View);
        ImageView ten = findViewById(R.id.card10view);
        TextView total = findViewById(R.id.totalView);
        tempPlayerHand.add(one);
        tempPlayerHand.add(two);
        tempPlayerHand.add(three);
        tempPlayerHand.add(four);
        tempPlayerHand.add(five);
        tempPlayerHand.add(six);
        tempPlayerHand.add(seven);
        tempPlayerHand.add(eight);
        tempPlayerHand.add(nine);
        tempPlayerHand.add(ten);
        TextView end1 = findViewById(R.id.handStatus1);
        betViews.add(betView);
        ImageView indicator1 = findViewById(R.id.indicator1);
        indicator1.setVisibility(View.INVISIBLE);
        indicators.add(indicator1);
        endHandStatus.add(end1);
        playerTotalViews.add(total);
        playerHands.add(tempPlayerHand);

        ArrayList<ImageView> tempPlayerHand2 = new ArrayList<>();
        tempPlayerHand2.add((ImageView) findViewById(R.id.card1View2));
        tempPlayerHand2.add((ImageView) findViewById(R.id.card2View2));
        tempPlayerHand2.add((ImageView) findViewById(R.id.card3View2));
        tempPlayerHand2.add((ImageView) findViewById(R.id.card4View2));
        tempPlayerHand2.add((ImageView) findViewById(R.id.card5View2));
        tempPlayerHand2.add((ImageView) findViewById(R.id.card6View2));
        tempPlayerHand2.add((ImageView) findViewById(R.id.card7View2));
        tempPlayerHand2.add((ImageView) findViewById(R.id.card8View2));
        tempPlayerHand2.add((ImageView) findViewById(R.id.card9View2));
        tempPlayerHand2.add((ImageView) findViewById(R.id.card10view2));
        TextView end2 = findViewById(R.id.handStatus2);
        betView2 = (TextView) findViewById(R.id.betView2);
        betViews.add(betView2);
        ImageView indicator2 = findViewById(R.id.indicator2);
        indicator2.setVisibility(View.INVISIBLE);
        indicators.add(indicator2);
        endHandStatus.add(end2);
        playerTotalViews.add((TextView) findViewById(R.id.totalView2));
        playerHands.add(tempPlayerHand2);

        ArrayList<ImageView> tempPlayerHand3 = new ArrayList<>();
        tempPlayerHand3.add((ImageView) findViewById(R.id.card1View3));
        tempPlayerHand3.add((ImageView) findViewById(R.id.card2View3));
        tempPlayerHand3.add((ImageView) findViewById(R.id.card3View3));
        tempPlayerHand3.add((ImageView) findViewById(R.id.card4View3));
        tempPlayerHand3.add((ImageView) findViewById(R.id.card5View3));
        tempPlayerHand3.add((ImageView) findViewById(R.id.card6View3));
        tempPlayerHand3.add((ImageView) findViewById(R.id.card7View3));
        tempPlayerHand3.add((ImageView) findViewById(R.id.card8View3));
        tempPlayerHand3.add((ImageView) findViewById(R.id.card9View3));
        tempPlayerHand3.add((ImageView) findViewById(R.id.card10view3));
        TextView end3 = findViewById(R.id.handStatus3);
        betView3 = (TextView) findViewById(R.id.betView3);
        betViews.add(betView3);
        ImageView indicator3 = findViewById(R.id.indicator3);
        indicator3.setVisibility(View.INVISIBLE);
        indicators.add(indicator3);
        endHandStatus.add(end3);
        playerTotalViews.add((TextView) findViewById(R.id.totalView3));
        playerHands.add(tempPlayerHand3);

        ArrayList<ImageView> tempPlayerHand4 = new ArrayList<>();
        tempPlayerHand4.add((ImageView) findViewById(R.id.card1View4));
        tempPlayerHand4.add((ImageView) findViewById(R.id.card2View4));
        tempPlayerHand4.add((ImageView) findViewById(R.id.card3View4));
        tempPlayerHand4.add((ImageView) findViewById(R.id.card4View4));
        tempPlayerHand4.add((ImageView) findViewById(R.id.card5View4));
        tempPlayerHand4.add((ImageView) findViewById(R.id.card6View4));
        tempPlayerHand4.add((ImageView) findViewById(R.id.card7View4));
        tempPlayerHand4.add((ImageView) findViewById(R.id.card8View4));
        tempPlayerHand4.add((ImageView) findViewById(R.id.card9View4));
        tempPlayerHand4.add((ImageView) findViewById(R.id.card10view4));
        TextView end4 = findViewById(R.id.handStatus4);
        betView4 = (TextView) findViewById(R.id.betView4);
        betViews.add(betView4);
        ImageView indicator4 = findViewById(R.id.indicator4);
        indicator4.setVisibility(View.INVISIBLE);
        indicators.add(indicator4);
        endHandStatus.add(end4);
        playerTotalViews.add((TextView) findViewById(R.id.totalView4));
        playerHands.add(tempPlayerHand4);

        ArrayList<ImageView> tempPlayerHand5 = new ArrayList<>();
        tempPlayerHand5.add((ImageView) findViewById(R.id.card1View5));
        tempPlayerHand5.add((ImageView) findViewById(R.id.card2View5));
        tempPlayerHand5.add((ImageView) findViewById(R.id.card3View5));
        tempPlayerHand5.add((ImageView) findViewById(R.id.card4View5));
        tempPlayerHand5.add((ImageView) findViewById(R.id.card5View5));
        tempPlayerHand5.add((ImageView) findViewById(R.id.card6View5));
        tempPlayerHand5.add((ImageView) findViewById(R.id.card7View5));
        tempPlayerHand5.add((ImageView) findViewById(R.id.card8View5));
        tempPlayerHand5.add((ImageView) findViewById(R.id.card9View5));
        tempPlayerHand5.add((ImageView) findViewById(R.id.card10view5));
        TextView end5 = findViewById(R.id.handStatus5);
        betView5 = (TextView) findViewById(R.id.betView5);
        betViews.add(betView5);
        ImageView indicator5 = findViewById(R.id.indicator5);
        indicator5.setVisibility(View.INVISIBLE);
        indicators.add(indicator5);
        endHandStatus.add(end5);
        playerTotalViews.add((TextView) findViewById(R.id.totalView5));
        playerHands.add(tempPlayerHand5);

    }

    private void populateDealerCardArray() {
        dealerHand = new ArrayList<>();
        ImageView one = (ImageView) findViewById(R.id.dealercard1View);
        ImageView two = (ImageView) findViewById(R.id.dealercard2View);
        ImageView three = (ImageView) findViewById(R.id.dealercard3View);
        ImageView four = (ImageView) findViewById(R.id.dealercard4View);
        ImageView five = (ImageView) findViewById(R.id.dealercard5View);
        ImageView six = (ImageView) findViewById(R.id.dealercard6View);
        ImageView seven = (ImageView) findViewById(R.id.dealercard7View);
        ImageView eight = (ImageView) findViewById(R.id.dealercard8View);
        ImageView nine = (ImageView) findViewById(R.id.dealercard9View);
        ImageView ten = (ImageView) findViewById(R.id.dealercard10view);
        dealerHand.add(one);
        dealerHand.add(two);
        dealerHand.add(three);
        dealerHand.add(four);
        dealerHand.add(five);
        dealerHand.add(six);
        dealerHand.add(seven);
        dealerHand.add(eight);
        dealerHand.add(nine);
        dealerHand.add(ten);

    }
    public static void populateScrollViews(final Game game, final Context context){
        for (int i = 0; i < cardBacks.size(); i++){
            final ImageView image = new ImageView(context);
            image.setImageBitmap(cardBacks.get(i));
            image.setId(i);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    game.deck = new Deck(context, game.decks, image.getId());
                    cardBack.setImageBitmap(cardBacks.get(image.getId()));
                }
            });
            deckBacks.addView(image, i);
        }
        for (int i = 0; i< game.backgrounds.size(); i++){
            final ImageView image = new ImageView(context);
            image.setImageBitmap(game.backgrounds.get(i));
            image.setId(i);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BitmapDrawable background = new BitmapDrawable(game.backgrounds.get(image.getId()));
                    mainScreen.setBackgroundDrawable(background);
                }
            });
            backgrounds.addView(image, i);
        }
    }
}
