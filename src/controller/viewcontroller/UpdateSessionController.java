package controller.viewcontroller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import controller.viewcontroller.SessionControlController;
import models.Session;
import controller.business.SessionController;

public class UpdateSessionController implements Initializable {
    private static Session session;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtMovieId;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtRoom;

    @FXML
    private TextField txtTime;

    /**
     * inicializa mudando a cor do texto e do fundo dos campos de texto
     * 
     * @param url O URL de onde o controlador foi carregado.
     * @param rb O ResourceBundle associado ao controlador.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Muda a cor do texto e do fundo dos campos de texto
        txtDate.setStyle("-fx-text-fill: white !important; -fx-background-color: #03002C !important;");
        txtMovieId.setStyle("-fx-text-fill: white !important; -fx-background-color: #03002C !important;");
        txtPrice.setStyle("-fx-text-fill: white !important; -fx-background-color: #03002C !important;");
        txtRoom.setStyle("-fx-text-fill: white !important; -fx-background-color: #03002C !important;");
        txtTime.setStyle("-fx-text-fill: white !important; -fx-background-color: #03002C !important;");

        MainViews.addOnChangeScreenListener(new MainViews.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object userDataObject) {
                if (userDataObject instanceof Session) {
                    session = (Session) userDataObject;
                    txtDate.setText(session.getDate());
                    txtTime.setText(session.getTime());
                    txtMovieId.setText(session.getMovie().getId() + "");
                    txtRoom.setText(session.getRoom().getId() + "");
                    txtPrice.setText(session.getTicketValue() + "");
                }
            }
        });
    }

    @FXML
    void backSessionController(ActionEvent event) {
        MainViews.changeScreen("sessionControl", null);
    }

    @FXML
    void updateSession(ActionEvent event) {
        String date = txtDate.getText().trim();
        String time = txtTime.getText().trim();
        String room = txtRoom.getText().trim();
        String movie = txtMovieId.getText().trim();
        String ticketPrice = txtPrice.getText().trim();
        double tck = Double.parseDouble(ticketPrice);

        SessionController.updateSession(session.getId(), date, time, session.getRoom(), session.getMovie(), tck);
        txtDate.clear();
        txtTime.clear();
        txtMovieId.clear();
        txtRoom.clear();
        txtPrice.clear();
        SessionControlController.mostrarPopUp("alterada");
    }

}
