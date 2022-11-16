package agh.ics.oop;

public class Grass {
    private Vector2d curPosition;
    public Grass(Vector2d position){
        this.curPosition = position;
    }

    public boolean isAt(Vector2d position){
        return curPosition.equals(position);
    }

    public Vector2d getPosition() {
        return curPosition;
    }

    public String toString() {
        return "*";
    }
}
