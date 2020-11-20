package io.zipcoder.casino.GameSupplies;

public class Card {

    private Suit suit;
    private Rank value;

    public Card(Suit suit, Rank value) {
        this.suit = suit;
        this.value = value;
    }

    public Card() {}

    public Suit getSuit() {
        return this.suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public Rank getValue() {
        return this.value;
    }

    public void setValue(Rank value) {
        this.value = value;
    }

    /**
     * can use the this.value.getThirdValue() so that the string in the enum Rank is returned
     */
    @Override
    public String toString() {
        return String.format(" %s of %s ", this.value, this.suit);
    }
}