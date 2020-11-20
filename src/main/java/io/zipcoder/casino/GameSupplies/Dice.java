package io.zipcoder.casino.GameSupplies;

public class Dice {
    //Maybe only need 1 class for this?


    Integer faceValue;
    Integer summedFaceValue;
    Integer numOfDice;

    public Dice(Integer numOfDice) {
        this.numOfDice = numOfDice;
    }

    public Integer tossAndSum(Integer numOfDice){
        summedFaceValue = 0;
        for (int i = 0; i<numOfDice; i++){
            faceValue = (int)(Math.random() * (6 - 1)) + 1;
            summedFaceValue += faceValue;
        }
        return summedFaceValue;
    }

    public Integer getNumOfDice(){
        return numOfDice;
    }

    public void setNumOfDice(Integer numOfDice){
        this.numOfDice = numOfDice;
    }
}









/* private int die1;
    private int die2;

    public Dice(int i) {
        roll();
    }

    public void roll(){
        die1 = (int)(Math.random() * 6) + 1;
        die2 = (int)(Math.random() * 6) + 1;
    }

    public int getDiceSum(){
        return die1 + die2;
    } */
