package com.example.recondition_car_importer.Noman;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AuctionVehicle {
    @javafx.fxml.FXML
    private TableColumn ClmChassisTableColumn;
    @javafx.fxml.FXML
    private TableColumn ClmYearTableColumn;
    @javafx.fxml.FXML
    private TableColumn ClmGradeTableColumn;
    @javafx.fxml.FXML
    private TextField TxtSearchChassisTF;
    @javafx.fxml.FXML
    private TableColumn ClmModelTableColumn;
    @javafx.fxml.FXML
    private TableView VehicleTableView;
    @javafx.fxml.FXML
    private TableColumn ClmMakeTableColumn;
    @javafx.fxml.FXML
    private ComboBox CmbGradeFilterComboBox;

    @javafx.fxml.FXML
    public void ShortlistCarOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void SearchOnAction(ActionEvent actionEvent) {
    }
}
