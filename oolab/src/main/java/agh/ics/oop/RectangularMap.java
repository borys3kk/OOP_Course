package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RectangularMap extends AbstractWorldMap implements IWorldMap{
    private final int width;
    private final int height;
    private final Vector2d leftLowerCorner;
    private final Vector2d rightUpperCorner;
    private final List<Animal> animals;

    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
        this.leftLowerCorner = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
        this.rightUpperCorner = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        this.animals = new ArrayList<>();
    }

    @Override
    public boolean canMoveTo(Vector2d position){
        return position.follows(leftLowerCorner) && position.precedes(rightUpperCorner) && !isOccupied(position);
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
    public boolean isOccupied(Vector2d position){
        for (Animal animal:animals){
            if (animal.isAt(position)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal:animals){
            if (animal.isAt(position)){
                return animal;
            }
        }
        return null;
    }
    @Override
    public Vector2d upperRight(){
        Vector2d curMax = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
        for (Animal animal: animals) {
            curMax = curMax.upperRight(animal.getCurPosition());
        }
        return curMax;
    }
    @Override
    public Vector2d lowerLeft(){
        Vector2d curMin = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        for (Animal animal: animals) {
            curMin = curMin.lowerLeft(animal.getCurPosition());
        }
        return curMin;
    }

    public String toString() {
        System.out.println("test");
        MapVisualizer map = new MapVisualizer(this);
        System.out.println(lowerLeft());
        return map.draw(lowerLeft(), upperRight());
    }
}
