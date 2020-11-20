package io.zipcoder.casino;

public class Gametable {
    Boolean won;

    public Boolean playGame(String player, String game){
        //call game method
        //give player as parameter to game
        //game method returns boolean for won or lost
        won = true;
        return won;
    }

    public Boolean playGame(String player, String dealer, String game){
        won = true;
        return won;
    }

    public Boolean winnerGetsPaid(String winner){
        //does this go on the individual games? Or on here?

        //pay winner
        return true;
    }
}
