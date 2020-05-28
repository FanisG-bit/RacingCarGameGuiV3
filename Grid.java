package Game;

import javax.swing.*;
import java.util.*;
public class Grid {
    private int[][] gridV; //table that will have the values in the background (back-end)
    private String[][] gridC = {  //table that will have the colors and cars (front-end)
            {".E", "[]", "[]", "[]", "[]", ".B", "[]", "[]", "[]", "[]"}, // [] = Gray
            {"[]", "[]", "[]", "[]", "[]", "[]", "[]", "[]", "[]", "[]"}, // .E = END
            {"[]", "[]", ".B", "[]", "[]", "[]", ".G", "[]", "[]", "[]"}, // .S = START
            {"[]", "[]", "[]", "[]", "[]", "[]", "[]", "[]", "[]", "[]"}, // .B = BLACK
            {"[]", "[]", ".G", "[]", "[]", "[]", "[]", "[]", ".B", "[]"}, // .G = GREEN
            {"[]", "[]", "[]", "[]", ".G", "[]", "[]", "[]", "[]", "[]"},
            {"[]", "[]", ".B", "[]", "[]", "[]", "[]", "[]", "[]", "[]"},
            {"[]", "[]", "[]", "[]", "[]", "[]", "[]", ".G", "[]", "[]"},
            {"[]", "[]", "[]", "[]", "[]", ".B", "[]", "[]", "[]", "[]"},
            {".S", "[]", "[]", "[]", "[]", "[]", "[]", "[]", "[]", "[]"}};
    private Random random = new Random();

    public void createGrid(){
        gridV = new int[10][10];
        grayInitialization();
        showColouredGrid();
        showValuesGrid();
    }

    public void showColouredGrid(){
        System.out.print("\n");
        System.out.println("     0   1   2   3   4   5   6   7   8   9");
        System.out.println("     -------------------------------------");
        for(int i=0;i<gridC.length;i++){
            System.out.print(i + "|\t");
            for(int j=0;j<gridC[i].length;j++){
                System.out.print(" "+ gridC[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public void showValuesGrid(){
        System.out.print("\n");
        System.out.println("     0   1   2   3   4   5   6   7   8   9");
        System.out.println("     -------------------------------------");
        for(int i=0;i<gridV.length;i++){
            System.out.print(i + "|\t");
            for(int j=0;j<gridV[i].length;j++){
                System.out.print(" "+ gridV[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public int[][] getGridValuesArray(){
        return gridV;
    }

    private void grayInitialization(){
        for(int i=0;i<gridV.length;i++){
            for(int j=0;j<gridV[i].length;j++){
                if(i==0&&j==5 || i==2&&j==2 || i==2&&j==6
                        || i==4&&j==2 || i==4&&j==8 || i==5&&j==4
                        || i==6&&j==2 || i==7&&j==7 || i==8&&j==5 || i==9&&j==0){
                    gridV[i][j] = 0; // this is the value of the special tiles.
                }
                else {
                    gridV[i][j] = 1 + random.nextInt(3);
                }
            }
        }
    }

    private void blackTiles(Car car){
        if((car.getPositionRow() == 0 && car.getPositionColumn() == 5) || (car.getPositionRow() == 2 && car.getPositionColumn() == 2) ||
                (car.getPositionRow() == 4 && car.getPositionColumn() == 8) || (car.getPositionRow() == 6 && car.getPositionColumn() == 2) ||
                (car.getPositionRow() == 8) && (car.getPositionColumn() == 5)){
            System.out.println("Black Tile!");
            JOptionPane.showMessageDialog(null, "BLACK TILE!\nBack to the beginning!");
            car.setPositionRow(9);
            car.setPositionColumn(0);
        }
    }

    private void greenTiles(Car car){
        if((car.getPositionRow() == 2 && car.getPositionColumn() == 6) || (car.getPositionRow() == 4 && car.getPositionColumn() == 2)
                || (car.getPositionRow() == 5 && car.getPositionColumn() == 4) || (car.getPositionRow() == 7 && car.getPositionColumn() == 7)){
            int messageWise = (car.getFuel() * 50/100);
            int increasedFuel = car.getFuel() + (car.getFuel() * 50/100);
            System.out.println("Green Tile! - Extra " + messageWise + " fuel!" );
            JOptionPane.showMessageDialog(null, "GREEN TILE!\nExtra " + messageWise + " fuel!");
            car.setFuel(increasedFuel);
        }
    }

    public void updateGridC(Car car1, Car car2){
        for(int i=0;i<gridC.length;i++){
            for(int j=0;j<gridC[i].length;j++){
                gridC[i][j] = "[]";
                if(i==0&&j==0){
                    gridC[i][j] = ".E";
                }
                if(i==9&&j==0){
                    gridC[i][j] = ".S";
                }
                if(i==0&&j==5 || i==2&&j==2 || i==4&&j==8 || i==6&&j==2 || i==8&&j==5){
                    gridC[i][j] = ".B";
                }
                if(i==2&&j==6 || i==4&&j==2 || i==5&&j==4 || i==7&&j==7){
                    gridC[i][j] = ".G";
                }
            }
        }
        try{
            gridC[car1.getPositionRow()][car1.getPositionColumn()] = "C1";
        }catch (IndexOutOfBoundsException e){
            System.out.println("Player C1 wins.");
        }
        try{
            gridC[car2.getPositionRow()][car2.getPositionColumn()] = "C2";
        }catch (IndexOutOfBoundsException e){
            System.out.println("Player C2 wins.");
        }
    }

    private boolean checkOutOfFuel(Car car) throws NumberFormatException {
        boolean done = false;
        if (car.getFuel() <= 0){
            while(!done){
                try{
                    String answerStr = JOptionPane.showInputDialog("You run out of fuel :(\nPlease enter the number of one of the below options:\n1) Your car will " +
                            "return to the START and gain a random amount of fuel litres in the range of [1-120].\n2) You will lose turns and gain 20 litres " +
                            "of fuel per turn (1-6 turns).");
                    int answer = Integer.parseInt(answerStr);
                    if (answer != 1 && answer != 2){
                        throw new NotAcceptableDataException("Choices are either 1 or 2.\nPlease pick one of these numbers.");
                    }
                    switch (answer){
                        case 1: choice1OutOfFuel(car);
                            break;
                        case 2: choice2OutOfFuel(car);
                            break;
                    }
                    done = true;
                }catch (NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Input must be an Integer.");
                    done = false;
                }
                catch (NotAcceptableDataException e){
                    done = false;
                }
            }
            return true;
        }
        else {
            return false;
        }
    }

    private void choice1OutOfFuel(Car car){
        car.setPositionRow(9);
        car.setPositionColumn(0);
        car.setFuel(1 + random.nextInt(120));
    }

    private void choice2OutOfFuel(Car car){
        boolean done = false;
        while (!done){
            try{
                String answer2Str = JOptionPane.showInputDialog("How many turns are you willing to lose?\n1) Gains - 20 litres of fuel.\n2) Gains - 40 litres of fuel." +
                        "\n3) Gains - 60 litres of fuel.\n4) Gains - 80 litres of fuel.\n5) Gains - 100 litres of fuel.\n6) Gains - 120 litres of fuel.");
                int answer2 = Integer.parseInt(answer2Str);
                if (answer2 < 1 || answer2 > 6){
                    throw new NotAcceptableDataException("Choices are either 1, 2, 3, 4, 5, 6.\nPlease pick one of these numbers.");
                }
                switch (answer2){
                    case 1: car.setFuel(20);
                        car.setNumberOfRoundsLost(1);
                        break;
                    case 2: car.setFuel(40);
                        car.setNumberOfRoundsLost(2);
                        break;
                    case 3: car.setFuel(60);
                        car.setNumberOfRoundsLost(3);
                        break;
                    case 4: car.setFuel(80);
                        car.setNumberOfRoundsLost(4);
                        break;
                    case 5: car.setFuel(100);
                        car.setNumberOfRoundsLost(5);
                        break;
                    case 6: car.setFuel(120);
                        car.setNumberOfRoundsLost(6);
                        break;
                }
                done = true;
            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Input must be an Integer.");
                done = false;
            }
            catch (NotAcceptableDataException e){
                done = false;
            }
        }
    }

    public void moveInGrid(Car car, int dieNumber) {
        int futurePosition = car.getPositionColumn() + dieNumber;
        System.out.println("Random Number = " + dieNumber);
        boolean changeRow = false; //we want it to change row only one time, so first time it will enter and diminish the row but after that it won't be able to change it again.
        boolean movedToNextRow = false; // if true it means that the row is now changed and we're ready to start cutting the other way.
        boolean f = false; //by diminishing the column it means that the first condition will become true again. So it will re-enter the loop and mess things up.->
        int sumOfFuelLoss = 0;           //->...we don't want this so we put that boolean variable in order to secure that.
        if (car.getPositionRow() % 2 != 0) {
            for (int i = car.getPositionColumn(); i < futurePosition; i++) {
                if (car.getPositionColumn() < 9 && !f) {
                    car.setPositionColumn(car.getPositionColumn() + 1);
                    sumOfFuelLoss += gridV[car.getPositionRow()][car.getPositionColumn()];
                    car.setFuel((car.getFuel() - gridV[car.getPositionRow()][car.getPositionColumn()]));
                    if (checkOutOfFuel(car)){
                        break;
                    }
                } else if (car.getPositionColumn() == 9 && !changeRow) {
                    changeRow = true;
                    movedToNextRow = true;
                    f = true;
                    car.setPositionRow(car.getPositionRow() - 1);
                    car.setPositionColumn(9);
                    sumOfFuelLoss += gridV[car.getPositionRow()][car.getPositionColumn()];
                    car.setFuel((car.getFuel() - gridV[car.getPositionRow()][car.getPositionColumn()]));
                    if (checkOutOfFuel(car)){
                        break;
                    }
                    continue;
                }
                if (movedToNextRow) {
                    car.setPositionColumn(car.getPositionColumn() - 1);
                    sumOfFuelLoss += gridV[car.getPositionRow()][car.getPositionColumn()];
                    car.setFuel((car.getFuel() - gridV[car.getPositionRow()][car.getPositionColumn()]));
                    if (checkOutOfFuel(car)){
                        break;
                    }
                }
            }
        } else {
            for (int i = car.getPositionColumn(); i < futurePosition; i++) {
                if (car.getPositionColumn() > 0 && !f) {
                    car.setPositionColumn(car.getPositionColumn() - 1);
                    sumOfFuelLoss += gridV[car.getPositionRow()][car.getPositionColumn()];
                    car.setFuel((car.getFuel() - gridV[car.getPositionRow()][car.getPositionColumn()]));
                    if (checkOutOfFuel(car)){
                        break;
                    }
                } else if (car.getPositionColumn() == 0 && !changeRow) {
                    changeRow = true;
                    movedToNextRow = true;
                    f = true;
                    car.setPositionRow(car.getPositionRow() - 1);
                    car.setPositionColumn(0);
                    sumOfFuelLoss += gridV[car.getPositionRow()][car.getPositionColumn()];
                    car.setFuel((car.getFuel() - gridV[car.getPositionRow()][car.getPositionColumn()]));
                    if (checkOutOfFuel(car)){
                        break;
                    }
                    continue;
                }
                if (movedToNextRow) {
                    car.setPositionColumn(car.getPositionColumn() + 1);
                    sumOfFuelLoss += gridV[car.getPositionRow()][car.getPositionColumn()];
                    car.setFuel((car.getFuel() - gridV[car.getPositionRow()][car.getPositionColumn()]));
                    if (checkOutOfFuel(car)){
                        break;
                    }
                }
            }
        }
        blackTiles(car);
        greenTiles(car);
        FuelCurrent currentState = new FuelCurrent(sumOfFuelLoss, car.getFuel());
        car.setCurrentFuel(currentState);
        System.out.println("Car color: " + car.getColor() + "\tFuel Loss = " + sumOfFuelLoss + "\tTotal Fuel = " + car.getFuel());
    }
}