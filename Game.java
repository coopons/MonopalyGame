package MonopalyGame;

public class Game {

	private final int turns;
    private int totalMoves;
	private final Board board;
	private final Player player1;
	private final CommunityChestDeck communityDeck;
	private final ChanceDeck chanceDeck;
	private String location;

	public Game(int turns) {
		this.turns = turns;
		totalMoves = 0;
		board = new Board();
		player1 = new Player();
		communityDeck = new CommunityChestDeck();
		chanceDeck = new ChanceDeck();
		location = " ";
	}

	public void strategyA() {
		int moves;
		String location;

		for (int i = 0; i < turns; i++) {
			moves = player1.rollDice();
			totalMoves += moves;
			board.setJail(false);
			board.move(moves);
			location = board.getLocation();

			if (player1.getDiceDoubleCount() == 3) {
				board.moveToJail();
			}

			if (location.contains("Chance")) {
				drawChanceCard();
			}
			else if (location.contains("Community Chest")) {
				drawCommunityCard();
			}
			else if (board.getJail()) {
				if (!player1.isEmptyHand() && communityDeck.notContainGetOutJail()) {
					communityDeck.discardCommunityChest(player1.useCard());

				}
				else if (!player1.isEmptyHand() && chanceDeck.notContainGetOutJail()) {
					chanceDeck.discardChance(player1.useCard());
				}

			}
		}
	}

	public void strategyB() {
		int jailTurns = 0;
		int moves;
		String location;

		for (int i = 0; i < turns; i++) {
			moves = player1.rollDice();
			totalMoves += moves;
			board.setJail(false);
			board.move(moves);
			location = board.getLocation();

			if (player1.getDiceDoubleCount() == 3) {
				board.moveToJail();
			}

			if (location.contains("Chance")) {
				drawChanceCard();
			}
			else if (location.contains("Community Chest")) {
				drawCommunityCard();
			}

			//System.out.print(location.contains("Jail"));
			if (board.getJail()) {
				if (player1.isEmptyHand()) {
					player1.resetDiceDoubleCount();
					jailTurns = 0;

					while (jailTurns < 4 & i < turns) {
						player1.rollDice();
						i++;

						if (player1.getDiceDoubleCount() == 1) {
							board.setJail(false);
							break;
						}

						board.moveToJail();
					}
				}
				else if (!player1.isEmptyHand() && communityDeck.notContainGetOutJail()) {
					communityDeck.discardCommunityChest(player1.useCard());

				}
				else if (!player1.isEmptyHand() && chanceDeck.notContainGetOutJail()) {
					chanceDeck.discardChance(player1.useCard());
				}

			}
		}
	}

	private void drawChanceCard() {

		if (chanceDeck.isEmptyChanceDeck()) {
			chanceDeck.replenishChance();
			drawChanceCard();
		}

		String card = chanceDeck.drawChanceCard();

		switch (card) {
		case "Advance to Boardwalk":
			board.moveToBoardwalk();
			chanceDeck.discardChance(card);
			break;

		case "Advance to Go":
			board.moveToGo();
			chanceDeck.discardChance(card);
			break;

		case "Advance to Illinois Avenue":
			board.moveToIllinoisAvenue();
			chanceDeck.discardChance(card);
			break;

		case "Advance to St. Charles Place":
			board.moveToStCharlesPlace();
			chanceDeck.discardChance(card);
			break;

		case "Advance to the nearest Railroad":
			board.moveToNearestRailroad();
			chanceDeck.discardChance(card);
			break;

		case "Advance token to nearest Utility":
			board.moveToNearestUtility();
			chanceDeck.discardChance(card);
			break;

		case "Get Out of Jail Free":
			player1.keepCard(card);
			break;

		case "Go Back 3 Spaces":
			board.moveBackThree();
			chanceDeck.discardChance(card);
			break;

		case "Go to Jail":
			board.moveToJail();
			chanceDeck.discardChance(card);
			break;

		case "Take a trip to Reading Railroad":
			board.moveToReadingRailroad();
			chanceDeck.discardChance(card);
			break;

		default:
			chanceDeck.discardChance(card);
		}
	}

	private void drawCommunityCard() {

		if (communityDeck.isEmptyCommunityDeckIs()) {
			communityDeck.replenishCommunityChest();
			drawCommunityCard();
		}
		String card = communityDeck.drawComunityCard();

		switch (card) {
		case "Advance to Go":
			board.moveToGo();
			communityDeck.discardCommunityChest(card);
			break;

		case "Get Out of Jail Free":
			player1.keepCard(card);
			break;

		case "Go to Jail":
			board.moveToJail();
			communityDeck.discardCommunityChest(card);
			break;

		default:
			chanceDeck.discardChance(card);
		}

	}

	public void printBoard() {
		System.out.print(board);
	}
}
