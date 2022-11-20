package com.javarush;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HorseTest {


    @Test
    void firstArgNullConstructorTest() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 2, 2));
        assertEquals("Name cannot be null.", exception.getMessage());

    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  "})
    void firstArgBlankConstructorTest(String name) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 2, 3));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void secondNegativeArgTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("asd", -2, 3));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void thirdNegativeArgTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("asd", 2, -3));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }


    @Test
    void getNameTest() {
        Horse horse = new Horse("s", 2, 3);
        assertEquals("s", horse.getName());
    }

    @Test
    void getSpeedTest() {
        Horse horse = new Horse("asd", 5, 2);
        assertEquals(5, horse.getSpeed());
    }

    @Test
    void getDistanceTest() {
        Horse horse = new Horse("zxc", 7, 5);
        assertEquals(5, horse.getDistance());
    }

    @Test
    void distanceEqualsNullTest() {
        Horse horse = new Horse("zxc", 7);
        assertEquals(0, horse.getDistance());
    }


    @Test
    void randomDoubleTest() {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            new Horse("asd", 2, 2).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }

    }

    @ParameterizedTest
    @ValueSource(doubles = {0.5})
    void moveDistanceCountTest(double f) {
        try (MockedStatic<Horse> horse = Mockito.mockStatic(Horse.class)) {

            horse.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(f);
            Horse asd = new Horse("asd", 2, 2);
            asd.move();
            assertEquals(2 + 2 * 0.5, asd.getDistance());
        }
    }
}


