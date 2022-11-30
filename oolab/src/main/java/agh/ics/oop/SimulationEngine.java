package agh.ics.oop;

import agh.ics.oop.gui.IAnimalObserver;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine, Runnable{

    private MoveDirection[] moves;
    private final IWorldMap map;
    private final List<Animal> animals;
    private int moveDelay = 0;
    private List<IAnimalObserver> observers = new ArrayList<IAnimalObserver>();

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] animalPositions){
        this.moves = moves;
        this.map = map;
        this.animals = new ArrayList<>();
        for (Vector2d position : animalPositions){
            Animal animal = new Animal(map, position);
            animals.add(animal);
            map.place(animal);
        }
    }

    public SimulationEngine(IWorldMap map, Vector2d[] animalPositions){
        this.moves = new MoveDirection[0];
        this.animals = new ArrayList<>();
        this.map = map;
        for(Vector2d position : animalPositions){
            Animal animal = new Animal(map, position);
            if (map.place(animal)){
                animals.add(animal);
            }
        }

    }
    public Animal getAnimal(int i){
        return animals.get(i);
    }

    public void setMoveDelay(int val){
        this.moveDelay = val;
    }

    public void addObserver(IAnimalObserver app){
        this.observers.add(app);
    }

    public void setMoves(MoveDirection[] directions){
        this.moves = directions;
    }
    @Override
    public void run(){
        int animalIndex = 0;
        for (MoveDirection move : this.moves){
            animals.get(animalIndex).move(move);
            animalIndex++;
            animalIndex = animalIndex%animals.size();
            for (IAnimalObserver observer : observers){
                observer.animalMoved();
            }
            try {
                System.out.println("Sleep");
                Thread.sleep(this.moveDelay);
            }
            catch (InterruptedException exception){
                System.out.println("Something happened: " + exception);
            }
        }
    }
}
