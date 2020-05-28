package Game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainScene.fxml"));
        primaryStage.setTitle("Racing Game");
        primaryStage.setScene(new Scene(root, 1080, 900));
        //primaryStage.setResizable(false); by doing that it will stop being resizable.
        primaryStage.show();
    }

    public void stop() throws Exception {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Quit Window");
        alert.setHeaderText("Thank you for playing our game!");
        alert.setContentText("Production Team: Fanis, George.");
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}