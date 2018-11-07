package edu.bsu.calcul8r;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("calculator.fxml"));
        primaryStage.setTitle("Calcul8r");
        primaryStage.setScene(new Scene(root, 252, 450));
        primaryStage.setAlwaysOnTop(true);
        primaryStage.requestFocus();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}