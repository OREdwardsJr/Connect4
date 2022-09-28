package com.connect4.controller;

import com.connect4.Board;
import com.connect4.Player;
import com.connect4.Computer;

public class Controller {

    private final String CPU = "CPU";
    private final Communicator COMMUNICATOR = Communicator.newInstance();
    private final Board BOARD = new Board();

    private Player player1;
    private Player player2;
    private Computer computer1;
    private Computer computer2;
    private Display display;

    private boolean newGame = true;

    public void go() {
        while (newGame) { // Start new game
            setupGame();

            playGame();

            newGame = COMMUNICATOR.playNewGame();
        }
        COMMUNICATOR.sayGoodBye();
    }

    private void setupGame() {
        BOARD.clear();

        // Create players
        // Additional properties can be set in the future (EG: color or slot symbol)
        player1 = new Player(COMMUNICATOR.newPlayerName(), 1);
        if (CPU.equalsIgnoreCase(player1.getName())) {
            computer1 = new Computer(COMMUNICATOR.selectDifficulty());
        }

        player2 = new Player(COMMUNICATOR.newPlayerName(), 2);
        if (CPU.equalsIgnoreCase(player2.getName())) {
            computer2 = new Computer(COMMUNICATOR.selectDifficulty());
        }

        // Swap names if value of equation is an odd number
        if ((int) (Math.random() * 100) % 2 != 0) {
            // Swap names
            String temp = player1.getName();
            player1.setName(player2.getName());
            player2.setName(temp);
        }
        COMMUNICATOR.announcePlayers(player1, player2);
    }

    private void playGame() {
        boolean validMove;
        int choice = 0;
        int turns = 0;
        boolean startNewRound = true;
        boolean winnerDetected = false;
        Player player = player1;

        while (startNewRound) {
            validMove = false;

            player = (turns++ % 2 == 0) ? player1 : player2;

            // Take turn
            while (!validMove) { // probably need to throw some type of error somewhere
                if (CPU.equalsIgnoreCase(player.getName())) {
                    if (1 == player.getPlayerID()) choice = computer1.takeTurn(BOARD, player);
                    else choice = computer2.takeTurn(BOARD, player);
                }
                else {
                    choice = player.takeTurn();
                }
                validMove = BOARD.validMove(choice);
            }

            // Update and print display
            display.update(player, BOARD.columnEntries()[choice], choice);
            display.print();

            // Occupy slot on board
            BOARD.occupySlot(player, choice);

            winnerDetected = BOARD.winnerDetected(player, choice);

            startNewRound = (!winnerDetected && turns < 42);
        }
        COMMUNICATOR.announceVictory(player, winnerDetected);
    }
}