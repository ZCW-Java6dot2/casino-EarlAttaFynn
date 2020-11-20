package io.zipcoder.casino.GameSupplies;

public enum Rank {

// Go Fish and Blackjack don't use the joker cards
// created this ranking because ACE can have a value of 1 or 11 and JACK, QUEEN, and KING have the value of 10 also in GoFish you ask for "ace", "seven", etc
        ACE(1, 1, "ace"),
        TWO(2, 2, "two"),
        THREE(3, 3, "three"),
        FOUR(4, 4, "four"),
        FIVE(5, 5, "five"),
        SIX(6, 6, "six"),
        SEVEN(7, 7, "seven"),
        EIGHT(8, 8, "eight"),
        NINE(9, 9, "nine"),
        TEN(10, 10, "ten"),
        JACK(11, 10, "Jack"),
        QUEEN(12, 10, "Queen"),
        KING(13, 10, "King");

        private int primaryValue;
        private int secondaryValue;
        private String thirdValue;

        Rank(int primaryValue, int secondaryValue, String thirdValue) {
            this.primaryValue = primaryValue;
            this.secondaryValue = secondaryValue;
            this.thirdValue = thirdValue;
        }

        public int getPrimaryValue() {
            return primaryValue;
        }
        public int getSecondaryValue() {
            return secondaryValue;
        }
        public String getThirdValue() {
            return thirdValue;
        }
}
