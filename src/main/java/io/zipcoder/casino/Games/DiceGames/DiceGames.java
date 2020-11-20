package io.zipcoder.casino.Games.DiceGames;

import io.zipcoder.casino.GameSupplies.Dice;
import io.zipcoder.casino.Games.GameInterface;


public class DiceGames implements GameInterface {
    private Dice dice;

    public DiceGames(){
        dice = new Dice(2);
    }

    public void numDice(int dice){
        dice = dice;

    }

/*
    public boolean playGame() {

    }
*/


    public boolean endGame() {
        return false;
    }
}
