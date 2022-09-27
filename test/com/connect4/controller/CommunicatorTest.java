package com.connect4.controller;

import com.apps.util.Prompter;
import com.connect4.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class CommunicatorTest {
    public static void main(String[] args) {


        /*final int MAXPLAYERS = 2;
        Prompter prompter = new Prompter(new Scanner(System.in));
        List<Player> gamePlayers = new ArrayList<>();
        Player player = new Player();

        for (int i = 0; i < MAXPLAYERS; i++) {
            int idPlus=i+1;
            player = new Player((prompter.prompt(("Please enter name: "))),idPlus);
            if(player.getName().equals("")){
                player.setName("CPU");
                gamePlayers.add(player);
            }
            else{
                gamePlayers.add(player);
            }
        }
        for(Player item:gamePlayers){
            System.out.println(item);
        }*/



    /*    // THIS IS TO TEST SINGLE PLAYER ENTRY
        Prompter prompter = new Prompter(new Scanner(System.in));
        List<Player> gamePlayers = new ArrayList<>();


        Player player = new Player();
        int id = 1;

        if (gamePlayers.isEmpty()) {
            player.setName(prompter.prompt("Please enter name: "));
            player.setID(id);
            if (player.getName().equals("")) {
                player.setName("CPU");
                gamePlayers.add(player);
            } else {
                gamePlayers.add(player);
            }
        } else {
            id++;
            player = new Player((prompter.prompt(("Please enter name: "))), id);
        }


        // prompt for username. if it's "" then return CPU


        System.out.println(player.getName());*/


        Prompter prompter = new Prompter(new Scanner(System.in));

        boolean quitNewPlayerName = false;
        String playerName = "";

        while (!quitNewPlayerName) {
            playerName = prompter.prompt("Please enter name ( or press return for CPU): ");
            switch (playerName.toLowerCase()) {
                case (""):
                    playerName = "CPU";
                    quitNewPlayerName = true;
                    break;
                case ("cpu"):
                    System.out.println("Invalid entry, for CPU player please press return.");
                    break;
                case ("computer"):
                    System.out.println("Invalid entry, for Computer/CPU player please press return.");
                    break;
                case("c p u"):case("cp u"):case("c pu"):
                    System.out.println("Invalid entry... but funny, I will allow it. However this is NOT a Computer/CPU player. You are now player: The-Comedian");
                    playerName=("The-Comedian");
                    quitNewPlayerName = true;
                    break;

                default:
                    quitNewPlayerName =true;
                    break;
            }
        }

        System.out.println(playerName);

    }




}