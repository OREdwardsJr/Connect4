package com.connect4.controller;

import com.connect4.Board;
import com.connect4.Player;
import com.connect4.Computer;

public class Controller {

    private final static String CPU = "CPU";

    private final Communicator communicator = Communicator.newInstance();
    private final Board board = new Board();
    private final Computer computer1 = new Computer();
    private final Computer computer2 = new Computer();
    private final Display display = new Display();

    private Player player1 = new Player();
    private Player player2 = new Player();
    private boolean newGame = true;

    public void go() {
        while (newGame) { // Start new game
            setupGame();

            playGame();

            newGame = communicator.playNewGame();
        }
        communicator.sayGoodBye();
    }

    private void setupGame() {
        board.setup();
        display.setup();
        display.print();

        // Create players
        // Additional properties can be set in the future (EG: color or slot symbol)
        player1 = new Player(communicator.newPlayerName(), 1);
        if (CPU.equalsIgnoreCase(player1.getName())) {
            computer1.setDifficultyLevel(communicator.selectDifficulty());
        }

        player2 = new Player(communicator.newPlayerName(), 2);
        if (CPU.equalsIgnoreCase(player2.getName())) {
            computer2.setDifficultyLevel(communicator.selectDifficulty());
        }

        // Swap names if value of equation is an odd number
        if ((int) (Math.random() * 100) % 2 != 0) {
            // Swap names
            String temp = player1.getName();
            player1.setName(player2.getName());
            player2.setName(temp);
        }
        communicator.announcePlayers(player1, player2);
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
                    if (1 == player.getPlayerID()) choice = computer1.takeTurn(board, player);
                    else choice = computer2.takeTurn(board, player);
                }
                else {
                    System.out.println(player.getName()+"...");//prints out player name
                    choice = player.takeTurn();
                }
                if (-2 == choice) endGame(); // terminates game

                validMove = board.validMove(choice);
            }


            // Update and print display
            //TODO: @Hency Add the clear board method here
            display.update(player, board.columnEntries()[choice], choice);
            display.print();

            // Occupy slot on board
            board.occupySlot(player, choice);

            winnerDetected = board.winnerDetected(player, choice);

            startNewRound = (!winnerDetected && turns < 42);
        }
        communicator.announceVictory(player, winnerDetected);
    }

    private void endGame() {
        System.out.println("Goodbye!");
        System.exit(0);
    }
}