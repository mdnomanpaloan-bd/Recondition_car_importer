package com.example.recondition_car_importer.Alvi.SalesExecutive;

import com.example.recondition_car_importer.Alvi.Customer.CarList;
import com.example.recondition_car_importer.SceneSwitcher;
import com.example.recondition_car_importer.utility.BinaryFileUtil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

import java.util.stream.Collectors;

public class Inventory2
{
    @javafx.fxml.FXML
    private RadioButton ReconditionedRadioB;
    @javafx.fxml.FXML
    private TableColumn<CarList,String> ModelTV;
    @javafx.fxml.FXML
    private RadioButton NewArrivedRadioB;
    @javafx.fxml.FXML
    private TableColumn<CarList,Double> priceTV;
    @javafx.fxml.FXML
    private Label SearchVehicleLabel;
    @javafx.fxml.FXML
    private TableView<CarList> InventoryTV;
    @javafx.fxml.FXML
    private TableColumn<CarList,String> yearTV;
    @javafx.fxml.FXML
    private TableColumn<CarList,String> MakeTV;
    @javafx.fxml.FXML
    private Label priceLabel;
    @javafx.fxml.FXML
    private TableColumn<CarList,String> IDTV;
    @javafx.fxml.FXML
    private TableColumn<CarList,String> conditionTV;
    private static final String CARS_FILE = "Data/Alvi-2310827/Cars";

    private ArrayList<CarList> cars;

    private final ToggleGroup group = new ToggleGroup();

    @javafx.fxml.FXML
    public void initialize() {

        NewArrivedRadioB.setToggleGroup(group);
        ReconditionedRadioB.setToggleGroup(group);

        IDTV.setCellValueFactory(new PropertyValueFactory<>("carID"));
        MakeTV.setCellValueFactory(new PropertyValueFactory<>("make"));
        ModelTV.setCellValueFactory(new PropertyValueFactory<>("model"));
        yearTV.setCellValueFactory(new PropertyValueFactory<>("year"));
        conditionTV.setCellValueFactory(new PropertyValueFactory<>("condition"));
        priceTV.setCellValueFactory(new PropertyValueFactory<>("price"));

        cars = BinaryFileUtil.readList(CARS_FILE);

        InventoryTV.setItems(FXCollections.observableArrayList(cars));

        InventoryTV.getSelectionModel().selectedItemProperty().addListener((obs, oldCar, newCar) -> {

            if (newCar != null) {

                SearchVehicleLabel.setText(
                        newCar.getMake() + " " + newCar.getModel()
                );

                priceLabel.setText(String.valueOf(newCar.getPrice()));
            }
        });

        NewArrivedRadioB.setOnAction(e -> filterCars());

        ReconditionedRadioB.setOnAction(e -> filterCars());
    }

    @javafx.fxml.FXML
    public void backOnAction(ActionEvent actionEvent) {
        try {
            SceneSwitcher.switchTo("Alvi/SalesExecutive/SalesExecutiveDashboard");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void updateOnAction(ActionEvent actionEvent) {

            cars = BinaryFileUtil.readList(CARS_FILE);

            InventoryTV.setItems(FXCollections.observableArrayList(cars));
    }






    private void filterCars() {

        if (NewArrivedRadioB.isSelected()) {

            InventoryTV.setItems(FXCollections.observableArrayList(

                    cars.stream()
                            .filter(car -> car.getCondition().equalsIgnoreCase("New Arrival"))
                            .collect(Collectors.toList())

            ));

        } else if (ReconditionedRadioB.isSelected()) {

            InventoryTV.setItems(FXCollections.observableArrayList(

                    cars.stream()
                            .filter(car -> car.getCondition().equalsIgnoreCase("Reconditioned"))
                            .collect(Collectors.toList())

            ));
        } else {

            InventoryTV.setItems(FXCollections.observableArrayList(cars));

        }
    }
}


