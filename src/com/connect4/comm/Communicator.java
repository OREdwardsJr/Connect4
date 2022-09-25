package com.connect4.comm;

import com.connect4.Player;

public class Communicator {
    // this class will be used to gather input from user
    /* make sure to clean input so that users cannot insert code
     *   that causes a vulnerability.
     */

    private static final Communicator COMM = new Communicator();

    public static Communicator newInstance() {
        return COMM;
    }

    public String newPlayerName() {
        String playerName = "prompt user for name";

        // prompt for username. if it's "" then return CPU

        return playerName;
    }

    public boolean playNewGame() {
        boolean result = false;

        return result;
    }

    public int takeTurn() {
        int columnNumber = 0;

        return columnNumber;
    }

    public void announcePlayers(Player p1, Player p2) {
        System.out.println("Player 1 is: " + p1.getName());
        System.out.println("Player 2 is: " + p2.getName());
    }

    public void announceVictory(Player player, boolean winnerDetected) {
        if (winnerDetected) {
            System.out.println(player.getName() + " wins!");
        }
        else {
            System.out.println("Tie game!");
        }
    }

    public void sayGoodBye() {
        System.out.println("goodbye");
    }
}