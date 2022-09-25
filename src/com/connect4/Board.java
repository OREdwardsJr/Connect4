package com.connect4;

import com.connect4.boardscanner.BoardScanner;

import java.util.Scanner;

public class Board {
    // Properties
    private static final Board BOARD = new Board();

    private final int columns = 7;
    private final int rows = 6;

    private final BoardScanner BOARDSCANNER = new BoardScanner(rows, columns);

    private int[][] board = new int[rows][columns];

    private final int i = rows - 1;
    private int[] indices = new int[] {i, i, i, i, i, i, i}; // tracks next available row for column entries

    // Business methods
    public static Board newInstance() {
        return BOARD;
    }

    public boolean validMove(int column) {
        return (column < indices.length && indices[column] < 6);
    }

    public void occupySlot(Player player, int column) {
        board[indices[column]][column] = player.getID();

        indices[column]--;
    }

    public boolean winnerDetected(Player player, int column) {
        boolean result = false;

        for (int i = 0; i < 7; i++) {
            if (!result) result = BOARDSCANNER.checkRow(i, 0, player, this.board, indices[column], column);
            else break;
        }
        return result;
    }

    public void clear() {
        board = new int[rows][columns];
        indices = new int[] {i, i, i, i, i, i, i};
    }

    public int getColumns() {
        return columns - 1;
    }

    public int getRows() {
        return rows - 1;
    }

    public int[][] get() {
        return board;
    }

}