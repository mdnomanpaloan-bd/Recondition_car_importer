package com.example.recondition_car_importer.Sifat;

import com.example.recondition_car_importer.Sifat.model.Car;
import com.example.recondition_car_importer.Sifat.model.DataManager;
import com.example.recondition_car_importer.Sifat.model.Expense;
import com.example.recondition_car_importer.Sifat.model.Invoice;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class FinancialAuditController implements Initializable {

    @FXML private DatePicker startDatePicker;
    @FXML private DatePicker endDatePicker;
    @FXML private TextArea txtAuditSummary;
    @FXML private Label lblMessage;

    private DataManager dataManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataManager = DataManager.getInstance();

        startDatePicker.setValue(LocalDate.now().withDayOfMonth(1));
        endDatePicker.setValue(LocalDate.now());
    }

    @FXML
    public void handleRunAudit(ActionEvent event) {
        LocalDate start = startDatePicker.getValue();
        LocalDate end = endDatePicker.getValue();

        if (start == null || end == null || start.isAfter(end)) {
            lblMessage.setText("Please select a valid date range.");
            return;
        }

        double totalInvoiced = 0;
        double totalTaxCollected = 0;
        double totalExpenses = 0;
        int salesCount = 0;

        StringBuilder sb = new StringBuilder();
        sb.append("=================================================================\n");
        sb.append("                FINANCIAL AUDIT & TAX REPORT\n");
        sb.append("PERIOD: ").append(start).append(" TO ").append(end).append("\n");
        sb.append("=================================================================\n\n");

        sb.append("--- INVOICE & TAX AUDIT SUMMARY ---\n");
        for (Invoice inv : dataManager.getInvoices()) {
            if (!inv.getInvoiceDate().isBefore(start) && !inv.getInvoiceDate().isAfter(end)) {
                totalInvoiced += inv.getTotalAmount();
                totalTaxCollected += (inv.getBasePrice() * inv.getTaxRate() / 100.0);
                salesCount++;
                sb.append(String.format("Inv #%-12s | Customer: %-15s | Tax: BDT %-10.2f | Total: BDT %.2f\n",
                        inv.getInvoiceId(), inv.getCustomerName(), (inv.getBasePrice() * inv.getTaxRate() / 100.0), inv.getTotalAmount()));
            }
        }

        sb.append("\n--- OPERATIONAL EXPENSE AUDIT ---\n");
        for (Expense exp : dataManager.getExpenses()) {
            if (!exp.getDate().isBefore(start) && !exp.getDate().isAfter(end)) {
                totalExpenses += exp.getAmount();
                sb.append(String.format("Date: %-10s | Category: %-15s | Amount: BDT %.2f\n",
                        exp.getDate(), exp.getCategory(), exp.getAmount()));
            }
        }

        double netProfit = totalInvoiced - totalExpenses;

        sb.append("\n=================================================================\n");
        sb.append(String.format("TOTAL TRANSACTIONS AUDITED : %d\n", salesCount));
        sb.append(String.format("TOTAL GROSS INVOICED       : BDT %.2f\n", totalInvoiced));
        sb.append(String.format("TOTAL CUSTOMS/VAT TAX      : BDT %.2f\n", totalTaxCollected));
        sb.append(String.format("TOTAL OPERATIONAL EXPENSES : BDT %.2f\n", totalExpenses));
        sb.append(String.format("NET AUDITED BALANCE        : BDT %.2f\n", netProfit));
        sb.append("=================================================================\n");

        txtAuditSummary.setText(sb.toString());
        lblMessage.setText("Financial audit completed successfully!");
    }

    @FXML
    public void handleSaveReport(ActionEvent event) {
        if (txtAuditSummary.getText().trim().isEmpty()) {
            lblMessage.setText("Run audit first before saving report.");
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Financial Audit Report");
        fileChooser.setInitialFileName("Financial_Audit_" + LocalDate.now() + ".txt");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File file = fileChooser.showSaveDialog(txtAuditSummary.getScene().getWindow());
        if (file != null) {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(txtAuditSummary.getText());
                lblMessage.setText("Audit report saved to: " + file.getAbsolutePath());
            } catch (IOException e) {
                lblMessage.setText("Failed to save report file.");
            }
        }
    }
}
