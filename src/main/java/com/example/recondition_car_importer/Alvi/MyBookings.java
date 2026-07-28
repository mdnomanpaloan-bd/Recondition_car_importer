package com.example.recondition_car_importer.Alvi;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class MyBookings
{
    @javafx.fxml.FXML
    private TableColumn<Inventory2,String> VehicleTV;
    @javafx.fxml.FXML
    private Label pickUpDateLabel;
    @javafx.fxml.FXML
    private TableColumn<Inventory2,String> pickupTV;
    @javafx.fxml.FXML
    private Label bookingDateLabel;
    @javafx.fxml.FXML
    private Label vehicleLabel;
    @javafx.fxml.FXML
    private Label BookingIDlabel;
    @javafx.fxml.FXML
    private TableView<String> myBookingTV;
    @javafx.fxml.FXML
    private TableColumn<Inventory2,String> BookingDateTV;
    @javafx.fxml.FXML
    private TableColumn<Inventory2,String> statusTV;
    @javafx.fxml.FXML
    private Label statusLabel;
    @javafx.fxml.FXML
    private TableColumn<Inventory2,String> BookingIDTV;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void backOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void CancelBookingOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void viewDetailsOnAction(ActionEvent actionEvent) {
    }
}