package com.connect4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class CompLogic {

    public static int easy(Board board, int id) {
        int choice = 0;
        int[][] getBoard = board.get();
        List<Integer> columnChoice = swapNums(getBoard[0].length);

        for (Integer i : columnChoice) {
            if (getBoard[0][i] >= 0) {
                choice = i;
                break;
            }
        }
        return choice;
    }

    /*
     * If the difficulty is set to medium then the computer searches for a move that would cause them to win,
     * or a move that can prevent a loss, or some move that matches the most connected cpu pieces
     * Methods are listed in descending dependency order.
     *  - medium() calls findBestOption() to return the highest value move
     *  - findBestOption calls checkRow() on each row to capture the row's highest value
     *  - checkRow() returns the largest number of connected pieces
     */
    public static int medium(Board board, int id) {
        // index 0 is the position - index 1 is the count of connected pieces
        int[] cpuBestOption = new int[2];
        int[] opponentBestOption = new int[2];

        cpuBestOption = findBestOption(board, id, true);
        opponentBestOption = findBestOption(board, id, false);

        if (cpuBestOption[1] == 4) return cpuBestOption[0]; // win game

        if (opponentBestOption[1] == 4) return opponentBestOption[0]; // try to prevent winning move from opponent

        return cpuBestOption[0]; // return option with the longest connection of cpu pieces
    }

    private static int[] findBestOption(Board board, int id, boolean useId) {
        int currentValue = 0;
        int[] bestOption = new int[2];
        int r = 0; int c = 0; int count = 0;

        for (c = 0; c < board.get()[0].length; c++) {
            r = board.columnEntries()[c];
            for (int option = 1; option < 5; option++) {
                currentValue = checkRow(board, option, id, r, c, count, useId);
                if (currentValue > bestOption[1]) {
                    bestOption[0] = option;
                    bestOption[1] = currentValue;
                    if (bestOption[1] == 4) break;
                }
            }
        }
        return bestOption;
    }

    private static int checkRow(Board board, int option, int id, int r, int c, int count, boolean useId) {
        int[][] getBoard = board.get();

        if ((r < 0 || r >= getBoard.length) || (c < 0 || c >= getBoard[0].length)) return count; // if out of bounds

        if (board.get()[r][c] != 0) {
            if ((useId && id != getBoard[r][c]) || (!useId && getBoard[r][c] == id)) {  // if board holds opponent's piece
                return count;
            }
        }

        if (++count == 4 || getBoard[r][c] == 0) return count; // if winning option is present or slot is empty

        switch (option) { // there are at most 7 directions to check for winning moves
            case 1: // travel Northeast
                return checkRow(board, option, id, --r, ++c, count, useId) +
                        checkRow(board, option, id, ++r, --c, count, useId);
            case 2: // travel East
                return checkRow(board, option, id, r, ++c, count, useId) +
                        checkRow(board, option, id, r, --c, count, useId);
            case 3: // travel Southeast
                return checkRow(board, option, id, ++r, ++c, count, useId) +
                        checkRow(board, option, id, --r, --c, count, useId);
            default: // travel South
                return checkRow(board, option, id, ++r, c, count, useId);
        }
    }

    private static List<Integer> swapNums(int arrSize) {
        List<Integer> arr = new ArrayList<>(arrSize);

        for (int i = 0; i < arrSize; i++) arr.set(i, i);

        Collections.shuffle(arr);

        return arr;
    }
}