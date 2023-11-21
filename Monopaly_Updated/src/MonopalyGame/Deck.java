package MonopalyGame;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The {@code Deck} class represents the community and chance deck.
 * It maintains an ArrayList of Cards keeping track of ones used is a separate ArrayList Discard.
 */

public class Deck {

    private ArrayList<String> deck;
    private final ArrayList<String> discard;

    public Deck(DeckType deckType) {

        deck = new ArrayList<>();
        discard = new ArrayList<>();

        if (deckType == DeckType.COMMUNITY) {
            deck.add("Advance to Go");
            deck.add("Bank error in your favor");
            deck.add("Doctor’s fee");
            deck.add("Sale of stock");

            deck.add("Get Out of Jail Free");
            deck.add("Go to Jail");
            deck.add("Holiday fund matures");
            deck.add("Income tax refund");

            deck.add("It is your birthday");
            deck.add("Life insurance matures");
            deck.add("Pay hospital fees");
            deck.add("Pay school fees");

            deck.add("Consultancy fee");
            deck.add("You are assessed for street repair");
            deck.add("You have won second prize in a beauty contest");
            deck.add("You inherit");

        }
        else {
            deck.add("Advance to Boardwalk");
            deck.add("Advance to Illinois Avenue");
            deck.add("Advance to St. Charles Place");
            deck.add("Advance to the nearest Railroad");

            deck.add("Advance token to nearest Utility");
            deck.add("Bank pays you dividend");
            deck.add("Get Out of Jail Free");
            deck.add("Go Back 3 Spots");

            deck.add("Go to Jail");
            deck.add("Make general repairs on all your property");
            deck.add("Speeding fine ");
            deck.add("Take a trip to Reading Railroad");

            deck.add("You have been elected Chairman of the Board");
            deck.add("Your building loan matures");
        }
        shuffle();
    }

    /**
     * Shuffles the arrayList.
     */
    private void shuffle() {
        Collections.shuffle(deck);
    }

    /**
     * Removes first String in array list.
     *
     * @return Item located at beginning of arrayList.
     */
    public String draw() {
        String temp = deck.get(0);
        deck.remove(0);
        return temp;
    }

    /**
     * Takes String and adds to Discard arrayList.
     *
     * @param d
     */
    public void discard(String d) {
        discard.add(d);
    }

    /**
     * Takes all Strings from discard and adds the to deck.
     * Removes all Strings from discard arrayList.
     */
    public void replenish() {
        deck = new ArrayList<>(discard);
        discard.clear();
        shuffle();
    }

    /**
     * Checks to see if deck arrayList is Empty.
     *
     * @return true if deck is empty
     */
    public boolean noCards() {
        return deck.isEmpty();

    }

    /**
     * Checks to see if "Get Out of Jail Free" String is in deck arrayList.
     * Checks to see if "Get Out of Jail Free" String is in discard arrayList.
     *
     * @return true if deck is empty
     */
    public boolean noGetOutOfJail() {
        if (!deck.contains("Get Out of Jail Free") && !discard.contains("Get Out of Jail Free")) {
            return true;
        }

        return false;
    }

}