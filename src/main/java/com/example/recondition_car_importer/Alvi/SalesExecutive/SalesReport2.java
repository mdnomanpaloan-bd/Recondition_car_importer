package com.example.recondition_car_importer.Alvi.SalesExecutive;

import com.example.recondition_car_importer.Alvi.Customer.BookingRecord;
import com.example.recondition_car_importer.SceneSwitcher;
import com.example.recondition_car_importer.utility.BinaryFileUtil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class SalesReport2
{
    @javafx.fxml.FXML
    private DatePicker reportFromDP;
    @javafx.fxml.FXML
    private TableColumn<BookingRecord,String> dateTV;
    @javafx.fxml.FXML
    private TableColumn<BookingRecord,String>  CustomerTV;
    @javafx.fxml.FXML
    private TableColumn <BookingRecord,String> vehicleTV;
    @javafx.fxml.FXML
    private Label totalRevenueLabel;
    @javafx.fxml.FXML
    private DatePicker ReportToDP;
    @javafx.fxml.FXML
    private TableView<BookingRecord>  salesRerportTV;
    @javafx.fxml.FXML
    private TableColumn <BookingRecord,String> invoiceTV;
    @javafx.fxml.FXML
    private TableColumn<BookingRecord,String>  amountTV;
    @javafx.fxml.FXML
    private Label totalVehicleSoldLabel;
    private static final String BOOKING_FILE = "Data/Alvi-2310827/Bookings";

    private ArrayList<BookingRecord> bookings;


    private void calculateSummary(ArrayList<BookingRecord> list) {

        double revenue = 0;

        for (BookingRecord booking : list) {
            revenue += booking.getFinalCost();
        }

        totalRevenueLabel.setText(String.format("%.2f", revenue));

        totalVehicleSoldLabel.setText(String.valueOf(list.size()));
    }

    @javafx.fxml.FXML
    public void initialize() {
        invoiceTV.setCellValueFactory(new PropertyValueFactory<>("bookingID"));
        CustomerTV.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        vehicleTV.setCellValueFactory(new PropertyValueFactory<>("carName"));
        dateTV.setCellValueFactory(new PropertyValueFactory<>("pickupDate"));
        amountTV.setCellValueFactory(new PropertyValueFactory<>("finalCost"));

        bookings = BinaryFileUtil.readList(BOOKING_FILE);

        salesRerportTV.setItems(FXCollections.observableArrayList(bookings));

        calculateSummary(bookings);
    }

    @javafx.fxml.FXML
    public void backOnAction(ActionEvent actionEvent) {
        try {
            SceneSwitcher.switchTo("Alvi/SalesExecutive/SalesExecutiveDashboard");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void GenerateReportOnAction(ActionEvent actionEvent) {
        if (reportFromDP.getValue() == null || ReportToDP.getValue() == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Please select both dates.");
            alert.show();

            return;
        }

        LocalDate from = reportFromDP.getValue();
        LocalDate to = ReportToDP.getValue();

        ArrayList<BookingRecord> report = new ArrayList<>();

        for (BookingRecord booking : bookings) {

            LocalDate date = booking.getPickupDate();

            if ((date.isEqual(from) || date.isAfter(from))
                    && (date.isEqual(to) || date.isBefore(to))) {

                report.add(booking);
            }
        }

        salesRerportTV.setItems(FXCollections.observableArrayList(report));

        calculateSummary(report);
    }


}