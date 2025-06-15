package controller.viewcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PopUpSessionController {
    private Stage stage;
    @FXML
    private Label msgSession;

    /**
     * Inicializa a tela de mensagem de sucesso.
     * 
     * @param mensagem mensagem a ser exibida na tela
     */
    public void setMensagemPersonalizada(String mensagem) {
        msgSession.setText("Sessão " + mensagem + " com Sucesso!");
    }

    /**
     * Fecha a tela de mensagem de sucesso.
     * 
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Fecha a tela de mensagem de sucesso e volta para o Controle de Sessão.
     * 
     * @param event evento de clique do botão
     */
    @FXML
    void backSessionControl(ActionEvent event) {
        MainViews.changeScreen("sessionControl", null);
        stage.close();
    }

}
