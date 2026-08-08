package com.example.recondition_car_importer.Sifat;

import com.example.recondition_car_importer.Sifat.model.DataManager;
import com.example.recondition_car_importer.Sifat.model.Expense;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ShowroomExpensesController implements Initializable {

    @FXML private ComboBox<String> comboCategory;
    @FXML private TextField txtAmount;
    @FXML private DatePicker datePickerExpense;

    @FXML private TableView<Expense> expenseTable;
    @FXML private TableColumn<Expense, String> colCategory;
    @FXML private TableColumn<Expense, Double> colAmount;
    @FXML private TableColumn<Expense, String> colDate;

    @FXML private Label lblTotalExpenditure;
    @FXML private Label lblMessage;

    private DataManager dataManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataManager = DataManager.getInstance();

        comboCategory.getItems().addAll("Rent", "Utility", "Salary", "Maintenance", "Marketing", "Misc");
        comboCategory.getSelectionModel().select("Rent");
        datePickerExpense.setValue(LocalDate.now());

        colCategory.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCategory()));
        colAmount.setCellValueFactory(cell -> new SimpleDoubleProperty(cell.getValue().getAmount()).asObject());
        colDate.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDate().toString()));

        expenseTable.setItems(dataManager.getExpenses());
        updateTotal();
    }

    @FXML
    public void handleAddExpense(ActionEvent event) {
        try {
            String category = comboCategory.getValue();
            double amount = Double.parseDouble(txtAmount.getText().trim());
            LocalDate date = datePickerExpense.getValue();

            if (amount <= 0 || date == null) {
                lblMessage.setText("Please enter valid amount and select date.");
                return;
            }

            String expId = "EXP-" + String.format("%02d", dataManager.getExpenses().size() + 1);
            Expense expense = new Expense(expId, category, amount, date);

            dataManager.getExpenses().add(expense);
            dataManager.saveAllData();

            updateTotal();
            txtAmount.clear();
            lblMessage.setText("Expense recorded successfully!");
        } catch (NumberFormatException e) {
            lblMessage.setText("Invalid amount format.");
        }
    }

    private void updateTotal() {
        double total = 0;
        for (Expense e : dataManager.getExpenses()) {
            total += e.getAmount();
        }
        lblTotalExpenditure.setText(String.format("Total Showroom Expenditure: BDT %.2f", total));
    }
}
