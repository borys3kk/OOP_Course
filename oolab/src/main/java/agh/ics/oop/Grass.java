package agh.ics.oop;

import java.util.ArrayList;

public class Grass extends AbstractWorldMapElement{
    private Vector2d curPosition;
    public Grass(Vector2d position){
        super(position);
        this.observers = new ArrayList<>();
    }

    public String toString() {
        return "*";
    }
}
