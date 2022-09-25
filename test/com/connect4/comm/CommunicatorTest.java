package com.connect4.comm;

import com.apps.util.Prompter;
import com.connect4.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class CommunicatorTest {
    public static void main(String[] args) {
        //TODO: list is not populating correctly
        Prompter prompter = new Prompter(new Scanner(System.in));
        List<Player> players = new ArrayList<>();
        Player player = new Player();

        for (int i = 1; i < 3; i++){
            String name =prompter.prompt("Please enter name: ");
            int id= i;
            player.setName(name);
            player.setID(id);
            players.add(player);
        }
        for (Player item: players){
            System.out.println(player.getName());
        }
    }

}