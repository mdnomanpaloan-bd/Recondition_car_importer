package com.example.recondition_car_importer.Alvi.SalesExecutive;

import com.example.recondition_car_importer.SceneSwitcher;
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
    private TableColumn<SalesReport2,String> dateTV;
    @javafx.fxml.FXML
    private TableColumn<SalesReport2,String>  CustomerTV;
    @javafx.fxml.FXML
    private TableColumn <SalesReport2,String> vehicleTV;
    @javafx.fxml.FXML
    private Label totalRevenueLabel;
    @javafx.fxml.FXML
    private DatePicker ReportToDP;
    @javafx.fxml.FXML
    private TableView<String>  salesRerportTV;
    @javafx.fxml.FXML
    private TableColumn <SalesReport2,String> invoiceTV;
    @javafx.fxml.FXML
    private TableColumn<SalesReport2,String>  amountTV;
    @javafx.fxml.FXML
    private Label totalVehicleSoldLabel;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void backOnAction(ActionEvent actionEvent) {
        try {
            SceneSwitcher.switchTo("Alvi/SalesExecutive/SalesExecutiveDashboard");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void GenerateReportOnAction(ActionEvent actionEvent) {
    }
}