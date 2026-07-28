package com.example.recondition_car_importer.Alvi;

import com.example.recondition_car_importer.SceneSwitcher;
import javafx.event.ActionEvent;


public class CustomerDashboard
{
    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void browseCarOnAction(ActionEvent actionEvent) {
        try {
            SceneSwitcher.switchTo("Alvi/BrowseCars");
        } catch (Exception e) {
        }
    }


    @javafx.fxml.FXML
    public void myBookingsOnAction(ActionEvent actionEvent) {
        try {
            SceneSwitcher.switchTo("Alvi/MyBookings");
        } catch (Exception e) {
        }

    }

    @javafx.fxml.FXML
    public void logOutOnAction(ActionEvent actionEvent) {
        try {
            SceneSwitcher.switchTo("LogInView");
        } catch (Exception e) {
        }
    }

    @javafx.fxml.FXML
    public void FeedbackOnAction(ActionEvent actionEvent) {
        try {
            SceneSwitcher.switchTo("Alvi/Feedback");
        } catch (Exception e) {
        }
    }

    @javafx.fxml.FXML
    public void uploadDocumentsOnAction(ActionEvent actionEvent) {
        try {
            SceneSwitcher.switchTo("Alvi/UploadDocuments");
        } catch (Exception e) {
        }
    }

    @javafx.fxml.FXML
    public void CostCalculatorOnAction(ActionEvent actionEvent) {
        try {
            SceneSwitcher.switchTo("Alvi/CarDetails");
        } catch (Exception e) {
        }
    }

    @javafx.fxml.FXML
    public void CompareCarOnAction(ActionEvent actionEvent) {
        try {
            SceneSwitcher.switchTo("Alvi/CompareCars");
        } catch (Exception e) {
        }
    }
}
