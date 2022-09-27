package com.connect4.controller;

import com.apps.util.Prompter;
import com.connect4.Player;

import java.util.Scanner;

public class Communicator {
    // this class will be used to gather input from user
    /* make sure to clean input so that users cannot insert code
     * that causes a vulnerability. - we may not have time for this but can speak about it in presentation
     */

    private static final Communicator COMM = new Communicator();
    private static final Prompter prompter = new Prompter(new Scanner(System.in));

    public static Communicator newInstance() {
        return COMM;
    }

    public String selectDifficulty() {
        String level = "1";

        boolean quit = false;
        while (!quit) {
            String selectLevel = prompter.prompt("Select number for difficulty level:\n1-Easy\n2-Medium\n");

            switch (selectLevel) {
                case ("1"):
                    level = "Easy";
                    quit = true;
                    break;
                case ("2"):
                    level = "Medium";
                    quit = true;
                    break;

                default:
                    System.out.println("Invalid input, please provide [1] for Easy, [2] for Medium");
            }
        }
        return level;
    }

    public String newPlayerName() {
        //TODO 09/26 Hency: @Orlando, take a quick glance at this
        // I am returning the name for Player
        // I am also returning the name for Player if the input is "" or "CPU". This method will not be case sensitive

        //TODO 09/27 Orlando: @Hency, let's go with the below and delete the commented out part. I'd like to
        // keep "CPU" free for user choice as an actual player name. Empty string "" is sufficient for setting 1-player IMO
        Prompter prompter = new Prompter(new Scanner(System.in));

        String playerName;

        playerName = prompter.prompt("Please enter name: ");
        if (playerName.equals("")) {
            playerName = "CPU";
        }
        /*
        else if(playerName.equalsIgnoreCase("CPU")){
            playerName="CPU";
        }
         */

        return playerName; // This will return the player name
    }

    public boolean playNewGame() {
        boolean result = false;
        boolean quit = false;
        String newGameQuery;

        while(!quit){
            newGameQuery = prompter.prompt("Would you like to play a new game? [Y-N]");

            switch (newGameQuery.toLowerCase()){
                case ("y"):
                    result = true;
                    quit=true;
                    break;
                case ("n"):
                    result = false;
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid input, please provide [Y] for Yes or [N] for No.");

            }
        }

        return result;
    }

    public int takeTurn() {
        int columnNumber;
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
        } else {
            System.out.println("Tie game!");
        }
    }

    public void sayGoodBye() {
        System.out.println("goodbye");
    }
}