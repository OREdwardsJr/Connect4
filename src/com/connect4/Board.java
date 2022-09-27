package com.connect4;

import java.util.Arrays;

public class Board {
    // Properties
    private final int columns = 7;
    private final int rows = 6;

    private int[][] board = new int[rows][columns];

    private final int i = rows - 1;
    private int[] rowTracker = new int[] {i, i, i, i, i, i, i}; // tracks next available row for column entries

    // Business methods
    public boolean validMove(int column) { //TODO move this to BoardScanner
        return (column < rowTracker.length && rowTracker[column] >= 0);
    }

    public void occupySlot(Player player, int columnChoice) {
        board[rowTracker[columnChoice]][columnChoice] = player.getID();

        rowTracker[columnChoice]--;
    }

    public boolean winnerDetected(Player player, int column) {
        // tracks the longest chain where slots are previously filled by player's id next to empty slot
        int longestChain = 0;
        int currChain = 0;
        int count = 0;

        /*
         * currChain holds the total number of contiguous pieces matching player.id
         * in two directions that would form a straight line in each valid winning directions.
         * The equation is: BoardScanner.checkRow("direction 1") + BoardScanner.checkRow("direction 2")
         * whereas checkRow's signature would be equal other than option being passed as option + 1 for direction two.
         */
        for (int option = 0; option < 7; option+=2) {
            currChain = BoardScanner.checkRow(this, option, count, player, rowTracker[column] + 1, column) +
                    BoardScanner.checkRow(this, option + 1, count, player, rowTracker[column] + 1, column);
            if (longestChain < 3) longestChain = Math.max(currChain, longestChain);
            else break;
        }
        return (longestChain >= 3);
    }

    public void clear() {
        board = new int[rows][columns];
        rowTracker = new int[] {i, i, i, i, i, i, i};
    }

    public int[][] get() {
        return board;
    }

    public int[] columnEntries() {
        return this.rowTracker;
    }

    private static class BoardScanner {
        // Checks for 4 matching pieces to player.getID() all valid directions.
        public static int checkRow(Board board, int option, int count, Player player, int r, int c) {
            int[][] getBoard = board.get();

            switch (option) {
                case 0:
                    r -= 1; c += 1; break;  // travel Northeast
                case 1:
                    r += 1; c -= 1; break;  // travel Southwest
                case 2:
                            c += 1; break;  // travel East
                case 3:
                            c -= 1; break;  // travel West
                case 4:
                    r += 1; c += 1; break;  // travel Southeast
                case 5:
                    r -= 1; c -= 1; break;  // travel Northwest
                case 6:
                    r += 1;         break;  // travel South
                default:
                    return count;           // North cannot be traveled. count == 0
            }

            if ((r < 0 || r >= getBoard.length)     ||
                (c < 0 || c >= getBoard[0].length)  ||
                (player.getID() != getBoard[r][c]))
                return count;

            if (count == 3) return count;

            return checkRow(board, option, count + 1, player, r, c);
        }
    }
}
