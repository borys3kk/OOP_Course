package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GrassField extends AbstractWorldMap{
    private final int clumpsOfGrass;
    private final Vector2d lowerLeftCorner;
    private final Vector2d upperRightCorner;
    public List<Grass> grassPositions = new ArrayList<Grass>();
    public List<Animal> animals = new ArrayList<Animal>();

    public GrassField(int num){
        this.clumpsOfGrass = num;
        this.lowerLeftCorner = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
        this.upperRightCorner = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Random random = new Random();
        for (int i = 0; i < num; i++){
            while (true) {
                int x = random.nextInt((int) Math.sqrt(10 * num));
                int y = random.nextInt((int) Math.sqrt(10 * num));
                if (!isOccupied(new Vector2d(x, y)) && canPlaceGrass(new Vector2d(x, y))){
                    this.grassPositions.add(new Grass(new Vector2d(x, y)));
                    break;
                }
            }
        }
    }

    public boolean canPlaceGrass(Vector2d position) {
        for(Grass grass : grassPositions){
            if (grass.getPosition().equals(position)) {
                return false;
            }
        }
        return true;
    }

    public Grass getGrassAt(int index){
        return grassPositions.get(index);
    }

    public Vector2d getUpperRightCorner(){
        return upperRightCorner;
    }

    public Vector2d getLowerLeftCorner(){
        return lowerLeftCorner;
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

    public boolean place(Animal animal){
        if (!isOccupied(animal.getCurPosition())){
            animals.add(animal);
            return true;
        }
        else {
            Object object = objectAt(animal.getCurPosition());
            if (object instanceof Grass){
                for(Grass grass : grassPositions){
                    if (grass.getPosition().equals(animal.getCurPosition())){
                        grassPositions.remove(grass);
                        break;
                    }
                }
                animals.add(animal);
            }
            else{
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean canMoveTo(Vector2d position){
        System.out.println(isOccupied(position));
        return position.follows(this.lowerLeftCorner) && position.precedes(this.upperRightCorner) && !isOccupied(position);
    }

    @Override
    public String toString(){
        MapVisualizer map = new MapVisualizer(this);
        return map.draw(lowerLeft(), upperRight());
    }
}
