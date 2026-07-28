package com.example.recondition_car_importer.Alvi;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CustomerPendingBookings2
{
    @javafx.fxml.FXML
    private TableColumn<CompareCars,String > VehicleTV;
    @javafx.fxml.FXML
    private TableColumn<CompareCars,String > StatusTV;
    @javafx.fxml.FXML
    private Label CustomerNameLabel;
    @javafx.fxml.FXML
    private TableColumn<CompareCars,String > CustomerTV;
    @javafx.fxml.FXML
    private Label VehicleLabel;
    @javafx.fxml.FXML
    private Label paymentMethodLabel;
    @javafx.fxml.FXML
    private TableView<String > CustomerBookingsTV;
    @javafx.fxml.FXML
    private Button backOnAction;
    @javafx.fxml.FXML
    private Label selectedBookingLabel;
    @javafx.fxml.FXML
    private Label pickupDateLabel;
    @javafx.fxml.FXML
    private TableColumn<CompareCars,String > BookingIDTV;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void confirmOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void CancelBookingOnAction(ActionEvent actionEvent) {
    }
}