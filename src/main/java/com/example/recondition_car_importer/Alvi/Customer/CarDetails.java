package com.example.recondition_car_importer.Alvi.Customer;

import com.example.recondition_car_importer.SceneSwitcher;
import com.example.recondition_car_importer.utility.BookingRecordHolder;
import com.example.recondition_car_importer.utility.SelectedCarHolder;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CarDetails
{
    @javafx.fxml.FXML
    private TextField TaxTF;
    @javafx.fxml.FXML
    private ComboBox<String> paymentMethodCB;
    @javafx.fxml.FXML
    private Label showTotalCostLabel;
    @javafx.fxml.FXML
    private TextField registrationTF;
    @javafx.fxml.FXML
    private TextField importDutyTF;
    @javafx.fxml.FXML
    private Label carIDLabel;
    @javafx.fxml.FXML
    private Label carMakeLabel;
    @javafx.fxml.FXML
    private Label yearLabel;
    @javafx.fxml.FXML
    private Label mileageLabel;
    @javafx.fxml.FXML
    private Label priceLabel;
    @javafx.fxml.FXML
    private Label statusLabel;
    @javafx.fxml.FXML
    private Label modelLabel;



    @javafx.fxml.FXML
    public void initialize() {
        paymentMethodCB.getItems().addAll(
                "Cash",
                "Bank Loan",
                "Installment"
        );

        if (SelectedCarHolder.selectedCar != null) {

            CarList car = SelectedCarHolder.selectedCar;

            carIDLabel.setText(car.getCarID());
            carMakeLabel.setText(car.getMake());
            modelLabel.setText(car.getModel());
            yearLabel.setText(String.valueOf(car.getYear()));
            mileageLabel.setText(String.valueOf(car.getMileage()));
            priceLabel.setText(String.valueOf(car.getPrice()));
            statusLabel.setText(car.getStatus());
        }
    }

    @javafx.fxml.FXML
    public void BookCarOnAction(ActionEvent actionEvent) {
        if (paymentMethodCB.getValue() == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Please select a payment method.");
            alert.show();

            return;
        }


        BookingRecordHolder.paymentMethod = paymentMethodCB.getValue();
        BookingRecordHolder.finalCost = Double.parseDouble(showTotalCostLabel.getText());

        try {
            SceneSwitcher.switchTo("Alvi/Customer/Booking");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void backOnAction(ActionEvent actionEvent) {
        try {
            SceneSwitcher.switchTo("Alvi/Customer/CustomerDashboard");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void calculateTotalCostOnAction(ActionEvent actionEvent) {
        if (SelectedCarHolder.selectedCar == null) {
            return;
        }

        try {

            double price = SelectedCarHolder.selectedCar.getPrice();
            double registration = Double.parseDouble(registrationTF.getText());
            double duty = Double.parseDouble(importDutyTF.getText());
            double tax = Double.parseDouble(TaxTF.getText());
            double total = price + registration + duty + tax;
            showTotalCostLabel.setText(String.format("%.2f", total));

        } catch (NumberFormatException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please enter valid numbers.");
            alert.show();
        }
    }

    @javafx.fxml.FXML
    public void CompareCarOnAction(ActionEvent actionEvent) {
        try {
            SceneSwitcher.switchTo("Alvi/Customer/CompareCars");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}