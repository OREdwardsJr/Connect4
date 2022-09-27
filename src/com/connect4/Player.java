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
    public int takeTurn() { // returns the column index choice
        //TODO 9/27 Orlando: Refactored where Player doesn't call on Computer for logic. Controller now handles this
        // saving for review from Hency before deleting

        //if ("CPU".equals(getName())) return cpuTakeTurn(board, this);

        // Grabs column choice from user accounting for user entry being 1-indexed based
        // should probably return columnNumber - 1 within COMM.takeTurn();
        return COMM.takeTurn() - 1;
    }

    //TODO: Orlando -  Same from above
//    private int cpuTakeTurn(Board board, Player player) {
//
//        int choice = 0;
//
//        if ("Easy".equals(player.getDifficultyLevel())){
//            choice = Computer.easy(board,player);
//        }
//        else if (COMM.selectDifficulty().equals("2")){
//            choice = Computer.medium(board,player);
//        }
//        return choice;
//    }


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

    private void setID(int ID) {
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