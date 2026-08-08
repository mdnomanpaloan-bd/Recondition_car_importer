package com.example.haqs_bay_automobili;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        primaryStage.setTitle("Haq's Bay Automobili - MS3 Management System");
        loadScene("role_selection.fxml", "Haq's Bay Automobili - Role Selection", 900, 600);
        primaryStage.show();
    }

    public static void loadScene(String fxmlFile, String title, double width, double height) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(fxmlFile));
            Scene scene = new Scene(fxmlLoader.load(), width, height);
            primaryStage.setTitle(title);
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
