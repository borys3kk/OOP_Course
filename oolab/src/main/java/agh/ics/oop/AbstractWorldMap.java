package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWorldMap implements IWorldMap{

    protected List<Animal> animals = new ArrayList<>();
    protected MapVisualizer visualizer = new MapVisualizer(this);
    protected Vector2d mapUpperRight;
    protected Vector2d mapLowerLeft;

    public AbstractWorldMap(Vector2d bottomLeftCorner, Vector2d upperRightCorner){
        this.mapLowerLeft = bottomLeftCorner;
        this.mapUpperRight = upperRightCorner;
    }

    abstract Vector2d lowerLeft();
    abstract Vector2d upperRight();

    public boolean canMoveTo(Vector2d position){
        return position.follows(mapLowerLeft) && position.precedes(mapUpperRight) && !(objectAt(position) instanceof Animal);
    }

    public boolean place(Animal animal){
        if (canMoveTo(animal.getCurPosition())){
            animals.add(animal);
            return true;
        }
        return false;
    }

    public boolean isOccupied(Vector2d position){
        return objectAt(position) instanceof Animal;
    }

    public Object objectAt(Vector2d position){
        for (Animal animal : animals){
            if (animal.isAt(position)){
                return animal;
            }
        }
        return null;
    }

    public String toString(){
        return visualizer.draw(lowerLeft(), upperRight());
    }
}
