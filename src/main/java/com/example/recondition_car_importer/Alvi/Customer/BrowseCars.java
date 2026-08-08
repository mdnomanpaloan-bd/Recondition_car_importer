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
import java.util.List;
import java.util.stream.Collectors;

public class BrowseCars
{
    // bin
    public static final String CARS_FILE = "Data/Alvi-2310827/Cars";

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
    private TableColumn<CarList, String> StatusTV;
    @javafx.fxml.FXML
    private TableColumn<CarList, String> carIDTV;
    @javafx.fxml.FXML
    private TableColumn<CarList, String> yearTV;
    @javafx.fxml.FXML
    private TableColumn<CarList, String> modelTV;
    @javafx.fxml.FXML
    private TableColumn<CarList, String> mileageTV;
    @javafx.fxml.FXML
    private RadioButton newArrivalRB;
    @javafx.fxml.FXML
    private TableView<CarList> browseAvailableCarsTV;
    @javafx.fxml.FXML
    private TableColumn<CarList, String> makeTV;
    @javafx.fxml.FXML
    private TableColumn<CarList, String> PriceTV;
    @javafx.fxml.FXML
    private RadioButton reconditionedRB;
    private final ToggleGroup conditionGroup = new ToggleGroup();
    private List<CarList> allCars = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {


        newArrivalRB.setToggleGroup(conditionGroup);
        reconditionedRB.setToggleGroup(conditionGroup);

        carIDTV.setCellValueFactory(new PropertyValueFactory<>("carID"));
        makeTV.setCellValueFactory(new PropertyValueFactory<>("make"));
        modelTV.setCellValueFactory(new PropertyValueFactory<>("model"));
        yearTV.setCellValueFactory(new PropertyValueFactory<>("year"));
        mileageTV.setCellValueFactory(new PropertyValueFactory<>("mileage"));
        PriceTV.setCellValueFactory(new PropertyValueFactory<>("price"));
        StatusTV.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadCarsFromFile();
        populateMakeCB();
        populateYearCB();
        refreshTable(allCars);
    }

    private void loadCarsFromFile() {
        allCars = BinaryFileUtil.readList(CARS_FILE);
    }

    private void populateMakeCB() {
        ObservableList<String> makes = allCars.stream()
                .map(CarList::getMake)
                .filter(m -> m != null)
                .distinct()
                .sorted()
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        makeCB.setItems(makes);
    }

    private void populateYearCB() {
        ObservableList<String> years = allCars.stream()
                .map(c -> String.valueOf(c.getYear()))
                .distinct()
                .sorted()
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        yearCB.setItems(years);
    }

    private void refreshTable(List<CarList> cars) {
        browseAvailableCarsTV.setItems(FXCollections.observableArrayList(cars));
    }

    @javafx.fxml.FXML
    public void onMakeSelected(ActionEvent actionEvent) {
        modelCB.getItems().clear();

        String make = makeCB.getValue();

        if (make == null) return;

        switch (make) {
            case "Toyota":
                modelCB.getItems().addAll("Axio", "Premio", "Corolla");
                break;

            case "Honda":
                modelCB.getItems().addAll("Vezel", "Grace", "Civic");
                break;

            case "Nissan":
                modelCB.getItems().addAll("GTR R-33 Bunny", "X-Trail", "Sunny");
                break;

            case "Mazda":
                modelCB.getItems().addAll("Miata", "MX-4", "Atenza");
                break;

            case "Mitsubishi":
                modelCB.getItems().addAll("Outlander", "Lancer");
                break;

            case "Suzuki":
                modelCB.getItems().addAll("Swift", "Wagon R");
                break;
        }

        modelCB.getSelectionModel().clearSelection();
    }

    @javafx.fxml.FXML
    public void searchBrowseCarsOnAction(ActionEvent actionEvent) {

        String selectedMake = makeCB.getValue();
        String selectedModel = modelCB.getValue();
        String selectedYear = yearCB.getValue();

        Double minPrice = null;
        Double maxPrice = null;

        try {
            if (minPriceTF.getText() != null && !minPriceTF.getText().trim().isEmpty()) {
                minPrice = Double.parseDouble(minPriceTF.getText().trim());
            }
            if (maxPriceTF.getText() != null && !maxPriceTF.getText().trim().isEmpty()) {
                maxPrice = Double.parseDouble(maxPriceTF.getText().trim());
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Price",
                    "Please enter valid numbers for Min Max Price.");
            return;
        }

        if (minPrice != null && maxPrice != null && minPrice > maxPrice) {
            showAlert(Alert.AlertType.ERROR, "Invalid Price Range",
                    "Min Price should be smaller than Max.");
            return;
        }

        String selectedCondition = null;
        if (newArrivalRB.isSelected()) {
            selectedCondition = "New Arrival";
        } else if (reconditionedRB.isSelected()) {
            selectedCondition = "Reconditioned";
        }

        final Double finalMinPrice = minPrice;
        final Double finalMaxPrice = maxPrice;
        final String finalCondition = selectedCondition;

        List<CarList> filtered = allCars.stream()
                .filter(c -> selectedMake == null || selectedMake.equals(c.getMake()))
                .filter(c -> selectedModel == null || selectedModel.equals(c.getModel()))
                .filter(c -> selectedYear == null || selectedYear.equals(String.valueOf(c.getYear())))
                .filter(c -> finalMinPrice == null || c.getPrice() >= finalMinPrice)
                .filter(c -> finalMaxPrice == null || c.getPrice() <= finalMaxPrice)
                .filter(c -> finalCondition == null || finalCondition.equals(c.getCondition()))
                .collect(Collectors.toList());

        if (filtered.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "No Results",
                    "No cars match");
        }

        refreshTable(filtered);
    }

    @javafx.fxml.FXML
    public void resetBrowseCarsOnAction(ActionEvent actionEvent) {
        makeCB.setValue(null);
        modelCB.setValue(null);
        modelCB.getItems().clear();
        yearCB.setValue(null);
        minPriceTF.clear();
        maxPriceTF.clear();
        conditionGroup.selectToggle(null);

        refreshTable(allCars);
    }

    @javafx.fxml.FXML
    public void viewDetailsOnAction(ActionEvent actionEvent) {
        CarList selected = browseAvailableCarsTV.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "No Car Selected",
                    "Please select a car.");
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
    public void backOnAction(ActionEvent actionEvent) {
        try {
            SceneSwitcher.switchTo("Alvi/Customer/CustomerDashboard");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}