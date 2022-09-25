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
            /*
             * From Orlando - I haven't tested this class. However, I'm guessing that
             * the for loop is printing out the same name below (line 34)?
             * If yes, it may be that everytime you call setName and setId
             * you are changing it on the same object. You're storing the same object
             * in the array 3 times. So, when you change the name and ID on one then
             * you change it on all. I'd recommend that you uncomment line 29
             * and comment out lines 30 and 31. See if that works.
             */
            // player = New Player(name, id)
            player.setName(name); // comment out
            player.setID(id);     // comment out
            players.add(player);
        }
        for (Player item: players){
            System.out.println(player.getName());
        }
    }

}