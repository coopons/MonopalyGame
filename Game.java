package MonopalyGame;


/**
 * The {@code Game} class represents a Monopoly game, managing turns, player movements,
 * and card draws for Community Chest and Chance.
 */
public class Game {

    // Total number of turns in the game
    private final int totalTurns;

    // Total number of moves made by the player
    private int totalMoves;

    // The game board
    private final Board board;

    // The player in the game
    private final Player player1;

    // Deck for Community Chest cards
    private final CommunityChestDeck communityDeck;

    // Deck for Chance cards
    private final ChanceDeck chanceDeck;

    /**
     * Constructs a new Monopoly game with a specified number of turns.
     *
     * @param totalTurns total number of turns to play in the game.
     */
    public Game(int totalTurns) {
        this.totalTurns = totalTurns;
        totalMoves = 0;
        board = new Board();
        player1 = new Player();
        communityDeck = new CommunityChestDeck();
        chanceDeck = new ChanceDeck();
    }

    /**
     * Executes the strategy A for the game:
     * If player has a Get Out of Jail Free card, they must use it immediately.
     * If player doesn't have the card, they immediately pay $50 and get out of jail.
     */
    public void strategyA() {
        int moves;
        String currSpot;

        // Iterate through each turn
        for (int currTurn = 0; currTurn < totalTurns; currTurn++) {
            moves = player1.rollDice();
            totalMoves += moves;
            board.setJail(false);
            board.move(moves);
            currSpot = board.getLocation();
            // Check if the player is in jail
            if (board.getJail()) {
                jailStrategyA();
            }
            // Go to jail if 3 doubles rolled in a row
            if (player1.getDiceDoubleCount() == 3) {
                board.moveToJail();
            }
            // Draws respective card if on Community Chest or Chance spot
            drawCardIfNeeded(currSpot);
        }
    }

    /**
     * Executes the strategy B for the game:
     * If player has a Get Out of Jail Free card, they must use it immediately.
     * If player doesn't have the card, they attempt to roll doubles for three turns.
     * If doubles are not rolled in three turns, they immediately pay $50 and get out of jail.
     */
    public void strategyB() {
        int moves;
        String currSpot;

        // Iterate through each turn
        for (int currTurn = 0; currTurn < totalTurns; currTurn++) {
            moves = player1.rollDice();
            totalMoves += moves;
            board.setJail(false);
            board.move(moves);
            currSpot = board.getLocation();
            // Check if the player is in jail
            if (board.getJail()) {
                currTurn = jailStrategyB(currTurn);
            }
            // Go to jail if 3 doubles rolled in a row
            if (player1.getDiceDoubleCount() == 3) {
                board.moveToJail();
            }
            // Draws respective card if on Community Chest or Chance spot
            drawCardIfNeeded(currSpot);
        }
    }

    /**
     * Draws a card if the player is on a Community Chest or Chance spot.
     *
     * @param currLocation current location of the player on the board.
     */
    private void drawCardIfNeeded(String currLocation) {
        // Draw Chance card if on a Chance spot
        if (currLocation.contains("Chance")) {
            drawChanceCard();
        }
        // Draw Community Chest card if on a Community Chest spot
        else if (currLocation.contains("Community Chest")) {
            drawCommunityCard();
        }
    }

    /**
     * Implements strategy A for when the player lands in jail.
     */
    private void jailStrategyA() {
        // If the player has a "Get Out of Jail Free" card from Community Chest
        if (player1.hasCards() && communityDeck.noGetOutOfJail()) {
            communityDeck.discard(player1.useCard());
        }
        // If the player has a "Get Out of Jail Free" card from Chance
        else if (player1.hasCards() && chanceDeck.noGetOutOfJail()) {
            chanceDeck.discard(player1.useCard());
        }
    }

    /**
     * Implements strategy B for when the player lands in jail.
     *
     * @param currTurn current turn.
     * @return updated current turn.
     */
    private int jailStrategyB(int currTurn) {
        // If the player has a "Get Out of Jail Free" card from Community Chest
        if (player1.hasCards() && communityDeck.noGetOutOfJail()) {
            communityDeck.discard(player1.useCard());
        }
        // If the player has a "Get Out of Jail Free" card from Chance
        else if (player1.hasCards() && chanceDeck.noGetOutOfJail()) {
            chanceDeck.discard(player1.useCard());
        }
        // If player has no "Get Out of Jail Free" cards
        // attempt to roll doubles for the next three turns or until out of jail
        else {
            player1.resetDiceDoubleCount();
            int jailTurns = 0;

            while (jailTurns < 3 && currTurn < totalTurns) {
                player1.rollDice();
                // Check if doubles are rolled
                if (player1.getDiceDoubleCount() == 1) {
                    board.setJail(false);
                    break;
                }
                // Player remains in jail
                board.moveToJail();
                // Increments turns in jail and current turn
                jailTurns++;
                currTurn++;
            }
        }
        return currTurn;
    }

    /**
     * Draws a Chance card and executes the corresponding action based on the drawn card.
     */
    private void drawChanceCard() {
        if (chanceDeck.noCards()) {
            chanceDeck.replenish();
        }

        String card = chanceDeck.draw();

        switch (card) {
            case "Advance to Boardwalk":
                board.moveToBoardwalk();
                chanceDeck.discard(card);
                break;

            case "Advance to Go":
                board.moveToGo();
                chanceDeck.discard(card);
                break;

            case "Advance to Illinois Avenue":
                board.moveToIllinoisAvenue();
                chanceDeck.discard(card);
                break;

            case "Advance to St. Charles Place":
                board.moveToStCharlesPlace();
                chanceDeck.discard(card);
                break;

            case "Advance to the nearest Railroad":
                board.moveToNearestRailroad();
                chanceDeck.discard(card);
                break;

            case "Advance token to nearest Utility":
                board.moveToNearestUtility();
                chanceDeck.discard(card);
                break;

            // Player only keeps "Get Out of Jail Free" cards
            case "Get Out of Jail Free":
                player1.keepCard(card);
                break;

            case "Go Back 3 Spots":
                board.moveBackThree();
                chanceDeck.discard(card);
                break;

            case "Go to Jail":
                board.moveToJail();
                chanceDeck.discard(card);
                break;

            case "Take a trip to Reading Railroad":
                board.moveToReadingRailroad();
                chanceDeck.discard(card);
                break;

            default:
                chanceDeck.discard(card);
        }
    }

    /**
     * Draws a Community Chest card and executes the corresponding action based on the drawn card.
     */
    private void drawCommunityCard() {
        if (communityDeck.noCards()) {
            communityDeck.replenish();
        }

        String card = communityDeck.draw();

        switch (card) {
            case "Advance to Go":
                board.moveToGo();
                communityDeck.discard(card);
                break;

            // Player only keeps "Get Out of Jail Free" cards
            case "Get Out of Jail Free":
                player1.keepCard(card);
                break;

            case "Go to Jail":
                board.moveToJail();
                communityDeck.discard(card);
                break;

            default:
                communityDeck.discard(card);
        }
    }

    /**
     * Gets the total number of moves made by the player in the game.
     *
     * @return total number of moves.
     */
    public int getTotalMoves() {
        return totalMoves;
    }

    /**
     * Prints the current state of the game board.
     */
    public void printBoard() {
        System.out.print(board);
    }


}
