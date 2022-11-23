package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class Animal extends AbstractWorldMapElement{
    private MapDirection curOrientation;
    private IWorldMap map;

    public Animal(IWorldMap map, Vector2d initialPosition){
        super(initialPosition);
        this.map = map;
        this.curOrientation = MapDirection.NORTH;
        this.observers = new ArrayList<>();
    }

    public MapDirection getCurOrientation() {
        return curOrientation;
    }

    public String toString(){
        return switch (curOrientation) {
            case NORTH -> "N";
            case EAST -> "E";
            case SOUTH -> "S";
            case WEST -> "W";
        };
    }

    public void move(MoveDirection direction){
        Vector2d new_pos;
        switch (direction){
            case LEFT -> curOrientation = curOrientation.previous();
            case RIGHT -> curOrientation = curOrientation.next();
            case FORWARD -> {
                new_pos = position.add(curOrientation.toUnitVector());
                if (map.canMoveTo(new_pos))
                    positionChange(new_pos);
            }
            case BACKWARD -> {
                new_pos = position.subtract(curOrientation.toUnitVector());
                if (map.canMoveTo(new_pos))
                    positionChange(new_pos);
            }

        }
    }


    void positionChange(Vector2d newPosition){
        for (IPositionChangeObserver observer : observers){
            observer.positionChange(this.position, newPosition);
        }
        this.position = newPosition;
    }
}
