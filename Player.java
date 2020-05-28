package Game;

import java.util.*;
public class Player {
    private String name;
    private Car car;
    private Grid grid;
    private Random random = new Random();
    public Player(Car car, Grid grid){
        this.car = car;
        this.grid = grid;
    }
    public int throwDie(){
        return 1 + random.nextInt(6);
    }
    public String getName() {
        return name;
    }
    public int moveCar(){
        int random = throwDie();
        grid.moveInGrid(car, random);
        return random;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", car=" + car +
                ", grid=" + grid +
                ", random=" + random +
                '}';
    }
}