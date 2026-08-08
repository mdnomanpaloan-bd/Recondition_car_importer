package com.example.recondition_car_importer.Sifat;

import com.example.recondition_car_importer.Sifat.model.Car;
import com.example.recondition_car_importer.Sifat.model.DataManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class TrackSalesBookingController implements Initializable {

    @FXML private ComboBox<String> comboFilterStatus;
    @FXML private TableView<Car> trackingTable;
    @FXML private TableColumn<Car, String> colCarId;
    @FXML private TableColumn<Car, String> colModel;
    @FXML private TableColumn<Car, String> colCustomer;
    @FXML private TableColumn<Car, String> colBookingDate;
    @FXML private TableColumn<Car, String> colStatus;

    @FXML private ComboBox<String> comboUpdateStatus;
    @FXML private TextField txtCustomerName;
    @FXML private Label lblMessage;

    private DataManager dataManager;
    private final ObservableList<Car> filteredList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataManager = DataManager.getInstance();

        comboFilterStatus.getItems().addAll("All", "Available", "Booked", "Sold");
        comboFilterStatus.getSelectionModel().select("All");

        comboUpdateStatus.getItems().addAll("Available", "Booked", "Sold");
        comboUpdateStatus.getSelectionModel().select("Booked");

        colCarId.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCarId()));
        colModel.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getBrand() + " " + cell.getValue().getModel()));
        colCustomer.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCustomerName()));
        colBookingDate.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getBookingDate() != null ? cell.getValue().getBookingDate().toString() : "N/A"));
        colStatus.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getStatus()));

        applyFilter();
        trackingTable.setItems(filteredList);

        trackingTable.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            if (newV != null) {
                txtCustomerName.setText(newV.getCustomerName());
                comboUpdateStatus.getSelectionModel().select(newV.getStatus());
            }
        });
    }

    @FXML
    public void handleFilterChange(ActionEvent event) {
        applyFilter();
    }

    private void applyFilter() {
        String filter = comboFilterStatus.getValue();
        if (filter == null || filter.equals("All")) {
            filteredList.setAll(dataManager.getCars());
        } else {
            filteredList.setAll(dataManager.getCars().filtered(c -> c.getStatus().equalsIgnoreCase(filter)));
        }
    }

    @FXML
    public void handleUpdateStatus(ActionEvent event) {
        Car selected = trackingTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            lblMessage.setText("Please select a car from the table.");
            return;
        }

        selected.setStatus(comboUpdateStatus.getValue());
        if (!txtCustomerName.getText().trim().isEmpty()) {
            selected.setCustomerName(txtCustomerName.getText().trim());
        }

        trackingTable.refresh();
        applyFilter();
        dataManager.saveAllData();
        lblMessage.setText("Car status updated to " + selected.getStatus());
    }
}
