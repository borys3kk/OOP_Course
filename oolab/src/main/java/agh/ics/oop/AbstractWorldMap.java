package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWorldMap implements IWorldMap{
    protected List<Animal> animals = new ArrayList<Animal>();
    protected List<Grass> grassPositions = new ArrayList<Grass>();

    public abstract Vector2d upperRight();
    public abstract Vector2d lowerLeft();

    @Override
    public boolean canMoveTo(Vector2d position){
        return position.follows(lowerLeft()) && position.precedes(upperRight()) && !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal){
        if (!isOccupied(animal.getCurPosition())){
            animals.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position){
        for (Animal animal : animals){
            if (animal.getCurPosition().equals(position)){
                return animal;
            }
        }
        for (Grass grass:grassPositions){
            if (grass.getPosition().equals(position)){
                return grass;
            }
        }
        return null;
    }
    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal animal : animals) {
            if (animal.getCurPosition().equals(position)) {
                return true;
            }
        }
        for (Grass grass : grassPositions) {
            if (grass.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public String toString(){
        MapVisualizer map = new MapVisualizer(this);
        return map.draw(lowerLeft(), upperRight());
    }
}
