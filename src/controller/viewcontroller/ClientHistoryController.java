package controller.viewcontroller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import models.Client;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientHistoryController implements Initializable {
    private static Client client;
    
    @FXML
    private VBox vboxHistorico;
    
    @FXML
    private Label lblEmail;

    @FXML
    private Label lblID;

    @FXML
    private Label lblNascimento;

    @FXML
    private Label lblNumIngressos;

    @FXML
    private Label lnlNome;

    @FXML
    void backPurchase(ActionEvent event) {
        MainViews.changeScreen("purchaseRelatory", null);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MainViews.addOnChangeScreenListener(new MainViews.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object userDataObject) {
                if (userDataObject instanceof Client) {
                    client = (Client) userDataObject;
                    accessClientData(); 
                }
            }
        });
    }

    private void accessClientData(){
        lnlNome.setText(client.getName());
        lblID.setText(String.valueOf(client.getId()));
        lblEmail.setText(client.getEmail());
        lblNascimento.setText(client.getBirthday());
        lblNumIngressos.setText(String.valueOf(client.getPurchasingHistory().size()));

        
    }
}
