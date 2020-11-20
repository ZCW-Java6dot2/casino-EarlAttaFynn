package io.zipcoder.casino.Games.DiceGames;
import io.zipcoder.casino.GameSupplies.Dice;
import io.zipcoder.casino.Players.ChoHanPlayer;
import io.zipcoder.casino.utilities.Input;
import io.zipcoder.casino.utilities.Output;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ChoHan extends DiceGames {
    private ChoHanPlayer choHanPlayer;
    private Output output = new Output();
    private boolean gameRuns = true;
    private boolean tossAndSum;
    private Dice dice = new Dice(2);



    Input input = new Input(System.in, System.out);

    public ChoHan(ChoHanPlayer choHanPlayer){
        this.choHanPlayer = choHanPlayer;
    }


    Scanner scanner = new Scanner(System.in);

   // @Override
    public boolean playGame() {
        boolean quit = false;
        output.printToScreen("\nWelcome to Cho Han " + choHanPlayer.getName() + "!");

        while (gameRuns){
            output.printToScreen("\nChoose between Cho and Han " + choHanPlayer.getName() + ".\n" +
                    "1 - Cho (Even number total wins)\n" +
                    "2 - Han (Odd number total wins)\n" +
                    "3 - Quit Game\n" +
                    "----------------------------------------------\n");
            int gameInput = input.getIntegerInput("Enter decision here -> ");

            try {

                if (gameInput == 1){
                    output.printToScreen("\nYou have chosen Cho (Evens)");
                } else if (gameInput == 2){
                    output.printToScreen("\nYou have chosen Han (Odds)");
                } else if (gameInput == 3){
                    quit = true;
                    return quit;
                }


            } catch (InputMismatchException e) {
                scanner.next();
                output.printToScreen("\n" + "Sorry! Please try again and choose a valid option.");
            }

            Integer currentRoll = dice.tossAndSum(2);


            if (currentRoll % 2 == 0 && gameInput == 1) {
                output.printToScreen("\nCongrats you win!! You rolled " + currentRoll);
            } else if (currentRoll % 2 != 0 && gameInput == 2) {
                output.printToScreen("\nCongrats you win!! You rolled " + currentRoll);
            } else {
                output.printToScreen("\nSorry you lose! You rolled a " + currentRoll + " better luck next time");
            }

        }
        return quit;

    }

    }

