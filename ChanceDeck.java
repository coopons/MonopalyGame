package MonopalyGame;

import java.util.ArrayList;
import java.util.Collections;

//TODO DocComments

public class ChanceDeck {

	private ArrayList<String> chanceCards;
	private final ArrayList<String> discard;

	public ChanceDeck() {
		chanceCards = new ArrayList<>();
		discard = new ArrayList<>();
		
		chanceCards.add("Advance to Boardwalk");
		chanceCards.add("Advance to Illinois Avenue");
		chanceCards.add("Advance to St. Charles Place");
		chanceCards.add("Advance to the nearest Railroad");
		
		chanceCards.add("Advance token to nearest Utility");
		chanceCards.add("Bank pays you dividend");
		chanceCards.add("Get Out of Jail Free");
		chanceCards.add("Go Back 3 Spots");
		
		chanceCards.add("Go to Jail");
		chanceCards.add("Make general repairs on all your property");
		chanceCards.add("Speeding fine ");
		chanceCards.add("Take a trip to Reading Railroad");
		
		chanceCards.add("You have been elected Chairman of the Board");
		chanceCards.add("Your building loan matures");

		shuffle();
	}

	private void shuffle() {
		Collections.shuffle(chanceCards);
	}

	public String draw() {
		String temp = chanceCards.get(0);
		chanceCards.remove(0);
		return temp;
	}

	public void discard(String d) {
		discard.add(d);
	}

	public void replenish() {
		
		chanceCards = new ArrayList<>(discard);
		discard.clear();
		shuffle();
	}

	public boolean noCards() {
		return chanceCards.isEmpty();

	}

	public boolean noGetOutOfJail() {
		
		return !chanceCards.contains("Get Out of Jail Free");
	}

}
