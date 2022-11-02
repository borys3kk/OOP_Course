package agh.ics.oop;

import java.util.List;

public class World {
    public static void main(String[] args)
    {
        //Lab 4
        System.out.println("debug");
        for (String arg : args){
            System.out.println(arg);
        }
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        System.out.println(map);
    }

}
