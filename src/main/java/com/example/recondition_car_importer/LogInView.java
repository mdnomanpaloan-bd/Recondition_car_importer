package com.example.recondition_car_importer;

import com.example.recondition_car_importer.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;

import java.io.IOException;

public class LogInView
{
    @javafx.fxml.FXML
    private ComboBox<String> userinputCB;
    @javafx.fxml.FXML
    private PasswordField password_TF;

    @javafx.fxml.FXML
    public void initialize() {

        userinputCB.getItems().addAll(
                "Customer",
                "Sales Executive",
                "Importer",
                "Admin"
        );
    }

    @javafx.fxml.FXML
    public void loginContinue_OA(ActionEvent actionEvent) throws IOException {

        String user = userinputCB.getValue();
        String password = password_TF.getText();


        if (user == null || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("Please select a user and enter a password.");
            alert.showAndWait();
            return;
        }


        switch (user) {

            case "Customer":
                if(password.equals("customer123")){
                    SceneSwitcher.switchTo("Alvi/Customer/CustomerDashboard");
                } else {
                    showError();
                }
                break;

            case "Sales Executive":
                if(password.equals("sales123")){
                    SceneSwitcher.switchTo("Alvi/SalesExecutive/SalesExecutiveDashboard");
                } else {
                    showError();
                }
                break;


//            case "Admin":
//                if(password.equals("admin123")){
//                    SceneSwitcher.switchTo("");
//                } else {
//                    showError();
//                }
//                break

//            case "Import Manager":
//                if(password.equals("import123")){
//                    SceneSwitcher.switchTo("");
//                } else {
//                    showError();
//                }
//                break
        }
    }

    private void showError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login Failed");
        alert.setHeaderText(null);
        alert.setContentText("Incorrect password.");
        alert.showAndWait();
    }
    }
