package io.zipcoder.casino.Players;

import io.zipcoder.casino.GameSupplies.Card;

import java.util.ArrayList;

public class BlackJackPlayer implements GamblingPlayerInterface{
    private String name;
    private Player player;
    private double wallet;
    private ArrayList<Card> playerHand;

    public BlackJackPlayer(Player player) {
        this.player = player;
        this.name = player.getName();
        this.wallet = player.getWallet();
        this.playerHand = new ArrayList<Card>();
    }

    public ArrayList<Card> getBlackJackPlayerHand() {
        return playerHand;
    }

    public double getWallet() {
        return this.wallet;
    }

    public void setWallet(double amount) {
        this.wallet = amount;
        player.setWallet(amount);
    }
//

    public double placeBet() {
        return 0;
    }

    public String getName() {
        return this.name;
    }
}
