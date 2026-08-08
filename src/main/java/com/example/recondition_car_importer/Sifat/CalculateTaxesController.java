package com.example.recondition_car_importer.Sifat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CalculateTaxesController {

    @FXML private TextField txtImportValue;
    @FXML private TextField txtEngineCC;
    @FXML private Label lblDutyAmount;
    @FXML private Label lblVatAmount;
    @FXML private Label lblTotalTax;
    @FXML private Label lblMessage;

    @FXML
    public void handleCalculateTax(ActionEvent event) {
        try {
            double importValue = Double.parseDouble(txtImportValue.getText().trim());
            int cc = Integer.parseInt(txtEngineCC.getText().trim());

            if (importValue <= 0 || cc <= 0) {
                lblMessage.setText("Please enter valid positive values for Import Value and CC.");
                return;
            }

            // Progressive Duty Rate according to Engine CC
            double dutyRate;
            if (cc <= 1500) {
                dutyRate = 0.25; // 25%
            } else if (cc <= 2000) {
                dutyRate = 0.45; // 45%
            } else if (cc <= 3000) {
                dutyRate = 0.80; // 80%
            } else {
                dutyRate = 1.20; // 120%
            }

            double duty = importValue * dutyRate;
            double vat = (importValue + duty) * 0.15; // 15% VAT on base + duty
            double totalTax = duty + vat;

            lblDutyAmount.setText(String.format("Import Duty (%.0f%%): BDT %.2f", (dutyRate * 100), duty));
            lblVatAmount.setText(String.format("VAT (15%%): BDT %.2f", vat));
            lblTotalTax.setText(String.format("Total Payable Customs Tax: BDT %.2f", totalTax));
            lblMessage.setText("Tax calculated successfully based on NBR / Customs Policy!");
        } catch (NumberFormatException e) {
            lblMessage.setText("Invalid input format for Import Value or Engine CC.");
        }
    }
}
