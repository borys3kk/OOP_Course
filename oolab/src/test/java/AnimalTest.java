package agh.ics.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {
    Animal testerAnimal;
    @BeforeEach
    void init(){
        testerAnimal = new Animal();
    }

    @Test
    public void orientationTest() {
        testerAnimal.move(MoveDirection.RIGHT);
        assertEquals("Zwierze znajduję sie na współrzędnych: (2, 2) skierowane na: Wschód", testerAnimal.toString());
        testerAnimal.move(MoveDirection.FORWARD);
        assertEquals("Zwierze znajduję sie na współrzędnych: (3, 2) skierowane na: Wschód", testerAnimal.toString());
        testerAnimal.move(MoveDirection.LEFT);
        assertNotEquals("Zwierze znajduję sie na współrzędnych: (3, 2) skierowane na: Zachód", testerAnimal.toString());
        testerAnimal.move(MoveDirection.LEFT);
        testerAnimal.move(MoveDirection.BACKWARD);
        assertNotEquals("Zwierze znajduję sie na współrzędnych: (1, 1) skierowane na: Północ", testerAnimal.toString());
    }

    @Test
    public void positionTest() {
        testerAnimal.move(MoveDirection.FORWARD);
        assertEquals("Zwierze znajduję sie na współrzędnych: (2, 3) skierowane na: Północ", testerAnimal.toString());
        testerAnimal.move(MoveDirection.RIGHT);
        testerAnimal.move(MoveDirection.FORWARD);
        testerAnimal.move(MoveDirection.FORWARD);
        assertEquals("Zwierze znajduję sie na współrzędnych: (4, 3) skierowane na: Wschód", testerAnimal.toString());
        testerAnimal.move(MoveDirection.LEFT);
        testerAnimal.move(MoveDirection.BACKWARD);
        assertNotEquals("Zwierze znajduję sie na współrzędnych: (2, 3) skierowane na: Północ", testerAnimal.toString());
        testerAnimal.move(MoveDirection.LEFT);
        testerAnimal.move(MoveDirection.FORWARD);
        assertNotEquals("Zwierze znajduję sie na współrzędnych: (1, 2) skierowane na: Północ", testerAnimal.toString());
    }

    @Test
    public void outsideMapTest() {
        testerAnimal.move(MoveDirection.FORWARD);
        testerAnimal.move(MoveDirection.FORWARD);
        testerAnimal.move(MoveDirection.FORWARD);
        assertEquals("Zwierze znajduję sie na współrzędnych: (2, 4) skierowane na: Północ", testerAnimal.toString());
        testerAnimal.move(MoveDirection.RIGHT);
        testerAnimal.move(MoveDirection.FORWARD);
        testerAnimal.move(MoveDirection.FORWARD);
        testerAnimal.move(MoveDirection.FORWARD);
        assertEquals("Zwierze znajduję sie na współrzędnych: (4, 4) skierowane na: Wschód", testerAnimal.toString());
        testerAnimal.move(MoveDirection.FORWARD);
        assertNotEquals("Zwierze znajduję sie na współrzędnych: (5, 4) skierowane na: Wschód", testerAnimal.toString());
        testerAnimal.move(MoveDirection.LEFT);
        testerAnimal.move(MoveDirection.FORWARD);
        assertNotEquals("Zwierze znajduję sie na współrzędnych: (4, 5) skierowane na: Wschód", testerAnimal.toString());
    }

    private boolean searchGoodValues(String arg){
        switch (arg) {
            case "f", "forward", "l", "left", "b", "backward", "r", "right" -> {
                return true;
            }
        }
        return false;
    }

    @Test
    public void goodArgumentsTest(){
        String[] goodArguments = {"f", "forward", "l", "left", "b", "backward", "r", "right"};
        String[] badArguments = {"a", "bad", "input","hehe", "go left"};

        for (String s : goodArguments)
            assertTrue(searchGoodValues(s));
        for (String s : badArguments)
            assertFalse(searchGoodValues(s));
    }
}