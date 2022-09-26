package com.connect4.controller;

import com.connect4.Board;
import com.connect4.Player;

class Controller {

    private static boolean newGame = true;

    private static Player player1;
    private static Player player2;

    private static final Communicator COMM = Communicator.newInstance();
    private static final Board BOARD =  new Board();

    public static void main(String[] args) {
        while (newGame) { // Start new game
            setupGame();

            playGame();

            newGame = COMM.playNewGame();
        }
        COMM.sayGoodBye();
    }

    private static void setupGame() {
        BOARD.clear();

        // Create players
        // Additional properties can be set in the future (EG: color or slot symbol)
        player1 = new Player(COMM.newPlayerName(), 1);
        player2 = new Player(COMM.newPlayerName(), 2);


        // Randomly assign who's player1 vs player2 via swap
        if ((int) (Math.random() * 100) % 2 != 0) {
            // Swap names
            String temp = player1.getName();
            player1.setName(player2.getName());
            player2.setName(temp);
        }
        COMM.announcePlayers(player1, player2);
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

            // probably need to throw some type of error somewhere
            while (!validMove) {
                choice = player.takeTurn(BOARD);
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