package com.example.recondition_car_importer.Sifat;

import com.example.recondition_car_importer.Sifat.model.CarLoan;
import com.example.recondition_car_importer.Sifat.model.DataManager;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class CarFinancingController implements Initializable {

    @FXML private TableView<CarLoan> loanTable;
    @FXML private TableColumn<CarLoan, String> colAppId;
    @FXML private TableColumn<CarLoan, String> colApplicant;
    @FXML private TableColumn<CarLoan, Double> colAmount;
    @FXML private TableColumn<CarLoan, Integer> colDuration;
    @FXML private TableColumn<CarLoan, Double> colInterest;
    @FXML private TableColumn<CarLoan, Double> colEmi;
    @FXML private TableColumn<CarLoan, String> colStatus;

    @FXML private TextField txtInterestRate;
    @FXML private Label lblEmiDisplay;
    @FXML private Label lblMessage;

    private DataManager dataManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataManager = DataManager.getInstance();

        colAppId.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getApplicationId()));
        colApplicant.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getApplicantName()));
        colAmount.setCellValueFactory(cell -> new SimpleDoubleProperty(cell.getValue().getLoanAmount()).asObject());
        colDuration.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getDurationMonths()).asObject());
        colInterest.setCellValueFactory(cell -> new SimpleDoubleProperty(cell.getValue().getInterestRate()).asObject());
        colEmi.setCellValueFactory(cell -> new SimpleDoubleProperty(cell.getValue().getMonthlyEmi()).asObject());
        colStatus.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getStatus()));

        loanTable.setItems(dataManager.getLoans());

        loanTable.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            if (newV != null) {
                txtInterestRate.setText(String.valueOf(newV.getInterestRate()));
                lblEmiDisplay.setText(String.format("Calculated Monthly EMI: BDT %.2f", newV.getMonthlyEmi()));
            }
        });
    }

    @FXML
    public void handleApproveLoan(ActionEvent event) {
        CarLoan selected = loanTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            lblMessage.setText("Please select a loan application to approve.");
            return;
        }

        try {
            double rate = Double.parseDouble(txtInterestRate.getText().trim());
            selected.setInterestRate(rate);
            selected.setStatus("Approved");

            loanTable.refresh();
            dataManager.saveAllData();

            lblEmiDisplay.setText(String.format("Calculated Monthly EMI: BDT %.2f", selected.getMonthlyEmi()));
            lblMessage.setText("Loan " + selected.getApplicationId() + " Approved!");
        } catch (NumberFormatException e) {
            lblMessage.setText("Invalid interest rate.");
        }
    }

    @FXML
    public void handleRejectLoan(ActionEvent event) {
        CarLoan selected = loanTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            lblMessage.setText("Please select a loan application to reject.");
            return;
        }

        selected.setStatus("Rejected");
        loanTable.refresh();
        dataManager.saveAllData();
        lblMessage.setText("Loan " + selected.getApplicationId() + " Rejected.");
    }
}
