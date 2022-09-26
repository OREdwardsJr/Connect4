package com.connect4;

import com.connect4.controller.Communicator;

public class Player {

    private String name;
    private static final Communicator COMM = Communicator.newInstance();
    private int ID;

    // ctor
    public Player() {
        //default
    }

    public Player(String name, int id) {
        setName(name);
        setID(id);
    }

    // business methods
    public int takeTurn(Board board) { // returns the column index choice
        // 9/26 Orlando: @Hency maybe pass the difficultly level to cpuTakeTurn?
        if ("CPU".equals(getName())) return cpuTakeTurn(board);

        // Grabs column choice from user accounting for user entry being 1-indexed based
        // should probably return columnNumber - 1 within COMM.takeTurn();
        return COMM.takeTurn() - 1;
    }

    private int cpuTakeTurn(Board board) {
        int choice = 0;

        // TODO @Hency
        // need conditional for choosing between different levels EG: Easy, Medium, Hard
        /*
        EG: if difficulty == easy then return CompLogic.easy()
            else return CompLogic.medium()
         */
        return choice;
    }

    public void announceVictory(boolean winnerDetected) {
        COMM.announceVictory(this, winnerDetected);
    }

    // Getter and Setter
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }


    public void setID(int ID) { //TODO Hency:  Discuss on whether or not the setID field is private and static
                                // 09/26 Orlando: @Hency I agree setID could be private. However, you're using it in
                                // Communicator.newPlayerName() so that method would need to be changed.
                                // I don't think it should be static as we both saw the problem
                                // that static brought with the names. I'm open to discuss further just let me know.
        /* the id is important because the corresponding symbol is based
         * on the player's id. EG: X for id 1 and O for id 2.
         * While names may be changed, it is not expected that the
         * ids are ever changed once players have been instantiated.
         */
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Player Name: " + getName() + " is " + "ID#: " + getID();
    }

}