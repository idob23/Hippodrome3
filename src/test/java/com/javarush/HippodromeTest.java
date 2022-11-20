package com.javarush;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    void constructorNullTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void constructorBlankTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorseTest() {
        ArrayList<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("a" + i, i, 2));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(hippodrome.getHorses(), horses);
    }

    @Test
    void moveTest() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
           horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse horse : horses) {
            Mockito.verify(horse).move();
        }
    }

    @Test
    void getWinnerTest(){
        List<Horse>list=new ArrayList<>();
        Horse h1 = new Horse("a",2,1);
        Horse h2 = new Horse("b",2,4);
        Horse h3 = new Horse("c",2,2);
        list.add(h1);
        list.add(h2);
        list.add(h3);
        Hippodrome hippodrome = new Hippodrome(list);

        assertSame(h2,hippodrome.getWinner());

    }


}