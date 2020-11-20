package io.zipcoder.casino.utilities;

public class Output {
    Integer selection;
    Double walletAmount;
    String name;
    private static Input input = new Input(System.in,System.out);
    /**
     * Welcome
     * Get user name
     * Get user type (gambling/non)
     * Get user wallet amt (if gambler)
     * User Game Menu (includes all games)
     * User Game Menu (includes only non gambling games)
     * Quit casino goodbye
     */

    public static String printToScreen(String input){
        System.out.println(input);
        return input;
    }

    public String welcomeMenu(){
        String welc1 = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
        String welc2 = "~~~~ Welcome to the Casino ~~~~\n";
        String welc3 = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
        String printMe = welc1+welc2+welc3;
        printToScreen(printMe);
        return printMe;
    }

    public String getPlayerName(){
        name = input.getStringInput("Enter your name:  \n");
        return name;
    }

    public Boolean isGambler(){

        String line1 = "~~~~~~~~~~~ Press 1 to gamble today! ~~~~~~~~~~~~\n";
        String line2 = "~~~~ Press any other number to play it safe! ~~~~\n";
        selection = input.getIntegerInput(line1 + line2);
        return selection == 1.0 ? true:false;
    }

    public Double getWalletAmount(){
         walletAmount = input.getDoubleInput("Deposit money into you wallet to gamble\nEnter amount: ");
         return walletAmount;
    }

    public Integer allGameOptions(){
        String line1 = "Which game would you like to play!?\n";
        String line2 ="1:BlackJack  2:GoFish  3:Cho-Han  4:Craps  5:Exit Casino\n";
        selection = input.getIntegerInput(line1 + line2);
        return selection;
    }

    public Integer nonGamblingOptions(){
        String line1 = "Which game would you like to play!?\n";
        String line2 ="1:GoFish  2:Cho-Han  3:Become a gambler  4:Exit Casino\n";
        selection = input.getIntegerInput(line1 + line2);
        return selection;
    }

    public String goodBye(){
       String printMe = "Gambling problem? Help is within reach!\nCall 1-800-522-4700\nNational Gambling Hotline";
        printToScreen(printMe);
        return printMe;
    }



}
