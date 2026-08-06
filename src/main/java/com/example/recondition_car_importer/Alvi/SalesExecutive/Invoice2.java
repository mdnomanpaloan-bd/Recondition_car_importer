package com.example.recondition_car_importer.Alvi.SalesExecutive;

import com.example.recondition_car_importer.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class Invoice2
{
    @javafx.fxml.FXML
    private Label vehiclePriceLabel;
    @javafx.fxml.FXML
    private Label vehicleLabel;
    @javafx.fxml.FXML
    private Label registrationFeeLabel;
    @javafx.fxml.FXML
    private Label VatLabel;
    @javafx.fxml.FXML
    private Label customerNameLabel;
    @javafx.fxml.FXML
    private Label importDutyLabel;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void printInvoiceOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void backOnaction(ActionEvent actionEvent) {
        try {
            SceneSwitcher.switchTo("Alvi/SalesExecutive/SalesExecutiveDashboard");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}