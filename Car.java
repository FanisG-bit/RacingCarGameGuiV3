package Game;

import java.util.ArrayList;

public class Car {
    private String color;
    private int fuel;
    private int positionRow = 9;
    private int positionColumn = 0;
    private int numberOfRoundsLost = 0;

    private ArrayList<FuelCurrent> currentFuel;
    public Car(){
        this.fuel = 120;
        currentFuel = new ArrayList<>();
    }
    public int getPositionRow(){
        return positionRow;
    }
    public int getPositionColumn(){
        return positionColumn;
    }
    public void setPositionRow(int positionRow) {
        this.positionRow = positionRow;
    }
    public void setPositionColumn(int positionColumn) {
        this.positionColumn = positionColumn;
    }
    public void setFuel(int fuel) {
        this.fuel = fuel;
    }
    public int getFuel() {
        return fuel;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getColor() {
        return color;
    }
    public ArrayList<FuelCurrent> getCurrentFuel() {
        return currentFuel;
    }
    public void setCurrentFuel(FuelCurrent current) {
        currentFuel.add(current);
    }
    public int getNumberOfRoundsLost() {
        return numberOfRoundsLost;
    }
    public void setNumberOfRoundsLost(int numberOfRoundsLost) {
        this.numberOfRoundsLost = numberOfRoundsLost;
    }
    @Override
    public String toString() {
        return "Car{" +
                "color='" + color + '\'' +
                ", fuel=" + fuel +
                '}';
    }
}