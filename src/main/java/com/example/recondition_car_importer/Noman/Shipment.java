package com.example.recondition_car_importer.Noman;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class Shipment {
    @javafx.fxml.FXML
    private TextField TxtVesselNameTF;
    @javafx.fxml.FXML
    private ComboBox CmbShipmentStatusComboBox;
    @javafx.fxml.FXML
    private TextField TxtBLNumberTF;
    @javafx.fxml.FXML
    private DatePicker DpDatePicker;

    @javafx.fxml.FXML
    public void UpdateTrackingOnAction(ActionEvent actionEvent) {
    }
}
