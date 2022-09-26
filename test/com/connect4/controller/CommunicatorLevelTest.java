package com.connect4.controller;

import com.apps.util.Prompter;

import java.util.Scanner;

class CommunicatorLevelTest {




    public static void main(String[] args) {
        final Prompter prompter = new Prompter(new Scanner(System.in));
        String level = "2";

        boolean quit = false;
        while (!quit){
        String selectLevel = prompter.prompt("Select number for difficulty level:\n1-Easy\n2-Medium\n3-Hard\n");
        switch (selectLevel) {
            case ("1"):
                level = "Easy";
                quit = true;
                break;
            case ("2"):
                level = "Medium";
                quit = true;
                break;
            case ("3"):
                level = "Hard";
                quit = true;
                break;
            default:
                System.out.println("Invalid input, please provide [1] for Easy, [2] for Medium or [3] for Hard.");

        }
        }

        System.out.println(level);

    }



}