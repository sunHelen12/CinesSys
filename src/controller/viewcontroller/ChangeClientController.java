package controller.viewcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import controller.viewcontroller.MainViews;

public class ChangeClientController {

    @FXML
    private TextField boxDate;

    @FXML
    private TextField boxEmail;

    @FXML
    private TextField boxName;

    @FXML
    void backClient(ActionEvent event) {
        MainViews.changeScreen("clientControl", null);
    }

    @FXML
    void changeClient(ActionEvent event) {

    }

}
