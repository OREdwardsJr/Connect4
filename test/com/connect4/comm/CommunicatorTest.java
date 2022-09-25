package com.connect4.comm;

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



        // THIS IS TO TEST SINGLE PLAYER ENTRY
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


        System.out.println(player.getName());




    }




}