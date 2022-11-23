package agh.ics.oop;

import java.util.Comparator;

public class PositionComparator implements Comparator<Vector2d> {
    private boolean compareByX;

    public PositionComparator(boolean compareByX){
        this.compareByX = compareByX;
    }

    @Override
    public int compare(Vector2d v1, Vector2d v2){
        if (compareByX){
            if (v1.x < v2.x)
                return -1;
            else if (v1.x > v2.x)
                return 1;
            else if (v1.y < v2.y)
                return -1;
            else if (v1.y > v2.y)
                return 1;
        }
        else{
            if (v1.y < v2.y)
                return -1;
            else if (v1.y > v2.y)
                return 1;
            else if (v1.x < v2.x)
                return -1;
            else if (v1.x > v2.x)
                return 1;
        }
        return 0;
    }
}
