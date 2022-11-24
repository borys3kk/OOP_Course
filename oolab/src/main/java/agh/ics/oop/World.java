package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Application;

import java.util.List;
import java.util.spi.AbstractResourceBundleProvider;

public class World {
    public static void main(String[] args)
    {
        Application.launch(App.class, args);

//        MoveDirection[] directions = OptionsParser.parse(new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"});
//        AbstractWorldMap map = new GrassField(10);
//        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(2, 4)};
//        IEngine engine = new SimulationEngine(directions, map, positions);
//        engine.run();
//        System.out.println(map);
    }

}
