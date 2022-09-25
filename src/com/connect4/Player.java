package com.connect4;

import com.connect4.comm.Communicator;

public class Player {

    private static String name;
    private static final Communicator COMM = Communicator.newInstance();
    private static int ID;

    // ctor
    public  Player(){
        //default
    };

    public Player(String name, int id) {
        setName(name);
        setID(id);
    }

    // business methods
    public int takeTurn(Board board) {
        // returns the column index choice

        // CPU logic would go here
        if ("CPU".equals(getName())) return cpuTakeTurn(board.get());

        // Grabs column choice from user accounting for user entry being 1-indexed based
        // should probably return columnNumber - 1 within COMM.takeTurn();
        return COMM.takeTurn() - 1;
    }

    private int cpuTakeTurn(int[][] board) {
        // returns a valid move
        // would need access to board to find open slots
        // turns could be based on difficultly level
        int choice = 0;

        // need conditional for choosing between different levels EG: Easy, Medium, Hard
        if ("easy") {
            return CompLogic.easy(board, this.getID());
        }

        return CompLogic.medium(board, this.getID());

    }

    public void announceVictory() {
        // print celebration for player
    }

    public void announceVictory(boolean playerVictory) {
        if (playerVictory) {
            // announce player name wins
            System.out.println();
        }
        else {
            // announce tie

        }
    }



    // Getter and Setter
    public void setName(String name) {
        Player.name = name;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }



    public void setID(int ID) { //TODO Hency:  Discuss on whether or not the setID field is private and static
        /* the id is important because the corresponding symbol is based
         * on the player's id. EG: X for id 1 and O for id 2.
         * While names may be changed, it is not expected that the
         * ids are ever changed once players have been instantiated.
         */
        Player.ID = ID;
    }
}