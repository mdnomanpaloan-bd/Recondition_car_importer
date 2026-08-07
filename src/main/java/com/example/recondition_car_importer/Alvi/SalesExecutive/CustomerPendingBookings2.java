package com.example.recondition_car_importer.Alvi.SalesExecutive;

import com.example.recondition_car_importer.Alvi.Customer.BookingRecord;

import com.example.recondition_car_importer.SceneSwitcher;
import com.example.recondition_car_importer.utility.BinaryFileUtil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class CustomerPendingBookings2
{
    @javafx.fxml.FXML
    private TableColumn<BookingRecord,String > VehicleTV;
    @javafx.fxml.FXML
    private TableColumn<BookingRecord,String > StatusTV;
    @javafx.fxml.FXML
    private Label CustomerNameLabel;
    @javafx.fxml.FXML
    private TableColumn<BookingRecord,String > CustomerTV;
    @javafx.fxml.FXML
    private Label VehicleLabel;
    @javafx.fxml.FXML
    private Label paymentMethodLabel;
    @javafx.fxml.FXML
    private TableView<BookingRecord > CustomerBookingsTV;
    @javafx.fxml.FXML
    private Button backOnAction;
    @javafx.fxml.FXML
    private Label selectedBookingLabel;
    @javafx.fxml.FXML
    private Label pickupDateLabel;
    @javafx.fxml.FXML
    private TableColumn<BookingRecord,String > BookingIDTV;

    private static final String BOOKING_FILE = "Data/Alvi-2310827/Bookings";

    private ArrayList<BookingRecord> bookings;

    private BookingRecord selectedBooking;


    @javafx.fxml.FXML
    public void initialize() {
        BookingIDTV.setCellValueFactory(new PropertyValueFactory<>("bookingID"));
        CustomerTV.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        VehicleTV.setCellValueFactory(new PropertyValueFactory<>("carName"));
        StatusTV.setCellValueFactory(new PropertyValueFactory<>("status"));

        bookings = BinaryFileUtil.readList(BOOKING_FILE);

        CustomerBookingsTV.setItems(FXCollections.observableArrayList(bookings));

        CustomerBookingsTV.getSelectionModel().selectedItemProperty().addListener((obs, oldBooking, newBooking) -> {

            if (newBooking != null) {

                selectedBooking = newBooking;

                selectedBookingLabel.setText(newBooking.getBookingID());
                CustomerNameLabel.setText(newBooking.getFullName());
                VehicleLabel.setText(newBooking.getCarName());
                paymentMethodLabel.setText(newBooking.getPaymentMethod());
                pickupDateLabel.setText(newBooking.getPickupDate().toString());
            }
        });
    }

    @javafx.fxml.FXML
    public void confirmOnAction(ActionEvent actionEvent) {

        if (selectedBooking == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Please select a booking.");
            alert.show();

            return;
        }

        selectedBooking.setStatus("Confirmed");

        BinaryFileUtil.saveList(BOOKING_FILE, bookings);

        CustomerBookingsTV.refresh();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Booking confirmed.");
        alert.show();
    }

    @javafx.fxml.FXML
    public void CancelBookingOnAction(ActionEvent actionEvent) {
        try {
            SceneSwitcher.switchTo("CancelBooking2");
        } catch (Exception e) {
        }
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