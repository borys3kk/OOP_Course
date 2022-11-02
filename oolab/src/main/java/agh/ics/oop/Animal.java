package agh.ics.oop;

public class Animal {
    private Vector2d curPosition;
    private MapDirection curOrientation;
    private IWorldMap map;

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map = map;
        this.curPosition = initialPosition;
        this.curOrientation = MapDirection.NORTH;
    }

    public Animal(IWorldMap map){
        this(map, new Vector2d(2, 2));
    }

    public Animal() {
        this(new RectangularMap(5, 5));
    }
    public Vector2d getCurPosition() {
        return curPosition;
    }

    public MapDirection getCurOrientation() {
        return curOrientation;
    }

    public String toString(){
        return switch (curOrientation) {
            case NORTH -> "N";
            case EAST -> "E";
            case SOUTH -> "S";
            case WEST -> "W";
        };
    }

    public boolean isAt(Vector2d position){
        return curPosition.equals(position);
    }

    public void move(MoveDirection direction){
        switch (direction){
            case LEFT -> curOrientation = curOrientation.previous();
            case RIGHT -> curOrientation = curOrientation.next();
            case FORWARD -> {
                if (map.canMoveTo(curPosition.add(curOrientation.toUnitVector())))
                    curPosition = curPosition.add(curOrientation.toUnitVector());
            }
            case BACKWARD -> {
                if (map.canMoveTo(curPosition.subtract(curOrientation.toUnitVector())))
                    curPosition=curPosition.subtract(curOrientation.toUnitVector());
            }

        }
    }

}
