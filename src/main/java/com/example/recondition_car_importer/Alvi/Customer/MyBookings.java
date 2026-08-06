package com.example.recondition_car_importer.Alvi.Customer;

import com.example.recondition_car_importer.Alvi.SalesExecutive.Inventory2;
import com.example.recondition_car_importer.SceneSwitcher;
import com.example.recondition_car_importer.utility.BinaryFileUtil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class MyBookings
{
    @javafx.fxml.FXML
    private TableColumn<BookingRecord,String> VehicleTV;
    @javafx.fxml.FXML
    private Label pickUpDateLabel;
    @javafx.fxml.FXML
    private TableColumn<BookingRecord,String> pickupTV;
    @javafx.fxml.FXML
    private Label bookingDateLabel;
    @javafx.fxml.FXML
    private Label vehicleLabel;
    @javafx.fxml.FXML
    private Label BookingIDlabel;
    @javafx.fxml.FXML
    private TableView<BookingRecord> myBookingTV;
    @javafx.fxml.FXML
    private TableColumn<BookingRecord,String> BookingDateTV;
    @javafx.fxml.FXML
    private TableColumn<BookingRecord,String> statusTV;
    @javafx.fxml.FXML
    private Label statusLabel;
    @javafx.fxml.FXML
    private TableColumn<BookingRecord,String> BookingIDTV;
    private static final String BOOKING_FILE = "Data/Alvi-2310827/Bookings";

    @javafx.fxml.FXML
    public void initialize() {
        BookingIDTV.setCellValueFactory(new PropertyValueFactory<>("bookingID"));
        VehicleTV.setCellValueFactory(new PropertyValueFactory<>("carName"));
        BookingDateTV.setCellValueFactory(new PropertyValueFactory<>("bookingDate"));
        pickupTV.setCellValueFactory(new PropertyValueFactory<>("pickupDate"));
        statusTV.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadBookings();

    }



    private void loadBookings() {

        ArrayList<BookingRecord> bookings =
                BinaryFileUtil.readList(BOOKING_FILE);

        myBookingTV.setItems(FXCollections.observableArrayList(bookings));
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
    public void CancelBookingOnAction(ActionEvent actionEvent) {


        BookingRecord booking =
                myBookingTV.getSelectionModel().getSelectedItem();

        if (booking == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Please select a booking.");
            alert.show();

            return;
        }

        ArrayList<BookingRecord> bookings =
                BinaryFileUtil.readList(BOOKING_FILE);

        bookings.remove(booking);

        BinaryFileUtil.saveList(BOOKING_FILE, bookings);

        loadBookings();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Booking cancelled.");
        alert.show();

    }

    @javafx.fxml.FXML
    public void viewDetailsOnAction(ActionEvent actionEvent) {
        BookingRecord booking =
                myBookingTV.getSelectionModel().getSelectedItem();

        if (booking == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Please select a booking.");
            alert.show();

            return;
        }

        BookingIDlabel.setText(booking.getBookingID());
        vehicleLabel.setText(booking.getCarName());
        bookingDateLabel.setText(booking.getBookingDate().toString());
        pickUpDateLabel.setText(booking.getPickupDate().toString());
        statusLabel.setText(booking.getStatus());

    }

}