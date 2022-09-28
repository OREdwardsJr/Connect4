package com.connect4;

import com.connect4.controller.Communicator;

public class Player {

    private String name;
    private final Communicator COMM = Communicator.newInstance();
    private int playerID;

    // ctor
    public Player() {
        //default
    }

    public Player(String name, int id) {
        setName(name);
        this.playerID = id;
    }

    // business methods
    public int takeTurn() { // returns the column index choice
        return COMM.takeTurn() - 1;
    }

    // Getter and Setter
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPlayerID() {
        return playerID;
    }

    @Override
    public String toString() {
        return "Player Name: " + getName() + " is " + "playerID#: " + getPlayerID();
    }
}