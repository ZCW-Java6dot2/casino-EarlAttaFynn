package io.zipcoder.casino.Players;

public class CrapsPlayer  implements GamblingPlayerInterface{
    private String name;
    private Player player;
    private double wallet;

    public CrapsPlayer(Player player) {
        this.player = player;
        this.name = player.getName();
        this.wallet = player.getWallet();
    }

    public double getWallet() {
        return 0;
    }

    public void setWallet(double amount) {
        this.wallet = amount;
        player.setWallet(amount);

    }

    public double placeBet() {
        return 0.00;
    }

    public String getName() {
        return this.name;
    }
}
