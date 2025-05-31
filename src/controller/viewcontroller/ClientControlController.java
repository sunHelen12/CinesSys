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

public class ClientControlController implements Initializable{

    @FXML
    private VBox containerResultados;

    @FXML
    private ScrollPane scrollResultados;

    @FXML
    private TextField txtBusca;

    private void addFilter() {
        txtBusca.setOnAction(event -> search());
    }

    // Simulação de uma base de dados
    private final List<Client> clients = new ArrayList<>();

    @FXML
    void buscar(ActionEvent event) {
        search();
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

        if (clientList.isEmpty()) {
            Label noResultsLabel = new Label("Nenhum cliente encontrado.");
            containerResultados.getChildren().add(noResultsLabel);
            return;
        }

        Label labelContagemResultados = new Label(clientList.size() + " cliente(s) encontrado(s)");
        containerResultados.getChildren().add(labelContagemResultados);

        for (Client client : clientList) {
            Label labelNome = new Label("\nNome: " + client.getName());
            labelNome.setStyle(
                    "-fx-text-fill: #f2e8c6 !important; -fx-font-family: Arial !important; -fx-font-size: 14px;");
            Label labelId = new Label("ID: " + client.getId());
            labelId.setStyle(
                    "-fx-text-fill: #f2e8c6 !important; -fx-font-family: Arial !important; -fx-font-size: 14px;");
            Label labelEmail = new Label("Email: " + client.getEmail());
            labelEmail.setStyle(
                    "-fx-text-fill: #f2e8c6 !important; -fx-font-family: Arial !important; -fx-font-size: 14px;");        
            Label labeldt = new Label("Data de Nascimento: " + client.getBirthday());
            labeldt.setStyle(
                    "-fx-text-fill: #f2e8c6 !important; -fx-font-family: Arial !important; -fx-font-size: 14px;");      
            Label labelRelatory= new Label("Acessar Relatório de Compras: " + client.getPurchasingHistory());
            labelRelatory.setStyle(
                    "-fx-text-fill: #f2e8c6 !important; -fx-font-family: Arial !important; -fx-font-size: 14px;"); 


            Button botaoExcluir = new Button("Excluir");
            botaoExcluir.setUserData(client);
            botaoExcluir.setStyle("-fx-background-color: #F58414; -fx-text-fill: #f2e8c6; -fx-font-weight: bold;");
            Button botaoAlterar = new Button("Alterar");
            botaoAlterar.setUserData(client);
            botaoAlterar.setStyle("-fx-background-color: #F58414; -fx-text-fill: #f2e8c6; -fx-font-weight: bold;");

            botaoExcluir.setOnAction(event -> {
                Client clienteSelecionado = (Client) ((Button) event.getSource()).getUserData();

                // Aqui estamos simulando um Ticket fictício com data atual
                Ticket ticketSimulado = new Ticket(clienteSelecionado,
                        new Session(LocalDate.now(), LocalTime.now(), Room.room1,
                                new Movie("Titulo", "Terror", 90, "Maior de idade", "Doideira"), 90.0),
                        PaymentMethod.CASH);

                // Mostrar PopUp
                //MainViews.changeScreen("", null);

                //acessar pelo repository e excluir
            });

            botaoAlterar.setOnAction(event -> {
                Client clienteSelecionado = (Client) ((Button) event.getSource()).getUserData();

                // Aqui estamos simulando um Ticket fictício com data atual
                Ticket ticketSimulado = new Ticket(clienteSelecionado,
                        new Session(LocalDate.now(), LocalTime.now(), Room.room1,
                                new Movie("Titulo", "Terror", 90, "Maior de idade", "Doideira"), 90.0),
                        PaymentMethod.CASH);

                // Trocar Tela
                MainViews.changeScreen("changeClient", clienteSelecionado);
            });

            VBox caixaEntradaResultado = new VBox(5);
            caixaEntradaResultado.setPadding(new Insets(0, 0, 10, 0));
            caixaEntradaResultado.getChildren().addAll(labelNome, labelId, labelEmail, labeldt, labelRelatory, botaoExcluir, botaoAlterar);

            containerResultados.getChildren().add(caixaEntradaResultado);
        }
    }

    @FXML
    void openHomeScreen(ActionEvent event) {
        MainViews.changeScreen("homeScreen", null);
    }

    @FXML
    void openRegisterClient(ActionEvent event) {
        MainViews.changeScreen("registerClient", null);
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

    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addFilter();
        inicializarClientesMock();
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
}
