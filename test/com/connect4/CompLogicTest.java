package com.connect4;

import com.connect4.controller.Controller;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class CompLogicTest {
    private static Computer CPU_EASY = new Computer("easy");
    private static Computer CPU_MEDIUM = new Computer("medium");

    private final Board BOARD = new Board();

    @BeforeClass
    public static void beforeClass() throws Exception {
        ControllerClassForTesting.setupGame();
        CPU_EASY = ControllerClassForTesting.computer1;
        CPU_MEDIUM = ControllerClassForTesting.computer2;
    }

    @Test
    public void getDifficultyLevel_shouldBeAllCaps_andReturnCorrectDifficulty() {
        assertEquals(CPU_EASY.getDifficultyLevel(), "EASY");
        assertEquals(CPU_MEDIUM.getDifficultyLevel(), "MEDIUM");
    }

    @Test
    public void name() {
        ControllerClassForTesting.playGame();
    }
}