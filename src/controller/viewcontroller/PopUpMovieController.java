package controller.viewcontroller;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
/**
 * Classe responsável por controlar a tela de pop up para confirmação de uma ação(cadastro, alteração, exclusão).
 * 
 * @author Gabryelle Beatriz Duarte Moraes
 * @since 14/06/2024
 * @version 1.0
 */
public class PopUpMovieController {

    @FXML
    private Label mensagemLabel;

    private Stage stage;

    /**
     * Método responsável por definir a mensagem a ser exibida na tela de pop up.
     * 
     * @param mensagem mensagem a ser exibida na tela de pop up
     */
    public void setMensagemPersonalizada(String mensagem) {
        mensagemLabel.setText("Filme " + mensagem + " com Sucesso!");
    }

    /**
     * Método responsável por definir a janela de pop up.
     * @param stage janela de pop up
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Método responsável por fechar a janela de pop up.
     */
    @FXML
    private void fecharPopUp() {
        if (stage != null) {
            stage.close();
        }
    }
}

