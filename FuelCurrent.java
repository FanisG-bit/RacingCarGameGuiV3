package Game;

public class FuelCurrent {
    private Integer sumOfFuelLoss;
    private Integer totalFuel;
    public FuelCurrent(Integer sumOfFuelLoss, Integer totalFuel){
        this.sumOfFuelLoss = sumOfFuelLoss;
        this.totalFuel = totalFuel;
    }

    public Integer getSumOfFuelLoss() {
        return sumOfFuelLoss;
    }

    public Integer getTotalFuel() {
        return totalFuel;
    }
}