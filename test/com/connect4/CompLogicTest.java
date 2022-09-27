package com.connect4;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class CompLogicTest {
    private final Computer CPU_EASY = new Computer("easy");
    private final Computer CPU_MEDIUM = new Computer("medium");

    @Test
    public void getDifficultyLevel_shouldBeAllCaps_andReturnCorrectDifficulty() {
        assertEquals(CPU_EASY.getDifficultyLevel(), "EASY");
        assertEquals(CPU_MEDIUM.getDifficultyLevel(), "MEDIUM");
    }
}