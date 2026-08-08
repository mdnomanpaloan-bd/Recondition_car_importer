package com.example.recondition_car_importer.Sifat;

import com.example.recondition_car_importer.Sifat.model.Car;
import com.example.recondition_car_importer.Sifat.model.DataManager;
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

public class SalesReportsController implements Initializable {

    @FXML private ComboBox<String> comboPeriod;
    @FXML private TextArea txtReportSummary;
    @FXML private Label lblTotalUnits;
    @FXML private Label lblTotalRevenue;
    @FXML private Label lblMessage;

    private DataManager dataManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataManager = DataManager.getInstance();

        comboPeriod.getItems().addAll("Current Month", "Last Month", "Year To Date 2026");
        comboPeriod.getSelectionModel().select("Current Month");
    }

    @FXML
    public void handleGenerateReport(ActionEvent event) {
        int totalUnits = 0;
        double totalRevenue = 0;

        StringBuilder sb = new StringBuilder();
        sb.append("====================================================\n");
        sb.append("      SHOWROOM MONTHLY SALES REPORT (").append(comboPeriod.getValue()).append(")\n");
        sb.append("====================================================\n");
        sb.append(String.format("%-10s %-25s %-15s %-12s\n", "CAR ID", "BRAND & MODEL", "STATUS", "FINAL PRICE (BDT)"));
        sb.append("----------------------------------------------------\n");

        for (Car car : dataManager.getCars()) {
            if ("Sold".equalsIgnoreCase(car.getStatus()) || "Booked".equalsIgnoreCase(car.getStatus())) {
                totalUnits++;
                totalRevenue += car.getFinalPrice();
                sb.append(String.format("%-10s %-25s %-15s BDT %-10.2f\n",
                        car.getCarId(), car.getBrand() + " " + car.getModel(), car.getStatus(), car.getFinalPrice()));
            }
        }

        sb.append("----------------------------------------------------\n");
        sb.append(String.format("TOTAL UNITS SOLD/BOOKED : %d\n", totalUnits));
        sb.append(String.format("TOTAL GROSS REVENUE     : BDT %.2f\n", totalRevenue));
        sb.append("====================================================\n");

        txtReportSummary.setText(sb.toString());
        lblTotalUnits.setText("Total Units Sold: " + totalUnits);
        lblTotalRevenue.setText(String.format("Total Revenue: BDT %.2f", totalRevenue));
        lblMessage.setText("Report generated successfully!");
    }

    @FXML
    public void handleExportReport(ActionEvent event) {
        if (txtReportSummary.getText().trim().isEmpty()) {
            lblMessage.setText("Generate a report first before exporting.");
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export Sales Report");
        fileChooser.setInitialFileName("Sales_Report_" + LocalDate.now() + ".txt");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File file = fileChooser.showSaveDialog(txtReportSummary.getScene().getWindow());
        if (file != null) {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(txtReportSummary.getText());
                lblMessage.setText("Report exported to: " + file.getAbsolutePath());
            } catch (IOException e) {
                lblMessage.setText("Failed to save report file.");
            }
        }
    }
}
