package com.example.recondition_car_importer.Sifat;

import com.example.recondition_car_importer.Sifat.model.DataManager;
import com.example.recondition_car_importer.Sifat.model.SalesRep;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SalesPerformanceController implements Initializable {

    @FXML private BarChart<String, Number> barChartSales;
    @FXML private CategoryAxis xAxis;
    @FXML private NumberAxis yAxis;

    @FXML private TableView<SalesRep> repTable;
    @FXML private TableColumn<SalesRep, String> colRepName;
    @FXML private TableColumn<SalesRep, Integer> colCarsSold;
    @FXML private TableColumn<SalesRep, Double> colRevenue;

    @FXML private DatePicker datePickerFilter;
    @FXML private Label lblSummary;

    private DataManager dataManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataManager = DataManager.getInstance();

        colRepName.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
        colCarsSold.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getCarsSold()).asObject());
        colRevenue.setCellValueFactory(cell -> new SimpleDoubleProperty(cell.getValue().getRevenueGenerated()).asObject());

        repTable.setItems(dataManager.getSalesReps());

        renderChart();
    }

    private void renderChart() {
        barChartSales.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Revenue Generated (BDT)");

        double totalRev = 0;
        int totalSold = 0;

        for (SalesRep rep : dataManager.getSalesReps()) {
            series.getData().add(new XYChart.Data<>(rep.getName(), rep.getRevenueGenerated()));
            totalRev += rep.getRevenueGenerated();
            totalSold += rep.getCarsSold();
        }

        barChartSales.getData().add(series);
        lblSummary.setText(String.format("Total Sales: %d Cars | Total Revenue: BDT %.2f", totalSold, totalRev));
    }

    @FXML
    public void handleFilterPeriod(ActionEvent event) {
        // Date filtering logic can filter and render chart
        renderChart();
    }
}
