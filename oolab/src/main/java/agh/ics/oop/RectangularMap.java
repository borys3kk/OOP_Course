package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RectangularMap extends AbstractWorldMap{

    public RectangularMap(int width, int height){
        super(new Vector2d(0, 0), new Vector2d(width - 1, height - 1));
    }

    public Vector2d lowerLeft(){
        return mapLowerLeft;
    }
    public Vector2d upperRight(){
        return mapUpperRight;
    }
}
