package controller.viewcontroller;

import controller.business.ClientController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import models.*;

/**
 * Classe responsável por controlar a tela de alteração de um cliente.
 * @author
 * @since
 * @version
 */
public class ChangeClientController implements Initializable {
    private static Client client;

    @FXML
    private TextField boxDate;

    @FXML
    private TextField boxEmail;

    @FXML
    private TextField boxName;

    /**
     * Inicializa o controlador.
     * 
     * @param url O URL de onde o controlador foi carregado.
     * @param rb O ResourceBundle associado ao controlador.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MainViews.addOnChangeScreenListener(new MainViews.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object userDataObject) {
                if (userDataObject instanceof Client) {
                    client = (Client) userDataObject;
                    boxDate.setText(client.getBirthday());
                    boxEmail.setText(client.getEmail());
                    boxName.setText(client.getName());
                }
            }
        });
    }
    
    /**
     * Retorna o cliente atual.
     * 
     * @param event O evento de clique do botão.
     */
    @FXML
    void backClient(ActionEvent event) {
        MainViews.changeScreen("clientControl", null);
    }

    /**
     * Altera os dados do cliente.
     * 
     * @param event O evento de clique do botão.
     */
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
