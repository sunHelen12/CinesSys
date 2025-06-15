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

    /**
     * Fecha o pop-up e volta para a tela de venda.
     */
    @FXML
    private void backToSellTicket() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();

        MainViews.changeScreen("sellTicket", null);
    }
}
