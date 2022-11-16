package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class Animal {
    private Vector2d curPosition;
    private MapDirection curOrientation;
    private IWorldMap map;
    private List<IPositionChangeObserver> observers;

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map = map;
        this.curPosition = initialPosition;
        this.curOrientation = MapDirection.NORTH;
        this.observers = new ArrayList<>();
    }
    public Vector2d getCurPosition() {
        return curPosition;
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

    public boolean isAt(Vector2d position){
        return curPosition.equals(position);
    }

    public void move(MoveDirection direction){
        Vector2d new_pos;
        switch (direction){
            case LEFT -> curOrientation = curOrientation.previous();
            case RIGHT -> curOrientation = curOrientation.next();
            case FORWARD -> {
                new_pos = curPosition.add(curOrientation.toUnitVector());
                if (map.canMoveTo(new_pos))
                    positionChange(new_pos);
            }
            case BACKWARD -> {
                new_pos = curPosition.subtract(curOrientation.toUnitVector());
                if (map.canMoveTo(new_pos))
                    positionChange(new_pos);
            }

        }
    }

    void addObserver(IPositionChangeObserver observer){
        this.observers.add(observer);
    }

    void removeObserver(IPositionChangeObserver observer){
        this.observers.remove(observer);
    }

    void positionChange(Vector2d newPosition){
        for (IPositionChangeObserver observer : observers){
            observer.positionChange(this.curPosition, newPosition);
        }
        this.curPosition = newPosition;
    }
}
