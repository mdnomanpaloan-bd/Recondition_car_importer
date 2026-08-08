package com.example.recondition_car_importer.Alvi.Customer;

import com.example.recondition_car_importer.SceneSwitcher;
import com.example.recondition_car_importer.utility.BinaryFileUtil;
import com.example.recondition_car_importer.utility.SelectedCarHolder;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.awt.print.Book;
import java.util.ArrayList;
import com.example.recondition_car_importer.utility.BookingRecordHolder;


public class Booking
{
    @javafx.fxml.FXML
    private TextField fullNameTF;
    @javafx.fxml.FXML
    private Label paymentMethodLabel;
    @javafx.fxml.FXML
    private Label SelectedVehicleLabel;
    @javafx.fxml.FXML
    private Label FinalCostLabel;
    @javafx.fxml.FXML
    private CheckBox agreementCB;
    @javafx.fxml.FXML
    private DatePicker pickupDateDP;
    @javafx.fxml.FXML
    private TextField NIDTF;
    @javafx.fxml.FXML
    private TextField PhoneNumberTF;
    private static final String BOOKING_FILE = "Data/Alvi-2310827/Bookings";



    @javafx.fxml.FXML
    public void initialize() {
        if (SelectedCarHolder.selectedCar != null) {

            SelectedVehicleLabel.setText(
                    SelectedCarHolder.selectedCar.getMake()
                            + " "
                            + SelectedCarHolder.selectedCar.getModel()
            );
        }

        paymentMethodLabel.setText(BookingRecordHolder.paymentMethod);

        FinalCostLabel.setText(
                String.format("%.2f", BookingRecordHolder.finalCost)
        );
    }

    @javafx.fxml.FXML
    public void backOnAction(ActionEvent actionEvent) {
        try {
            SceneSwitcher.switchTo("Alvi/Customer/CarDetails");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void confirmBookingOnAction(ActionEvent actionEvent) {
        if (fullNameTF.getText().isEmpty()
                || NIDTF.getText().isEmpty()
                || PhoneNumberTF.getText().isEmpty()
                || pickupDateDP.getValue() == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields.");
            alert.show();

            return;
        }

        if (!agreementCB.isSelected()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Please accept the agreement.");
            alert.show();

            return;
        }

        ArrayList<BookingRecord> bookings =
                BinaryFileUtil.readList(BOOKING_FILE);

        BookingRecord booking = new BookingRecord(

                fullNameTF.getText(),
                NIDTF.getText(),
                PhoneNumberTF.getText(),
                pickupDateDP.getValue(),

                SelectedCarHolder.selectedCar.getCarID(),

                SelectedCarHolder.selectedCar.getMake()
                        + " "
                        + SelectedCarHolder.selectedCar.getModel(),

                BookingRecordHolder.paymentMethod,

                BookingRecordHolder.finalCost

        );

        bookings.add(booking);

        BinaryFileUtil.saveList(BOOKING_FILE, bookings);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Booking Confirmed!");
        alert.showAndWait();
    }


}