package com.connect4.controller;

import com.connect4.Board;
import com.connect4.Player;
import com.connect4.Computer;

public class Controller {

    private static final String CPU = "CPU";
    private static final Communicator COMMUNICATOR = Communicator.newInstance();
    private static final Board BOARD = new Board();

    private static Player player1;
    private static Player player2;
    private static boolean newGame = true;

    public static void main(String[] args) {
        while (newGame) { // Start new game
            setupGame();

            playGame();

            newGame = COMMUNICATOR.playNewGame();
        }
        COMMUNICATOR.sayGoodBye();
    }

    private static void setupGame() {
        BOARD.clear();

        // Create players
        // Additional properties can be set in the future (EG: color or slot symbol)
        player1 = new Player(COMMUNICATOR.newPlayerName(), 1);
        player2 = new Player(COMMUNICATOR.newPlayerName(), 2);

        // Set CPU difficulty
        if (CPU.equalsIgnoreCase(player2.getName())) {
            Computer.setDifficultyLevel(COMMUNICATOR.selectDifficulty());
        }

        // Randomly assign who's player1 vs player2 via swap
        if ((int) (Math.random() * 100) % 2 != 0) {
            // Swap names
            String temp = player1.getName();
            player1.setName(player2.getName());
            player2.setName(temp);
        }
        COMMUNICATOR.announcePlayers(player1, player2);
    }

    private static void playGame() {
        boolean validMove;
        int choice = 0;
        int turns = 0;
        boolean startNewRound = true;
        boolean winnerDetected = false;
        Player player = player1;    // set to player1 only to quiet IDE warnings.

        while (startNewRound) {
            validMove = false;

            player = (turns++ % 2 == 0) ? player1 : player2;

            // Take turn
            while (!validMove) { // probably need to throw some type of error somewhere
                if (CPU.equals(player.getName())) {
                    choice = Computer.takeTurn(BOARD, player);
                }
                else {
                    choice = player.takeTurn();
                }
                validMove = BOARD.validMove(choice);
            }

            BOARD.occupySlot(player, choice);

            winnerDetected = BOARD.winnerDetected(player, choice);

            startNewRound = (!winnerDetected && turns < 42);
        }
        player.announceVictory(winnerDetected);
    }
}

/*
 * If time permits then implement a scoreboard
 */