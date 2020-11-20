package io.zipcoder.casino.Games.CardGames;

import io.zipcoder.casino.GameSupplies.Card;
import io.zipcoder.casino.Games.GamblingGameInterface;
import io.zipcoder.casino.Players.BlackJackPlayer;
import io.zipcoder.casino.utilities.Input;
import io.zipcoder.casino.utilities.Output;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BlackJack extends CardGames implements GamblingGameInterface {

    Input input = new Input(System.in, System.out);
    Scanner scanner = new Scanner(System.in);

    private BlackJackPlayer blackJackPlayer;
    private ArrayList<Card> dealersHand;
    private Integer playerTotal = 0;
    private Integer dealerTotal = 0;
    private boolean powerOn = true;
    private boolean hitCheck = true;
    private double currentWager = 0;


    public BlackJack(BlackJackPlayer blackJackPlayer) {
        this.blackJackPlayer = blackJackPlayer;
        this.dealersHand = new ArrayList<Card>();
    }

// --runs the game------------------------------------------------------------------------------------------------------
    public boolean playGame() {

        boolean quit = false;

        Output.printToScreen("\nWelcome to Blackjack " + blackJackPlayer.getName() + "!!!");

        while(powerOn) {
            Output.printToScreen("\nPlease select from the following options:\n" +
                    "1 - Play a game of Blackjack\n" +
                    "2 - See how much is in your wallet\n" +
                    "3 - Quit Blackjack\n" +
                    "---------------------------------\n");

            int input1 = input.getIntegerInput("Enter choice here -> ");

            try {
                switch (input1) {
                    case 1:
                        clearHands();
                        takeBet();
                        dealHand(2);
                        handSum(); // BlackJackPlayer now has a hand of cards, need to evaluate the hand and then decide what to do
                        firstShow(); // Show results of the first round of cards
                        playerHitOrStand();

                        // ***** if player game state is under run dealer Hit or Stand
                        if (playerStateOfGame().equals(stateOfGame.UNDER)) {
                            dealerHitOrStand();
                            if(dealerStateOfGame().equals(stateOfGame.UNDER)) {
                                winConditions(); // evaluate dealer and blackjack player hands
                            }
                        }

                        break;
                    case 2:
                        Output.printToScreen("Your current wallet balance is: " + blackJackPlayer.getWallet());
                        break;
                    case 3:
                        Output.printToScreen("\n" + "Thank you for visiting!");
                        powerOn = false;
                        return quit = true;
                    default:
                        Output.printToScreen("\n" + "Incorrect option chosen, please choose one of the menu options below.");
                        break;
                }
            } catch (InputMismatchException e) {
                Output.printToScreen("\n" + "Incorrect option chosen, please choose one of the menu options below.");
            }

        }

        return quit;

    }

// --shows result of first set of cards that were dealt, dealer shows one card------------------------------------------

    public void handSum() {
        playerTotal += sum(blackJackPlayer.getBlackJackPlayerHand());
        dealerTotal += sum(getDealersHand());
    }

    public int sum(ArrayList<Card> hand) {
        Integer count = 0;

        for (Card card : hand) {
            count += card.getValue().getSecondaryValue();
        }
        return count;
    }

    public void firstShow() { // see what your hand totals and see the dealer's face up card
        Output.printToScreen("\nPLAYER: " + displayHand(blackJackPlayer));
        Output.printToScreen("\nPLAYER: " + playerTotal);
        Output.printToScreen("\n-----------------");
        Output.printToScreen("\nDEALER: " + getDealersHand().get(0) + " and ********** of **********");
        Output.printToScreen("\nDEALER: " + getDealersHand().get(0).getValue().getSecondaryValue());
    }

    public String displayHand(BlackJackPlayer blackJackPlayer) { // gives nice output to the console
        String answer = "";
        for (int i = 0; i < blackJackPlayer.getBlackJackPlayerHand().size(); i++) {
                    Card card = blackJackPlayer.getBlackJackPlayerHand().get(i);
                    answer += card.toString();
            }
        return answer;
    }

    public String displayDealersHand(ArrayList<Card> dealersHand) {
        String answer = "";
        for (int i = 0; i < dealersHand.size(); i++) {
            Card card = dealersHand.get(i);
            answer += card.toString();
        }
        return answer;
    }


// --based on first set of cards player and dealer decide what to do----------------------------------------------------
    public void playerHitOrStand() { // player hit or stand
        if (playerStateOfGame().equals(stateOfGame.UNDER)) {
            Output.printToScreen("\nDo you want to 'hit' or 'stand'?");
            String answer = input.getStringInput("Type answer -> ");

            if (answer.equals("hit")) {
                blackJackPlayer.getBlackJackPlayerHand().add(getDeck().dealCard());
                playerTotal = playerTotal + blackJackPlayer.getBlackJackPlayerHand().get(blackJackPlayer.getBlackJackPlayerHand().size() - 1).getValue().getSecondaryValue();
                Output.printToScreen("\nPLAYER:" + displayHand(blackJackPlayer));
                Output.printToScreen("\nPLAYER: " + playerTotal);
                playerHitOrStand();
            }

        } else if (playerStateOfGame().equals(stateOfGame.BLACKJACK)) {
            Output.printToScreen("\nPlayer wins with blackjack!");
            blackJackPlayer.setWallet(blackJackPlayer.getWallet() + (2 * getCurrentWager()));
            printState();
        } else {
            Output.printToScreen("\nPlayer loses...");
            printState();
        }
    }

    public stateOfGame playerStateOfGame() {
        if (sum(blackJackPlayer.getBlackJackPlayerHand()) > 21) {
            return stateOfGame.BUST;
        } else if (sum(blackJackPlayer.getBlackJackPlayerHand()) < 21) {
            return stateOfGame.UNDER;
        } else {
            return stateOfGame.BLACKJACK;
        }
    }

    public void dealerHitOrStand() { // dealer hit or stand
        if (dealerStateOfGame().equals(stateOfGame.UNDER)) {
            int dealerCount = dealerTotal + dealersHand.get(dealersHand.size() -1).getValue().getSecondaryValue();
            if (dealerCount < 17) {
                dealersHand.add(getDeck().dealCard());
                dealerTotal = dealerTotal + dealersHand.get(dealersHand.size() -1).getValue().getSecondaryValue();;
                dealerHitOrStand();
            }
        } else if (dealerStateOfGame().equals(stateOfGame.BLACKJACK)) {
            Output.printToScreen("\nDealer wins!");
            printState();
        } else {
            Output.printToScreen("\nDealer loses...");
            printState();
        }
    }

    public stateOfGame dealerStateOfGame() {
        if (sum(dealersHand) > 21) {
            return stateOfGame.BUST;
        } else if (sum(dealersHand) < 21) {
            return stateOfGame.UNDER;
        } else {
            return stateOfGame.BLACKJACK;
        }
    }

    public enum stateOfGame {
        UNDER, BLACKJACK, BUST
    }

// --evaluates non-blackjack scenario-----------------------------------------------------------------------------------
    public void winConditions(){
        if(dealerTotal > playerTotal){
            Output.printToScreen("\nDEALER WINS");
            printState();
                }
        else if(dealerTotal < playerTotal){
            Output.printToScreen("\nPLAYER WINS");
            blackJackPlayer.setWallet(blackJackPlayer.getWallet() + (2 * getCurrentWager()));
            printState();
            }
        else if(dealerTotal.equals(playerTotal)){
            Output.printToScreen("\nPLAYER WINS");
            blackJackPlayer.setWallet(blackJackPlayer.getWallet() + (2 * getCurrentWager()));
            printState();
            }
    }

    public void printState() {
        Output.printToScreen("\n************* GAME RESULTS *************");
        Output.printToScreen("\nPLAYER: " + displayHand(blackJackPlayer));
        Output.printToScreen("\nPLAYER: " + playerTotal);
        Output.printToScreen("\n-----------------");
        Output.printToScreen("\nDEALER: " + displayDealersHand(dealersHand));
        Output.printToScreen("\nDEALER: " + dealerTotal);
        Output.printToScreen("\n****************************************");
    }

// --clear hand---------------------------------------------------------------------------------------------------------
    public void clearHands(){
        getPlayersHand().clear();
        getDealersHand().clear();
        playerTotal = 0;
        dealerTotal = 0;
    }

// --take player bet, need to tie into interface method below-----------------------------------------------------------
    public void takeBet() {
        double betInput = 0.0;
        betInput = input.getDoubleInput("\nEnter the amount you would like to bet here -> ");
        if (betInput > blackJackPlayer.getWallet()) {
            Output.printToScreen("\nYou do not have enough to cover that bet.");
            takeBet();
        } else {
            Output.printToScreen("Your bet is: $" + betInput);
            blackJackPlayer.setWallet(blackJackPlayer.getWallet() - betInput);
            setCurrentWager(betInput);
        }
    }

    public double bet() {
        return 0;
    }

// --deals cards at beginning of the game-------------------------------------------------------------------------------
    public void dealHand(int howManyCards) {
        for (int i = 0; i < howManyCards; i++) {
            dealersHand.add(getDeck().dealCard());
            blackJackPlayer.getBlackJackPlayerHand().add(getDeck().dealCard());
        }
    }

// --need to incorporate this method------------------------------------------------------------------------------------
    @Override
    public boolean endGame() {
        return false;
    }

// ---------------------------------------------------------------------------------------------------------------------
    public void setCurrentWager(double wager) {
        this.currentWager = wager;
    }

    public double getCurrentWager() {
        return this.currentWager;
    }

    public ArrayList<Card> getDealersHand() {
        return dealersHand;
    }

    public ArrayList<Card> getPlayersHand() {
        return blackJackPlayer.getBlackJackPlayerHand();
    }


    public double payOut() {
        return 0;
    }

// ---------------------------------------------------------------------------------------------------------------------
    public boolean isPowerOn() {
        return powerOn;
    }

    public void setPowerOn(boolean powerOn) {
        this.powerOn = powerOn;
    }
}




// ---------------------------------------------------------------------------------------------------------------------
//    public Integer getPlayerTotal() {
//        return playerTotal;
//    }
//
//    public void setPlayerTotal(Integer playerTotal) {
//        this.playerTotal = playerTotal;
//    }
//
//    public Integer getDealerTotal() {
//        return dealerTotal;
//    }
//
//    public void setDealerTotal(Integer dealerTotal) {
//        this.dealerTotal = dealerTotal;
//    }
//    public boolean isHitCheck() {
//        return hitCheck;
//    }
//
//    public void setHitCheck(boolean hitCheck) {
//        this.hitCheck = hitCheck;
//    }
//    public void winConditions() {
//        if (playerTotal > 21) {
//            Output.printToScreen("DEALER WINS");
//            Output.printToScreen("PLAYER BUSTS");
//
//        }
//    }