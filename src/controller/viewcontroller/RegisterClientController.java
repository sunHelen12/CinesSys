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
    private TextField enterDate;

    @FXML
    private TextField enterEmail;

    @FXML
    private TextField enterName;
    
    @FXML
    void backClient(ActionEvent event) {
        MainViews.changeScreen("clientControl", null);
    }

    @FXML
    void registerClient(ActionEvent event) {
        String name = enterName.getText().trim();
        String email = enterEmail.getText().trim();
        String date = enterDate.getText().trim();

        if (name.isEmpty() || email.isEmpty() || date.isEmpty()) {
            return;
        }

        //Cadastrar Cliente
    }
}
