package controller.viewcontroller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Controlador do PopUp de Desconto.
 * Exibe o valor do desconto e retorna à tela de venda de ingressos.
 *
 * @author Helen Santos Rocha
 * @since 13-06-2025
 * @version 1.0
 */
public class PopUpDiscountController {

    @FXML
    private Label discountMessage;

    /**
     * Define o valor de desconto no label da interface.
     * @param discount Valor percentual de desconto
     */
    public void setDiscount(double discount) {
        discountMessage.setText(String.format("Desconto aplicado: %.0f%%", discount));
    }

    /**
     * Fecha o pop-up e retorna à tela de venda de ingressos.
     */
    @FXML
    private void backClientControl() {
        // Fecha o pop-up
        Stage stage = (Stage) discountMessage.getScene().getWindow();
        stage.close();

        // Retorna para a tela de venda (SellTicket)
        MainViews.changeScreen("sellTicket", null);
    }
}