package io.zipcoder.casino.GameSupplies;

import java.util.Collections;
import java.util.Stack;

public class DeckOfCards extends Stack<Card> {

    private Stack<Card> deckOfCards;

    public DeckOfCards() {
        refreshDeck();
        this.deckOfCards = shuffleDeck();
    }

    public Stack<Card> getDeck() {
        return deckOfCards;
    }

    private Stack<Card> shuffleDeck() {
        Collections.shuffle(deckOfCards);
        return deckOfCards;
    }

    public Card dealCard() {
        return deckOfCards.pop();
    }

    /**
     * This code block creates the deck of cards
     */
    private void refreshDeck() {
        Stack<Card> newDeck = new Stack<Card>();
        for (Rank r : Rank.values()) {
            for (Suit s : Suit.values()) {
                Card temp = new Card(s, r);
                newDeck.push(temp);
            }
        }

        this.deckOfCards = newDeck;
    }

}
