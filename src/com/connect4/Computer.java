package com.connect4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Computer extends Player {
    private String difficultyLevel;

    public Computer() {}

    public Computer(String difficultyLevel) {
        setDifficultyLevel(difficultyLevel);
    }

    public int takeTurn(Board board, Player player) {
        if ("easy".equalsIgnoreCase(this.getDifficultyLevel())) return easy(board);

        return medium(board, player);
    }

    public int easy(Board board) {
        int choice = 0;
        List<Integer> columnChoice = swapNums(board.get()[0].length);

        for (Integer i : columnChoice) {
            if (board.columnEntries()[i] >= 0) {
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
     *
     * For int[2] arrays the indexes represent: index 0 for column - index 1 for the count of matching connected pieces
     */
    public int medium(Board board, Player player) {
        int[] thisBestOption;
        int[] opponentBestOption;

        thisBestOption = findBestOption(board, player, true);
        opponentBestOption = findBestOption(board, player, false);

        if (thisBestOption[1] >= 2) return thisBestOption[0]; // win game

        if (opponentBestOption[1] >= 2) return opponentBestOption[0]; // try to prevent winning move from opponent

        return thisBestOption[0]; // return option with the longest connection of cpu pieces
    }

    private int[] findBestOption(Board board, Player player, boolean useId) {
        int currentValue = 0;
        int[] bestOption = new int[2];
        int r = 0; int c = 0; int count = 0;

        for (c = 0; c < board.get()[0].length; c++) {
            r = board.columnEntries()[c];
            if (board.validMove(c)) {
                for (int option = 0; option < 7; option += 2) {
                    currentValue = checkRow(board, option, count, player, r, c, useId) +
                            checkRow(board, option + 1, count, player, r, c, useId);
                    if (currentValue > bestOption[1]) {
                        bestOption[0] = c;
                        bestOption[1] = currentValue;
                    }
                }
                if (bestOption[1] >= 3) break;
            }
        }
        bestOption[0] = (bestOption[1] > 0) ? bestOption[0] : easy(board);

        return bestOption;
    }

    private int checkRow(Board board, int option, int count, Player player, int r, int c, boolean useId) {
        // checks the surrounding pieces of getBoard[r][c] to find contiguous matches
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

        if ((!board.validMove(r, c))                        ||
            (getBoard[r][c] == 0)                           ||
            (useId && (player.getPlayerID() != getBoard[r][c]))   ||
            (!useId && (player.getPlayerID() == getBoard[r][c]))  ||
            (count >= 3))
            return count;

        return checkRow(board, option, count + 1, player, r, c, useId);
    }

    private List<Integer> swapNums(int listSize) {
        List<Integer> list = new ArrayList<>(listSize);

        for (int i = 0; i < listSize; i++) list.add(i);

        Collections.shuffle(list);

        return list;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel.toUpperCase();
    }

    public String getDifficultyLevel() {
        return this.difficultyLevel;
    }
}