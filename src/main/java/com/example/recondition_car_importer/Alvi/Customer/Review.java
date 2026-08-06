package com.example.recondition_car_importer.Alvi.Customer;

import com.example.recondition_car_importer.SceneSwitcher;
import com.example.recondition_car_importer.utility.BinaryFileUtil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.util.ArrayList;

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
    private static final String REVIEW_FILE = "Data/Alvi-2310827/Reviews";

    private final ToggleGroup ratingGroup = new ToggleGroup();


    @javafx.fxml.FXML
    public void initialize() {
        oneStarRB.setToggleGroup(ratingGroup);
        twoStarRB.setToggleGroup(ratingGroup);
        threeStarRB.setToggleGroup(ratingGroup);
        fourStarRB.setToggleGroup(ratingGroup);
        fiveStarRB.setToggleGroup(ratingGroup);

        ArrayList<BookingRecord> bookings =
                BinaryFileUtil.readList("Data/Alvi-2310827/Bookings");

        ArrayList<String> vehicles = new ArrayList<>();

        for (BookingRecord booking : bookings) {
            vehicles.add(booking.getCarName());
        }

        selectVehicleCB.setItems(FXCollections.observableArrayList(vehicles));
    }

    @javafx.fxml.FXML
    public void clearOnAction(ActionEvent actionEvent) {
        selectVehicleCB.getSelectionModel().clearSelection();

        ratingGroup.selectToggle(null);

        commentTextA.clear();
    }

    @javafx.fxml.FXML
    public void submitReviewOnAction(ActionEvent actionEvent) {
        if (selectVehicleCB.getValue() == null ||
                ratingGroup.getSelectedToggle() == null ||
                commentTextA.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Please complete all fields.");
            alert.show();

            return;
        }

        int rating = 0;

        if (oneStarRB.isSelected()) rating = 1;
        if (twoStarRB.isSelected()) rating = 2;
        if (threeStarRB.isSelected()) rating = 3;
        if (fourStarRB.isSelected()) rating = 4;
        if (fiveStarRB.isSelected()) rating = 5;

        ArrayList<ReviewRecord> reviews =
                BinaryFileUtil.readList(REVIEW_FILE);

        reviews.add(new ReviewRecord(

                selectVehicleCB.getValue().toString(),

                rating,

                commentTextA.getText()

        ));

        BinaryFileUtil.saveList(REVIEW_FILE, reviews);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Review submitted successfully.");
        alert.show();

        clearOnAction(null);
    }

    @javafx.fxml.FXML
    public void backOnAction(ActionEvent actionEvent) {
        try {
            SceneSwitcher.switchTo("Alvi/Customer/CustomerDashboard");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}