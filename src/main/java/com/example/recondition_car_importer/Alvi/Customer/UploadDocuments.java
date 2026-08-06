package com.example.recondition_car_importer.Alvi.Customer;

import com.example.recondition_car_importer.SceneSwitcher;
import com.example.recondition_car_importer.utility.BinaryFileUtil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.util.ArrayList;



public class UploadDocuments
{
    @javafx.fxml.FXML
    private DatePicker expiryDateDP;
    @javafx.fxml.FXML
    private ComboBox<String>  documentTCB;
    @javafx.fxml.FXML
    private TextField documentNumberTF;
    @javafx.fxml.FXML
    private CheckBox documentUploadCheck;
    @javafx.fxml.FXML
    private DatePicker issueDateDP;
    private static final String DOCUMENT_FILE = "Data/Alvi-2310827/Documents";

    @javafx.fxml.FXML
    public void initialize() {
        documentTCB.setItems(FXCollections.observableArrayList(

                "National ID",
                "Passport",
                "Driving License"

        ));
    }

    @javafx.fxml.FXML
    public void clearOnAction(ActionEvent actionEvent) {
        documentTCB.getSelectionModel().clearSelection();

        documentNumberTF.clear();

        issueDateDP.setValue(null);

        expiryDateDP.setValue(null);

        documentUploadCheck.setSelected(false);
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
    public void submitOnAction(ActionEvent actionEvent) {
        if (documentTCB.getValue() == null
                || documentNumberTF.getText().isEmpty()
                || issueDateDP.getValue() == null
                || expiryDateDP.getValue() == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields.");
            alert.show();

            return;
        }

        if (!documentUploadCheck.isSelected()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Please confirm document upload.");
            alert.show();

            return;
        }

        ArrayList<UploadDocumentRecord> documents =
                BinaryFileUtil.readList(DOCUMENT_FILE);

        UploadDocumentRecord record = new UploadDocumentRecord(

                documentTCB.getValue().toString(),

                documentNumberTF.getText(),

                issueDateDP.getValue(),

                expiryDateDP.getValue(),

                true

        );

        documents.add(record);

        BinaryFileUtil.saveList(DOCUMENT_FILE, documents);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Document submitted successfully.");
        alert.showAndWait();

        clearOnAction(null);}
}
