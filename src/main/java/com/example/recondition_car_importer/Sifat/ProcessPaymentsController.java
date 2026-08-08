package com.example.recondition_car_importer.Sifat;

import com.example.recondition_car_importer.Sifat.model.DataManager;
import com.example.recondition_car_importer.Sifat.model.Invoice;
import com.example.recondition_car_importer.Sifat.model.Payment;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ProcessPaymentsController implements Initializable {

    @FXML private ComboBox<Invoice> comboInvoice;
    @FXML private ComboBox<String> comboPaymentMethod;
    @FXML private TextField txtPaymentAmount;
    @FXML private Label lblRemainingBalance;

    @FXML private TableView<Payment> paymentTable;
    @FXML private TableColumn<Payment, String> colPaymentId;
    @FXML private TableColumn<Payment, String> colInvoiceId;
    @FXML private TableColumn<Payment, String> colMethod;
    @FXML private TableColumn<Payment, Double> colAmount;
    @FXML private TableColumn<Payment, String> colDate;
    @FXML private TableColumn<Payment, Double> colRemaining;

    @FXML private Label lblMessage;

    private DataManager dataManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataManager = DataManager.getInstance();

        comboInvoice.setItems(dataManager.getInvoices());
        comboPaymentMethod.getItems().addAll("Cash", "Card", "Bank Transfer", "Installment");
        comboPaymentMethod.getSelectionModel().select("Cash");

        colPaymentId.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPaymentId()));
        colInvoiceId.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getInvoiceId()));
        colMethod.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPaymentMethod()));
        colAmount.setCellValueFactory(cell -> new SimpleDoubleProperty(cell.getValue().getAmount()).asObject());
        colDate.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPaymentDate().toString()));
        colRemaining.setCellValueFactory(cell -> new SimpleDoubleProperty(cell.getValue().getRemainingBalance()).asObject());

        paymentTable.setItems(dataManager.getPayments());

        comboInvoice.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            if (newV != null) {
                lblRemainingBalance.setText(String.format("Remaining Balance: BDT %.2f", newV.getRemainingBalance()));
            }
        });
    }

    @FXML
    public void handleRecordPayment(ActionEvent event) {
        Invoice selectedInv = comboInvoice.getValue();
        if (selectedInv == null) {
            lblMessage.setText("Please select an Invoice.");
            return;
        }

        try {
            double amount = Double.parseDouble(txtPaymentAmount.getText().trim());
            if (amount <= 0) {
                lblMessage.setText("Payment amount must be greater than 0.");
                return;
            }

            double remaining = selectedInv.getRemainingBalance() - amount;
            selectedInv.setPaidAmount(selectedInv.getPaidAmount() + amount);

            String payId = "PAY-" + String.format("%02d", dataManager.getPayments().size() + 1);
            Payment payment = new Payment(payId, selectedInv.getInvoiceId(), comboPaymentMethod.getValue(), amount, LocalDate.now(), remaining);

            dataManager.getPayments().add(payment);
            dataManager.saveAllData();

            lblRemainingBalance.setText(String.format("Remaining Balance: BDT %.2f", selectedInv.getRemainingBalance()));
            lblMessage.setText("Payment " + payId + " recorded successfully!");
            txtPaymentAmount.clear();
        } catch (NumberFormatException e) {
            lblMessage.setText("Invalid payment amount.");
        }
    }
}
