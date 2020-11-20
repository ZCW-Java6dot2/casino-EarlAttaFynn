package io.zipcoder.casino.Games.CardGames;
import io.zipcoder.casino.GameSupplies.Card;
import io.zipcoder.casino.GameSupplies.DeckOfCards;
import io.zipcoder.casino.GameSupplies.Rank;
import io.zipcoder.casino.GameSupplies.Suit;
import io.zipcoder.casino.Games.GameInterface;
import io.zipcoder.casino.Players.GoFishPlayer;
import io.zipcoder.casino.Players.Player;
import io.zipcoder.casino.utilities.Input;
import io.zipcoder.casino.utilities.Output;

import java.util.*;

public class GoFish extends CardGames implements GameInterface {

    private GoFishPlayer player;
    private Player dealer;
    private final Stack<Card> deck;
    private final ArrayList<Card> playerHand;
    private final ArrayList<Card> dealerHand;
    private Integer playerBooks = 0;
    private Integer dealerBooks = 0;
    private final Scanner in = new Scanner(System.in);
    public Scanner hold = new Scanner(System.in);
    private boolean gameOver;
    private boolean running = true;
    private Input input = new Input(System.in, System.out);
    private boolean playerTurn;
    private int caughtFish;
    private Card drawnCard;


    public GoFish (GoFishPlayer player) {
        this.player = player;
        this.dealer = new Player(10000000.00, "Dealer");
        this.deck = goFishDeck();
        this.dealerHand = new ArrayList<Card>();
        this.playerHand = this.player.getHand();
        this.gameOver = false;
    }

    public Stack<Card> goFishDeck (){
        Stack<Card> goFishDeck = new Stack<Card>();
        for (Rank r : Rank.values()) {
            for (Suit s : Suit.values()) {
                Card temp = new Card(s, r);
                goFishDeck.push(temp);
            }
        }
        Collections.shuffle(goFishDeck);
        return goFishDeck;
    }



    public boolean playGame () {
        boolean quit = false;

        while (running) {
            preamble();

        }
        return quit;
    }

    public boolean preamble () {
        boolean quit = false;
        int choice;

        Output.printToScreen(
                "\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~Welcome to the Go Fish Table~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Ya Simple Bitch!!~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Output.printToScreen("\n" +
                "************************************MENU********************************************\n\n" +
                "1: Play Go Fish!\n2: Back to Games Menu");

        choice = in.nextInt();

        while (choice < 1 || choice > 2) {
            Output.printToScreen("\n\nPlease select one of the given options.\n" +
                    "\n~~~~~~~~~~~~~~~~~~~~Menu~~~~~~~~~~~~~~~~~~~\n\n" +
                    "1: Play Go Fish!\n2: Back to Games Menu.");
            choice = in.nextInt();
        }

        try {
            switch (choice) {
                case 1:
                    startingDeal();
                    coinToss();
                    break;
                case 2:
                    return quit = true;

            }
        }
        catch (InputMismatchException e) {
            in.next();
            System.out.println("\n" + "Sorry! Please try again and choose a valid option.");
        }
        return quit = true;
    }

    public void startingDeal() {
        //Deal starting hands, 7 cards each.
        for (int i = 0; i < 7; i++) {
            draw(playerHand);
            draw(dealerHand);
        }

    }

    public void coinToss() {

        int toss = (int) (Math.floor(Math.random()*2));
        int choice = input.getIntegerInput("\n\nChoose heads or tails:\n" +
                "1: Heads\n2: Tails");

        if (choice - 1 == toss) {
            Output.printToScreen("\n\nYou won the coin toss, you get first turn.\n");
            pause();
            playerTurn();
        }
        else Output.printToScreen("\n\nThe dealer won the coin toss, they get first turn.\n");
        pause();
        dealerTurn();
    }


    public void playerTurn() {
        playerTurn = true;
        boolean askAgain = true;
        Card askingCard;
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Your Turn~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.print(deckCardsLeft());

        while(askAgain) {
            askAgain = false;

            //Display hand, check for book from deal and ask which card to go fishing for.

            printHand(playerHand);

            if(checkBooks(playerHand)) {
                System.out.println("Nice you formed a book!");
                playerBooks++;
                printHand(playerHand);
            }

            System.out.println("Which card would you like to fish for?");
            int choice = in.nextInt();
            askingCard = playerHand.get(choice-1);

            while (choice > playerHand.size() || choice == 0) {
                System.out.println("Please make an appropriate choice.");
                printHand(playerHand);
                System.out.println("Again...Which card would you like to fish for?");
                choice = in.nextInt();
                askingCard = playerHand.get(choice-1);
            }

            //Fishing method sets caughtFish and prints return statement of how many fish caught
            //checkBook;

            System.out.printf("You politely ask: Do you have any %ss\n", askingCard.getValue().getThirdValue());
            System.out.println(fishing(askingCard, playerTurn));

            if(checkBooks(playerHand)) {
                System.out.println("Nice you formed a book!\n");
                playerBooks++;
                printHand(playerHand);
            }

            //If caughtFish>0 askAgain=true, else player draws
            // from deck and if they draw a fish askAgain = true
            if(caughtFish > 0) {
                askAgain = true;
                System.out.println("Looks like you caught some fishies, have another go!");

                if(checkBooks(playerHand)) {
                    System.out.println("Nice you formed a book!");
                    playerBooks++;
                    printHand(playerHand);
                }
                pause();
            }
            else {
                System.out.println("Dealer Says: Go Fish Bitch!!!");
                pause();
                drawnCard = draw(playerHand);
                System.out.printf("You drew a %s\n\n", drawnCard.getValue().getThirdValue());
                if (drawnCard.getValue().getThirdValue().equals(askingCard.getValue().getThirdValue())) {
                    askAgain = true;
                    System.out.printf("Since, you drew a %s, you get to go again!", drawnCard.getValue().getThirdValue());
                }
            }
            dealerTurn();
        }
    }

    public void dealerTurn() {
        playerTurn = false;
        boolean askAgain = true;
        Card askingCard;
        printHand(playerHand);
        System.out.println("\"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Dealer Turn~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\"");
        System.out.print(deckCardsLeft());

        while(askAgain) {

            //check for dealerBook from deal and ask player for card before taking it

            if(checkBooks(dealerHand)) {
                System.out.println("Dealer formed a book!");
                dealerBooks++;
            }

            askingCard = dealerHand.get((int)Math.random()*(dealerHand.size()));

            //Fishing method sets caughtFish and prints return statement of how many fish caught

            System.out.printf("Dealer asks: Got any %ss?", askingCard.getValue().getThirdValue());
            System.out.println("\n");
            System.out.println(fishing(askingCard, playerTurn));

            if(checkBooks(dealerHand)) {
                System.out.println("Dealer formed a book!");
                dealerBooks++;
            }

            if(caughtFish > 0) {
                askAgain = true;
                System.out.println("Looks like Dealer caught some fishies, they get to have another go!");
                pause();
            }
            else {
                System.out.println("You Say: you will have to go fishing sir.");
                pause();
                drawnCard = draw(dealerHand);
                System.out.printf("Dealer drew a %s\n\n", drawnCard.getValue().getThirdValue());
                if (drawnCard.getValue().getThirdValue().equals(askingCard.getValue().getThirdValue())) {
                    askAgain = true;
                    System.out.printf("Dealer drew a %s, dealer gets to go again!", drawnCard.getValue().getThirdValue());
                }
                else {askAgain = false;}
            }
            System.out.print(deckCardsLeft());
        }
        playerTurn();
    }


    public String fishing(Card askingCard, boolean turn) {
        caughtFish = 0;

        if (turn) {
            for (Card c : dealerHand) {
                if (c.equals(askingCard)) {
                    dealerHand.remove(c);
                    playerHand.add(c);
                    caughtFish++;

                }
            }
            return String.format("Your opponent had %d %ss",
                    caughtFish, askingCard.getValue().getThirdValue());
        }


        else {
            for (Card c : playerHand) {
                if (c.equals(askingCard)) {
                    playerHand.remove(c);
                    dealerHand.add(c);
                    caughtFish++;
                }
            }

        }
        return String.format("You have %d %ss",
                caughtFish, askingCard.getValue().getThirdValue());
    }

    public void printHand(ArrayList<Card> hand) {
        int handIndex = 0;
        for (Card c : hand) {
            System.out.printf((handIndex+1) +": %s of %s\n", c.getValue().getThirdValue(), c.getSuit());
            handIndex++;
        }
    }

    public Card draw(ArrayList<Card> hand) {
        Card drawn = deck.pop();
        hand.add(drawn);
        return drawn;
    }


    public String deckCardsLeft() {
        return String.format("\nThere are %d cards left in the deck.\n\n", deck.size());
    }

    public void pause() {
        System.out.println("\nPress Enter to continue...");
        hold.nextLine();
    }

    public boolean checkBooks(ArrayList<Card> hand) {

        boolean bookFound = false;
        String found = "";
        int count = 0;
        Map<String, Integer> books = new HashMap<String, Integer>();

        for (Card c : hand) {

            count = books.get(c.getValue().getThirdValue()) == null ? 0 :books.get(c.getValue().getThirdValue());
            books.put(c.getValue().getThirdValue(), (count==0) ? 1 : ++count);
            if ((count) == 4) {
                found = c.getValue().getThirdValue();
                bookFound = true;
            }
        }

        for (Card c : hand) {
            if (c.getValue().getThirdValue().equals(found)) {
                hand.remove(c);
            }

        }
        if(bookFound) {
            return true;
        }
        else return false;

    }

    public boolean checkGameOver() {
        if (getDeck().size() == 0 && playerHand.size() == 0 && dealerHand.size() == 0) {
            System.out.println("All the cards are gone...finally, let's check who won.\n");
            checkWinner();
            gameOver = true;
        }
        return gameOver;
    }

    public void checkWinner() {
        if (playerBooks > dealerBooks) {
            System.out.printf("You have %d books and dealer has %d books.\n" +
                    "Looks like you won this game!!!\n...Ya simple bitch!!!", playerBooks, dealerBooks);
        }
        else if (dealerBooks > playerBooks) {
            System.out.printf("You have %d books and dealer has %d books.\n" +
                    "Looks like you lost this game!!!\n...Ya simple bitch!!!", playerBooks, dealerBooks);
        }
        else {
            System.out.printf("You have %d books and dealer has %d books.\n" +
                    "Looks like a tie game!!!\n...Thanks for wasting my time.", playerBooks, dealerBooks);
        }
    }








    public void incrementPlayerBooks() {
        this.playerBooks++;
    }

    public void incrementDealerBooks() {
        this.dealerBooks++;
    }




}
