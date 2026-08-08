module com.example.recondition_car_importer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.base;

    opens com.example.recondition_car_importer.Sifat to javafx.fxml;
    opens com.example.recondition_car_importer.Sifat.model to javafx.base, java.base;

    exports com.example.recondition_car_importer.Sifat;
    exports com.example.recondition_car_importer.Sifat.model;
}
