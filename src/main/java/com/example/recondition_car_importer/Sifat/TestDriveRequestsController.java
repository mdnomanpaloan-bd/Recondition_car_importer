package com.example.recondition_car_importer.Sifat;

import com.example.recondition_car_importer.Sifat.model.DataManager;
import com.example.recondition_car_importer.Sifat.model.TestDriveRequest;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class TestDriveRequestsController implements Initializable {

    @FXML private TableView<TestDriveRequest> requestTable;
    @FXML private TableColumn<TestDriveRequest, String> colId;
    @FXML private TableColumn<TestDriveRequest, String> colCustomer;
    @FXML private TableColumn<TestDriveRequest, String> colCarModel;
    @FXML private TableColumn<TestDriveRequest, String> colDate;
    @FXML private TableColumn<TestDriveRequest, String> colStatus;

    @FXML private TextArea txtManagerRemarks;
    @FXML private Label lblStatusMessage;

    private DataManager dataManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataManager = DataManager.getInstance();

        colId.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getRequestId()));
        colCustomer.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCustomerName()));
        colCarModel.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCarModel()));
        colDate.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getRequestDate().toString()));
        colStatus.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getStatus()));

        requestTable.setItems(dataManager.getTestDriveRequests());

        requestTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                txtManagerRemarks.setText(newVal.getManagerRemarks() != null ? newVal.getManagerRemarks() : "");
            }
        });
    }

    @FXML
    public void handleApprove(ActionEvent event) {
        TestDriveRequest selected = requestTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            lblStatusMessage.setText("Please select a test drive request to approve.");
            return;
        }

        selected.setStatus("Approved");
        selected.setManagerRemarks(txtManagerRemarks.getText().trim());
        requestTable.refresh();
        dataManager.saveAllData();
        lblStatusMessage.setText("Request " + selected.getRequestId() + " Approved!");
    }

    @FXML
    public void handleReject(ActionEvent event) {
        TestDriveRequest selected = requestTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            lblStatusMessage.setText("Please select a test drive request to reject.");
            return;
        }

        selected.setStatus("Rejected");
        selected.setManagerRemarks(txtManagerRemarks.getText().trim());
        requestTable.refresh();
        dataManager.saveAllData();
        lblStatusMessage.setText("Request " + selected.getRequestId() + " Rejected!");
    }
}
