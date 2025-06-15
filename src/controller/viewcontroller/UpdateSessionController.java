package controller.viewcontroller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class UpdateSessionController implements Initializable {

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtMovie;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtRoom;

    @FXML
    private TextField txtTime;

    /**
     * inicializa mudando a cor do texto e do fundo dos campos de texto
     * 
     * @param url O URL de onde o controlador foi carregado.
     * @param rb O ResourceBundle associado ao controlador.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Muda a cor do texto e do fundo dos campos de texto
        txtDate.setStyle("-fx-text-fill: white !important; -fx-background-color: #03002C !important;");
        txtMovie.setStyle("-fx-text-fill: white !important; -fx-background-color: #03002C !important;");
        txtPrice.setStyle("-fx-text-fill: white !important; -fx-background-color: #03002C !important;");
        txtRoom.setStyle("-fx-text-fill: white !important; -fx-background-color: #03002C !important;");
        txtTime.setStyle("-fx-text-fill: white !important; -fx-background-color: #03002C !important;");
    }

    @FXML
    void backSessionController(ActionEvent event) {
        MainViews.changeScreen("sessionControl", null);
    }

    @FXML
    void updateSession(ActionEvent event) {
        String date = txtDate.getText().trim();
        String time = txtTime.getText().trim();
        String room = txtRoom.getText().trim();
        String movie = txtMovie.getText().trim();
        String ticketPrice = txtPrice.getText().trim();


    }

}
