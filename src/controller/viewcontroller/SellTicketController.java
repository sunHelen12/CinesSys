package controller.viewcontroller;

import controller.business.ClientController;
import controller.business.SessionController;
import controller.business.TicketController;
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

    @FXML private Label movieName;                        // Nome do filme exibido
    @FXML private Label room2DateTime;                    // Data/hora da sessão sala 2 (1ª exibição)
    @FXML private Label room3DateTime;                    // Data/hora da sessão sala 3
    @FXML private Label room2DateTime2;                   // Data/hora da sessão sala 2 (2ª exibição)
    @FXML private Label seatsFreeTotalRoom2;              // Total de assentos livres na sala 2 (1ª exibição)
    @FXML private Label seatsFreeTotalRoom3;              // Total de assentos livres na sala 3
    @FXML private Label seatsFreeTotalRoom2_2;            // Total de assentos livres na sala 2 (2ª exibição)
    @FXML private RadioButton radioRoom2;                 // Opção de seleção sala 2 (1ª)
    @FXML private RadioButton radioRoom3;                 // Opção de seleção sala 3
    @FXML private RadioButton radioRoom2_2;               // Opção de seleção sala 2 (2ª)
    @FXML private TextField clientId;                     // Campo para digitar o ID do cliente
    @FXML private TextField paymentMethod;                // Campo para informar o método de pagamento
    @FXML private Button saleRegister;                    // Botão para registrar a venda
    @FXML private Button backButton;                      // Botão para voltar para a tela anterior

    private final ToggleGroup radioGroup = new ToggleGroup(); // Agrupando os radio buttons

    private List<Session> sessions; // Lista das sessões carregadas para exibição

    /**
     * Método chamado automaticamente ao carregar a tela (FXML).
     * Inicializa os botões de sessão, carrega sessões disponíveis e exibe as infos na interface.
     */
    @FXML
    public void initialize() {
        // Agrupando os radio buttons pra garantir que só um possa ser selecionado por vez
        radioRoom2.setToggleGroup(radioGroup);
        radioRoom3.setToggleGroup(radioGroup);
        radioRoom2_2.setToggleGroup(radioGroup);

        // Obtém todas as sessões disponíveis
        sessions = Arrays.asList((Session[]) SessionController.getAllSessions().getAll());

        if (sessions != null && sessions.size() >= 3) {
            // Formata e exibe infos das três sessões
            room2DateTime.setText(formatDateTime(sessions.get(0)));
            room3DateTime.setText(formatDateTime(sessions.get(1)));
            room2DateTime2.setText(formatDateTime(sessions.get(2)));

            seatsFreeTotalRoom2.setText(sessions.get(0).getTotalAvailableSeats() + " disponíveis");
            seatsFreeTotalRoom3.setText(sessions.get(1).getTotalAvailableSeats() + " disponíveis");
            seatsFreeTotalRoom2_2.setText(sessions.get(2).getTotalAvailableSeats() + " disponíveis");

            // Mostra o nome do filme (assumindo que seja o mesmo nas 3 sessões)
            movieName.setText(sessions.get(0).getMovie().getTitle());
        } else {
            // Se não tiver sessões, limpa tudo
            room2DateTime.setText("");
            room3DateTime.setText("");
            room2DateTime2.setText("");
            seatsFreeTotalRoom2.setText("");
            seatsFreeTotalRoom3.setText("");
            seatsFreeTotalRoom2_2.setText("");
            movieName.setText("");
        }
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
            Session session = getSelectedSession();

            // Valida se cliente e sessão foram encontrados
            if (client == null || session == null) {
                showAlert("Selecione um cliente e uma sessão válidos.");
                return;
            }

            // Realiza a venda do ticket com base nas infos fornecidas
            Ticket ticket = TicketController.purchaseTicket(clientID, session.getId(), paymentStr);

            // Mostra o desconto aplicado
            double discount = ClientController.calculateDiscount(clientID);
            showDiscountPopup(discount);

            // Vai pra tela de confirmação com os dados do ticket
            MainViews.changeScreen("popUpRegisteredSale", List.of(ticket));

        } catch (NumberFormatException e) {
            showAlert("ID do cliente inválido.");
        } catch (IllegalArgumentException e) {
            showAlert("Erro: " + e.getMessage());
            // Redireciona para tela de "lotado" se for o caso
            MainViews.changeScreen("oversold", null);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erro inesperado.");
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
     * Verifica qual sessão foi selecionada com os radio buttons.
     * @return A sessão escolhida, ou null se nenhuma estiver selecionada.
     */
    private Session getSelectedSession() {
        if (sessions == null || sessions.size() < 3) return null;
        if (radioRoom2.isSelected()) return sessions.get(0);
        if (radioRoom3.isSelected()) return sessions.get(1);
        if (radioRoom2_2.isSelected()) return sessions.get(2);
        return null;
    }

    /**
     * Formata a data e hora de uma sessão para exibição.
     * @param session Sessão a ser formatada.
     * @return String no formato "data - hora"
     */
    private String formatDateTime(Session session) {
        return session.getDate().toString() + " - " + session.getTime().toString();
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
     * Exibe uma janela pop-up com o valor do desconto aplicado ao cliente.
     * Essa janela é carregada a partir do arquivo FXML "PopUpDiscount.fxml"
     * e mostra dinamicamente o valor do desconto.
     *
     * @param discount Valor percentual do desconto (ex: 10.0 para 10%)
     */
    @FXML
    private void showDiscountPopup(double discount) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/PopUpDiscount.fxml"));
            Parent root = loader.load();

            PopUpDiscountController controller = loader.getController();
            controller.setDiscount(discount);

            Stage popupStage = new Stage();
            popupStage.setTitle("Desconto Aplicado");
            popupStage.setScene(new Scene(root));
            popupStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erro ao carregar o pop-up de desconto.");
        }
    }

}
