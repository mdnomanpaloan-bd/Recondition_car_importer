package com.example.recondition_car_importer.Alvi;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class BrowseCars
{
    @javafx.fxml.FXML
    private ComboBox<String> makeCB;
    @javafx.fxml.FXML
    private TextField minPriceTF;
    @javafx.fxml.FXML
    private TextField maxPriceTF;
    @javafx.fxml.FXML
    private ComboBox<String> modelCB;
    @javafx.fxml.FXML
    private ComboBox<String> yearCB;
    @javafx.fxml.FXML
    private TableColumn<BrowseCars, String> StatusTV;
    @javafx.fxml.FXML
    private TableColumn<BrowseCars, String> carIDTV;
    @javafx.fxml.FXML
    private TableColumn<BrowseCars, String> yearTV;
    @javafx.fxml.FXML
    private TableColumn<BrowseCars, String> modelTV;
    @javafx.fxml.FXML
    private TableColumn<BrowseCars, String> mileageTV;
    @javafx.fxml.FXML
    private RadioButton newArrivalRB;
    @javafx.fxml.FXML
    private TableView<String> browseAvailableCarsTV;
    @javafx.fxml.FXML
    private TableColumn<BrowseCars, String> makeTV;
    @javafx.fxml.FXML
    private TableColumn<BrowseCars, String> PriceTV;
    @javafx.fxml.FXML
    private RadioButton reconditionedRB;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void searchBrowseCarsOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void backOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void resetBrowseCarsOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void viewDetailsOnAction(ActionEvent actionEvent) {
    }
}