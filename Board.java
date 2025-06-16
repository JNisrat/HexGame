// BaseBoard.java

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Board {
    private int boardSize;
    private int turn;
    private int[][] grid;

    public Board(int boardSize2) {
        boardSize = boardSize2;
        grid = new int[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            Arrays.fill(grid[i], 0); // Initialize the grid with 0
        }
        turn = 1; // Set initial turn
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int getGrid(int x, int y) {
        return grid[x][y];
    }

    public int getTurn() {
        return turn;
    }

    public boolean validInput(int x, int y) {
        if (x < 0 || y < 0 || x >= boardSize || y >= boardSize) {
            System.out.println("Move (" + (x + 1) + ", " + (y + 1) + ") out of range!");
            return false;
        }

        if (grid[x][y] != 0) {
            System.out.println("Invalid move. The cell has been occupied.");
            return false;
        }

        return true;
    }

    public boolean addMove(int playerType, int x, int y) {
        if (playerType != turn) {
            System.out.println("It is not the player's turn!");
            return false;
        }

        if (grid[x][y] != 0) {
            System.out.println("Invalid move. The cell has been occupied.");
            return false;
        }

        grid[x][y] = playerType; // Mark the move on the grid
        turn = -1 * turn; // Switch turn
        return true;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (grid[i][j] == 0) {
                    return false; // Found an empty spot, board is not full
                }
            }
        }
        return true; // No empty spots found, board is full
    }

    public void printBoard() {
        System.out.print("   ");
        for (int j = 0; j < boardSize; j++) {
            System.out.print((j + 1) + (j < 9 ? "   " : "  "));
        }
        System.out.println();

        System.out.print("   ");
        for (int j = 0; j < boardSize; j++) {
            System.out.print("----");
        }
        System.out.println();

        for (int i = 0; i < boardSize; i++) {
            for (int k = 0; k < i; k++)
                System.out.print("  ");

            System.out.print((i < 9 ? " " : "") + (i + 1) + " ");

            for (int j = 0; j < boardSize; j++) {
                if (grid[i][j] == 0) {
                    System.out.print((j == 0 ? "|   |" : "   |"));
                } else if (grid[i][j] == 1) {
                    System.out.print((j == 0 ? "| R |" : " R |"));
                } else {
                    System.out.print((j == 0 ? "| B |" : " B |"));
                }
            }
            System.out.println();
        }

        for (int k = 0; k <= boardSize; k++)
            System.out.print("  ");

        for (int j = 0; j < boardSize; j++) {
            System.out.print("----");
        }

        System.out.println();
        System.out.println();
    }

    public List<int[]> getAvailableMoves() {
        List<int[]> availableMoves = new ArrayList<>();
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (grid[i][j] == 0) {
                    availableMoves.add(new int[]{i, j});
                }
            }
        }
        return availableMoves;
    }
    public boolean checkWinningStatus(int playerType) {
        boolean[][] visited = new boolean[boardSize][boardSize];

        // Determine start and target sides based on player type
        if (playerType == 1) { // Red player - connects top to bottom
            for (int col = 0; col < boardSize; col++) {
                if (grid[0][col] == playerType && dfs(0, col, visited, playerType)) {
                    return true;
                }
            }
        } else if (playerType == -1) { // Blue player - connects left to right
            for (int row = 0; row < boardSize; row++) {
                if (grid[row][0] == playerType && dfs(row, 0, visited, playerType)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(int x, int y, boolean[][] visited, int playerType) {
        // Check if we've reached the target side
        if (playerType == 1 && x == boardSize - 1) {
            return true; // Red reaches bottom
        }
        if (playerType == -1 && y == boardSize - 1) {
            return true; // Blue reaches right
        }

        visited[x][y] = true;

        // Define possible directions to move (6 directions in a hex grid)
        int[] dx = {-1, -1, 0, 0, 1, 1};
        int[] dy = {0, 1, -1, 1, -1, 0};

        for (int i = 0; i < 6; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            if (isValidMove(newX, newY, visited, playerType)) {
                if (dfs(newX, newY, visited, playerType)) {
                    return true;
                }
            }
        }

        return false;
    }
    private boolean isValidMove(int x, int y, boolean[][] visited, int playerType) {
        return x >= 0 && x < boardSize && y >= 0 && y < boardSize && 
               !visited[x][y] && grid[x][y] == playerType;
    }

    public int evaluateBoard(int playerType) {
        // A simple heuristic: the difference in number of cells between the player
        // and the opponent. You can enhance this with more advanced heuristics.
        int score = 0;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (grid[i][j] == playerType) {
                    score++;
                } else if (grid[i][j] == -playerType) {
                    score--;
                }
            }
        }
        return score;
    }


    public void makeMove(int playerType, int x, int y) {
        grid[x][y] = playerType;
    }

    public void undoMove(int x, int y) {
        grid[x][y] = 0;
    }

}
