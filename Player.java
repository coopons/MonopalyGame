package MonopalyGame;

import java.util.ArrayList;
import java.util.Random;

//TODO DocComments

public class Player {

	private int diceDoubleCount;
	private int getOutOfJailCount;
	private final ArrayList<String> hand;

	//use hash table to keep track of cards drawn by platter

	public Player() {
		hand = new ArrayList<>();
		diceDoubleCount = 0;
		getOutOfJailCount = 0;

	}

	public void keepCard(String card) {
		hand.add(card);
		getOutOfJailCount++;
	}

	public boolean hasCards() {
		return !hand.isEmpty();
	}

	public String useCard() {
		String card = hand.get(0);
		hand.remove(0);
		return card;
	}

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
			diceDoubleCount = 0;
		}
		
		return result1 + result2;
	}
	
	public void resetDiceDoubleCount() {
		diceDoubleCount = 0;
	}

	public int getDiceDoubleCount() {
		return diceDoubleCount;
	}

}
