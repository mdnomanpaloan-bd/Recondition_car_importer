module com.example.recondition_car_importer {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    exports com.example.recondition_car_importer;
    opens com.example.recondition_car_importer to javafx.fxml;

    exports com.example.recondition_car_importer.Alvi.Customer;
    opens com.example.recondition_car_importer.Alvi.Customer to javafx.fxml;

    exports com.example.recondition_car_importer.Alvi.SalesExecutive;
    opens com.example.recondition_car_importer.Alvi.SalesExecutive to javafx.fxml;
}