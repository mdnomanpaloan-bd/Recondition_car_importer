package com.example.recondition_car_importer.Alvi.Customer;

import com.example.recondition_car_importer.SceneSwitcher;
import javafx.event.ActionEvent;

import java.io.IOException;


public class CustomerDashboard
{
    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void browseCarOnAction(ActionEvent actionEvent) throws IOException {
        SceneSwitcher.switchTo("Alvi/Customer/BrowseCars");
    }


    @javafx.fxml.FXML
    public void myBookingsOnAction(ActionEvent actionEvent) {
        try {
            SceneSwitcher.switchTo("Alvi/Customer/MyBookings");
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
            SceneSwitcher.switchTo("Alvi/Customer/Feedback");
        } catch (Exception e) {
        }
    }

    @javafx.fxml.FXML
    public void uploadDocumentsOnAction(ActionEvent actionEvent) {
        try {
            SceneSwitcher.switchTo("Alvi/Customer/UploadDocuments");
        } catch (Exception e) {
        }
    }

    @javafx.fxml.FXML
    public void CostCalculatorOnAction(ActionEvent actionEvent) {
//calculate total cost code
    }

    @javafx.fxml.FXML
    public void CompareCarOnAction(ActionEvent actionEvent) {
        try {
            SceneSwitcher.switchTo("Alvi/Customer/CompareCars");
        } catch (Exception e) {
        }
    }
}
