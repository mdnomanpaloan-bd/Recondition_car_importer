package com.example.recondition_car_importer.Alvi;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class Deliveries2
{
    @javafx.fxml.FXML
    private ComboBox<String> ShipmentStatusCB;
    @javafx.fxml.FXML
    private Label customerLabel;
    @javafx.fxml.FXML
    private TableColumn<Deliveries2,String> customerTV;
    @javafx.fxml.FXML
    private TableColumn<Deliveries2,String>  vehicleTV;
    @javafx.fxml.FXML
    private Label VehicleLabel;
    @javafx.fxml.FXML
    private TableView<String>  deliveriesTV;
    @javafx.fxml.FXML
    private TableColumn<Deliveries2,String>  bookingIDTV;
    @javafx.fxml.FXML
    private TableColumn<Deliveries2,String>  statusTV;
    @javafx.fxml.FXML
    private DatePicker ExpectedDP;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void updateDeliveryOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void backOnAction(ActionEvent actionEvent) {
    }
}