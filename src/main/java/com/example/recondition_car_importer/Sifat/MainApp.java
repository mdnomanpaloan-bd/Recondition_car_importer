package com.example.recondition_car_importer.Sifat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/recondition_car_importer/Sifat/MainDashboardView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1240, 780);
        stage.setTitle("Reconditioned Car Importer Management System - Sifat");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
