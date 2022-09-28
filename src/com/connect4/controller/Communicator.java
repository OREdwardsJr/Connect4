package com.connect4.controller;

import com.apps.util.Prompter;
import com.connect4.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
                    System.out.println("Invalid input, please provide [1] for Easy, [2] for Medium.");
            }
        }
        return level;
    }

    public String newPlayerName() {
        Prompter prompter = new Prompter(new Scanner(System.in));
        boolean quitNewPlayerName = false;
        String playerName = "";

        while (!quitNewPlayerName) {
            playerName = prompter.prompt("Please enter name (press return for CPU): ");

            switch (playerName.toLowerCase()) {
                case (""):
                    playerName = "CPU";
                    quitNewPlayerName = true;
                    break;
                case ("cpu"):
                    System.out.println("Invalid entry, for CPU player please press return.");
                    break;
                case ("computer"):
                    System.out.println("Invalid entry, for Computer/CPU player please press return.");
                    break;
                case("c p u"):case("cp u"):case("c pu"):
                    System.out.println("Invalid entry... but funny, I will allow it.\nHowever this is NOT a Computer/CPU player.\nYou are now player: The-Comedian");
                    playerName=("The-Comedian");
                    quitNewPlayerName = true;
                    break;
                default:
                    quitNewPlayerName =true;
                    break;
            }
        }
            return playerName; // This will return the player name
    }

    public boolean playNewGame() {
        boolean result = false;
        boolean quitLoopNewGame = false;
        String newGameQuery;

        while(!quitLoopNewGame){
            newGameQuery = prompter.prompt("Would you like to play a new game? [Y-N]:");

            switch (newGameQuery.toLowerCase()){
                case ("y"):
                    result = true;
                    quitLoopNewGame=true;
                    break;
                case ("n"):
                    quitLoopNewGame = true;
                    break;
                default:
                    System.out.println("Invalid entry, please provide [Y] for Yes or [N] for No.");
            }
        }
        return result;
    }

    public int takeTurn() {
        int columnNumber = 0;
        boolean quitLoopTakeTurn = false;
        String move;

        while(!quitLoopTakeTurn){
            move = prompter.prompt("Select your move [1-7] (-1 to quit game):");

            switch (move){
                case("1"):case("2"):case("3"):case("4"):case("5"):case("6"):case("7"):case("-1"):
                    columnNumber = Integer.parseInt(move);
                    quitLoopTakeTurn = true;
                    break;
                default:
                    System.out.println("Invalid move, please select a number [1-7].");
            }
        }

        return columnNumber;
    }

    public void announcePlayers(Player p1, Player p2) {
        System.out.println("Player 1 is: " + p1.getName());
        System.out.println("Player 2 is: " + p2.getName());
    }

    public void announceVictory(Player player, boolean winnerDetected) throws IOException {
        try {
            if (winnerDetected) {
                if(player.getPlayerID() == 1) {
                    String playerOneWins = Files.readString(Path.of("images/player1wins.txt"));
                    System.out.println(playerOneWins);
                } else {
                    String playerTwoWins = Files.readString(Path.of("images/player2wins.txt"));
                    System.out.println(playerTwoWins);
                }
            } else {
                    String tieGame =  Files.readString(Path.of("images/tiegame.txt"));
                System.out.println(tieGame);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sayGoodBye() throws IOException {
        String goodBye = Files.readString(Path.of("images/goodbye.txt"));
        System.out.println(goodBye);
    }

    public void welcomeToConnectFour() throws IOException {
        String welcome = Files.readString(Path.of("images/welcome.txt"));
        System.out.println(welcome);
    }

}