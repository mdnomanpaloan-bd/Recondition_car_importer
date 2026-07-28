package com.example.recondition_car_importer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        SceneSwitcher.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Alvi/CustomerDashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Recondition Car Importer");
        stage.setScene(scene);
        stage.show();
    }
}
