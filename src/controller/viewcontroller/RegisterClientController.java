package controller.viewcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import controller.viewcontroller.MainViews;
import repository.*;

public class RegisterClientController {
    @FXML
    void backClient(ActionEvent event) {
        MainViews.changeScreen("clientControl", null);
    }

    @FXML
    void registerClient(ActionEvent event) {
        //repository.ClientRepository.add();
    }

}
