package MonopalyGame;

import java.util.ArrayList;
import java.util.Random;

/**
 * The {@code Player} class represents the player of the game.
 * It keeps track of the amount of "Get Out Of Jail cards" the player has and
 * keeps track of the amount of times a player rolls a double.
 */

public class Player {

    private int diceDoubleCount;
    private final ArrayList<String> hand;

    public Player() {
        hand = new ArrayList<>();
        diceDoubleCount = 0;
    }

    /**
     * Adds String to hand ArrayList
     *
     * @param card
     */
    public void keepCard(String card) {
        hand.add(card);
    }

    /**
     * Checks to see if hand ArrayList is not empty
     *
     * @return true if hand ArrayList is not empty
     */
    public boolean hasCards() {
        return !hand.isEmpty();
    }

    /**
     * Removes first item from hand ArrayList.
     *
     * @return String of first Item in hand ArrayList
     */
    public String useCard() {
        String card = hand.get(0);
        hand.remove(0);
        return card;
    }

    /**
     * Produces 2 random numbers that represent a dice if both random numbers are
     * equal to each other the diceDoubleCount goes up by 1.
     *
     * @return result of both dice added up;
     */
    public int rollDice() {
        Random rand = new Random();
        int low = 1;
        int high = 6;

        int result1 = rand.nextInt(high - low) + low;
        int result2 = rand.nextInt(high - low) + low;

        if (result1 == result2) {
            diceDoubleCount++;
        }
        else {
            resetDiceDoubleCount();
        }

        return result1 + result2;
    }

    /**
     * Resets DiceDoubleCount to 0;
     */
    public void resetDiceDoubleCount() {
        diceDoubleCount = 0;
    }

    /**
     * returns DiceDoubleCount.
     *
     * @return DiceDoubleCount
     */
    public int getDiceDoubleCount() {
        return diceDoubleCount;
    }

}