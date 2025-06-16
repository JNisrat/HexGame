import java.util.List;
import java.util.Random;

class RandomPlayer extends Player {
    private Random random;

    public RandomPlayer(int type, String name) {
        super(type, name);
        this.random = new Random();
    }

    @Override
    public boolean getMove(Board board, int[] coordinates) {
        List<int[]> availableMoves = board.getAvailableMoves();
        if (availableMoves.isEmpty()) {
            return false; // No available moves, game is likely a draw.
        }

        int[] move = availableMoves.get(random.nextInt(availableMoves.size()));
        coordinates[0] = move[0];
        coordinates[1] = move[1];
        return true;
    }
}
