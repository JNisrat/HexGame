import java.util.Scanner;

public class HumanPlayer extends Player {
    private Scanner scanner;

    public HumanPlayer(int t, String name) {
        super(t, name);
        this.scanner = new Scanner(System.in);
    }

    @Override
    public boolean getMove(Board board, int[] coordinates) {
        boolean flag = false;
        int bs = board.getBoardSize();

        while (!flag) {
            System.out.println("Input row and column (x, y) between 1 to " + bs + " for " + name + " player:");
            int row, col;

            // Read input
            row = scanner.nextInt();
            col = scanner.nextInt();

            // Adjust for zero-based index
            coordinates[0] = row - 1;
            coordinates[1] = col - 1;

            flag = board.validInput(coordinates[0], coordinates[1]);
            if (!flag) {
                System.out.println("Invalid input! Please input again.");
            }
        }

        return true;
    }
}
