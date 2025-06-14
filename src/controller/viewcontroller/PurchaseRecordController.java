package controller.viewcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import structures.list.GenericDynamicList;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import models.*;

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

    private void accessTicketData(){
        labelMovie.setText(ticket.getSession().getMovie().getTitle());
        labelName.setText(ticket.getClient().getName());
        labelSession.setText(ticket.getSession().getDate() + " às " + ticket.getSession().getTime() + " (Sala " + ticket.getSession().getRoom().getId() + ")");
        labelPagamento.setText(ticket.getPaymentMethod().toString());
        
        GenericDynamicList<Ticket> list = ticket.getClient().getPurchasingHistory();
        int tickets = 0;
        for(Ticket tck : list){
            if(ticket.getId() == tck.getId()){
                tickets++;
            }
        }
        labelTicket.setText(tickets+"");
    }

    @FXML
    void backPurchase(ActionEvent event) {
        MainViews.changeScreen("clientHistory", null);
    }

    @FXML
    void cancelTicket(ActionEvent event) {
        //controller ticket
    }

    @FXML
    void openClient(ActionEvent event) {
        MainViews.changeScreen("clientControl", ticket.getClient());
    }
}