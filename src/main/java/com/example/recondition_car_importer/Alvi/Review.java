package com.example.recondition_car_importer.Alvi;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;

public class Review
{
    @javafx.fxml.FXML
    private RadioButton fiveStarRB;
    @javafx.fxml.FXML
    private RadioButton fourStarRB;
    @javafx.fxml.FXML
    private ComboBox<String> selectVehicleCB;
    @javafx.fxml.FXML
    private RadioButton twoStarRB;
    @javafx.fxml.FXML
    private RadioButton oneStarRB;
    @javafx.fxml.FXML
    private RadioButton threeStarRB;
    @javafx.fxml.FXML
    private TextArea commentTextA;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void clearOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void submitReviewOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void backOnAction(ActionEvent actionEvent) {
    }
}