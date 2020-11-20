package io.zipcoder.casino.Games.DiceGames;

import io.zipcoder.casino.GameSupplies.Dice;
import io.zipcoder.casino.Players.CrapsPlayer;
import io.zipcoder.casino.utilities.Input;
import io.zipcoder.casino.utilities.Output;

import java.util.Scanner;

public class Craps extends DiceGames {
    private CrapsPlayer crapsPlayer;
    private Output output = new Output();
    private boolean tossAndSum;
    public Dice dice = new Dice(2);

    Input input = new Input(System.in, System.out);

    public Craps(CrapsPlayer crapsPlayer){
        this.crapsPlayer = crapsPlayer;

        Scanner scanner = new Scanner(System.in);
    }
}
