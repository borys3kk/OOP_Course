package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class OptionsParser {
    public static MoveDirection[] parse(String args[]){
        List<MoveDirection> directions = new ArrayList<>();
        for (String direction: args){
            switch (direction) {
                case "f", "forward" -> directions.add(MoveDirection.FORWARD);
                case "b", "backward" -> directions.add(MoveDirection.BACKWARD);
                case "r", "right" -> directions.add(MoveDirection.RIGHT);
                case "l", "left" -> directions.add(MoveDirection.LEFT);
            }
        }
        MoveDirection[] newDirections = new MoveDirection[directions.size()];
        directions.toArray(newDirections);
        return newDirections;
    }
}
