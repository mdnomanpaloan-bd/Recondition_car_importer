package com.example.recondition_car_importer.Alvi;

import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SalesReport2
{
    @javafx.fxml.FXML
    private DatePicker reportFromDP;
    @javafx.fxml.FXML
    private TableColumn dateTV;
    @javafx.fxml.FXML
    private TableColumn CustomerTV;
    @javafx.fxml.FXML
    private TableColumn vehicleTV;
    @javafx.fxml.FXML
    private Label totalRevenueLabel;
    @javafx.fxml.FXML
    private DatePicker ReportToDP;
    @javafx.fxml.FXML
    private TableView salesRerportTV;
    @javafx.fxml.FXML
    private TableColumn invoiceTV;
    @javafx.fxml.FXML
    private TableColumn amountTV;
    @javafx.fxml.FXML
    private Label totalVehicleSoldLabel;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void backOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void GenerateReportOnAction(ActionEvent actionEvent) {
    }
}