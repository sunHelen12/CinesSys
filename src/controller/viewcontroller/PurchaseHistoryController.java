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
import repository.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import controller.viewcontroller.MainViews;
import enums.PaymentMethod;

public class PurchaseHistoryController implements Initializable {
    private final static ClientRepository clientRepository = new ClientRepository();

    @FXML
    private ScrollPane scrollPaneResultados;

    @FXML
    private VBox containerResultados;

    @FXML
    private TextField txtBusca;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addFilter();
        inicializarClientesMock();
    }

    @FXML
    void openHomeScreen(ActionEvent event) {
        MainViews.changeScreen("homeScreen", null);
    }

    @FXML
    void openClientControl(ActionEvent event) {
        MainViews.changeScreen("clientControl", null);
    }

    @FXML
    void openMovieControl(ActionEvent event) {
        MainViews.changeScreen("movieControl", null);
    }

    @FXML
    void openPurchaseHistory(ActionEvent event) {
        MainViews.changeScreen("purchaseRelatory", null);
    }

    @FXML
    void openRoomOccupation(ActionEvent event) {
        MainViews.changeScreen("roomOccupation", null);
    }

    @FXML
    void openSessionControl(ActionEvent event) {
        // MainViews.changeScreen("sessionControl", null);
    }


    private void addFilter() {
        txtBusca.setOnAction(event -> search());
    }

    @FXML
    void buscar(ActionEvent event) {
        search();
    }

    @FXML
    private void search() {
        String searchTerm = txtBusca.getText().toLowerCase(Locale.ROOT).trim();
        List<Client> searchResultsList = new ArrayList<>();

        if (!searchTerm.isEmpty()) {
            for (Client client : clientRepository.getAll()) {
                if (client.getName().toLowerCase(Locale.ROOT).contains(searchTerm)) {
                    searchResultsList.add(client);
                }
            }
        }

        searchResults(searchResultsList);
    }

    private void searchResults(List<Client> clientList) {
        containerResultados.getChildren().clear();

        if (clientList.isEmpty()) {
            Label noResultsLabel = new Label("Nenhum cliente encontrado.");
            containerResultados.getChildren().add(noResultsLabel);
            return;
        }
            
        Label labelContagemResultados = new Label(clientList.size() + " cliente(s) encontrado(s)");
        containerResultados.getChildren().add(labelContagemResultados);
        
        

        for (Client client : clientList) {
            Label labelNome = new Label("\nNome: " + client.getName());
            labelNome.setStyle("-fx-text-fill: #f2e8c6 !important; -fx-font-family: Arial !important; -fx-font-size: 14px;");
            Label labelId = new Label("ID: " + client.getId());
            labelId.setStyle("-fx-text-fill: #f2e8c6 !important; -fx-font-family: Arial !important; -fx-font-size: 14px;");

            Button botaoAcessar = new Button("Acessar Relatório(s)");
            botaoAcessar.setUserData(client);
            botaoAcessar.setStyle("-fx-background-color: #D92550; -fx-text-fill: #f2e8c6; -fx-font-weight: bold;");

            botaoAcessar.setOnAction(event -> {
                Client clienteSelecionado = (Client) ((Button) event.getSource()).getUserData();
                MainViews.changeScreen("clientHistory", clienteSelecionado);
            });

            VBox caixaEntradaResultado = new VBox(5);
            caixaEntradaResultado.setPadding(new Insets(0, 0, 10, 0));
            caixaEntradaResultado.getChildren().addAll(labelNome, labelId, botaoAcessar);

            containerResultados.getChildren().add(caixaEntradaResultado);
        }
    }

    // dados de teste
    private static void inicializarClientesMock() {
        Client c1 = new Client("Vinicius", "vinicius@email.com", LocalDate.now());
        Client c2 = new Client("Maria Eduarda", "mariaeduarda@email.com", LocalDate.now());
        Client c3 = new Client("Maria Helena", "mariahelena@email.com", LocalDate.now());
        Client c4 = new Client("Helen", "helen@email.com", LocalDate.now());
        Client c5 = new Client("Thiago", "thiago@email.com", LocalDate.now());
        Client c6 = new Client("Kaique", "kaique@email.com", LocalDate.now());
        Client c7 = new Client("Carlos Henrique", "carloshenrique@email.com", LocalDate.now());
        Client c8 = new Client("Gabriele", "gabriele@email.com", LocalDate.now());

        clientRepository.add(c1);
        clientRepository.add(c2);
        clientRepository.add(c3);
        clientRepository.add(c4);
        clientRepository.add(c5);
        clientRepository.add(c6);
        clientRepository.add(c7);
        clientRepository.add(c8);

        Ticket ticketSimulado = new Ticket(c2,
                new Session(LocalDate.now(), LocalTime.now(), Room.room1,
                        new Movie("It: A coisa", "Terror", 90, "+18", "Doideira"), 90.0),
                PaymentMethod.CASH);
        Ticket ticketSimulado2 = new Ticket(c2,
                new Session(LocalDate.now(), LocalTime.now(), Room.room1,
                        new Movie("Filme Aleatorio", "Drama", 150, "+14", "Doideira"), 90.0),
                PaymentMethod.CASH);
        Ticket ticketSimulado3 = new Ticket(c3,
                new Session(LocalDate.now(), LocalTime.now(), Room.room1,
                        new Movie("Chapeuzinho", "Infantil", 120, "Livre", "Leve"), 90.0),
                PaymentMethod.CASH);
        c2.addTicketToHistory(ticketSimulado);
        c2.addTicketToHistory(ticketSimulado2);
        c3.addTicketToHistory(ticketSimulado3);
    }
}
