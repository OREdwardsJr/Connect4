package com.connect4.boardscanner;

import com.connect4.Board;
import com.connect4.Player;

public class BoardScanner {

    private BoardScanner(){}

    public static int checkRow(Board board, int option, int count, Player player, int r, int c) {
        /*
         * Checks for 3 matching pieces to player.getID() all valid directions.
         * Our recursive calls need to have count passed as 0 due to us technically searching for the number of
         * matching surrounding pieces.
         *
         * EG: [_ _ O _ O O _] We'd want to add our O piece to index 3. CheckRow registers that there are three matching
         * slots as it will return results of a recursive search headed left of index 3 and headed right of index 3
         * 1 + 2 will return 3, which means that if we enter into that slot we will now have 4 connecting pieces
         * and will win the game.
         *
         * Note - if count no longer gets passed as -1 when checkRow is originally called then adjust logic below
         */
        // recursive method that travels in a direction based on option while count < 4
        int[][] getBoard = board.get();

        if ((r < 0 || r >= getBoard.length)    ||
            (c < 0 || c >= getBoard[0].length) ||
            (player.getID() != getBoard[r][c]) ||
            (count > 0 && getBoard[r][c] == 0)) // empty spaces after first iterations are not accepted
            return count;

        if (count >= 3) return count;

        switch (option) {
            case 0: // travel Northeast + Southwest
                return checkRow(board, option, count + 1, player, --r, ++c) +
                        checkRow(board, option, count + 1, player, ++r, --c);
            case 1: // travel East + West
                return checkRow(board, option, count + 1, player, r, ++c) +
                        checkRow(board, option, count + 1, player, r, --c);
            case 2: // travel Southeast + Northwest
                return checkRow(board, option, count + 1, player, ++r, ++c) +
                        checkRow(board, option, count + 1, player, --r, --c);
            default: // travel South
                return checkRow(board, option, count + 1, player,++r, c);
        }
    }
}