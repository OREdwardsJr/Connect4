package com.connect4.controller;

import com.apps.util.Prompter;
import com.connect4.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Communicator {
    // this class will be used to gather input from user
    /* make sure to clean input so that users cannot insert code
     *   that causes a vulnerability.
     */

    final int MAXPLAYERS = 2;// TODO This may be unnecessary please discuss and then delete.

    private static final Communicator COMM = new Communicator();
    private static final Prompter prompter = new Prompter(new Scanner(System.in));

    List<Player> gamePlayers = new ArrayList<>();

    public static Communicator newInstance() {
        return COMM;
    }

    public String selectDifficulty() {

        String level = "1";

        boolean quit = false;
        while (!quit){
            String selectLevel = prompter.prompt("Select number for difficulty level:\n1-Easy\n2-Medium\n3-Hard\n");
            switch (selectLevel) {
                case ("1"):
                    level = "Easy";
                    quit = true;
                    break;
                case ("2"):
                    level = "Medium";
                    quit = true;
                    break;
                case ("3"):
                    level = "Hard";
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid input, please provide [1] for Easy, [2] for Medium or [3] for Hard.");
            }
        }
        return level;
    }

    public String newPlayerName() {
        //TODO 09/26 Hency: @Orlando, take a quick glance at this
        // I am returning the name for Player
        // I am also returning the name for Player if the input is "" or "CPU". This method will not be case sensitive
        Prompter prompter = new Prompter(new Scanner(System.in));

        String playerName;

        playerName = prompter.prompt("Please enter name: ");
        if (playerName.equals("")) {
            playerName = "CPU";
        }
        else if(playerName.equalsIgnoreCase("CPU")){
            playerName="CPU";
        }

        return playerName; // This will return the player name
    }

    public boolean playNewGame() {
        boolean result = false;

        return result;
    }

    public int takeTurn() {
        int columnNumber = 0;
        String move = prompter.prompt("Select your move [1-7]");
        columnNumber = Integer.parseInt(move);
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