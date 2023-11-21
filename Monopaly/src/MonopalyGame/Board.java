package MonopalyGame;

/**
 * The {@code Board} class represents the game board in Monopoly.
 * It maintains a linked list of spots and keeps track of player positions and jail status.
 */
public class Board {

    private Node head; // first node of the list or null
    private Node tail; // last node of the list or null
    private int size; // number of integers in the list
    private boolean jail; //keep track passing or in jail
    private int jailCount; // times in jail

    /**
     * Node of LinkedList that stores the item
     * and a single reference to the next node.
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

    /**
     * Determine whether the player is currently in jail or not.
     *
     * @return true if player is in jail.
     */
    public boolean getJail() {
        return jail;
    }

    /**
     * Sends player to jail
     *
     * @param jailState true if player is in jail
     */
    public void setJail(boolean jailState) {
        jail = jailState;
    }

    /**
     * Determines whether the list is empty or not.
     *
     * @return true if there are no elements in the list.
     */
    private boolean isEmpty() {
        return size == 0;
    }

    /**
     * Adds a node containing the new spot at the end of the list.
     */
    private void add(Spot spot) {
        Node newNode = new Node(spot);
        newNode.spot = spot;

        if (isEmpty()) {
            head = newNode;
        }
        else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;

    }

    /**
     * The location player is currently at.
     *
     * @return name of spot player is occupying.
     */
    public String getLocation() {
        return head.spot.getName();
    }

    /**
     * Moves players' location on the board based on the specified number of moves.
     * Implements a circular shift to simulate continuous movement on the board.
     *
     * @param moves number of spots to move.
     */
    public void move(int moves) {
        if (!isEmpty()) {
            Node current = head;
            int shift = moves % size;

            if (shift != 0) {
                for (int i = size - (shift); i > 1; i--) {
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

    /**
     * Moves player location back three spots by moving forward length of board-3
     */
    public void moveBackThree() {
        move(36);
    }

    /**
     * Moves player location to spot Boardwalk
     */
    public void moveToBoardwalk() {
        moveTo("Boardwalk");

        head.spot.landed();
    }

    /**
     * Moves player location to spot Jail
     * Sets player {@code jail} state to true
     */
    public void moveToJail() {
        moveTo("Jail");
        head.spot.landed();
        jail = true;
        jailCount++;
    }

    /**
     * Moves player location to spot Go
     */
    public void moveToGo() {
        moveTo("Go");
        head.spot.landed();
    }

    /**
     * Moves player location to spot IllinoisAvenue
     */
    public void moveToIllinoisAvenue() {
        moveTo("Illinois Avenue");
        head.spot.landed();
    }

    /**
     * Moves player location to spot StCharlesPlace
     */
    public void moveToStCharlesPlace() {
        moveTo("St. Charles Place");
        head.spot.landed();
    }

    /**
     * Moves player location to spot ReadingRailroad
     */
    public void moveToReadingRailroad() {
        moveTo("Reading Railroad");
        head.spot.landed();
    }

    /**
     * Moves player location to nearest Railroad spot
     */
    public void moveToNearestRailroad() {
        Node current = head;

        while (current.next != null && !current.next.spot.getName().contains("Railroad")
                && !current.next.spot.getName().equals("Short Line")) {
            current = current.next;
        }

        tail.next = head;
        head = current.next;
        tail = current;
        current.next = null;

        head.spot.landed();

    }

    /**
     * Moves player location to nearest Utility spot
     */
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

    /**
     * Moves player location to specified spot.
     *
     * @param name name of the spot to move to.
     */
    private void moveTo(String name) {

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

    /**
     * Add up all the amount of times each spot was landed on.
     *
     * @param name name of the spot to move to.
     */
    private double getTotalLanded() {
        Node current = head;
        int moveTotal = 0;

        while (current != null) {
            moveTotal += current.spot.getLandedCount();
            current = current.next;
        }
        return (double) moveTotal;
    }

    /**
     * Returns a string representation of the board, including spot names and landing counts.
     *
     * @return string representation of the board.
     */
    @Override
    public String toString() {
        moveTo("Go");
        StringBuilder sb = new StringBuilder();
        Node current = head;
        double avrage = 0.0;
        double moveTotal = getTotalLanded();
        int landedCount = 0;

        avrage = ((double) jailCount / moveTotal) * 100;
        sb.append(String.format("%-22s | %-5d   | %.2f %s\n", "Times In Jail", jailCount, avrage, "|"));

        while (current != null) {
            landedCount = current.spot.getLandedCount();

            if (current.spot.getName().equals("Jail")) landedCount = current.spot.getLandedCount() - jailCount;

            avrage = ((double) landedCount / moveTotal) * 100;
            sb.append(String.format("%-22s | %-5d   | %.2f %s\n", current.spot.getName(),
                    current.spot.getLandedCount(), avrage, "|"));
            current = current.next;
        }
        sb.append("\n");
        return sb.toString();
    }

}