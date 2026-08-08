package com.example.recondition_car_importer.Sifat;

import com.example.recondition_car_importer.Sifat.model.Car;
import com.example.recondition_car_importer.Sifat.model.DataManager;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class ManageInventoryController implements Initializable {

    @FXML private TableView<Car> carTable;
    @FXML private TableColumn<Car, String> colId;
    @FXML private TableColumn<Car, String> colBrand;
    @FXML private TableColumn<Car, String> colModel;
    @FXML private TableColumn<Car, Integer> colYear;
    @FXML private TableColumn<Car, Double> colPrice;
    @FXML private TableColumn<Car, String> colStatus;

    @FXML private TextField txtBrand;
    @FXML private TextField txtModel;
    @FXML private TextField txtYear;
    @FXML private TextField txtPrice;
    @FXML private ComboBox<String> comboStatus;
    @FXML private Label lblStatusMessage;

    private DataManager dataManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataManager = DataManager.getInstance();

        comboStatus.getItems().addAll("Available", "Sold", "Booked");
        comboStatus.getSelectionModel().select("Available");

        colId.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCarId()));
        colBrand.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getBrand()));
        colModel.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getModel()));
        colYear.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getYear()).asObject());
        colPrice.setCellValueFactory(cell -> new SimpleDoubleProperty(cell.getValue().getOriginalPrice()).asObject());
        colStatus.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getStatus()));

        carTable.setItems(dataManager.getCars());

        carTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                txtBrand.setText(newSel.getBrand());
                txtModel.setText(newSel.getModel());
                txtYear.setText(String.valueOf(newSel.getYear()));
                txtPrice.setText(String.valueOf(newSel.getOriginalPrice()));
                comboStatus.getSelectionModel().select(newSel.getStatus());
            }
        });
    }

    @FXML
    public void handleAddCar(ActionEvent event) {
        try {
            String brand = txtBrand.getText().trim();
            String model = txtModel.getText().trim();
            int year = Integer.parseInt(txtYear.getText().trim());
            double price = Double.parseDouble(txtPrice.getText().trim());
            String status = comboStatus.getValue();

            if (brand.isEmpty() || model.isEmpty()) {
                lblStatusMessage.setText("Brand and Model cannot be empty.");
                return;
            }

            String id = "CAR-" + (100 + dataManager.getCars().size() + 1);
            Car newCar = new Car(id, brand, model, year, price, status);

            dataManager.getCars().add(newCar);
            dataManager.saveAllData();
            clearForm();
            lblStatusMessage.setText("Car added successfully!");
        } catch (NumberFormatException e) {
            lblStatusMessage.setText("Invalid number format for Year or Price.");
        }
    }

    @FXML
    public void handleUpdateSelected(ActionEvent event) {
        Car selected = carTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            lblStatusMessage.setText("Please select a car from table to update.");
            return;
        }

        try {
            selected.setBrand(txtBrand.getText().trim());
            selected.setModel(txtModel.getText().trim());
            selected.setYear(Integer.parseInt(txtYear.getText().trim()));
            selected.setOriginalPrice(Double.parseDouble(txtPrice.getText().trim()));
            selected.setStatus(comboStatus.getValue());

            carTable.refresh();
            dataManager.saveAllData();
            lblStatusMessage.setText("Car updated successfully!");
        } catch (NumberFormatException e) {
            lblStatusMessage.setText("Invalid number format for Year or Price.");
        }
    }

    @FXML
    public void handleDeleteCar(ActionEvent event) {
        Car selected = carTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            lblStatusMessage.setText("Please select a car from table to delete.");
            return;
        }

        dataManager.getCars().remove(selected);
        dataManager.saveAllData();
        clearForm();
        lblStatusMessage.setText("Car deleted successfully!");
    }

    private void clearForm() {
        txtBrand.clear();
        txtModel.clear();
        txtYear.clear();
        txtPrice.clear();
        comboStatus.getSelectionModel().select("Available");
        carTable.getSelectionModel().clearSelection();
    }
}
