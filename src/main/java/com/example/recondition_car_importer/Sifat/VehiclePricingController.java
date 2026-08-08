package com.example.recondition_car_importer.Sifat;

import com.example.recondition_car_importer.Sifat.model.Car;
import com.example.recondition_car_importer.Sifat.model.DataManager;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class VehiclePricingController implements Initializable {

    @FXML private TableView<Car> pricingTable;
    @FXML private TableColumn<Car, String> colModel;
    @FXML private TableColumn<Car, Double> colOriginalPrice;
    @FXML private TableColumn<Car, Double> colDiscount;
    @FXML private TableColumn<Car, Double> colFinalPrice;

    @FXML private TextField txtDiscountPercent;
    @FXML private Label lblFinalPrice;
    @FXML private Label lblMessage;

    private DataManager dataManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataManager = DataManager.getInstance();

        colModel.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getBrand() + " " + cell.getValue().getModel()));
        colOriginalPrice.setCellValueFactory(cell -> new SimpleDoubleProperty(cell.getValue().getOriginalPrice()).asObject());
        colDiscount.setCellValueFactory(cell -> new SimpleDoubleProperty(cell.getValue().getDiscountPercent()).asObject());
        colFinalPrice.setCellValueFactory(cell -> new SimpleDoubleProperty(cell.getValue().getFinalPrice()).asObject());

        pricingTable.setItems(dataManager.getCars());

        pricingTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                txtDiscountPercent.setText(String.valueOf(newVal.getDiscountPercent()));
                lblFinalPrice.setText(String.format("Final Price: BDT %.2f", newVal.getFinalPrice()));
            }
        });
    }

    @FXML
    public void handleApplyDiscount(ActionEvent event) {
        Car selected = pricingTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            lblMessage.setText("Please select a car from table to update pricing.");
            return;
        }

        try {
            double discount = Double.parseDouble(txtDiscountPercent.getText().trim());
            if (discount < 0 || discount > 100) {
                lblMessage.setText("Discount must be between 0% and 100%.");
                return;
            }

            selected.setDiscountPercent(discount);
            pricingTable.refresh();
            dataManager.saveAllData();

            lblFinalPrice.setText(String.format("Final Price: BDT %.2f", selected.getFinalPrice()));
            lblMessage.setText("Discount applied successfully!");
        } catch (NumberFormatException e) {
            lblMessage.setText("Invalid discount format.");
        }
    }
}
