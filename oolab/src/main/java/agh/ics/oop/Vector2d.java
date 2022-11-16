package agh.ics.oop;

import java.util.Objects;
import java.util.Vector;

public class Vector2d{
    public final int x;
    public final  int y;

    public Vector2d(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.x, this.y);
    }
    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }

    public boolean precedes(Vector2d other)
    {
        if (this.x <= other.x && this.y <= other.y)
            return true;
        return false;
    }

    public boolean follows(Vector2d other)
    {
        if (this.x >= other.x && this.y >= other.y)
            return true;
        return false;
    }

    public Vector2d add(Vector2d other)
    {
        return new Vector2d(this.x + other.x, this.y + other.y);
    }

    public Vector2d subtract(Vector2d other)
    {
        return new Vector2d(this.x - other.x, this.y - other.y);
    }

    public Vector2d upperRight(Vector2d other)
    {
        return new Vector2d(Math.max(this.x, other.x), Math.max(this.y, other.y));
    }

    public Vector2d lowerLeft(Vector2d other)
    {
        return new Vector2d(Math.min(this.x, other.x),Math.min(this.y, other.y));
    }

    public Vector2d opposite()
    {
        return new Vector2d(-x, -y);
    }

    public boolean equals(Object other)
    {
        if (other instanceof Vector2d)
        {
            return this.x == ((Vector2d) other).x && this.y == ((Vector2d) other).y;
        }
        else
        {
                return false;
        }
    }
}
