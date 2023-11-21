package MonopalyGame;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GameApp {

    public static void main(String[] args) {

        Game p;
        int n;
        File myObj = null;
        try {
            myObj = new File("src\\MonopalyGame\\Results\\results");

            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            }
            else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter(myObj);
            n = 1_000;
            for (int i = 1; i <= 10; i++) {
                for (int j = 0; j < 5; j++) {
                    p = new Game(n);
                    p.strategy(StrategyType.STRATEGYA);
                    myWriter.write(p.printBoard(i, 10));
                    System.out.println(p.printBoard(i, 10));

                    p.strategy(StrategyType.STRATEGYB);
                    myWriter.write(p.printBoard(i, 10));
                    System.out.println(p.printBoard(i, 10));
                    n *= 10;
                }
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

}
