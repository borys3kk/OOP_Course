package agh.ics.oop;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;


public class GrassField extends AbstractWorldMap{
    private Random random = new Random();
    private int numOfGrass;
    public ArrayList<Grass> grasses;

    public GrassField(int numOfGrass){
        super(new Vector2d(Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 1), new Vector2d(Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 1));
        this.numOfGrass = numOfGrass;
        this.grasses = new ArrayList<>();
        placeGrass(numOfGrass);
    }

    public boolean isOccupied(Vector2d position){
        if (super.isOccupied(position))
            return true;
        for (Grass grass : grasses){
            if (grass.isAt(position)){
                return true;
            }
        }
        return false;
    }

    public boolean canMoveTo(Vector2d position) {
        if (super.canMoveTo(position)){
            Object checkPos = objectAt(position);
            if (checkPos instanceof Grass){
                placeGrass(1);
                Grass grassToMow = (Grass) checkPos;
                grasses.remove(grassToMow);
                mapBorder.removeElement(grassToMow.getPosition());
            }
            return true;
        }
        return false;
    }

    public boolean placeGrass(int numOfGrass){
        for (int i = 0; i < numOfGrass; i++){
            while (true){
                if (placeGrassRandom())
                    break;
            }
        }
        return true;
    }

    public boolean placeGrassRandom(){
        Vector2d new_pos = new Vector2d(random.nextInt((int) Math.sqrt(10 * numOfGrass)), random.nextInt((int) Math.sqrt(10 * numOfGrass)));
        if (objectAt(new_pos) == null){
            Grass grassToGrow = new Grass(new_pos);
            grasses.add(grassToGrow);
            grassToGrow.addObserver(this.mapBorder);
            mapBorder.newElement(grassToGrow.getPosition());
            return true;
        }
        return false;
    }

    public Object objectAt(Vector2d position) {
        if (super.objectAt(position) != null)
            return super.objectAt(position);
        for (Grass grass : grasses){
            if (grass.isAt(position)){
                return grass;
            }
        }
        return null;
    }

    public Vector2d lowerLeft(){
        return mapBorder.getLowerLeft();
    }

    public Vector2d upperRight(){
        return mapBorder.getUpperRight();
    }
}
