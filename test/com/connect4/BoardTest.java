package com.connect4;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class BoardTest {
    public static Board board = new Board();
    public static Player p1 = new Player("Orlando", 1);
    public static Player p2 = new Player("Shyan", -1);

    public static void fillBoard() {
        for (int i = board.columnEntries()[0]; i > 0; i--) {
            for (int j = 0; j < board.get()[0].length; j++) {
                board.occupySlot(p1, j);
            }
        }
    }

    public static void dump() {
        for (int[] row : board.get()) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }

    @Before
    public void beforeClass() throws Exception {
        Player p1 = new Player("Orlando", 1);
        Board board = new Board();
    }

    // validMove()
    @Test
    public void validMove_shouldReturnTrue_whenGivenAcceptableColumn() {
        board = new Board();

        for (int i = 0; i < board.get()[0].length; i++) {
            assertTrue(board.validMove(i));
        }
    }

    @Test
    public void validMove_shouldReturnFalse_whenGivenUnacceptableColumn() {
        board = new Board();

        for (int i = board.get()[0].length; i < 20; i++) {
            assertFalse(board.validMove(i));
        }
    }

    // occupySlot()
    @Test
    public void columnEntries_shouldPrintToConsole_matchingCallsTo_BoardOccupySlot() {
        Board board = new Board();
        Player p1 = new Player("Orlando", 1);

        System.out.println(Arrays.toString(board.columnEntries()));

        for (int i = board.columnEntries()[0]; i > 0; i--) {
            board.occupySlot(p1, 0);
            System.out.println(Arrays.toString(board.columnEntries()));
        }

        System.out.println();

        for (int i = board.columnEntries()[1]; i > 0; i--) {
            board.occupySlot(p1, 1);
            System.out.println(Arrays.toString(board.columnEntries()));
        }

        System.out.println();

        for (int i = board.columnEntries()[2]; i > 0; i--) {
            board.occupySlot(p1, 2);
            System.out.println(Arrays.toString(board.columnEntries()));
        }

        System.out.println();

        for (int i = board.columnEntries()[3]; i > 0; i--) {
            board.occupySlot(p1, 3);
            System.out.println(Arrays.toString(board.columnEntries()));
        }

        System.out.println();

        for (int i = board.columnEntries()[4]; i > 0; i--) {
            board.occupySlot(p1, 4);
            System.out.println(Arrays.toString(board.columnEntries()));
        }

        System.out.println();

        for (int i = board.columnEntries()[5]; i > 0; i--) {
            board.occupySlot(p1, 5);
            System.out.println(Arrays.toString(board.columnEntries()));
        }

        System.out.println();

        for (int i = board.columnEntries()[6]; i > 0; i--) {
            board.occupySlot(p1, 6);
            System.out.println(Arrays.toString(board.columnEntries()));
        }
    }

    @Test
    public void clear_shouldResetBoard_whenCalled() {
        fillBoard();
        board.clear();

        assertArrayEquals(board.get(), new Board().get());
    }


    @Test
    public void winnerDetected_shouldReturnTrue_whenWinningComboPresent() {
        // Horizontal
        board.clear();
        board.occupySlot(p1, 0);
        board.occupySlot(p1, 1);
        board.occupySlot(p1, 2);
        board.occupySlot(p1, 3);

        System.out.println(Arrays.toString(board.columnEntries()));
        assertTrue(board.winnerDetected(p1,3));

        // Vertical
        board.clear();
        board.occupySlot(p1, 0);
        board.occupySlot(p1, 0);
        board.occupySlot(p1, 0);
        board.occupySlot(p1, 0);

        assertTrue(board.winnerDetected(p1,0));

        // Diagonal
        board.clear();
        board.occupySlot(p1, 0);

        board.occupySlot(p2, 1);
        board.occupySlot(p1, 1);

        board.occupySlot(p2, 2);
        board.occupySlot(p2, 2);
        board.occupySlot(p1, 2);

        board.occupySlot(p2, 3);
        board.occupySlot(p2, 3);
        board.occupySlot(p2, 3);
        board.occupySlot(p1, 3);

        assertTrue(board.winnerDetected(p1, 3));

        // Complicated diagonal for p1
        board.clear();
        board.occupySlot(p1, 0);

        board.occupySlot(p2, 1);
        board.occupySlot(p1, 1);

        board.occupySlot(p2, 2);
        board.occupySlot(p2, 2);
        board.occupySlot(p1, 2);

        board.occupySlot(p2, 3);
        board.occupySlot(p2, 3);
        board.occupySlot(p2, 3);

        board.occupySlot(p2, 4);
        board.occupySlot(p2, 4);
        board.occupySlot(p1, 4);

        board.occupySlot(p2, 5);
        board.occupySlot(p1, 5);

        board.occupySlot(p1, 6);

        board.occupySlot(p1, 3);

        assertTrue(board.winnerDetected(p1, 3));

        // Complicated diagonal for p2
        board.clear();
        board.occupySlot(p2, 0);

        board.occupySlot(p1, 1);
        board.occupySlot(p2, 1);

        board.occupySlot(p1, 2);
        board.occupySlot(p1, 2);
        board.occupySlot(p2, 2);

        board.occupySlot(p1, 3);
        board.occupySlot(p1, 3);
        board.occupySlot(p1, 3);

        board.occupySlot(p1, 4);
        board.occupySlot(p1, 4);
        board.occupySlot(p2, 4);

        board.occupySlot(p1, 5);
        board.occupySlot(p2, 5);

        board.occupySlot(p2, 6);

        board.occupySlot(p2, 3);

        assertTrue(board.winnerDetected(p2, 3));
    }
}

// HELPFUL TIP: Arrays.deepToString can print a 2D array
// EG: System.out.println(Arrays.deepToString(board.get()));
