package controller.viewcontroller;

import controller.business.ClientController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import models.*;

public class ChangeClientController implements Initializable {
    private static Client client;

    @FXML
    private TextField boxDate;

    @FXML
    private TextField boxEmail;

    @FXML
    private TextField boxName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MainViews.addOnChangeScreenListener(new MainViews.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object userDataObject) {
                if (userDataObject instanceof Client) {
                    client = (Client) userDataObject;
                }
            }
        });
    }
    
    @FXML
    void backClient(ActionEvent event) {
        MainViews.changeScreen("clientControl", null);
    }

    @FXML
    void changeClient(ActionEvent event) {
        String name = boxName.getText().trim();
        String email = boxEmail.getText().trim();
        String date = boxDate.getText().trim();
       
        ClientController.updateClient(client.getId(), name, email, date);
        boxName.clear();
        boxEmail.clear();
        boxDate.clear();
        ClientControlController.mostrarPopUp("alterado");
    }

}
