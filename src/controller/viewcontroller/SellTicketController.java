package controller.viewcontroller;

import controller.business.ClientController;
import controller.business.SessionController;
import controller.business.TicketController;
import controller.viewcontroller.SellTicketController;
import controller.viewcontroller.PopUpDiscountController;
import controller.viewcontroller.OversoldController;
import controller.viewcontroller.PopUpRegisteredSaleController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Client;
import models.Session;
import models.Ticket;
import services.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Controlador da tela de venda de ingressos.
 * Responsável por lidar com a exibição das sessões e realizar a venda de tickets.
 *
 * @author Helen Santos Rocha
 * @since 13-06-2025
 * @version 1.0
 */
public class SellTicketController {

    // Componentes da interface gráfica (FXML)

    @FXML private TextField clientId;                     // Campo para digitar o ID do cliente
    @FXML private TextField paymentMethod;                // Campo para informar o método de pagamento
    private Session session;


    private final ToggleGroup radioGroup = new ToggleGroup(); // Agrupando os radio buttons

    private List<Session> sessions; // Lista das sessões carregadas para exibição

    /**
     * Método chamado automaticamente ao carregar a tela (FXML).
     * Inicializa os botões de sessão, carrega sessões disponíveis e exibe as infos na interface.
     * Alem disso, muda a cor do texto e do fundo dos campos de texto.
     */
    @FXML
    public void initialize() {
        clientId.setStyle("-fx-text-fill: white !important; -fx-background-color: #03002C !important;");
        paymentMethod.setStyle("-fx-text-fill: white !important; -fx-background-color: #03002C !important;");
        MainViews.addOnChangeScreenListener(new MainViews.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object userDataObject) {
                if (userDataObject instanceof Session) {
                    session = (Session) userDataObject;
                }
            }
        });
    }

    /**
     * Trata o evento de clicar no botão de registrar a venda.
     * Faz validações, tenta realizar a compra e redireciona para a tela de confirmação ou erro.
     */
    @FXML
    private void handleRegisterSale() {
        try {
            int clientID = Integer.parseInt(clientId.getText());
            String paymentStr = paymentMethod.getText();

            Client client = ClientController.getClientById(clientID);

            // Valida se cliente foi encontrado
            if (client == null ) {
                showAlert("Selecione um cliente válido.");
                return;
            }

            // Realiza a venda do ticket com base nas infos fornecidas
            Ticket ticket = TicketController.purchaseTicket(clientID, session.getId(), paymentStr);

            mostrarPopUpSale();
            paymentMethod.clear();
            clientId.clear();

        } catch (NumberFormatException e) {
            showAlert("ID do cliente inválido.");
        } catch (IllegalArgumentException e) {
            // Redireciona para tela de "lotado" se for o caso
            MainViews.changeScreen("oversold", null);
        } catch (RuntimeException e) {
            // Redireciona para tela de "lotado" se for o caso
            MainViews.changeScreen("oversold", null);
        }
    }

    /**
     * Volta para a tela inicial.
     */
    @FXML
    private void handleBack() {
        MainViews.changeScreen("homeScreen", null);
    }

    /**
     * Exibe um alerta de erro na tela.
     * @param msg Mensagem de erro a ser exibida.
     */
    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Erro");
        alert.setContentText(msg);
        alert.show();
    }

    /**
     * Mostra uma janela.
     * * @param acao Ação realizada.
     */
    public static void mostrarPopUpSale() {
        try {
            FXMLLoader loader = new FXMLLoader(SellTicketController.class.getResource("/gui/PopUpRegisteredSale.fxml"));
            Parent root = loader.load();

            PopUpRegisteredSaleController controller = loader.getController();
            Stage stage = new Stage();
            controller.setStage(stage);

            stage.setScene(new Scene(root));
            stage.setTitle("Confirmação");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
