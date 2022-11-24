package agh.ics.oop;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver{

    Comparator<Vector2d> xComp = new PositionComparator(true);
    Comparator<Vector2d> yComp = new PositionComparator(false);

    SortedSet<Vector2d> xSorted = new TreeSet<>(xComp);
    SortedSet<Vector2d> ySorted = new TreeSet<>(yComp);

    @Override
    public void positionChange(Vector2d oldPosition, Vector2d newPosition){
        removeElement(oldPosition);
        newElement(newPosition);
    }

    public void newElement(Vector2d element){
        xSorted.add(element);
        ySorted.add(element);
    }

    public void removeElement(Vector2d element){
        xSorted.remove(element);
        ySorted.remove(element);
    }

    public void printElements(){
        System.out.println("xSorted:");
        for (Vector2d elem : xSorted){
            System.out.println(elem);
        }
        System.out.println("ySorted:");
        for (Vector2d elem : ySorted){
            System.out.println(elem);
        }
    }
    public Vector2d getLowerLeft(){
        return new Vector2d(xSorted.first().x,ySorted.first().y);
    }

    public Vector2d getUpperRight(){
        return new Vector2d(xSorted.last().x, ySorted.last().y);
    }
}
