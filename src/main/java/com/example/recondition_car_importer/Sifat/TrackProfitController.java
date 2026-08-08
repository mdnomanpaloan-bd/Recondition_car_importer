package com.example.recondition_car_importer.Sifat;

import com.example.recondition_car_importer.Sifat.model.Car;
import com.example.recondition_car_importer.Sifat.model.DataManager;
import com.example.recondition_car_importer.Sifat.model.Expense;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class TrackProfitController implements Initializable {

    @FXML private Label lblTotalRevenue;
    @FXML private Label lblTotalExpenses;
    @FXML private Label lblNetProfit;
    @FXML private Label lblProfitMargin;

    @FXML private PieChart pieChartRevenueExpense;

    private DataManager dataManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataManager = DataManager.getInstance();
        calculateAndRender();
    }

    public void calculateAndRender() {
        double revenue = 0;
        for (Car car : dataManager.getCars()) {
            if ("Sold".equalsIgnoreCase(car.getStatus()) || "Booked".equalsIgnoreCase(car.getStatus())) {
                revenue += car.getFinalPrice();
            }
        }

        double expenses = 0;
        for (Expense exp : dataManager.getExpenses()) {
            expenses += exp.getAmount();
        }

        double netProfit = revenue - expenses;
        double profitMargin = (revenue > 0) ? (netProfit / revenue) * 100.0 : 0.0;

        lblTotalRevenue.setText(String.format("Total Revenue: BDT %.2f", revenue));
        lblTotalExpenses.setText(String.format("Total Expenses: BDT %.2f", expenses));
        lblNetProfit.setText(String.format("Net Profit: BDT %.2f", netProfit));
        lblProfitMargin.setText(String.format("Profit Margin: %.2f%%", profitMargin));

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Expenses (BDT " + String.format("%.0f", expenses) + ")", expenses),
                new PieChart.Data("Net Profit (BDT " + String.format("%.0f", netProfit) + ")", Math.max(0, netProfit))
        );

        pieChartRevenueExpense.setData(pieChartData);
    }
}
