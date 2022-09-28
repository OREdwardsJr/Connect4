package com.connect4;

import com.connect4.controller.Communicator;
import com.connect4.controller.Display;

// bogus comment :)

class DummyController {
    private static final String CPU = "CPU";

    private static final Board BOARD = new Board();

    public static final Communicator COMMUNICATOR = Communicator.newInstance();
    public static Player player1;
    public static Player player2;
    public static Computer computer1;
    public static Computer computer2;
    public static Display display = new Display();

    private static boolean newGame = true;

    public static void run() {
        while (newGame) { // Start new game
            setupGame();

            playGame();

            newGame = COMMUNICATOR.playNewGame();
        }
        COMMUNICATOR.sayGoodBye();
    }

    public static void setupGame() {
        BOARD.clear();

        player1 = new Player("CPU", 1);
        player2 = new Player("CPU", 2);

        computer1 = new Computer("easy");
        computer2 = new Computer("medium");

        // Swap names if value of equation is an odd number
        if ((int) (Math.random() * 100) % 2 != 0) {
            // Swap names
            String temp = player1.getName();
            player1.setName(player2.getName());
            player2.setName(temp);
        }
        COMMUNICATOR.announcePlayers(player1, player2);
    }

    public static void playGame() {
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