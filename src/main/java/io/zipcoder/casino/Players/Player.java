package io.zipcoder.casino.Players;

import io.zipcoder.casino.GameSupplies.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Player {

    private double wallet;
    private String name = "";
    private HashMap<String, ArrayList<Object>> jackets;

    public ArrayList<Card> getHand() {
        return hand;
    }

    private ArrayList<Card> hand;

    public Player(double wallet, String name) {
        this.wallet = wallet;
        this.name = name;
        this.jackets = new HashMap<String, ArrayList<Object>>();
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJackets(HashMap<String, ArrayList<Object>> jackets) {
        this.jackets = jackets;
    }




}

