package controller.viewcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import models.Session;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller relacionado ao fxml PayPerSession
 *
 * @author Vinícius Nunes de Andrade
 * @since 14-06-2025
 * @version 1.0
 */
public class PayPerSessionController  implements Initializable {

    Session session;

    @FXML
    private Button backButton;

    @FXML
    private Label dateTime;

    @FXML
    private Label freeSeatsTotalSeats;

    @FXML
    private Label mapSeats;

    @FXML
    private Label movieDuration;

    @FXML
    private Label movieName;

    @FXML
    private Label soldTickets;

    @FXML
    private Label ticketValue;

    @FXML
    private Label valueCollected;

    @FXML
    void voltar(ActionEvent event) {
        MainViews.changeScreen("sessionControl", null);
    }

    /**
     * Inicializa o controlador.
     *
     * @param url URL de localização do arquivo FXML, se necessário.
     * @param rb Conjunto de recursos localizados, se necessário.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MainViews.addOnChangeScreenListener(new MainViews.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object userDataObject) {
                if (userDataObject instanceof Session) {
                    session = (Session) userDataObject;
                    updateSessionSpecificUI();
                }
            }
        });
    }

    /**
     * Atualiza a interface da tela com informações da sessão
     */
    private void updateSessionSpecificUI() {
        if (session != null) {
            movieName.setText(session.getMovie().getTitle());
            dateTime.setText(session.getDate() + " às " + session.getTime());
            freeSeatsTotalSeats.setText(session.getTotalAvailableSeats()+"");
            //Cálculo do valor arrecadado pela sessão
            Double valueEarned = (session.getRoom().getTotalSeat() - session.getTotalAvailableSeats()) * session.getTicketValue();
            valueCollected.setText(valueEarned+"");
            movieDuration.setText(session.getMovie().getDuration()+"");
            ticketValue.setText(session.getTicketValue()+"");
            soldTickets.setText((session.getRoom().getTotalSeat() - session.getTotalAvailableSeats())+"");

        } else {
            movieName.setText("Sala (N/A)");
        }
    }

}

