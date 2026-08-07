package com.example.recondition_car_importer.Alvi.SalesExecutive;

import com.example.recondition_car_importer.Alvi.Customer.BookingRecord;
import com.example.recondition_car_importer.SceneSwitcher;
import com.example.recondition_car_importer.utility.SelectedCarHolder;
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



        if (SelectedCarHolder.selectedCar == null) {
            return;
        }

        double vehiclePrice = SelectedCarHolder.selectedCar.getPrice();

        double importDuty = vehiclePrice * 0.15;        //15%
        double registrationFee = 50000;                 //Fixed fee
        double vat = vehiclePrice * 0.05;               //5%

        customerNameLabel.setText("Customer");

        vehicleLabel.setText(
                SelectedCarHolder.selectedCar.getMake()
                        + " "
                        + SelectedCarHolder.selectedCar.getModel()
        );

        vehiclePriceLabel.setText(String.format("%.2f", vehiclePrice));

        importDutyLabel.setText(String.format("%.2f", importDuty));

        registrationFeeLabel.setText(String.format("%.2f", registrationFee));

        VatLabel.setText(String.format("%.2f", vat));
    }

    @javafx.fxml.FXML
    public void printInvoiceOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setHeaderText("Invoice");

        alert.setContentText("Invoice printed successfully.");

        alert.showAndWait();
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