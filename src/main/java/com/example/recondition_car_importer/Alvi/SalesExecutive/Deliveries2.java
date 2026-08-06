package com.example.recondition_car_importer.Alvi.SalesExecutive;

import com.example.recondition_car_importer.Alvi.Customer.BookingRecord;
import com.example.recondition_car_importer.SceneSwitcher;
import com.example.recondition_car_importer.utility.BinaryFileUtil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class Deliveries2
{
    @javafx.fxml.FXML
    private ComboBox<String> ShipmentStatusCB;
    @javafx.fxml.FXML
    private Label customerLabel;
    @javafx.fxml.FXML
    private TableColumn<BookingRecord,String> customerTV;
    @javafx.fxml.FXML
    private TableColumn<BookingRecord,String>  vehicleTV;
    @javafx.fxml.FXML
    private Label VehicleLabel;
    @javafx.fxml.FXML
    private TableView<BookingRecord>  deliveriesTV;
    @javafx.fxml.FXML
    private TableColumn<BookingRecord,String>  bookingIDTV;
    @javafx.fxml.FXML
    private TableColumn<BookingRecord,String>  statusTV;
    @javafx.fxml.FXML
    private DatePicker ExpectedDP;

    private static final String BOOKING_FILE = "Data/Alvi-2310827/Bookings";

    private ArrayList<BookingRecord> bookings;

    private BookingRecord selectedBooking;

    @javafx.fxml.FXML
    public void initialize() {
        bookingIDTV.setCellValueFactory(new PropertyValueFactory<>("bookingID"));
        customerTV.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        vehicleTV.setCellValueFactory(new PropertyValueFactory<>("carName"));
        statusTV.setCellValueFactory(new PropertyValueFactory<>("status"));

        ShipmentStatusCB.setItems(FXCollections.observableArrayList(
                "Preparing",
                "Shipped",
                "Delivered"
        ));

        bookings = BinaryFileUtil.readList(BOOKING_FILE);

        deliveriesTV.setItems(FXCollections.observableArrayList(bookings));

        deliveriesTV.getSelectionModel().selectedItemProperty().addListener((obs, oldBooking, newBooking) -> {

            if (newBooking != null) {

                selectedBooking = newBooking;

                customerLabel.setText(newBooking.getFullName());
                VehicleLabel.setText(newBooking.getCarName());
            }
        });


    }

    @javafx.fxml.FXML
    public void updateDeliveryOnAction(ActionEvent actionEvent) {

        if (selectedBooking == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Please select a booking.");
            alert.show();
            return;
        }

        if (ShipmentStatusCB.getValue() == null ||
                ExpectedDP.getValue() == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Please complete all fields.");
            alert.show();
            return;
        }

        selectedBooking.setStatus(ShipmentStatusCB.getValue().toString());

        selectedBooking.setExpectedDelivery(ExpectedDP.getValue());

        BinaryFileUtil.saveList(BOOKING_FILE, bookings);

        deliveriesTV.refresh();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Delivery updated successfully.");
        alert.show();
    }

    @javafx.fxml.FXML
    public void backOnAction(ActionEvent actionEvent) {
        try {
            SceneSwitcher.switchTo("Alvi/SalesExecutive/SalesExecutiveDashboard");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}