package io.zipcoder.casino.Players;
import io.zipcoder.casino.GameSupplies.Card;
import java.util.ArrayList;

public class GoFishPlayer {
    private final String name;
    private final Player player;
    private ArrayList<Card> hand;

    public GoFishPlayer(Player player) {
        this.player = player;
        this.name = player.getName();
        this.hand = new ArrayList<Card>();
    }

    public String getName() {
        return name;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }






}
