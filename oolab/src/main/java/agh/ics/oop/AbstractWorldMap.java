package agh.ics.oop;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{

    protected Map<Vector2d, Animal> animals = new LinkedHashMap<>();
    protected MapVisualizer visualizer = new MapVisualizer(this);
    protected Vector2d mapUpperRight;
    protected Vector2d mapLowerLeft;

    public AbstractWorldMap(Vector2d bottomLeftCorner, Vector2d upperRightCorner){
        this.mapLowerLeft = bottomLeftCorner;
        this.mapUpperRight = upperRightCorner;
    }

    public void positionChange(Vector2d oldPosition, Vector2d newPosition){
        Animal animal = this.animals.get(oldPosition);
        this.animals.remove(oldPosition);
        this.animals.put(newPosition, animal);
    }

    abstract Vector2d lowerLeft();
    abstract Vector2d upperRight();

    public boolean canMoveTo(Vector2d position){
        return position.follows(mapLowerLeft) && position.precedes(mapUpperRight) && !(objectAt(position) instanceof Animal);
    }

    public boolean place(Animal animal){
        if (canMoveTo(animal.getCurPosition())){
            animals.put(animal.getCurPosition(), animal);
            animal.addObserver(this);
            return true;
        }
        return false;
    }

    public boolean isOccupied(Vector2d position){
        return objectAt(position) instanceof Animal;
    }

    public Object objectAt(Vector2d position){
        return animals.get(position);
    }

    public String toString(){
        return visualizer.draw(lowerLeft(), upperRight());
    }
}
