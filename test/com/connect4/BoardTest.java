package com.connect4;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class BoardTest {
    Board board;
    Player p1;

    @BeforeClass
    public static void beforeClass() throws Exception {
        Player p1 = new Player("Orlando", 1);
    }

    @Before
    public void setUp() throws Exception {
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
}

// HELPFUL TIP: Arrays.deepToString can print a 2D array
// EG: System.out.println(Arrays.deepToString(board.get()));
