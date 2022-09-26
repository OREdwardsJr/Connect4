package com.connect4;

import com.connect4.boardscanner.BoardScanner;

public class Board {
    // Properties
    private static final Board BOARD = new Board();

    private final int columns = 7;
    private final int rows = 6;

    private int[][] board = new int[rows][columns];

    private final int i = rows - 1;
    private int[] rowTracker = new int[] {i, i, i, i, i, i, i}; // tracks next available row for column entries

    // Business methods
    public static Board newInstance() {
        return BOARD;
    }

    public boolean validMove(int column) {
        return (column < rowTracker.length && rowTracker[column] >= 0);
    }

    public void occupySlot(Player player, int columnChoice) {
        board[rowTracker[columnChoice]][columnChoice] = player.getID();

        rowTracker[columnChoice]--;
    }

    public boolean winnerDetected(Player player, int column) {
        // tracks the longest chain where slots are previously filled by player's id next to empty slot
        int longestChain = 0;

        for (int i = 0; i < 4; i++) {
            if (longestChain < 3) longestChain = BoardScanner.checkRow(this, i, 0, player, rowTracker[column], column);
            else break;
        }
        return (longestChain >= 3);
    }

    public void clear() {
        board = new int[rows][columns];
        rowTracker = new int[] {i, i, i, i, i, i, i};
    }

    public int[][] get() {
        // most likely to be used for displaying the board
        return board;
    }

    public int[] columnEntries() {
        return this.rowTracker;
    }

}