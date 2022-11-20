package com.javarush;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    @Disabled
    void mainTest() {
            assertTimeout(Duration.ofSeconds(22),()->Main.main(null) );
    }

}