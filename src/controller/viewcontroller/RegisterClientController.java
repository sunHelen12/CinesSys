package controller.viewcontroller;

import controller.bussines.ClientController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import controller.viewcontroller.MainViews;
import controller.viewcontroller.ClientControlController;
import services.*;

/**
 * Classe referente ao controle da interface "Registrar Clientes" presente na
 * aplicação.
 * 
 * @author Maria Eduarda Campos
 * @since 31-05-2025
 * @version 2
 */
public class RegisterClientController {
    @FXML
    private TextField enterDate;
    @FXML
    private TextField enterEmail;
    @FXML
    private TextField enterName;
    
    /**
     * Método que abre a Tela de Controle de Clientes.
     * 
     * @param event Evento ao apertar o botão, caso necessário.
     */
    @FXML
    void backClient(ActionEvent event) {
        MainViews.changeScreen("clientControl", null);
    }

    /**
     * Método que registra um novo Cliente.
     * 
     * @param event Evento ao apertar o botão, caso necessário.
     */
    @FXML
    void registerClient(ActionEvent event) {
        String name = enterName.getText().trim();
        String email = enterEmail.getText().trim();
        String date = enterDate.getText().trim();

        if (name.isEmpty() || email.isEmpty() || date.isEmpty()) {
            return;
        }else{
            ClientController.addClient(name, email, date);
            ClientControlController.mostrarPopUp("cadastrado");
        }
    }
}
