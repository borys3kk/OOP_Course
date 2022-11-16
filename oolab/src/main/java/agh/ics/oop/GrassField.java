package agh.ics.oop;

import java.util.ArrayList;
import java.util.Random;


public class GrassField extends AbstractWorldMap{
    private Random random = new Random();

    public ArrayList<Grass> grasses;

    public GrassField(int numOfGrass){
        super(new Vector2d(Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 1), new Vector2d(Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 1));
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


    public boolean placeGrass(int numOfGrass){
        for (int i = 0; i < numOfGrass; i++){
            while (true){
                Vector2d new_pos = new Vector2d(random.nextInt((int) Math.sqrt(10 * numOfGrass)), random.nextInt((int) Math.sqrt(10 * numOfGrass)));
                if (objectAt(new_pos) == null){
                    this.grasses.add(new Grass(new_pos));
                    break;
                }
            }
        }
        return true;
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
        Vector2d drawLeftLower = mapUpperRight;
        for (Vector2d position : animals.keySet()){
            drawLeftLower = drawLeftLower.lowerLeft(position);
        }
        for (Grass grass : grasses){
            drawLeftLower = drawLeftLower.lowerLeft(grass.getPosition());
        }
        return drawLeftLower;
    }

    public Vector2d upperRight(){
        Vector2d drawRightUpper = mapLowerLeft;
        for (Vector2d position : animals.keySet()){
            drawRightUpper = drawRightUpper.upperRight(position);
        }
        for (Grass grass : grasses){
            drawRightUpper = drawRightUpper.upperRight(grass.getPosition());
        }
        return drawRightUpper;
    }
}
