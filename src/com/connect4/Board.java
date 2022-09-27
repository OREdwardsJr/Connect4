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

        // this line helps checkRow perform its function
        //this.get()[rowTracker[column]][column] = player.getID();

        for (int i = 0; i < 7; i+=2) {
            currChain = BoardScanner.checkRow(this, i, count, player, rowTracker[column] + 1, column) +
                    BoardScanner.checkRow(this, i+1, count, player, rowTracker[column] + 1, column);
            if (longestChain < 4) longestChain = Math.max(currChain, longestChain);
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

            if ((r < 0 || r >= getBoard.length)     ||
                (c < 0 || c >= getBoard[0].length)  ||
                (player.getID() != getBoard[r][c]))
                return count;

            if (count > 3) return count;

            switch (option) { // adds every 2 cases to check all valid sides of token entry in board
                case 0: // travel Northeast
                    return checkRow(board, option, count + 1, player, --r, ++c);
                case 1: // travel Southwest
                    return checkRow(board, option, count + 1, player, ++r, --c);
                case 2: // travel East
                    return checkRow(board, option, count + 1, player, r, ++c);
                case 3: // travel West
                    return checkRow(board, option, count + 1, player, r, --c);
                case 4: // travel Southeast
                    return checkRow(board, option, count + 1, player, ++r, ++c);
                case 5: // travel Northwest
                    return checkRow(board, option, count + 1, player, --r, --c);
                case 6: // travel South
                    return checkRow(board, option, count + 1, player, ++r, c);
                default: // traveling North is impossible so return 0
                    return 0;
            }
        }
    }
}
