public class HexGame {
    private Board board;
    private Player[] players;

    public HexGame(Board b, Player p1, Player p2) {
        this.board = b;
        this.players = new Player[2];
        this.players[0] = p1;
        this.players[1] = p2;
    }

    public void play() {
        int[] coordinates = new int[2];
        while (true) {
            int playerType = board.getTurn();
            int playerIndex = (playerType == players[0].getType()) ? 0 : 1;
    
            if (!players[playerIndex].getMove(board, coordinates)) {
                System.out.println("No available move");
                return;
            }
    
            System.out.println(players[playerIndex].getPlayerName() + 
                               " plays (" + (coordinates[0] + 1) + ", " + (coordinates[1] + 1) + "):");
    
            if (!board.addMove(playerType, coordinates[0], coordinates[1])) {
                return; // Move is invalid, exit the loop
            }
    
            board.printBoard();
    
            // Check for a winning status
            if (board.checkWinningStatus(playerType)) {
                System.out.println(players[playerIndex].getPlayerName() + " wins!");
                return;
            }
    
            // Check if the board is full
            if (board.isBoardFull()) {
                System.out.println("The board is full.");
                return;
            }
        }
    }
}
    

