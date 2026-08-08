package com.example.recondition_car_importer.Sifat;

import com.example.recondition_car_importer.Sifat.model.Car;
import com.example.recondition_car_importer.Sifat.model.DataManager;
import com.example.recondition_car_importer.Sifat.model.SalesRep;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AssignSalesController implements Initializable {

    @FXML private ComboBox<Car> comboCar;
    @FXML private ComboBox<SalesRep> comboSalesRep;
    @FXML private TableView<Car> assignmentTable;
    @FXML private TableColumn<Car, String> colCarId;
    @FXML private TableColumn<Car, String> colModel;
    @FXML private TableColumn<Car, String> colAssignedRep;
    @FXML private TableColumn<Car, String> colDate;
    @FXML private Label lblMessage;

    private DataManager dataManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataManager = DataManager.getInstance();

        comboCar.setItems(dataManager.getCars());
        comboSalesRep.setItems(dataManager.getSalesReps());

        colCarId.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCarId()));
        colModel.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getBrand() + " " + cell.getValue().getModel()));
        colAssignedRep.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getAssignedRep()));
        colDate.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getBookingDate() != null ? cell.getValue().getBookingDate().format(DateTimeFormatter.ISO_DATE) : LocalDate.now().toString()));

        assignmentTable.setItems(dataManager.getCars());
    }

    @FXML
    public void handleAssignRep(ActionEvent event) {
        Car selectedCar = comboCar.getValue();
        SalesRep selectedRep = comboSalesRep.getValue();

        if (selectedCar == null || selectedRep == null) {
            lblMessage.setText("Please select both a Car and a Sales Representative.");
            return;
        }

        selectedCar.setAssignedRep(selectedRep.getName());
        selectedCar.setBookingDate(LocalDate.now());
        assignmentTable.refresh();
        dataManager.saveAllData();

        lblMessage.setText("Successfully assigned " + selectedCar.getCarId() + " to " + selectedRep.getName());
    }
}
