package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RectangularMapTest {
    @Test
    public void goodMovementTest(){
        MoveDirection[] directions = OptionsParser.parse(new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"});
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        SimulationEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        assertTrue(engine.getAnimal(0).isAt(new Vector2d(2, -1)));
        assertFalse(engine.getAnimal(0).isAt(new Vector2d(4, 0)));
        assertTrue(engine.getAnimal(1).isAt(new Vector2d(3, 7)));
        assertFalse(engine.getAnimal(1).isAt(new Vector2d(0, 2)));
    }
    @Test
    public void wrongMovementTest() {
        MoveDirection[] directions = OptionsParser.parse(new String[]{"f", "l", "f", "r", "f", "f", "f", "f", "f"});
        IWorldMap map = new RectangularMap(10, 10);
        Vector2d[] positions = {new Vector2d(1, 4), new Vector2d(3, 5), new Vector2d(2, 2)};
        SimulationEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        assertTrue(engine.getAnimal(0).isAt(new Vector2d(1, 5)));
        assertFalse(engine.getAnimal(0).isAt(new Vector2d(1, 4)));
        assertTrue(engine.getAnimal(1).isAt(new Vector2d(2, 5)));
        assertFalse(engine.getAnimal(1).isAt(new Vector2d(3, 5)));
        assertTrue(engine.getAnimal(2).isAt(new Vector2d(2, 4)));
        assertFalse(engine.getAnimal(2).isAt(new Vector2d(2, 2)));
    }
}