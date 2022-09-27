package com.connect4.client;

import com.connect4.controller.Controller;

class Connect4 {
    private static final Controller controller = new Controller();

    public static void main(String[] args) {
        controller.go();
    }
}