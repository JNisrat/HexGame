import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int boardSize;
        Scanner scanner = new Scanner(System.in);
        
        // Input the size of the board
        System.out.println("Input the size of the board (minimum size 3):");
        boardSize = scanner.nextInt();
        if (boardSize < 3) {
            boardSize = 3; // Ensure board size is at least 3
        }

        Board board = new Board(boardSize); // Create a new Board
        
        // Display menu for player selection
        System.out.println("\nSelect Player 1 type:");
        Player player1 = selectPlayer(scanner, 1);

        System.out.println("\nSelect Player 2 type:");
        Player player2 = selectPlayer(scanner, -1);

        // Create and start the HexGame
        HexGame game = new HexGame(board, player1, player2);
        game.play();

        scanner.close();
    }

    // Method to select player type based on user input
    private static Player selectPlayer(Scanner scanner, int playerType) {
        System.out.println("1. Human Player");
        System.out.println("2. Random Player");
        System.out.println("3. Minimax Player");
        System.out.print("Enter choice (1-3): ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        String playerName;

        switch (choice) {
            case 1:
                playerName = "Human";
                System.out.println("Selected Human Player.");
                return new HumanPlayer(playerType, playerName);
            case 2:
                playerName = "RandomBot";
                System.out.println("Selected Random Player.");
                return new RandomPlayer(playerType, playerName);
            case 3:
                playerName = "AI";
                System.out.println("Selected Minimax Player.");
                int depth = 5;
                return new MinimaxPlayer(playerType, playerName, depth);
            default:
                System.out.println("Invalid choice. Defaulting to Human Player.");
                return new HumanPlayer(playerType, "Human");
        }
    }
}
