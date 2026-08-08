package com.example.recondition_car_importer.Alvi.SalesExecutive;

import com.example.recondition_car_importer.Alvi.Customer.BookingRecord;
import com.example.recondition_car_importer.Alvi.Customer.CompareCars;
import com.example.recondition_car_importer.SceneSwitcher;
import com.example.recondition_car_importer.utility.BinaryFileUtil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class CustomerRecords2
{
    @javafx.fxml.FXML
    private TableColumn<BookingRecord,String > phoneTV;
    @javafx.fxml.FXML
    private Label phoneLabel;
    @javafx.fxml.FXML
    private TableColumn<BookingRecord,String > emailTV;
    @javafx.fxml.FXML
    private TextField SearchCustomerTF;
    @javafx.fxml.FXML
    private Label PurchaseHistoryLabel;
    @javafx.fxml.FXML
    private TableView<BookingRecord > CustomerRecordTV;
    @javafx.fxml.FXML
    private TableColumn<BookingRecord,String > totalPurchaseTV;
    @javafx.fxml.FXML
    private TableColumn<BookingRecord,String > customerNameTV;
    @javafx.fxml.FXML
    private Label addressLabel;
    @javafx.fxml.FXML
    private Label customerNameLabel;
    @javafx.fxml.FXML
    private Label emailLabel;
    private static final String BOOKING_FILE = "Data/Alvi-2310827/Bookings";

    private ArrayList<BookingRecord> bookings;

    private BookingRecord selectedCustomer;

    @javafx.fxml.FXML
    public void initialize() {

        customerNameTV.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        emailTV.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneTV.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        totalPurchaseTV.setCellValueFactory(new PropertyValueFactory<>("purchaseCount"));

        bookings = BinaryFileUtil.readList(BOOKING_FILE);

        CustomerRecordTV.setItems(FXCollections.observableArrayList(bookings));

        CustomerRecordTV.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {

            if (newValue != null) {

                selectedCustomer = newValue;

                customerNameLabel.setText(newValue.getFullName());
                emailLabel.setText(newValue.getEmail());
                phoneLabel.setText(newValue.getPhoneNumber());
                addressLabel.setText(newValue.getAddress());
                PurchaseHistoryLabel.setText(String.valueOf(newValue.getPurchaseCount()));
            }
        });
    }

    @javafx.fxml.FXML
    public void backOnAction(ActionEvent actionEvent) {
        try {
            SceneSwitcher.switchTo("Alvi/SalesExecutive/SalesExecutiveDashboard");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public void viewButtonHistoryOnAction(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Purchase History");
        alert.setContentText(
                "Customer: " + selectedCustomer.getFullName() +
                        "\nVehicle: " + selectedCustomer.getCarName() +
                        "\nPayment: " + selectedCustomer.getPaymentMethod() +
                        "\nTotal Cost: " + selectedCustomer.getFinalCost()
        );
        alert.showAndWait();
    }

    @javafx.fxml.FXML
    public void SearchCustomerOnAction(ActionEvent actionEvent) {
        String name = SearchCustomerTF.getText().trim();

        if (name.isEmpty()) {
            CustomerRecordTV.setItems(FXCollections.observableArrayList(bookings));
            return;
        }

        ArrayList<BookingRecord> result = new ArrayList<>();

        for (BookingRecord booking : bookings) {

            if (booking.getFullName().toLowerCase().contains(name.toLowerCase())) {
                result.add(booking);
            }
        }

        CustomerRecordTV.setItems(FXCollections.observableArrayList(result));

        if (result.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("No customer found.");
            alert.show();
        }
    }
}