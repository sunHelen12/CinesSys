package controller.viewcontroller;

import com.sun.tools.javac.Main;
import controller.business.SaleController;
import controller.business.TicketController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import models.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import controller.viewcontroller.PopUpTicketCanceledController;


/**
 * Classe responsável por controlar a tela de registro de compras.
 * 
 * @author Maria Eduarda Campos 
 * @since 10/06/2025
 * @version 1.0
 */
public class PurchaseRecordController implements Initializable {
    private static Ticket ticket;
    
    @FXML
    private Label labelMovie;

    @FXML
    private Label labelName;

    @FXML
    private Label labelPagamento;

    @FXML
    private Label labelSession;

    @FXML
    private Label labelTicket;

    @FXML
    private Label lnlNome;

    /**
     * Inicializa a tela de registro de compras.
     */    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MainViews.addOnChangeScreenListener(new MainViews.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object userDataObject) {
                if (userDataObject instanceof Ticket) {
                    ticket = (Ticket) userDataObject;
                    accessTicketData();
                }
            }
        });
    }

    /**
     * Mostra uma janela.
     * * @param acao Ação realizada.
     */
    public static void mostrarPopUpCancellTicket() {
        try {
            FXMLLoader loader = new FXMLLoader(PurchaseRecordController.class.getResource("/gui/PopUpTicketCanceled.fxml"));
            Parent root = loader.load();

            PopUpTicketCanceledController controller = loader.getController();
            Stage stage = new Stage();
            controller.setStage(stage);
            controller.getClient(ticket.getClient());

            stage.setScene(new Scene(root));
            stage.setTitle("Confirmação");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * acessa as datas de cada ticket no sistema e exibe na tela
     */
    private void accessTicketData(){
        labelMovie.setText(ticket.getSession().getMovie().getTitle());
        labelName.setText(ticket.getClient().getName());
        labelSession.setText(ticket.getSession().getDate() + " às " + ticket.getSession().getTime() + " (Sala " + ticket.getSession().getRoom().getId() + ")");
        labelPagamento.setText(ticket.getPaymentMethod().toString());
        
        List<Ticket> list = ticket.getClient().getPurchasingHistory();
        int tickets = 0;
        for(Ticket tck : list){
            if(ticket.getId() == tck.getId()){
                tickets++;
            }
        }
        labelTicket.setText(tickets+"");
    }

    /**
     * Retorna ao histórico de compras do cliente.
     * 
     * @param event evento de clique do botão
     */
    @FXML
    void backPurchase(ActionEvent event) {
        MainViews.changeScreen("clientHistory", ticket.getClient());
    }

    /**
     * Cancela o ticket.
     * 
     * @param event evento de clique do botão
     */
    @FXML
    void cancelTicket(ActionEvent event) {
        LocalDate date = ticket.getSession().getDateObject();
        LocalTime time = ticket.getSession().getTimeObject();

        LocalDate dateNow = LocalDate.now();
        LocalTime timeNow = LocalTime.now();

        LocalDateTime dateTimeNow = LocalDateTime.of(dateNow, timeNow);
        LocalDateTime dateTime = LocalDateTime.of(date, time);

        long diff = java.time.Duration.between(dateTimeNow, dateTime).toHours();

        if (diff > 2) {
            SaleController.cancelSale(ticket.getId());
            mostrarPopUpCancellTicket();
        } else {
            showAlert("Não é possível cancelar o ingresso com menos de 2 horas de antes do horário da sessão.");
        }
    }

    /**
     * Abre o histórico de compras do cliente.
     * 
     * @param event evento de clique do botão
     */
    @FXML
    void openClient(ActionEvent event) {
        MainViews.changeScreen("clientControl", ticket.getClient());
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
}