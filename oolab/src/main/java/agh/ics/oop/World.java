package agh.ics.oop;

import java.time.format.SignStyle;

public class World {
    public static void main(String[] args)
    {
        System.out.println("System wystartował");
        Direction[] newArgs;
        newArgs = change_args(args);
        run(newArgs);
        System.out.println("System zakończył działanie");
    }

    public static Direction[] change_args(String[] args)
    {
        Direction[] directions = new Direction[args.length];
        int i = 0;
        for (String arg:args)
        {
            switch (arg)
            {
                case "f":
                    directions[i] = Direction.FORWARD;
                    break;
                case "b":
                    directions[i] = Direction.BACKWARD;
                    break;
                case "l":
                    directions[i] = Direction.LEFT;
                    break;
                case "r":
                    directions[i] = Direction.RIGHT;
                    break;
                default:
                    directions[i] = Direction.NONE;
            }
            i++;
        }
        return directions;
    }

    public static void run(Direction[] args)
    {
        for (Direction direct:args) {
            switch (direct)
            {
                case FORWARD:
                    System.out.println("Zwierzak idzie do przodu");
                    break;
                case BACKWARD:
                    System.out.println("Zwierzak idzie do tyłu");
                    break;
                case LEFT:
                    System.out.println("Zwierzak skręca w lewo");
                    break;
                case RIGHT:
                    System.out.println("Zwierzak skręca w prawo");
                    break;
                default:
            }
        }
    }
}
