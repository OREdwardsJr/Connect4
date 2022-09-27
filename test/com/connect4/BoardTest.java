package com.connect4;

import org.junit.After;
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

    @After
    public void after() throws Exception {
        board.clear();
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
        // Horizontal (tests winning positions at each index in range [0,3]
        board.occupySlot(p1, 1);
        board.occupySlot(p1, 3);
        board.occupySlot(p1, 2);
        board.occupySlot(p1, 0);

        assertTrue(board.winnerDetected(p1,0));
        board.clear();

        board.occupySlot(p1, 0);
        board.occupySlot(p1, 3);
        board.occupySlot(p1, 2);
        board.occupySlot(p1, 1);

        assertTrue(board.winnerDetected(p1,1));
        board.clear();

        board.occupySlot(p1, 0);
        board.occupySlot(p1, 1);
        board.occupySlot(p1, 3);
        board.occupySlot(p1, 2);

        assertTrue(board.winnerDetected(p1,2));
        board.clear();

        board.occupySlot(p1, 0);
        board.occupySlot(p1, 1);
        board.occupySlot(p1, 2);
        board.occupySlot(p1, 3);

        assertTrue(board.winnerDetected(p1,3));

        // Vertical
        board.occupySlot(p1, 0);
        board.occupySlot(p1, 0);
        board.occupySlot(p1, 0);
        board.occupySlot(p1, 0);

        assertTrue(board.winnerDetected(p1,0));
        board.clear();

        board.occupySlot(p1, 0);
        board.occupySlot(p1, 0);
        board.occupySlot(p1, 0);
        board.occupySlot(p1, 0);

        assertTrue(board.winnerDetected(p1,0));
        board.clear();

        board.occupySlot(p1, 1);
        board.occupySlot(p1, 1);
        board.occupySlot(p1, 1);
        board.occupySlot(p1, 1);

        assertTrue(board.winnerDetected(p1,1));
        board.clear();

        board.occupySlot(p1, 2);
        board.occupySlot(p1, 2);
        board.occupySlot(p1, 2);
        board.occupySlot(p1, 2);

        assertTrue(board.winnerDetected(p1,2));
        board.clear();

        // with blocker
        board.occupySlot(p1, 0);
        board.occupySlot(p2, 0);
        board.occupySlot(p1, 0);
        board.occupySlot(p1, 0);
        board.occupySlot(p1, 0);
        board.occupySlot(p1, 0);

        assertTrue(board.winnerDetected(p1,0));
        board.clear();

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
        board.clear();

        // Complicated diagonal for p1
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
        board.clear();

        // Complicated diagonal for p2
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

    @Test
    public void winnerDetected_shouldReturnFalse_whenWinningComboNotPresent() {
        // Horizontal
        board.occupySlot(p1, 0);
        board.occupySlot(p1, 1);
        board.occupySlot(p1, 2);

        assertFalse(board.winnerDetected(p1,0));
        assertFalse(board.winnerDetected(p1,1));
        assertFalse(board.winnerDetected(p1,2));
        assertFalse(board.winnerDetected(p1,3));
        assertFalse(board.winnerDetected(p1,4));
        assertFalse(board.winnerDetected(p1,5));

        // Vertical
        board.clear();
        board.occupySlot(p1, 0);
        board.occupySlot(p1, 0);
        board.occupySlot(p1, 0);

        assertFalse(board.winnerDetected(p1,0));
        assertFalse(board.winnerDetected(p1,1));
        assertFalse(board.winnerDetected(p1,2));
        assertFalse(board.winnerDetected(p1,3));
        assertFalse(board.winnerDetected(p1,4));
        assertFalse(board.winnerDetected(p1,5));
        board.clear();

        // Diagonal

        board.occupySlot(p1, 0);

        board.occupySlot(p2, 1);
        board.occupySlot(p1, 1);

        board.occupySlot(p2, 2);
        board.occupySlot(p2, 2);
        board.occupySlot(p1, 2);

        board.occupySlot(p2, 3);
        board.occupySlot(p2, 3);
        board.occupySlot(p2, 3);

        assertFalse(board.winnerDetected(p1,0));
        assertFalse(board.winnerDetected(p1,1));
        assertFalse(board.winnerDetected(p1,2));
        assertFalse(board.winnerDetected(p1,3));
        assertFalse(board.winnerDetected(p1,4));
        assertFalse(board.winnerDetected(p1,5));
        board.clear();

        // Complicated diagonal for p1
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

        assertFalse(board.winnerDetected(p1,0));
        assertFalse(board.winnerDetected(p1,1));
        assertFalse(board.winnerDetected(p1,2));
        assertFalse(board.winnerDetected(p1,3));
        assertFalse(board.winnerDetected(p1,4));
        assertFalse(board.winnerDetected(p1,5));
        board.clear();

        // Complicated diagonal for p2
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

        assertFalse(board.winnerDetected(p2,0));
        assertFalse(board.winnerDetected(p2,1));
        assertFalse(board.winnerDetected(p2,2));
        assertFalse(board.winnerDetected(p2,3));
        assertFalse(board.winnerDetected(p2,4));
        assertFalse(board.winnerDetected(p2,5));
    }
}

// HELPFUL TIP: Arrays.deepToString can print a 2D array
// EG: System.out.println(Arrays.deepToString(board.get()));
