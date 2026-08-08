package com.example.recondition_car_importer.Sifat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SifatDashboardController implements Initializable {

    @FXML private StackPane contentArea;
    @FXML private Label lblActiveViewTitle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadView("manage_inventory.fxml", "Goal 1: Manage Inventory & Showroom Cars");
    }

    private void loadView(String fxmlFile, String title) {
        try {
            URL resource = getClass().getResource("/com/example/recondition_car_importer/Sifat/" + fxmlFile);
            if (resource == null) {
                System.err.println("Error: Could not locate FXML file at /com/example/recondition_car_importer/Sifat/" + fxmlFile);
                return;
            }
            FXMLLoader loader = new FXMLLoader(resource);
            Node view = loader.load();
            contentArea.getChildren().setAll(view);
            lblActiveViewTitle.setText(title);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error loading FXML: " + fxmlFile + " -> " + e.getMessage());
        }
    }

    // USER 5: SHOWROOM MANAGER GOALS
    @FXML public void handleGoal1(ActionEvent e) { loadView("manage_inventory.fxml", "Goal 1: Manage Inventory & Showroom Cars"); }
    @FXML public void handleGoal2(ActionEvent e) { loadView("assign_sales.fxml", "Goal 2: Assign Cars to Sales Representatives"); }
    @FXML public void handleGoal3(ActionEvent e) { loadView("vehicle_pricing.fxml", "Goal 3: Set Vehicle Pricing & Discounts"); }
    @FXML public void handleGoal4(ActionEvent e) { loadView("test_drive_requests.fxml", "Goal 4: Approve Customer Test Drive Requests"); }
    @FXML public void handleGoal5(ActionEvent e) { loadView("sales_performance.fxml", "Goal 5: Monitor Sales Rep Performance"); }
    @FXML public void handleGoal6(ActionEvent e) { loadView("track_sales_booking.fxml", "Goal 6: Track Vehicle Sales & Booking Status"); }
    @FXML public void handleGoal7(ActionEvent e) { loadView("customer_complaints.fxml", "Goal 7: Handle Customer Complaints & Escalations"); }
    @FXML public void handleGoal8(ActionEvent e) { loadView("sales_reports.fxml", "Goal 8: Generate Monthly Showroom Sales Reports"); }

    // USER 6: FINANCE OFFICER GOALS
    @FXML public void handleGoal9(ActionEvent e) { loadView("generate_invoice.fxml", "Goal 9: Generate & Issue Invoices"); }
    @FXML public void handleGoal10(ActionEvent e) { loadView("process_payments.fxml", "Goal 10: Process Customer Payments & Installments"); }
    @FXML public void handleGoal11(ActionEvent e) { loadView("car_financing.fxml", "Goal 11: Approve Car Financing & Loans"); }
    @FXML public void handleGoal12(ActionEvent e) { loadView("calculate_taxes.fxml", "Goal 12: Calculate Taxes, Import Duty & Customs"); }
    @FXML public void handleGoal13(ActionEvent e) { loadView("showroom_expenses.fxml", "Goal 13: Manage Showroom Expense Records"); }
    @FXML public void handleGoal14(ActionEvent e) { loadView("employee_salary.fxml", "Goal 14: Process Employee Salary & Commissions"); }
    @FXML public void handleGoal15(ActionEvent e) { loadView("track_profit.fxml", "Goal 15: Track Profit, Revenue & Profit Margin"); }
    @FXML public void handleGoal16(ActionEvent e) { loadView("financial_audit.fxml", "Goal 16: Generate Financial Audit & Tax Reports"); }
}
