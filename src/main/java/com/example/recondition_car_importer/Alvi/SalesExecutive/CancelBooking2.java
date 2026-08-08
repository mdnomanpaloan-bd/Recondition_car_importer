package com.example.recondition_car_importer.Alvi.SalesExecutive;

import com.example.recondition_car_importer.Alvi.Customer.BookingRecord;
import com.example.recondition_car_importer.SceneSwitcher;
import com.example.recondition_car_importer.utility.BinaryFileUtil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.util.ArrayList;

public class CancelBooking2
{
    @javafx.fxml.FXML
    private Label customerLabel;
    @javafx.fxml.FXML
    private Label currentStatusLabel;
    @javafx.fxml.FXML
    private Label bookingDateLabel;
    @javafx.fxml.FXML
    private Label bookingIDlabel;
    @javafx.fxml.FXML
    private TextField searchBookingTF;
    @javafx.fxml.FXML
    private Label vehicleLabel;
    @javafx.fxml.FXML
    private ComboBox<String> cancellationReasonCB;

    private static final String BOOKING_FILE = "Data/Alvi-2310827/Bookings";

    private ArrayList<BookingRecord> bookings;

    private BookingRecord selectedBooking;

    @javafx.fxml.FXML
    public void initialize() {
        cancellationReasonCB.setItems(FXCollections.observableArrayList(

                "Customer Request",
                "Payment Failed",
                "Vehicle Unavailable",
                "Duplicate Booking",
                "Other"

        ));

        bookings = BinaryFileUtil.readList(BOOKING_FILE);
    }

    @javafx.fxml.FXML
    public void cancelBookingOnAction(ActionEvent actionEvent) {
        if (selectedBooking == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Search a booking first.");
            alert.show();

            return;
        }

        if (cancellationReasonCB.getValue() == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Select a cancellation reason.");
            alert.show();

            return;
        }

        selectedBooking.setStatus("Cancelled");

        BinaryFileUtil.saveList(BOOKING_FILE, bookings);

        currentStatusLabel.setText("Cancelled");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Booking cancelled successfully.");
        alert.show();
    }

    @javafx.fxml.FXML
    public void SearchCancelBookingOnAction(ActionEvent actionEvent) {
        String bookingID = searchBookingTF.getText();

        selectedBooking = null;

        for (BookingRecord booking : bookings) {

            if (booking.getBookingID().equalsIgnoreCase(bookingID)) {

                selectedBooking = booking;

                break;
            }
        }

        if (selectedBooking == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Booking not found.");
            alert.show();

            return;
        }

        bookingIDlabel.setText(selectedBooking.getBookingID());
        customerLabel.setText(selectedBooking.getFullName());
        vehicleLabel.setText(selectedBooking.getCarName());
        bookingDateLabel.setText(selectedBooking.getBookingDate().toString());
        currentStatusLabel.setText(selectedBooking.getStatus());


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