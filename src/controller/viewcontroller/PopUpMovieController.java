package controller.viewcontroller;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
/**
 * Classe responsável por controlar a tela de pop up para confirmação de uma ação(cadastro, alteração, exclusão).
 * @author Gabryelle Beatriz Duarte Moraes
 * @since 14/06/2024
 * @version 1.0
 */
public class PopUpMovieController {

    @FXML
    private Label mensagemLabel;

    private Stage stage;

    public void setMensagemPersonalizada(String mensagem) {
        mensagemLabel.setText("Filme " + mensagem + " com Sucesso!");
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void fecharPopUp() {
        if (stage != null) {
            stage.close();
        }
    }
}

