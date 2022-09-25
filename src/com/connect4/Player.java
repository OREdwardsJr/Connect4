package com.connect4;

import com.connect4.comm.Communicator;

public class Player {

    private static String name;
    private static final Communicator COMM = Communicator.newInstance();
    private static int ID;

    // ctor
    public Player(String name, int id) {
        setName(name);
        setID(id);
    }

    // business methods
    public int takeTurn() {
        // returns the column index choice

        // CPU logic would go here
        if ("CPU".equals(getName())) return cpuTakeTurn();

        return COMM.takeTurn();
    }

    private int cpuTakeTurn() {
        // returns a valid move
        // would need access to board to find open slots
        // turns could be based on difficultly level
        int choice = 0;

        return choice;
    }

    public void announceVictory(boolean winnerDetected) {
        COMM.announceVictory(this, winnerDetected);
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



    private static void setID(int ID) {
        /* the id is important because the corresponding symbol is based
         * on the player's id. EG: X for id 1 and O for id 2.
         * While names may be changed, it is not expected that the
         * ids are ever changed once players have been instantiated.
         */
        Player.ID = ID;
    }
}