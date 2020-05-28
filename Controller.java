package Game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private Label warningValidationLabel1;
    @FXML private Label warningValidationLabel;
    @FXML private Button registeringButton;
    @FXML private Label randomNumberLabel;
    @FXML private Label randomNumberShow;
    @FXML private Button valuesGridButton;
    @FXML private TableView tableView;
    @FXML private TableView tableView2;
    @FXML private Button rollDieID;
    @FXML private Button throwDieStart;
    @FXML private HBox startGame;
    @FXML private  GridPane grid;
    @FXML private VBox registeringMenu;
    @FXML private Label mainLabel;

    @FXML private TextField player1Name;
    @FXML private ComboBox colorComboBox1;
    @FXML private TableColumn player1ColumnLabelLeft;
    @FXML private TableColumn player1ColumnLabelRight;
    @FXML private Label player1Label;

    @FXML private TextField player2Name;
    @FXML private ComboBox colorComboBox2;
    @FXML private TableColumn player2ColumnLabelLeft;
    @FXML private TableColumn player2ColumnLabelRight;
    @FXML private Label player2Label;

    private Image blueCar;
    private Image redCar;
    private AdditionalMethods extraMethods = new AdditionalMethods();
    private boolean turn;
    private boolean playsFisrt;
    private Grid table = new Grid();
    private ObservableList fuelDataP1;
    private ObservableList fuelDataP2;

    private Car car1 = new Car();
    private Player player1 = new Player(car1, table);
    private Car car2 = new Car();
    private Player player2 = new Player(car2, table);


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table.createGrid();
        ObservableList<String> colours = FXCollections.observableArrayList(
                "RED", "BLUE");
        colorComboBox1.setItems(colours);
        colorComboBox2.setItems(colours);
    }

    private ImageView startImageViewRED;
    private ImageView startImageViewBLUE;
    public void registerElements() throws FileNotFoundException {
        // Registering Player1 Data
        player1.setName(player1Name.getText());
        player1Label.setText(player1Name.getText());
        // Registering Player2 Data
        player2.setName(player2Name.getText());
        player2Label.setText(player2Name.getText());
        //Setting Colors
        String car1selectedColor = (String) colorComboBox1.getSelectionModel().getSelectedItem();
        car1.setColor(car1selectedColor);
        player1ColumnLabelLeft.setStyle("-fx-background-color : " + car1selectedColor);
        player1ColumnLabelRight.setStyle("-fx-background-color : " + car1selectedColor);

        String car2selectedColor = (String) colorComboBox2.getSelectionModel().getSelectedItem();
        car2.setColor(car2selectedColor);
        player2ColumnLabelLeft.setStyle("-fx-background-color : " + car2selectedColor);
        player2ColumnLabelRight.setStyle("-fx-background-color : " + car2selectedColor);

        registeringMenu.setVisible(false);
        registeringMenu.setDisable(true);
        //Placing cars on the beginning
        //red
        FileInputStream imageStream = new FileInputStream("src/images/sports_convertible.png");
        redCar = new Image(imageStream, 50, 15, false, false);
        startImageViewRED = new ImageView(redCar);
        grid.add(startImageViewRED, 0, 9);
        //blue
        FileInputStream imageStream1 = new FileInputStream("src/images/sedan_vintage.png");
        blueCar = new Image(imageStream1, 30, 20, false, false);
        startImageViewBLUE = new ImageView(blueCar);
        grid.add(startImageViewBLUE, 0, 9);

        startGame.setVisible(true);
        startGame.setDisable(false);
    }

    public void throwStartDie() {
        //if turn is true then player 1 is playing first, else the second one.
        turn = extraMethods.playsFirst(player1, player2);
        if(turn){
            mainLabel.setText(player1.getName() + " plays First!");
            playsFisrt = true;
        }
        else{
            mainLabel.setText(player2.getName() + " plays First!");
            playsFisrt = false;
        }
        throwDieStart.setVisible(false);
        throwDieStart.setDisable(true);

        rollDieID.setDisable(false);
        rollDieID.setVisible(true);

        valuesGridButton.setDisable(false);
        valuesGridButton.setVisible(true);

        randomNumberLabel.setDisable(false);
        randomNumberLabel.setVisible(true);

        randomNumberShow.setDisable(false);
        randomNumberShow.setVisible(true);
    }
    // if playFirst = true then player 1 plays first, else player 2.
    public void rollDie() throws IndexOutOfBoundsException{
        int randomNumber;
        grid.getChildren().remove(startImageViewRED);
        grid.getChildren().remove(startImageViewBLUE);
        if ((playsFisrt)){
            if (car1.getNumberOfRoundsLost() == 0){
                try{
                    randomNumber = player1.moveCar();
                    randomNumberShow.setText(String.valueOf(randomNumber));
                    placeImagesInGrid(car1);
                    playsFisrt = false;
                    mainLabel.setText(player2.getName() + "'s turn.");
                    if(extraMethods.landedOnEndTile(car1)){
                        JOptionPane.showMessageDialog(null,player1.getName() + " Wins!\nScore: " + car1.getFuel());
                    }
                }catch (IndexOutOfBoundsException e){
                    JOptionPane.showMessageDialog(null,player1.getName() + " Wins!\nScore: " + car1.getFuel());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else {
                car1.setNumberOfRoundsLost(car1.getNumberOfRoundsLost() - 1);
                playsFisrt = false;
                mainLabel.setText(player2.getName() + "'s turn.");
            }
        }
        else {
            if (car2.getNumberOfRoundsLost() == 0){
                try{
                    randomNumber = player2.moveCar();
                    randomNumberShow.setText(String.valueOf(randomNumber));
                    placeImagesInGrid(car2);
                    playsFisrt = true;
                    mainLabel.setText(player1.getName() + "'s turn.");
                    if(extraMethods.landedOnEndTile(car2)){
                        JOptionPane.showMessageDialog(null,player2.getName() + " Wins!\nScore: " + car2.getFuel());
                    }
                }catch (IndexOutOfBoundsException e){
                    JOptionPane.showMessageDialog(null,player2.getName() + " Wins!\nScore: " + car2.getFuel());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else {
                car2.setNumberOfRoundsLost(car2.getNumberOfRoundsLost() - 1);
                playsFisrt = true;
                mainLabel.setText(player1.getName() + "'s turn.");
            }
        }
        table.updateGridC(car1, car2);
        table.showColouredGrid();
        updateTableViewData();
    }

    public void updateTableViewData(){
        boolean skipUpdate = false;
        if ((car1.getPositionRow() == 0 && car1.getPositionColumn() < 0) || (car2.getPositionRow() == 0 && car2.getPositionColumn() < 0)){
            skipUpdate = true;
        }
        if (!skipUpdate){
            fuelDataP1 = FXCollections.observableArrayList();
            fuelDataP1.addAll(car1.getCurrentFuel());
            player1ColumnLabelRight.setCellValueFactory(new PropertyValueFactory<FuelCurrent, Integer>("sumOfFuelLoss"));
            player1ColumnLabelLeft.setCellValueFactory(new PropertyValueFactory<FuelCurrent, Integer>("totalFuel"));
            tableView.setItems(fuelDataP1);

            fuelDataP2 = FXCollections.observableArrayList();
            fuelDataP2.addAll(car2.getCurrentFuel());
            player2ColumnLabelRight.setCellValueFactory(new PropertyValueFactory<FuelCurrent, Integer>("sumOfFuelLoss"));
            player2ColumnLabelLeft.setCellValueFactory(new PropertyValueFactory<FuelCurrent, Integer>("totalFuel"));
            tableView2.setItems(fuelDataP2);
        }
    }

    private ImageView imageView;
    private ImageView imageView2;
    private void placeImagesInGrid(Car car) throws FileNotFoundException {
        if (Objects.equals(car.getColor(), "RED")){
            grid.getChildren().remove(imageView);
            FileInputStream imageStream = new FileInputStream("src/images/sports_convertible.png");
            Image image = new Image(imageStream);
            imageView = new ImageView(image);
            grid.add(imageView, car.getPositionColumn(), car.getPositionRow());
        }
        else {
            grid.getChildren().remove(imageView2);
            FileInputStream imageStream2 = new FileInputStream("src/images/sedan_Vintage.png");
            Image image = new Image(imageStream2);
            imageView2 = new ImageView(image);
            grid.add(imageView2, car.getPositionColumn(), car.getPositionRow());
        }
    }

    private Label[][] label;
    public void showValuesGridButton() {
        int[][] gridValuesArray = table.getGridValuesArray();
        label = new Label[10][10];
        for (int i=0;i<gridValuesArray.length;i++){
            for (int j=0;j<gridValuesArray[i].length;j++){
                label[i][j] = new Label(String.valueOf(gridValuesArray[i][j]));
                label[i][j].setMaxWidth(Double.MAX_VALUE);
                label[i][j].setAlignment(Pos.BOTTOM_RIGHT);
                grid.add(label[i][j], j, i);
            }
        }
    }

    public void removeValuesGridButton() {
        for (int i=0;i<10;i++){
            for (int j=0;j<10;j++){
                grid.getChildren().remove(label[i][j]);
            }
        }
    }

    public void checkValidationButton() throws NullPointerException {
        boolean condition1 = false;
        boolean condition2 = false;
        if (player1Name.getText().equals(player2Name.getText())){
            registeringButton.setDisable(true);
            warningValidationLabel.setText("* duplicate player names detected.");
            warningValidationLabel.setVisible(true);
        }
        else {
            warningValidationLabel.setVisible(false);
            condition1 = true;
        }
        String car1selectedColor = (String) colorComboBox1.getSelectionModel().getSelectedItem();
        String car2selectedColor = (String) colorComboBox2.getSelectionModel().getSelectedItem();
        try {
            if (car1selectedColor.equals(car2selectedColor)){
                registeringButton.setDisable(true);
                warningValidationLabel1.setText("* duplicate car color detected.");
                warningValidationLabel1.setVisible(true);
            }
            else {
                warningValidationLabel1.setVisible(false);
                condition2 = true;
            }
        }catch (NullPointerException e){
            //no need to do anything.
        }
        if (condition1 && condition2){
            registeringButton.setDisable(false);
        }
    }
}