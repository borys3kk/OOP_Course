package agh.ics.oop;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{

    protected Map<Vector2d, Animal> animals = new LinkedHashMap<>();
    protected MapVisualizer visualizer = new MapVisualizer(this);
    protected Vector2d mapUpperRight;
    protected Vector2d mapLowerLeft;
    protected MapBoundary mapBorder = new MapBoundary();

    public AbstractWorldMap(Vector2d bottomLeftCorner, Vector2d upperRightCorner){
        this.mapLowerLeft = bottomLeftCorner;
        this.mapUpperRight = upperRightCorner;
    }

    @Override
    public void positionChange(Vector2d oldPosition, Vector2d newPosition){
        Animal animal = this.animals.get(oldPosition);
        this.animals.remove(oldPosition);
        this.animals.put(newPosition, animal);
    }

    public abstract Vector2d lowerLeft();
    public abstract Vector2d upperRight();

    public boolean canMoveTo(Vector2d position){
        return position.follows(mapLowerLeft) && position.precedes(mapUpperRight) && !(objectAt(position) instanceof Animal);
    }

    public boolean place(Animal animal){
        if (canMoveTo(animal.getPosition())){
            animals.put(animal.getPosition(), animal);
            animal.addObserver(this);
            animal.addObserver(mapBorder);
            mapBorder.newElement(animal.getPosition());
            return true;
        }
        throw new IllegalArgumentException(animal.getPosition() + " is not a valid position for placing");
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
