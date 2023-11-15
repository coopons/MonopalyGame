package MonopalyGame;

/**
 * The {@code Spot} class represents a location on the Monopoly game board.
 * Each spot has a name and keeps track of how many times players have landed on it.
 */
public class Spot {

	private final String name;
	private int landedCount;

	/**
	 * Constructs a new spot with the given name.
	 *
	 * @param name the name of the spot.
	 */
	public Spot(String name) {
		this.name = name;
		landedCount = 0;
	}

	/**
	 * Gets the name of the spot.
	 *
	 * @return name of the spot.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the number of times players have landed on this spot.
	 *
	 * @return number of times players have landed on this spot.
	 */
	public int getLandedCount() {
		return landedCount;
	}

	/**
	 * Increments the count of how many times players have landed on this spot.
	 */
	public void landed() {
		landedCount++;
	}

	/**
	 * Returns a string representation of the spot.
	 *
	 * @return name of the spot.
	 */
	@Override
	public String toString() {
		return name;
	}

}
