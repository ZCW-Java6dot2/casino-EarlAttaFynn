package io.zipcoder.casino.utilities;

import io.zipcoder.casino.Games.CardGames.BlackJack;
import io.zipcoder.casino.Games.CardGames.GoFish;
import io.zipcoder.casino.Games.DiceGames.ChoHan;
import io.zipcoder.casino.Games.DiceGames.Craps;
import io.zipcoder.casino.Players.*;

import java.util.Collection;

public class Switchboard {

    Output output = new Output();
    String name;
    Boolean isGambler;
    Double wallet;
    Player currentPlayer;
    public Switchboard(){}

    public void runMenu() {
        this.currentPlayer = setPlayer();
        if(isGambler) {
                runGambler();
        }
        else {
               runNonGambler();
            }
        output.goodBye();
        }


    public Player setPlayer(){
        output.welcomeMenu();
        name = output.getPlayerName();
        isGambler = output.isGambler();
        if(isGambler){
            wallet = output.getWalletAmount();
        }
        else {
            wallet = 0.0;
        }
     Player playa = new Player(wallet,name);
        return playa;
    }

    public Integer gameChoice(){
        Integer selection;
        if(isGambler){
            selection = output.allGameOptions();
        }
        else {
            selection = output.nonGamblingOptions();
        }
        return selection;
    }

    public void runGambler(){
        while(!gamblerMenuSorter()){
            gamblerMenuSorter();
        }
    }

    public Boolean gamblerMenuSorter(){
        Boolean exit = false;
            Integer selection = gameChoice();
            switch (selection) {
                case 1:
                    BlackJackPlayer blackJackPlayer = new BlackJackPlayer(currentPlayer);
                    //BlackJack blackJack = new BlackJack(blackJackPlayer);
                    //blackJack.playGame();
                    break;
                case 2:
                    GoFishPlayer goFishPlayer = new GoFishPlayer(currentPlayer);
                    GoFish goFish = new GoFish(goFishPlayer);
                    while (!goFish.playGame()) {
                        goFish.playGame();
                    }
                    break;
                case 3:
                    ChoHanPlayer choHanPlayer = new ChoHanPlayer(currentPlayer);
                    ChoHan choHan = new ChoHan(choHanPlayer);
                    while (!choHan.playGame()){
                        choHan.playGame();
                    }
                    break;
                case 4:
                    CrapsPlayer crapsPlayer = new CrapsPlayer(currentPlayer);
                   // Craps craps = new Craps(crapsPlayer);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Incorrect Input");
                    break;

            }
            return exit;
    }
    public void runNonGambler(){
        while(!nonGamblerMenuSorter()){
            nonGamblerMenuSorter();
        }
    }
    public Boolean nonGamblerMenuSorter(){
        Boolean exit = false;
        Integer selection = gameChoice();
        switch (selection) {
            case 1:
                GoFishPlayer goFishPlayer = new GoFishPlayer(currentPlayer);
                // GoFish goFish = new GoFish(goFishPlayer);
                break;
            case 2:
                ChoHanPlayer choHanPlayer = new ChoHanPlayer(currentPlayer);
                ChoHan choHan = new ChoHan(choHanPlayer);
                while (!choHan.playGame()){
                    choHan.playGame();
                }
                break;
            case 3:
                isGambler = true;
                wallet = output.getWalletAmount();
                runGambler();
            case 4:
                exit = true;
                break;
            default:
                System.out.println("Incorrect Input");
                break;

        }
        return exit;
    }


}
