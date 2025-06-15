package controller.viewcontroller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controlador do pop-up de venda cancelada com sucesso.
 *
 * @author Maria Eduarda Campos
 * @since 15-06-2025
 * @version 1.0
 */
public class PopUpTicketCanceledController {

    @FXML
    private Button backButton;

    private Stage stage;

    /**
     * Fecha a tela de mensagem de sucesso.
     * 
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Fecha o pop-up e volta para a tela de venda.
     */
    @FXML
    private void backToClientHistory() {
        MainViews.changeScreen("clientHistory", null);
        stage.close();
    }
}
