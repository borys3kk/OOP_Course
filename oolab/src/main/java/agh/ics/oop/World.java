package agh.ics.oop;

public class World {
    public static void main(String[] args)
    {
//        //Lab 1
//        System.out.println("System wystartował");
//        MoveDirection[] newArgs;
//        newArgs = change_args(args);
//        //run(newArgs);
//        System.out.println("System zakończył działanie");
//
//        //Lab 2
//        Vector2d position1 = new Vector2d(1, 2);
//        System.out.println(position1);
//        Vector2d position2 = new Vector2d(-2, 1);
//        System.out.println(position2);
//        System.out.println(position1.add(position2));
//        position1 = position1.add(position2);
//        System.out.println(position1);
//        System.out.println(MapDirection.NORTH);
//        System.out.println(MapDirection.SOUTH.next());
//        System.out.println(MapDirection.SOUTH.previous());

        //Lab 3
        Animal testAnimal = new Animal();
        System.out.println(testAnimal);
        System.out.println(testAnimal.isAt(new Vector2d(2,2)));

        testAnimal.move(MoveDirection.RIGHT);
        testAnimal.move(MoveDirection.FORWARD);
        testAnimal.move(MoveDirection.FORWARD);
        testAnimal.move(MoveDirection.FORWARD);
        System.out.println(testAnimal);
        testAnimal = new Animal();
        String[] directions = new String[]{"r", "forward", "f", "forward","r", "f", "f", "backward"};
        MoveDirection[] newDirections = OptionsParser.parse(directions);
        for (MoveDirection direction : newDirections){
            testAnimal.move(direction);
            System.out.println(testAnimal);
        }
    }

    public static MoveDirection[] change_args(String[] args)
    {
        MoveDirection[] directions = new MoveDirection[args.length];
        int i = 0;
        for (String arg:args)
        {
            switch (arg)
            {
                case "f":
                    directions[i] = MoveDirection.FORWARD;
                    break;
                case "b":
                    directions[i] = MoveDirection.BACKWARD;
                    break;
                case "l":
                    directions[i] = MoveDirection.LEFT;
                    break;
                case "r":
                    directions[i] = MoveDirection.RIGHT;
                    break;
                default:
                    directions[i] = MoveDirection.NONE;
            }
            i++;
        }
        return directions;
    }

    public static void run(MoveDirection[] args)
    {
        for (MoveDirection direct:args) {
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
