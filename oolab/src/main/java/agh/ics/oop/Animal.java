package agh.ics.oop;

public class Animal {
    private Vector2d curPosition = new Vector2d(2, 2);
    private MapDirection curOrientation = MapDirection.NORTH;

    public String toString(){
        return "Zwierze znajduję sie na współrzędnych: " + curPosition.toString() + " skierowane na: " + curOrientation.toString();
    }

    public boolean isAt(Vector2d position){
        return curPosition.equals(position);
    }

    public void move(MoveDirection direction){
        switch (direction){
            case LEFT -> curOrientation = curOrientation.previous();
            case RIGHT -> curOrientation = curOrientation.next();
            case FORWARD -> {
                if (curPosition.add(curOrientation.toUnitVector()).follows(new Vector2d(-4, -4)) &&
                        curPosition.add(curOrientation.toUnitVector()).precedes(new Vector2d(4, 4)))
                    curPosition = curPosition.add(curOrientation.toUnitVector());
            }
            case BACKWARD -> {
                if (curPosition.subtract(curOrientation.toUnitVector()).follows(new Vector2d(-4, -4)) &&
                        curPosition.subtract(curOrientation.toUnitVector()).precedes(new Vector2d(4, 4)))
                    curPosition=curPosition.subtract(curOrientation.toUnitVector());
            }

        }
    }

}
