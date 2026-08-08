package com.example.haqs_bay_automobili;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class RoleSelectionController {

    @FXML
    public void handleOpenShowroomManager(ActionEvent event) {
        MainApp.loadScene("showroom_manager_dashboard.fxml", "Haq's Bay Automobili - Showroom Manager Workspace", 1150, 750);
    }

    @FXML
    public void handleOpenFinanceOfficer(ActionEvent event) {
        MainApp.loadScene("finance_officer_dashboard.fxml", "Haq's Bay Automobili - Finance Officer Workspace", 1150, 750);
    }
}
