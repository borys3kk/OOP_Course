package agh.ics.oop;

import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

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
        for (Grass grass : grassField.grasses){
            assertTrue(grassField.canMoveTo(grass.getPosition()));
        }
    }
}
