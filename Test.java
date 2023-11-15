package MonopalyGame;

public class Test {
	// TODO Package name to "Monopoly Game"

	public static void main(String[] args) {
		Game g = new Game(1000);
		// Change to whatever strategy is to be used
		g.strategyA();
		g.printBoard();

//		g.strategyB();
//		g.printBoard();
	
	}
}
