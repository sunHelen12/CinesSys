package controller.viewcontroller;

import enums.PaymentMethod;
import javafx.event.ActionEvent;
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
import view.MainViews;

public class PurchaseTicketController {

    @FXML private TextField idDoCliente;
    @FXML private TextField metodoPagamento;

    @FXML private RadioButton radioSala2;
    @FXML private RadioButton radioSala3;
    @FXML private RadioButton radioSala2_2;

    @FXML private Label sala2DataHorario;
    @FXML private Label sala3DataHorario;
    @FXML private Label sala2DataHorario2;

    @FXML private Label assentosLivresTotalSala2;
    @FXML private Label assentosLivresTotalSala3;
    @FXML private Label assentosLivresTotalSala2_2;

    private ToggleGroup grupoSessoes = new ToggleGroup();

    private final ClientService clientService = new ClientService(new repository.ClientRepository());
    private final SessionService sessionService = new SessionService(new repository.SessionRepository());
    private final LoyaltyService loyaltyService = new LoyaltyService(new repository.ClientRepository());
    private final TicketService ticketService = new TicketService(new repository.TicketRepository());

    private Session sessaoSala2;
    private Session sessaoSala3;
    private Session sessaoSala2_2;

    @FXML
    public void initialize() {
        // Agrupa os RadioButtons
        radioSala2.setToggleGroup(grupoSessoes);
        radioSala3.setToggleGroup(grupoSessoes);
        radioSala2_2.setToggleGroup(grupoSessoes);

        // Carregar sessões (ajuste os IDs conforme teu banco real)
        sessaoSala2 = sessionService.getSessionById(1);
        sessaoSala3 = sessionService.getSessionById(2);
        sessaoSala2_2 = sessionService.getSessionById(3);

        atualizarInformacoesSessao();
    }

    private void atualizarInformacoesSessao() {
        if (sessaoSala2 != null) {
            sala2DataHorario.setText(sessaoSala2.getDate() + " - " + sessaoSala2.getTime());
            assentosLivresTotalSala2.setText("Assentos: " + sessaoSala2.getTotalAvailableSeats());
        }
        if (sessaoSala3 != null) {
            sala3DataHorario.setText(sessaoSala3.getDate() + " - " + sessaoSala3.getTime());
            assentosLivresTotalSala3.setText("Assentos: " + sessaoSala3.getTotalAvailableSeats());
        }
        if (sessaoSala2_2 != null) {
            sala2DataHorario2.setText(sessaoSala2_2.getDate() + " - " + sessaoSala2_2.getTime());
            assentosLivresTotalSala2_2.setText("Assentos: " + sessaoSala2_2.getTotalAvailableSeats());
        }
    }

    @FXML
    void registrarCompra(ActionEvent event) {
        String idClienteStr = idDoCliente.getText().trim();
        String metodo = metodoPagamento.getText().trim();

        if (idClienteStr.isEmpty() || metodo.isEmpty()) {
            mostrarAlerta("Preencha todos os campos obrigatórios.");
            return;
        }

        int clientId;
        try {
            clientId = Integer.parseInt(idClienteStr);
        } catch (NumberFormatException e) {
            mostrarAlerta("ID do cliente inválido! Digite um número.");
            return;
        }

        Session sessaoSelecionada = null;
        if (radioSala2.isSelected()) sessaoSelecionada = sessaoSala2;
        else if (radioSala3.isSelected()) sessaoSelecionada = sessaoSala3;
        else if (radioSala2_2.isSelected()) sessaoSelecionada = sessaoSala2_2;

        if (sessaoSelecionada == null) {
            mostrarAlerta("Selecione uma sessão antes de continuar.");
            return;
        }

        try {
            Ticket ticket = ticketService.purchaseTicket(
                    clientId,
                    sessaoSelecionada.getId(),
                    metodo,
                    clientService,
                    sessionService,
                    loyaltyService
            );

            double desconto = loyaltyService.calculateDiscount(clientId);

            abrirPopUpDesconto(ticket, desconto);

        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Erro ao registrar a compra: " + e.getMessage());
        }
    }

    private void abrirPopUpDesconto(Ticket ticket, double desconto) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/PopUpDiscount.fxml"));
            Parent root = loader.load();

            PopUpClientController controller = loader.getController();
            Stage stage = new Stage();
            controller.setStage(stage);
            controller.setMensagemDesconto(
                    "Compra concluída!\nDesconto aplicado: " +
                            String.format("%.0f", desconto) + "%\nValor final: R$" +
                            String.format("%.2f", ticket.getFinalPrice())
            );

            stage.setScene(new Scene(root));
            stage.setTitle("Compra Confirmada");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Erro ao abrir o pop-up.");
        }
    }

    @FXML
    void voltarParaHome(ActionEvent event) {
        MainViews.changeScreen("home", null);
    }

    private void mostrarAlerta(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Atenção");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}