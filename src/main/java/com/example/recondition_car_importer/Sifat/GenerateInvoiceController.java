package com.example.recondition_car_importer.Sifat;

import com.example.recondition_car_importer.Sifat.model.Car;
import com.example.recondition_car_importer.Sifat.model.DataManager;
import com.example.recondition_car_importer.Sifat.model.Invoice;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class GenerateInvoiceController implements Initializable {

    @FXML private TextField txtCustomerName;
    @FXML private ComboBox<Car> comboSoldCar;
    @FXML private TextField txtBasePrice;
    @FXML private TextField txtTaxRate;
    @FXML private TextArea txtInvoicePreview;
    @FXML private Label lblMessage;

    private DataManager dataManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataManager = DataManager.getInstance();
        comboSoldCar.setItems(dataManager.getCars());

        comboSoldCar.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            if (newV != null) {
                txtBasePrice.setText(String.valueOf(newV.getFinalPrice()));
                if (newV.getCustomerName() != null && !newV.getCustomerName().equals("N/A")) {
                    txtCustomerName.setText(newV.getCustomerName());
                }
            }
        });
    }

    @FXML
    public void handleGenerateInvoice(ActionEvent event) {
        try {
            String customer = txtCustomerName.getText().trim();
            Car selectedCar = comboSoldCar.getValue();
            double basePrice = Double.parseDouble(txtBasePrice.getText().trim());
            double taxRate = Double.parseDouble(txtTaxRate.getText().trim());

            if (customer.isEmpty() || selectedCar == null) {
                lblMessage.setText("Please enter customer name and select a car.");
                return;
            }

            String invId = "INV-2026-" + String.format("%03d", dataManager.getInvoices().size() + 1);
            Invoice invoice = new Invoice(invId, customer, selectedCar.getBrand() + " " + selectedCar.getModel(), basePrice, taxRate, LocalDate.now());

            dataManager.getInvoices().add(invoice);
            dataManager.saveAllData();

            StringBuilder sb = new StringBuilder();
            sb.append("====================================================\n");
            sb.append("            OFFICIAL CAR SALES INVOICE\n");
            sb.append("====================================================\n");
            sb.append("INVOICE NO   : ").append(invoice.getInvoiceId()).append("\n");
            sb.append("DATE         : ").append(invoice.getInvoiceDate()).append("\n");
            sb.append("CUSTOMER     : ").append(invoice.getCustomerName()).append("\n");
            sb.append("VEHICLE MODEL: ").append(invoice.getCarModel()).append("\n");
            sb.append("----------------------------------------------------\n");
            sb.append(String.format("BASE VEHICLE PRICE : BDT %.2f\n", invoice.getBasePrice()));
            sb.append(String.format("APPLICABLE TAX (%.1f%%): BDT %.2f\n", invoice.getTaxRate(), (invoice.getBasePrice() * invoice.getTaxRate() / 100.0)));
            sb.append("----------------------------------------------------\n");
            sb.append(String.format("TOTAL PAYABLE      : BDT %.2f\n", invoice.getTotalAmount()));
            sb.append("====================================================\n");

            txtInvoicePreview.setText(sb.toString());
            lblMessage.setText("Invoice " + invId + " issued successfully!");
        } catch (NumberFormatException e) {
            lblMessage.setText("Invalid price or tax rate format.");
        }
    }
}
