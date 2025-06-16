import java.util.List;

class MinimaxPlayer extends Player {
    private int maxDepth; // Maximum depth for the minimax algorithm

    public MinimaxPlayer(int type, String name, int maxDepth) {
        super(type, name);
        this.maxDepth = maxDepth; // Initialize maximum depth
    }

    @Override
    public boolean getMove(Board board, int[] coordinates) {
        int bestValue = Integer.MIN_VALUE;
        int[] bestMove = new int[2];

        // Get all available moves from the board
        List<int[]> availableMoves = board.getAvailableMoves();
        for (int[] move : availableMoves) {
            // Make the move
            board.makeMove(getType(), move[0], move[1]);
            // Evaluate the move using minimax
            int moveValue = minimax(board, maxDepth, false);
            // Undo the move
            board.undoMove(move[0], move[1]);

            // Update the best move if this move is better
            if (moveValue > bestValue) {
                bestValue = moveValue;
                bestMove[0] = move[0];
                bestMove[1] = move[1];
            }
        }

        // Store the best move coordinates
        coordinates[0] = bestMove[0];
        coordinates[1] = bestMove[1];
        return true; // Indicate a move was found
    }

    private int minimax(Board board, int depth, boolean isMaximizing) {
        // Base case: check for terminal states or maximum depth
        if (depth == 0 || board.isBoardFull()) {
            return board.evaluateBoard(getType()); // Evaluate the board
        }

        int playerType = isMaximizing ? getType() : -getType();
        int bestValue = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        // Get all available moves
        List<int[]> availableMoves = board.getAvailableMoves();
        for (int[] move : availableMoves) {
            // Make the move
            board.makeMove(playerType, move[0], move[1]);
            // Recursively call minimax
            int currentValue = minimax(board, depth - 1, !isMaximizing);
            // Undo the move
            board.undoMove(move[0], move[1]);

            // Update best value based on maximizing or minimizing player
            if (isMaximizing) {
                bestValue = Math.max(bestValue, currentValue);
            } else {
                bestValue = Math.min(bestValue, currentValue);
            }
        }

        return bestValue; // Return the best value found
    }
}

