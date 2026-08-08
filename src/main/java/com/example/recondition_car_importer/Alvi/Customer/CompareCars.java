package com.example.recondition_car_importer.Alvi.Customer;

import com.example.recondition_car_importer.SceneSwitcher;
import com.example.recondition_car_importer.utility.BinaryFileUtil;
import com.example.recondition_car_importer.utility.SelectedCarHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class CompareCars
{
    @javafx.fxml.FXML
    private TableColumn<CompareCarRecord,String >  car1TV;
    @javafx.fxml.FXML
    private TableColumn<CompareCarRecord,String >  FeatureTV;
    @javafx.fxml.FXML
    private TableView<CompareCarRecord>  CompareCarsTV;
    @javafx.fxml.FXML
    private TableColumn<CompareCarRecord,String >  car2TV;
    @javafx.fxml.FXML
    private ComboBox<CarList>  FirstVehicleCB;
    @javafx.fxml.FXML
    private ComboBox<CarList> SecondVehicleCB;
    private static final String CARS_FILE = "Data/Alvi-2310827/Cars";

    private ArrayList<CarList> cars;

    @javafx.fxml.FXML
    public void initialize() {
        FeatureTV.setCellValueFactory(new PropertyValueFactory<>("feature"));
        car1TV.setCellValueFactory(new PropertyValueFactory<>("car1"));
        car2TV.setCellValueFactory(new PropertyValueFactory<>("car2"));

        cars = BinaryFileUtil.readList(CARS_FILE);

        FirstVehicleCB.getItems().addAll(cars);
        SecondVehicleCB.getItems().addAll(cars);
    }

    @javafx.fxml.FXML
    public void backOnAction(ActionEvent actionEvent) {
        try {
            SceneSwitcher.switchTo("Alvi/Customer/CustomerDashboard");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void viewDetailsTV(ActionEvent actionEvent) {
        CarList selected = FirstVehicleCB.getValue();

        if (selected == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Please select a vehicle.");
            alert.show();

            return;
        }

        SelectedCarHolder.selectedCar = selected;

        try {
            SceneSwitcher.switchTo("Alvi/Customer/CarDetails");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void compareOnAction(ActionEvent actionEvent) {
        CarList first = FirstVehicleCB.getValue();
        CarList second = SecondVehicleCB.getValue();

        if (first == null || second == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Select two vehicles.");
            alert.show();

            return;
        }

        ObservableList<CompareCarRecord> list =
                FXCollections.observableArrayList();

        list.add(new CompareCarRecord("Make",
                first.getMake(),
                second.getMake()));

        list.add(new CompareCarRecord("Model",
                first.getModel(),
                second.getModel()));

        list.add(new CompareCarRecord("Year",
                String.valueOf(first.getYear()),
                String.valueOf(second.getYear())));

        list.add(new CompareCarRecord("Mileage",
                String.valueOf(first.getMileage()),
                String.valueOf(second.getMileage())));

        list.add(new CompareCarRecord("Price",
                String.valueOf(first.getPrice()),
                String.valueOf(second.getPrice())));

        list.add(new CompareCarRecord("Condition",
                first.getCondition(),
                second.getCondition()));

        CompareCarsTV.setItems(list);
    }
}