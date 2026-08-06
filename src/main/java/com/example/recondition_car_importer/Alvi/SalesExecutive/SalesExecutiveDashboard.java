package com.example.recondition_car_importer.Alvi.SalesExecutive;

import com.example.recondition_car_importer.SceneSwitcher;
import javafx.event.ActionEvent;

public class SalesExecutiveDashboard
{
    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void logOutOnAction(ActionEvent actionEvent) {
        try {
            SceneSwitcher.switchTo("LogInView");
        } catch (Exception e) {
        }
    }

    @javafx.fxml.FXML
    public void deliveriesOnAction(ActionEvent actionEvent) {
        try {
            SceneSwitcher.switchTo("Alvi/SalesExecutive/Deliveries2");
        } catch (Exception e) {
        }
    }

    @javafx.fxml.FXML
    public void addNewCarOnAction(ActionEvent actionEvent) {
        try {
            SceneSwitcher.switchTo("Alvi/SalesExecutive/AddnewCar2");
        } catch (Exception e) {
        }
    }

    @javafx.fxml.FXML
    public void customerRecordsOnAction(ActionEvent actionEvent) {
        try {
            SceneSwitcher.switchTo("Alvi/SalesExecutive/CustomerRecords2");
        } catch (Exception e) {
        }
    }

    @javafx.fxml.FXML
    public void inventoryOnAction(ActionEvent actionEvent) {
        try {
            SceneSwitcher.switchTo("Alvi/SalesExecutive/Inventory2");
        } catch (Exception e) {
        }
    }

    @javafx.fxml.FXML
    public void customerBookingOnAction(ActionEvent actionEvent) {
        try {
            SceneSwitcher.switchTo("Alvi/SalesExecutive/CustomerPendingBookings2");
        } catch (Exception e) {
        }
    }

    @javafx.fxml.FXML
    public void salesReportOnAction(ActionEvent actionEvent) {
        try {
            SceneSwitcher.switchTo("Alvi/SalesExecutive/SalesReport2");
        } catch (Exception e) {
        }

    }
}