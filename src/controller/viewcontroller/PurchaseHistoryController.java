package controller.viewcontroller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import models.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class PurchaseHistoryController implements Initializable {

    @FXML
    private Label labelContagemResultados;

    @FXML
    private ScrollPane scrollPaneResultados;

    @FXML
    private VBox containerResultados;

    @FXML
    private TextField txtBusca;

    // Simulação de uma base de dados
    private final List<Client> clients = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inicializarClientesMock();
        addFilter();
    }

    private void inicializarClientesMock() {
        clients.add(new Client("Vinicius", "vinicius@email.com", LocalDate.now()));
        clients.add(new Client("Maria Eduarda", "mariaeduarda@email.com", LocalDate.now()));
        clients.add(new Client("Maria Helena", "mariahelena@email.com", LocalDate.now()));
        clients.add(new Client("Helen", "helen@email.com", LocalDate.now()));
        clients.add(new Client("Thiago", "thiago@email.com", LocalDate.now()));
        clients.add(new Client("Kaique", "kaique@email.com", LocalDate.now()));
        clients.add(new Client("Carlos Henrique", "carloshenrique@email.com", LocalDate.now()));
        clients.add(new Client("Gabriele", "gabriele@email.com", LocalDate.now()));
    }

    private void addFilter() {
        txtBusca.setOnAction(event -> search());
    }

    @FXML
    private void search() {
        String searchTerm = txtBusca.getText().toLowerCase(Locale.ROOT).trim();
        List<Client> searchResultsList = new ArrayList<>();

        if (!searchTerm.isEmpty()) {
            for (Client client : clients) {
                if (client.getName().toLowerCase(Locale.ROOT).contains(searchTerm)) {
                    searchResultsList.add(client);
                }
            }
        }

        searchResults(searchResultsList);
    }

    private void searchResults(List<Client> clientList) {
        containerResultados.getChildren().clear();
        labelContagemResultados.setText(clientList.size() + " cliente(s) encontrado(s)");

        if (clientList.isEmpty()) {
            Label noResultsLabel = new Label("Nenhum cliente encontrado.");
            containerResultados.getChildren().add(noResultsLabel);
            return;
        }

        for (Client client : clientList) {
            Label labelNome = new Label("Nome: " + client.getName());
            Label labelId = new Label("ID: " + client.getId());

            Button botaoAcessar = new Button("Acessar Relatório(s)");
            botaoAcessar.setUserData(client);
            botaoAcessar.setStyle("-fx-background-color: #D92550; -fx-text-fill: white; -fx-font-weight: bold;");

            botaoAcessar.setOnAction(event -> {
                Client clienteSelecionado = (Client) ((Button) event.getSource()).getUserData();

                // Aqui estamos simulando um Ticket fictício com data atual
                Ticket ticketSimulado = new Ticket(clienteSelecionado, new Session(LocalDate.now(), Room.room1, new Movie("Titulo", "Terror", 90, "Maior de idade", "Doideira"), 90.0), PaymentMethod.CASH);
                abrirDetalhesDoRelatorio(ticketSimulado);
            });

            VBox caixaEntradaResultado = new VBox(5);
            caixaEntradaResultado.setPadding(new Insets(0, 0, 10, 0));
            caixaEntradaResultado.getChildren().addAll(labelNome, labelId, botaoAcessar);

            containerResultados.getChildren().add(caixaEntradaResultado);
        }
    }

    private void abrirDetalhesDoRelatorio(Ticket compra) {
        System.out.println("Acessando relatório para compra ID: " + compra.getId() +
                " de " + compra.getClient().getName());

        // Aqui você pode carregar um novo FXML com FXMLLoader
        // e passar os dados do ticket para o próximo controller
    }
}
