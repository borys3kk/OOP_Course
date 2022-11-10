package agh.ics.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {
    private int mapX = 5;
    private int mapY = 5;
    IWorldMap map = new RectangularMap(mapX, mapY);
    Animal testerAnimal;
    @BeforeEach
    void init(){
        testerAnimal = new Animal(map);
    }

    @Test
    public void orientationTest() {
        testerAnimal.move(MoveDirection.RIGHT);
        assertEquals(MapDirection.EAST, testerAnimal.getCurOrientation());
        testerAnimal.move(MoveDirection.FORWARD);
        assertEquals(MapDirection.EAST, testerAnimal.getCurOrientation());
        testerAnimal.move(MoveDirection.LEFT);
        testerAnimal.move(MoveDirection.LEFT);
        assertEquals(MapDirection.WEST, testerAnimal.getCurOrientation());
        testerAnimal.move(MoveDirection.LEFT);
        testerAnimal.move(MoveDirection.BACKWARD);
        assertNotEquals(MapDirection.NORTH, testerAnimal.getCurOrientation());
        testerAnimal.move(MoveDirection.LEFT);
        testerAnimal.move(MoveDirection.BACKWARD);
        assertNotEquals(MapDirection.NORTH, testerAnimal.getCurOrientation());
    }

    @Test
    public void positionTest() {
        testerAnimal.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(2, 3), testerAnimal.getCurPosition());
        testerAnimal.move(MoveDirection.RIGHT);
        testerAnimal.move(MoveDirection.FORWARD);
        testerAnimal.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(4, 3), testerAnimal.getCurPosition());
        testerAnimal.move(MoveDirection.LEFT);
        testerAnimal.move(MoveDirection.BACKWARD);
        assertEquals(new Vector2d(4, 2), testerAnimal.getCurPosition());
        testerAnimal.move(MoveDirection.FORWARD);
        assertNotEquals(new Vector2d(2, 3), testerAnimal.getCurPosition());
        testerAnimal.move(MoveDirection.LEFT);
        testerAnimal.move(MoveDirection.FORWARD);
        assertNotEquals(new Vector2d(1, 2), testerAnimal.getCurPosition());
    }

    @Test
    public void outsideMapTest() {
        testerAnimal.move(MoveDirection.FORWARD);
        testerAnimal.move(MoveDirection.FORWARD);
        testerAnimal.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(2,5), testerAnimal.getCurPosition());
        testerAnimal.move(MoveDirection.RIGHT);
        testerAnimal.move(MoveDirection.FORWARD);
        testerAnimal.move(MoveDirection.FORWARD);
        testerAnimal.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(5,5), testerAnimal.getCurPosition());
        testerAnimal.move(MoveDirection.FORWARD);
        testerAnimal.move(MoveDirection.LEFT);
        testerAnimal.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(6,6), testerAnimal.getCurPosition());
    }

    @Test
    public void goodMoves(){
        MoveDirection[] directions = new OptionsParser().parse(new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"});
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        assertTrue(engine.getAnimal(0).isAt(new Vector2d(2, -1)));
        assertFalse(engine.getAnimal(0).isAt(new Vector2d(4, 0)));
        assertTrue(engine.getAnimal(1).isAt(new Vector2d(3, 7)));
        assertFalse(engine.getAnimal(1).isAt(new Vector2d(0, 2)));
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