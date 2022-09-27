package com.connect4;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class CompLogicTest {
    private static Computer CPU_EASY;
    private static Computer CPU_MEDIUM;

    private final Board BOARD = new Board();

    @BeforeClass
    public static void beforeClass() throws Exception {
        CPU_EASY = new Computer("easy");
        CPU_MEDIUM = new Computer("medium");
        DummyController.setupGame();
        CPU_EASY = DummyController.computer1;
        CPU_MEDIUM = DummyController.computer2;
    }

    @Test
    public void getDifficultyLevel_shouldBeAllCaps_andReturnCorrectDifficulty() {
        assertEquals(CPU_EASY.getDifficultyLevel(), "EASY");
        assertEquals(CPU_MEDIUM.getDifficultyLevel(), "MEDIUM");
    }

    @Test
    public void name() {
        DummyController.playGame();
    }
}