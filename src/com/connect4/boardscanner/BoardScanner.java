package com.connect4.boardscanner;

import com.connect4.Board;
import com.connect4.Player;

public class BoardScanner {

    private BoardScanner(){};

    public static boolean checkRow(int option, int count, Player player, int[][] board, int r, int c) {
        // recursive method that travels in a direction based on option while count < 4
        if ((r < 0 || r >= board.length) ||
            (c < 0 || c >= board[0].length) ||
            (player.getID() != board[r][c]))
            return false;

        if (++count == 4) return true;

        switch (option) { // there are at most 7 directions to check for winning moves
            case 0: // travel Northeast
                return checkRow(option, count, player, board, --r, ++c);
            case 1: // travel East
                return checkRow(option, count, player, board, r, ++c);
            case 2: // travel Southeast
                return checkRow(option, count, player, board, ++r, ++c);
            case 3: // travel South
                return checkRow(option, count, player, board, ++r, c);
            case 4: // travel Southwest
                return checkRow(option, count, player, board, ++r, --c);
            case 5: // travel West
                return checkRow(option, count, player, board, r, --c);
            default: // travel Northwest
                return checkRow(option, count, player, board, --r, --c);
        }
    }
}