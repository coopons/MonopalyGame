package MonopalyGame;

import java.util.ArrayList;
import java.util.Collections;

public class CommunityChestDeck {

	private ArrayList<String> communityCards;
	private final ArrayList<String> discard;

	public CommunityChestDeck() {
		communityCards = new ArrayList<>();
		discard = new ArrayList<>();

		communityCards.add("Advance to Go");
		communityCards.add("Bank error in your favor");
		communityCards.add("Doctor’s fee");
		communityCards.add("Sale of stock");
		communityCards.add("Get Out of Jail Free");
		communityCards.add("Go to Jail");
		communityCards.add("Holiday fund matures");
		communityCards.add("Income tax refund");
		communityCards.add("It is your birthday");
		communityCards.add("Life insurance matures");
		communityCards.add("Pay hospital fees");
		communityCards.add("Pay school fees");
		communityCards.add("Consultancy fee");
		communityCards.add("You are assessed for street repair");
		communityCards.add("You have won second prize in a beauty contest");
		communityCards.add("You inherit");
		shuffl();
	}

	private void shuffl() {
		Collections.shuffle(communityCards);
	}

	public String drawComunityCard() {
		String temp = communityCards.get(0);
		communityCards.remove(0);
		return temp;
	}

	public void discardCommunityChest(String d) {
		discard.add(d);
	}

	public void replenishCommunityChest() {
		communityCards = new ArrayList<>(discard);
		discard.clear();
		shuffl();
	}

	public boolean isEmptyCommunityDeckIs() {
		return communityCards.isEmpty();

	}

	public boolean notContainGetOutJail() {
		return !communityCards.contains("Get Out of Jail Free");
	}

}
