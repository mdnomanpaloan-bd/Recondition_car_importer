package com.example.recondition_car_importer.Alvi;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class Inventory2
{
    @javafx.fxml.FXML
    private RadioButton ReconditionedRadioB;
    @javafx.fxml.FXML
    private TableColumn<Inventory2,String> ModelTV;
    @javafx.fxml.FXML
    private RadioButton NewArrivedRadioB;
    @javafx.fxml.FXML
    private TableColumn<Inventory2,String> priceTV;
    @javafx.fxml.FXML
    private Label SearchVehicleLabel;
    @javafx.fxml.FXML
    private TableView<String> InventoryTV;
    @javafx.fxml.FXML
    private TableColumn<Inventory2,String> yearTV;
    @javafx.fxml.FXML
    private TableColumn<Inventory2,String> MakeTV;
    @javafx.fxml.FXML
    private Label priceLabel;
    @javafx.fxml.FXML
    private TableColumn<Inventory2,String> IDTV;
    @javafx.fxml.FXML
    private TableColumn<Inventory2,String> conditionTV;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void backOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void updateOnAction(ActionEvent actionEvent) {
    }
}