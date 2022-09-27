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
    public boolean validMove(int column) {
        System.out.println(Arrays.toString(this.columnEntries()));
        return (0 <= column && column < rowTracker.length && rowTracker[column] >= 0);
    }

    public void occupySlot(Player player, int columnChoice) {
        board[rowTracker[columnChoice]][columnChoice] = player.getID();

        rowTracker[columnChoice]--;
    }

    public boolean winnerDetected(Player player, int column) {
        // tracks the longest chain where slots are previously filled by player's id next to empty slot
        int currChain = 0;
        int longestChain = 0;
        int count = 0;


        /*
         * currChain holds the total number of contiguous pieces matching player.id
         * in two directions that would form a straight line in each valid winning directions.
         * The equation is: BoardScanner.checkRow("direction 1") + BoardScanner.checkRow("direction 2")
         * whereas checkRow's signature would be equal other than option being passed as option + 1 for direction two.
         */
        for (int option = 0; option < 7; option+=2) {
            currChain = checkRow(this, option, count, player, rowTracker[column] + 1, column) +
                    checkRow(this, option + 1, count, player, rowTracker[column] + 1, column);
            if (longestChain < 3) longestChain = Math.max(currChain, longestChain);
            else break;
        }
        return (longestChain >= 3);
    }

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

        switch (option) {
            case 0: // travel Northeast
                r -= 1;
                c += 1;
                break;
            case 1: // travel Southwest
                r += 1;
                c -= 1;
                break;
            case 2: // travel East
                c += 1;
                break;
            case 3: // travel West
                c -= 1;
                break;
            case 4: // travel Southeast
                r += 1;
                c += 1;
                break;
            case 5: // travel Northwest
                r -= 1;
                c -= 1;
                break;
            case 6: // travel South
                r += 1;
                break;
            default:
                return 0;
        }

        if ((r < 0 || r >= getBoard.length)    ||
                (c < 0 || c >= getBoard[0].length) ||
                (player.getID() != getBoard[r][c])) // empty spaces after first iterations are not accepted
            return count;

        if (count >= 3) return count;

        return checkRow(board, option, count + 1, player, r, c);
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
}
