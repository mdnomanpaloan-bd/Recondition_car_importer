package com.example.recondition_car_importer.Noman;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class DeliveryLog {
    @javafx.fxml.FXML
    private TableColumn TblDeliveryLogsTableColumn;
    @javafx.fxml.FXML
    private TableView TblDeliveryTableView;
    @javafx.fxml.FXML
    private TableColumn DateTableColumn;
    @javafx.fxml.FXML
    private DatePicker dpDispatchDatePicker;
    @javafx.fxml.FXML
    private ComboBox CmbBranchComboBox;

    @javafx.fxml.FXML
    public void ConfromDispatchOnAction(ActionEvent actionEvent) {
    }
}
