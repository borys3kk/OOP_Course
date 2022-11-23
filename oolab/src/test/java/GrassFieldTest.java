package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GrassFieldTest {
    @Test
    public void placeGrassTest() {
        int numGrass = 10;
        MoveDirection[] directions = OptionsParser.parse(new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"});
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        GrassField grassField = new GrassField(numGrass);
        IEngine engine = new SimulationEngine(directions, grassField, positions);
        engine.run();
        for (Grass grass : grassField.grasses){
            assertTrue(grass.isAt(grass.getPosition()));
        }
    }

    @Test
    public void correctMovementTest() {
        int numGrass = 10;
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        GrassField grassField = new GrassField(numGrass);
        MoveDirection[] directions = OptionsParser.parse(new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"});
        IEngine engine = new SimulationEngine(directions, grassField, positions);
        engine.run();
        for (int i = 0; i < grassField.grasses.size(); i++){
            assertTrue(grassField.canMoveTo(grassField.grasses.get(i).getPosition()));
        }
    }



    @Test
    public void placeAnimalTest(){
        GrassField map = new GrassField(10);
        try{
            map.place(new Animal(map, new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE)));
            Assertions.fail("Can't place at border");
        }
        catch (IllegalArgumentException ex){
            Assertions.assertTrue(true);
        }

        try{
            map.place(new Animal(map, new Vector2d(3, 1)));
            map.place(new Animal(map, new Vector2d(3, 1)));
            Assertions.fail("Can't place two times on the same spot");
        }
        catch (IllegalArgumentException ex)
        {
            Assertions.assertTrue(true);
        }
    }
}
