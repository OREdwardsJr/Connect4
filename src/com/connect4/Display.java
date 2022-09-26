package com.connect4;

class Display {

    // private Display display;
    // private Player player;
    // int row;
    // int column;

    private static String[][] values = new String[6][7];    // declare # of rows then declare # of columns.
    public Display() {            // ctor
        //String[][]  values = new String[6][7];    // declared to have 6 rows and 7 columns.
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                values[i][j] = String.valueOf(" "); // storing characters. Whitespace.
            }
        }
    }

    public static void printDisplay() {
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
                System.out.print("| " + values[i][j] + " ");  // row & column
            }
            System.out.println("|");    // for ending line print of sticks
            for (int j = 0; j <7; j++) {
                System.out.print("+---");
            }
            System.out.println("+");    // for ending line print of dashes
        }
    }

    public static void main(String[] args) {
        Display display = new Display();
        Display.printDisplay();
    }

    public void updateDisplay(Player player, int row, int column) {
    // this.player = player.getID();
    // this.row = row;
    // this.column = column;
        String boardPiece = "X";

        if(player.getID() ==1) {
            boardPiece = "X";
        }
        else {
            boardPiece = "O";
        }
        values[row][column] = boardPiece;
    }

}