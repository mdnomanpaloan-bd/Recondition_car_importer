package com.example.recondition_car_importer.Sifat;

import com.example.recondition_car_importer.Sifat.model.DataManager;
import com.example.recondition_car_importer.Sifat.model.EmployeeSalary;
import com.example.recondition_car_importer.Sifat.model.Expense;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EmployeeSalaryController implements Initializable {

    @FXML private TableView<EmployeeSalary> salaryTable;
    @FXML private TableColumn<EmployeeSalary, String> colEmpId;
    @FXML private TableColumn<EmployeeSalary, String> colName;
    @FXML private TableColumn<EmployeeSalary, Double> colBaseSalary;
    @FXML private TableColumn<EmployeeSalary, Double> colCommRate;
    @FXML private TableColumn<EmployeeSalary, Integer> colSalesCount;
    @FXML private TableColumn<EmployeeSalary, Double> colTotalPay;

    @FXML private Label lblPayoutSummary;
    @FXML private Label lblMessage;

    private DataManager dataManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataManager = DataManager.getInstance();

        colEmpId.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getEmployeeId()));
        colName.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
        colBaseSalary.setCellValueFactory(cell -> new SimpleDoubleProperty(cell.getValue().getBaseSalary()).asObject());
        colCommRate.setCellValueFactory(cell -> new SimpleDoubleProperty(cell.getValue().getCommissionRate()).asObject());
        colSalesCount.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getSalesCount()).asObject());
        colTotalPay.setCellValueFactory(cell -> new SimpleDoubleProperty(cell.getValue().getTotalPay()).asObject());

        salaryTable.setItems(dataManager.getEmployeeSalaries());
        updateSummary();
    }

    @FXML
    public void handleCalculateAndPay(ActionEvent event) {
        EmployeeSalary selected = salaryTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            lblMessage.setText("Please select an employee to process payout.");
            return;
        }

        double totalPay = selected.getTotalPay();
        // Log as expense
        Expense salaryExpense = new Expense("EXP-SAL-" + selected.getEmployeeId(), "Salary", totalPay, LocalDate.now());
        dataManager.getExpenses().add(salaryExpense);
        dataManager.saveAllData();

        lblMessage.setText("Successfully paid BDT " + String.format("%.2f", totalPay) + " to " + selected.getName());
        updateSummary();
    }

    private void updateSummary() {
        double grandTotal = 0;
        for (EmployeeSalary emp : dataManager.getEmployeeSalaries()) {
            grandTotal += emp.getTotalPay();
        }
        lblPayoutSummary.setText(String.format("Total Monthly Payroll Expense: BDT %.2f", grandTotal));
    }
}
