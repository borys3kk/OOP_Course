package agh.ics.oop;

import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GrassFieldTest {
    @Test
    public void placeGrassTest() {
        int grass = 10;
        MoveDirection[] directions = OptionsParser.parse(new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"});
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        GrassField grassField = new GrassField(grass);
        System.out.println(grassField.grassPositions);
        IEngine engine = new SimulationEngine(directions, grassField, positions);
        engine.run();
        for (int i = 0; i < grass; i++) {
            System.out.println(grassField.getGrassAt(i).getPosition());
        }
    }

    @Test
    public void correctMovementTest() {
        int grass = 10;
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        GrassField grassField = new GrassField(grass);
        MoveDirection[] directions = OptionsParser.parse(new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"});
        IEngine engine = new SimulationEngine(directions, grassField, positions);
        engine.run();
        System.out.println(grassField);
        for (int i = 0; i < grass; i++) {
            assertTrue(grassField.canMoveTo(grassField.getGrassAt(i).getPosition()));
        }
    }
}