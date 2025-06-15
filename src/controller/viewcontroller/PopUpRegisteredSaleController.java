package controller.viewcontroller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controlador do pop-up de venda registrada com sucesso.
 * Fecha a janela e retorna para a tela de venda de ingressos.
 *
 * @author Helen Santos Rocha
 * @since 14-06-2025
 * @version 1.0
 */
public class PopUpRegisteredSaleController {

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
    private void backToSellTicket() {
        MainViews.changeScreen("controlSession", null);
        stage.close();
    }
}
