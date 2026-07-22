module com.example.recondition_car_importer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.recondition_car_importer to javafx.fxml;
    exports com.example.recondition_car_importer;
}