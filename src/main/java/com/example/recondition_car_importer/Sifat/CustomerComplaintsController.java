package com.example.recondition_car_importer.Sifat;

import com.example.recondition_car_importer.Sifat.model.Complaint;
import com.example.recondition_car_importer.Sifat.model.DataManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerComplaintsController implements Initializable {

    @FXML private TableView<Complaint> complaintTable;
    @FXML private TableColumn<Complaint, String> colId;
    @FXML private TableColumn<Complaint, String> colCustomer;
    @FXML private TableColumn<Complaint, String> colIssue;
    @FXML private TableColumn<Complaint, String> colPriority;
    @FXML private TableColumn<Complaint, String> colStatus;

    @FXML private ComboBox<String> comboStatus;
    @FXML private TextArea txtResolution;
    @FXML private Label lblMessage;

    private DataManager dataManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataManager = DataManager.getInstance();

        comboStatus.getItems().addAll("Pending", "In Progress", "Resolved");
        comboStatus.getSelectionModel().select("In Progress");

        colId.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getComplaintId()));
        colCustomer.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCustomerName()));
        colIssue.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getIssue()));
        colPriority.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPriority()));
        colStatus.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getStatus()));

        complaintTable.setItems(dataManager.getComplaints());

        complaintTable.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            if (newV != null) {
                comboStatus.getSelectionModel().select(newV.getStatus());
                txtResolution.setText(newV.getResolutionDetails());
            }
        });
    }

    @FXML
    public void handleSaveResolution(ActionEvent event) {
        Complaint selected = complaintTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            lblMessage.setText("Please select a complaint to update.");
            return;
        }

        selected.setStatus(comboStatus.getValue());
        selected.setResolutionDetails(txtResolution.getText().trim());

        complaintTable.refresh();
        dataManager.saveAllData();

        lblMessage.setText("Complaint " + selected.getComplaintId() + " resolution saved.");
    }
}
