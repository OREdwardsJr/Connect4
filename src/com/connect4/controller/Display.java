package com.connect4.controller;

import com.connect4.Board;
import com.connect4.Player;

public class Display {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED   = "\u001B[31m";

    private final String[][] values = new String[6][7];    // declare # of rows then declare # of columns.

    public Display() { // ctor
        setup();
    }

    public void update(Player player, int row, int column)  {
        String boardPiece = "X";

        if(player.getPlayerID() == 1) {
            boardPiece = "X";
        }
        else {
            boardPiece = "O";
        }

        // animate(player, row, column, boardPiece);

        values[row][column] = boardPiece;
    }

    public void print() {
        for (int i = 0; i < 7; i++) {
            System.out.print("  " + (i+1) + " ");
        }
        System.out.println();

        for (int j = 0; j <7; j++) {
            System.out.print("+---");   // used to close in the top.
        }
        System.out.println("+");    // for ending line print of dashes at top.

        for (int i = 0; i < 6; i++) {       // making 6 rows from 1-6
            for (int j = 0; j <7; j++) {    // making 7 columns per row.
                System.out.print("| " );
                         colorText(values[i][j]);
                System.out.print(" ");  // row & column
            }
            System.out.println("|");    // for ending line print of sticks
            for (int j = 0; j <7; j++) {
                System.out.print("+---");
            }
            System.out.println("+");    // for ending line print of dashes
        }
    }

    public void setup() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                values[i][j] = String.valueOf(" "); // storing characters. Whitespace.
            }
        }
    }

    private static void colorText(String value) {
        if(value.equals("X")) {
            System.out.print(ANSI_GREEN
                    + value
                    + ANSI_RESET);
        }
        else if(value.equals("O")) {
            System.out.print(ANSI_RED
                    + value
                    + ANSI_RESET);
        }
        else {
            System.out.print(value);
        }
    }

//    private void animate(Player player, int row, int column, String boardPiece) throws InterruptedException {
//        for (int i = 0; i < row; i++) {
//            values[row][i] = boardPiece;
//            try {
//                Thread.sleep(1500);
//            }
//            catch (InterruptedException e) {
//                System.out.println(e.getMessage());
//            }
//            values[row][column] = String.valueOf(" ");
//        }
//    }
}