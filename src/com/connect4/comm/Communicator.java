package com.connect4.comm;

import com.apps.util.Prompter;
import com.connect4.Player;
import com.connect4.Player.*;

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
    private static final Prompter prompter = new Prompter(new Scanner(System.in)); //

    List<Player> gamePlayers = new ArrayList<>();

    public static Communicator newInstance() {
        return COMM;
    }

    public String newPlayerName() {
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

    public int takeTurn() {
        int columnNumber = 0;

        return columnNumber;
    }

    public void announcePlayers(Player p1, Player p2) {
        System.out.println("Player 1 is: " + p1.getName());
        System.out.println("Player 2 is: " + p2.getName());
    }

    public void sayGoodBye() {
        System.out.println("goodbye");
    }
}