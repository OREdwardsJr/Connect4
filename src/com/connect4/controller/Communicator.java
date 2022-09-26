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

        String level = "2";

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
        //TODO 09/26 Orlando: @Hency, you don't need to set the ID here as that's done in the Controller?
        // Maybe I'm not understanding fully but we just need this method to
        // return something like -> prompter.prompt("Please enter name (Enter CPU for computer): ")
        // Controller takes care of everything else. Let's discuss?
        Player player = new Player();
        int id = 1;

        if (gamePlayers.isEmpty()) { // Check if Object ArrayList is empty. This is to facilitate the assignment of ID's
            player.setName(prompter.prompt("Please enter name: "));
            player.setID(id);
            if (player.getName().equals("")) {//This will check if the Player is Human or CPU
                player.setName("CPU");
                gamePlayers.add(player);
            } else {
                gamePlayers.add(player);
            }
        } else { // If the Object ArrayList is not empty, then the ID field will increase by 1, which will identify Player#2
            id++;
            player = new Player((prompter.prompt(("Please enter name: "))), id);
        }

        return player.getName(); // This will return the current player entry
    }

    public boolean playNewGame() {
        boolean result = false;

        return result;
    }

    public int takeTurn() {//TODO: Need to figure out how to feed this to player and board
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