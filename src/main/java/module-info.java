module com.example.recondition_car_importer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.recondition_car_importer to javafx.fxml;
    exports com.example.recondition_car_importer;
    opens com.example.recondition_car_importer.Alvi to javafx.fxml;
    exports com.example.recondition_car_importer.Alvi;
}