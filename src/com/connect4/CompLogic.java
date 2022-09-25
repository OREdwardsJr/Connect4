package com.connect4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class CompLogic {

    public static int easy(int[][] board, int id) {
        int choice = 0;
        List<Integer> columnChoice = swapNums(board[0].length);

        for (Integer i : columnChoice) {
            if (board[0][i] >= 0) {
                choice = i;
                break;
            }
        }
        return choice;
    }

    private static List<Integer> swapNums(int arrSize) {
        List<Integer> arr = new ArrayList<>(arrSize);

        for (int i = 0; i < arrSize; i++) arr.set(i, i);

        Collections.shuffle(arr);

        return arr;
    }

    //TODO describe how this below method works
    /*
     * If the difficulty is set to medium then the computer searches for a move that would cause them to win,
     * or a move that can prevent a loss, or some move that matches the most connected cpu pieces
     * Methods are listed in descending order.
     *  - medium() calls findBestOption() to return the highest value move
     *  - findBestOption calls checkRow()
     *  - checkRow() returns the largest number of connected pieces
     */
    private static int medium(int[][] board, int id) {
        // index 0 is the position - index 1 is the count of connected pieces
        int[] cpuBestOption = new int[2];
        int[] opponentBestOption = new int[2];

        opponentBestOption = findBestOption(board, id, false);
        cpuBestOption = findBestOption(board, id, true);

        if (cpuBestOption[1] == 4) return cpuBestOption[0];

        if (opponentBestOption[1] == 4) return opponentBestOption[0];

        return cpuBestOption[0];
    }

    private static int[] findBestOption(int[][] board, int id, boolean useId) {
        int currentValue = 0;
        int[] bestOption = new int[2];

        for (int i = 0; i < board[0].length; i++) {
            currentValue = checkRow(board, i, id, 0, 0, 0, useId);

            if (currentValue > bestOption[1]) {
                bestOption[0] = i;
                bestOption[1] = currentValue;
                if (bestOption[1] == 4) break;
            }
        }
        return bestOption;
    }

    private static int checkRow(int[][] board, int option, int id, int r, int c, int count, boolean useId) {
        int result = 0;

        // TODO Need to verify that an if statement will not check subsequent conditions if condition has been found
        //  if it continues to check then move "(++count == 4) beyond scope as count only be iterated in this scope
        //  if none of the previous conditions are true while ++count == 4 is true.
        if ((r < 0 || r >= board.length) ||
                (c < 0 || c >= board[0].length) ||
                (useId && id != board[r][c]) ||
                (!useId && board[r][c] == id) ||
                (++count == 4))
            return count;

        switch (option) { // there are at most 7 directions to check for winning moves
            case 0: // travel Northeast
                return checkRow(board, option, id, --r, ++c, count, useId);
            case 1: // travel East
                return checkRow(board, option, id, r, ++c, count, useId);
            case 2: // travel Southeast
                return checkRow(board, option, id, ++r, ++c, count, useId);
            case 3: // travel South
                return checkRow(board, option, id, ++r, c, count, useId);
            case 4: // travel Southwest
                return checkRow(board, option, id, ++r, --c, count, useId);
            case 5: // travel West
                return checkRow(board, option, id, r, --c, count, useId);
            default: // travel Northwest
                return checkRow(board, option, id, --r, --c, count, useId);
        }
    }
}