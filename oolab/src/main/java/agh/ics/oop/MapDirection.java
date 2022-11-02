package agh.ics.oop;

import java.util.Map;

public enum MapDirection
{
    NORTH,
    SOUTH,
    WEST,
    EAST;


    public String toString()
    {
        return switch (this)
        {
            case NORTH -> "N";
            case SOUTH -> "S";
            case EAST -> "E";
            case WEST -> "W";
        };
    }

    public MapDirection next()
    {
        return switch (this)
        {
            case NORTH -> MapDirection.EAST;
            case EAST -> MapDirection.SOUTH;
            case SOUTH -> MapDirection.WEST;
            case WEST -> MapDirection.NORTH;
        };
    }

    public MapDirection previous()
    {
        return switch (this)
            {
                case NORTH -> MapDirection.WEST;
                case WEST -> MapDirection.SOUTH;
                case SOUTH -> MapDirection.EAST;
                case EAST -> MapDirection.NORTH;
            };
    }

    public Vector2d toUnitVector()
    {
        return switch (this)
        {
            case NORTH -> new Vector2d(0, 1);
            case SOUTH -> new Vector2d(0, -1);
            case EAST -> new Vector2d(1, 0);
            case WEST -> new Vector2d(-1, 0);
        };
    }
}