package controller.viewcontroller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

//a remover
import models.*;
import enums.*;
import repository.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HomeScreenController implements Initializable {
    @FXML
    private ImageView closeButton;

    @FXML
    void openClientControl(ActionEvent event) {
        MainViews.changeScreen("clientControl", null); 
    }

    @FXML
    void openRegisterClient(ActionEvent event) {
        MainViews.changeScreen("registerClient", null);
    }

    @FXML
    void openMovieControl(ActionEvent event) {
        MainViews.changeScreen("movieControl", null);
    }

    @FXML
    void openOccupationRelatory(ActionEvent event) {
        MainViews.changeScreen("roomOccupation", null);
    }

    @FXML
    void openPurchaseControl(ActionEvent event) {
        MainViews.changeScreen("purchaseRelatory", null);
    }

    @FXML
    void openPurchaseRelatory(ActionEvent event) {
        MainViews.changeScreen("purchaseRelatory", null);
    }

    @FXML
    void openSessionControl(ActionEvent event) {
        //MainViews.changeScreen("sessionControl", null);
    }    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarClientesMock();
    }

    // dados de teste

    public static final ClientRepository clientRepository = new ClientRepository();

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