package com.example.recondition_car_importer.Alvi.SalesExecutive;

import com.example.recondition_car_importer.Alvi.Customer.CarList;
import com.example.recondition_car_importer.SceneSwitcher;
import com.example.recondition_car_importer.utility.BinaryFileUtil;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.util.ArrayList;

import static com.example.recondition_car_importer.Alvi.Customer.BrowseCars.CARS_FILE;

public class AddnewCar2
{
    @javafx.fxml.FXML
    private TextField priceTF;
    @javafx.fxml.FXML
    private ComboBox<String> FuelTypeCB;
    @javafx.fxml.FXML
    private ComboBox<String> conditionCB;
    @javafx.fxml.FXML
    private TextField chassisNumberTF;
    @javafx.fxml.FXML
    private ComboBox<String> yearCB;
    @javafx.fxml.FXML
    private ComboBox<String> carMakeCB;
    @javafx.fxml.FXML
    private TextField mileageTF;
    @javafx.fxml.FXML
    private ComboBox<String> transmissionCB;
    @javafx.fxml.FXML
    private ComboBox<String> modelCB;

    @javafx.fxml.FXML
    public void initialize() {

        carMakeCB.getItems().addAll(
                "Toyota",
                "Honda",
                "Nissan",
                "Mazda",
                "Mitsubishi",
                "Suzuki"
        );

        yearCB.getItems().addAll(
                "2020","2021","2022","2023","2024","2025","2026"
        );

        conditionCB.getItems().addAll(
                "New Arrival",
                "Reconditioned"
        );

        FuelTypeCB.getItems().addAll(
                "Petrol",
                "Diesel",
                "Hybrid",
                "Electric"
        );

        transmissionCB.getItems().addAll(
                "Automatic",
                "Manual"
        );

    }

    @javafx.fxml.FXML
    public void clearOnAction(ActionEvent actionEvent) {
        chassisNumberTF.clear();
        mileageTF.clear();
        priceTF.clear();

        carMakeCB.getSelectionModel().clearSelection();
        yearCB.getSelectionModel().clearSelection();
        FuelTypeCB.getSelectionModel().clearSelection();
        transmissionCB.getSelectionModel().clearSelection();
        conditionCB.getSelectionModel().clearSelection();
        modelCB.getSelectionModel().clearSelection();

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
    public void addVehicleOnAction(ActionEvent actionEvent) {

        ArrayList<CarList> cars = BinaryFileUtil.readList(CARS_FILE);

        CarList newCar = new CarList(
                chassisNumberTF.getText(),              // Car ID
                carMakeCB.getValue(),                   // Make
                modelCB.getValue(),                      // Model
                Integer.parseInt(yearCB.getValue()),    // Year
                Integer.parseInt(mileageTF.getText()),  // Mileage
                Double.parseDouble(priceTF.getText()),  // Price
                "Available",                            // Status
                conditionCB.getValue()                  // Condition
        );


        cars.add(newCar);

        BinaryFileUtil.saveList(CARS_FILE, cars);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Car added successfully!");
        alert.showAndWait();
    }

    @javafx.fxml.FXML
    public void onMakeSelected(ActionEvent actionEvent) {
        modelCB.getItems().clear();

        String make = carMakeCB.getValue();

        if (make == null) {
            return;
        }

        switch (make) {

            case "Toyota":
                modelCB.getItems().addAll(
                        "Axio",
                        "Premio",
                        "Corolla"
                );
                break;

            case "Honda":
                modelCB.getItems().addAll(
                        "Vezel",
                        "Grace",
                        "Civic"
                );
                break;

            case "Nissan":
                modelCB.getItems().addAll(
                        "GTR R-33 Bunny",
                        "X-Trail",
                        "Sunny"
                );
                break;

            case "Mazda":
                modelCB.getItems().addAll(
                        "Miata",
                        "MX-4",
                        "Atenza"
                );
                break;

            case "Mitsubishi":
                modelCB.getItems().addAll(
                        "Outlander",
                        "Lancer"
                );
                break;

            case "Suzuki":
                modelCB.getItems().addAll(
                        "Swift",
                        "Wagon R"
                );
                break;
        }

        modelCB.getSelectionModel().clearSelection();
    }

}