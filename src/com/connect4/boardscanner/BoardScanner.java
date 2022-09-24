package com.connect4.boardscanner;

import com.connect4.Board;
import com.connect4.Player;

public class BoardScanner {
    private static int rows = Board.newInstance().getRows();
    private static int columns = Board.newInstance().getColumns();

    // private static final Scanner SCANNER = new Scanner(rows, columns);


    public BoardScanner() {
    }

    public BoardScanner(int rows, int cols) {
        this.rows = rows;
        this.columns = cols;
    }

//    public Scanner newInstance(int rows, int cols) {
//        return SCANNER;
//    }

    public boolean checkRow(int option, int count, Player player, int[][] board, int r, int c) {
        // recursive method that travels in a direction based on option while count < 4
        boolean result = false;

        if ((r < 0 || r >= rows) ||
            (c < 0 || c >= columns) ||
            // (count > 4) ||
            (player.getID() != board[r][c]))
            return false;

        if (++count == 4) return true;

        switch (option) { // there are at most 7 directions to check for winning moves
            case 0: // travel East
                return checkRow(option, count, player, board, r, ++c);
            case 1: // travel Southeast
                return checkRow(option, count, player, board, ++r, ++c);
            case 2: // travel South
                return checkRow(option, count, player, board, ++r, c);
            case 3: // travel Southwest
                return checkRow(option, count, player, board, ++r, --c);
            case 4: // travel West
                return checkRow(option, count, player, board, r, --c);
            default: // travel Northwest
                return checkRow(option, count, player, board, --r, --c);
        }
    }
}