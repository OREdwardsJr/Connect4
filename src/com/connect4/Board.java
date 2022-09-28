package com.connect4;

public class Board {
    // Properties
    private final int columns = 7;
    private final int rows = 6;
    private final int column = rows - 1;

    private int[][] board = new int[rows][columns];
    private int[] rowTracker = new int[]{column, column, column, column, column, column, column};

    // Business methods
    public void occupySlot(Player player, int columnChoice) {
        board[rowTracker[columnChoice]][columnChoice] = player.getID();

        rowTracker[columnChoice]--;
    }

    public boolean winnerDetected(Player player, int column) {
        /*
         * currChain holds the total number of contiguous pieces matching player.id
         * in two directions that would form a straight line in each valid winning direction.
         * The equation is: BoardScanner.checkRow("direction 1") + BoardScanner.checkRow("direction 2")
         * whereas checkRow's signature would be equal other than option being passed as option + 1 for direction two.
         */
        int currChain = 0;
        int longestChain = 0;
        int count = 0;

        for (int option = 0; option < 7; option += 2) {
            currChain = checkRow(this, option, count, player, rowTracker[column] + 1, column) +
                    checkRow(this, option + 1, count, player, rowTracker[column] + 1, column);
            if (longestChain < 3) longestChain = Math.max(currChain, longestChain);
            else break;
        }
        return (longestChain >= 3);
    }

    public int checkRow(Board board, int option, int count, Player player, int r, int c) {
        /*
         * Checks for 3 matching pieces to player.getID() in all valid directions.
         * Our recursive calls need to have count passed as 0 due to us technically searching for the number of
         * matching surrounding pieces without explicitly accounting for the empty slot the piece is dropped into.
         *
         * EG: [_ _ O _ O O _] We'd want to add our O piece to index 3. CheckRow registers that there are three matching
         * slots as it will return results of a recursive search headed left of index 3 and headed right of index 3
         * where 1 + 2 will return 3, which means that if we enter into that slot we will now have 4 connecting pieces
         * and will win the game.
         */
        int[][] getBoard = board.get();

        switch (option) {
            case 0: // travel Northeast
                r -= 1; c += 1; break;
            case 1: // travel Southwest
                r += 1; c -= 1; break;
            case 2: // travel East
                        c += 1; break;
            case 3: // travel West
                        c -= 1; break;
            case 4: // travel Southeast
                r += 1; c += 1; break;
            case 5: // travel Northwest
                r -= 1; c -= 1; break;
            case 6: // travel South
                r += 1;         break;
            default:
                return 0;
        }

        if ((r < 0 || r >= getBoard.length)     ||
            (c < 0 || c >= getBoard[0].length)  ||
            (player.getID() != getBoard[r][c]) ||
            (count >= 3))
            return count;

        return checkRow(board, option, count + 1, player, r, c);
    }

    public boolean validMove(int column) {
        return (0 <= column && column < rowTracker.length && rowTracker[column] >= 0);
    }

    public boolean validMove(int row, int column) {
        return (0 <= column && column < rowTracker.length && 0 <= row && row < get().length);
    }

    public void clear() {
        board = new int[rows][columns];
        rowTracker = new int[]{column, column, column, column, column, column, column};
    }

    public int[][] get() {
        return board;
    }

    public int[] columnEntries() {
        return this.rowTracker;
    }
}
