package com.connect4;

class Display {

    private static char[][] values;    // declare # of rows then declare # of columns.
    public Display() {            // ctor
        values = new char[6][7];    // declared to have 6 rows and 7 columns.
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                values[i][j] = ' '; // storing characters. Whitespace.
            }
        }
    }

    public static void printDisplay() {
        for (int i = 0; i < 7; i++) {
            System.out.print("  " + (i+1) + " ");
        }
        System.out.println();   // testing for commit.

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
}