package MonopalyGame;

public class Board {

	private Node head; // first node of the list or null
	private Node tail; // last node of the list or null
	private int n; // number of integers in the list
	private boolean jail; //keep track passing or in jail 
	private int jailCount; // times in jail

	/**
	 * Node of LinkedList that stores the item and a single reference to the next
	 * node.
	 */

	private static class Node {
		private Spot spot;
		private Node next;

		Node(Spot spot) {
			this.spot = spot;
		}

	}

	public Board() {
		add(new Spot("Go"));
		add(new Spot("Boardwalk"));
		add(new Spot("Luxury Tax"));
		add(new Spot("Park Place"));
		add(new Spot("Chance #3"));

		add(new Spot("Short Line"));
		add(new Spot("Pennsylvania Avenue"));
		add(new Spot("Community Chest #3"));
		add(new Spot("North Carolina Avenue"));

		add(new Spot("Pacific Avenue"));
		add(new Spot("Go To Jail"));
		add(new Spot("Marvin Gardens"));
		add(new Spot("Water Works"));

		add(new Spot("Ventnor Avenue"));
		add(new Spot("Atlantic Avenue"));
		add(new Spot("B. & O. Railroad"));
		add(new Spot("Illinois Avenue"));

		add(new Spot("Indiana Avenue"));
		add(new Spot("Chance #2"));
		add(new Spot("Kentucky Avenue"));
		add(new Spot("Free Parking"));

		add(new Spot("New York Avenue"));
		add(new Spot("Tennessee Avenue"));
		add(new Spot("Community Chest #2"));
		add(new Spot("St. James Place"));

		add(new Spot("Pennsylvania Railroad"));
		add(new Spot("States Avenue"));
		add(new Spot("Electric Company"));
		add(new Spot("St. Charles Place"));

		add(new Spot("Jail"));
		add(new Spot("Connecticut Avenue"));
		add(new Spot("Vermont Avenue"));
		add(new Spot("Chance #1"));

		add(new Spot("Oriental Avenue"));
		add(new Spot("Reading Railroad"));
		add(new Spot("Income Tax"));
		add(new Spot("Baltic Avenue"));

		add(new Spot("Community Chest #1"));
		add(new Spot("Mediterranean Avenue"));

	}

	public boolean getJail() {
		return jail;
	}

	public void setJail(boolean change) {
		jail = change;
	}

	/**
	 * Determines whether the list is empty or not.
	 * 
	 * @return true if there are no elements in the list.
	 */
	private boolean isEmpty() {
		return n == 0;
	}

	/**
	 * Adds a node containing the new item at the end of the list.
	 *
     */
	public void add(Spot spot) {
		Node newNode = new Node(spot);
		newNode.spot = spot;

		if (isEmpty()) {
			head = newNode;
        }
		else {
			tail.next = newNode;
        }
        tail = newNode;
        n++;

	}

	public String getLocation() {
		return head.spot.getName();
	}

	/**
	 * Moves players position on bored.
     */

	public void move(int moves) {

		if (!isEmpty()) {
			Node current = head;
			int shift = moves % n;
			if (shift != 0) {
				for (int i = n - (shift); i > 1; i--) {
					current = current.next;
				}
				tail.next = head;
				head = current.next;
				tail = current;
				current.next = null;
			}
		}
		head.spot.landed();
	}

	public void moveBackThree() {
		move(36);
	}

	public void moveToBoardwalk() {
		moveto("Boardwalk");

		head.spot.landed();
	}

	public void moveToJail() {
		moveto("Jail");
		head.spot.landed();
		jail = true;
		jailCount++;
	}

	public void moveToGo() {
		moveto("Go");
		head.spot.landed();
	}

	public void moveToIllinoisAvenue() {
		moveto("Illinois Avenue");
		head.spot.landed();
	}

	public void moveToStCharlesPlace() {
		moveto("St. Charles Place");
		head.spot.landed();
	}

	public void moveToReadingRailroad() {
		moveto("Reading Railroad");
		head.spot.landed();
	}

	public void moveToNearestRailroad() {
		Node current = head;

		while (current.next != null && !current.next.spot.getName().contains("Railroad") && !current.next.spot.getName().equals("Short Line")) {
			current = current.next;
		}
		
		tail.next = head;
		head = current.next;
		tail = current;
		current.next = null;
		
		head.spot.landed();

	}

	public void moveToNearestUtility() {

		if (!isEmpty()) {
			Node current = head;

			while (current.next != null && !current.next.spot.getName().equals("Water Works")
					&& !current.next.spot.getName().equals("Electric Company")) {
				current = current.next;
			}
			tail.next = head;
			head = current.next;

			tail = current;
			current.next = null;
			head.spot.landed();
		}
	}

	private void moveto(String name) {

		if (!isEmpty()) {
			Node current = head;
			while (current.next != null && !current.next.spot.getName().equals(name)) {
				current = current.next;
			}
			tail.next = head;
			head = current.next;
			tail = current;
			current.next = null;

		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Node current = head;
		sb.append(jailCount).append(" times in jail \n");
		while (current != null) {
			sb.append(current.spot).append("|").append(current.spot.getLandedCount()).append("\n");
			current = current.next;
		}
		return sb.toString();
	}

}
