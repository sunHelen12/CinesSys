package controller.viewcontroller;

import com.sun.tools.javac.Main;
import controller.business.ClientController;
import controller.business.MovieController;
import controller.business.RoomController;
import controller.business.SessionController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.Movie;

/**
 * Classe responsável por controlar a tela de cadastro de uma sessão.
 * 
 * @author
 * @since
 * @version
 */
public class RegisterSessionController {

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtMovie;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtRoom;

    @FXML
    private TextField txtTime;

    /**
     * Retorna para a tela de controle de sessões.
     * 
     * @param event evento de clique no botão de voltar
     */
    @FXML
    void backSessionController(ActionEvent event) {
        MainViews.changeScreen("sessionControl", null);
    }

    /**
     * Cadastra uma sessão no sistema.
     * 
     * @param event evento de clique no botão de cadastrar sessão
     */
    @FXML
    void registerSession(ActionEvent event) {
        String date = txtDate.getText().trim();
        String time = txtTime.getText().trim();
        String room = txtRoom.getText().trim();
        String movie = txtMovie.getText().trim();
        String ticketPrice = txtPrice.getText().trim();

        if (!date.isEmpty() && !time.isEmpty() && !room.isEmpty() && !movie.isEmpty() && !ticketPrice.isEmpty()) {
            int roomId = Integer.parseInt(room);
            double ticketPriceValue = Double.parseDouble(ticketPrice);
            SessionController.addSession(date, time, RoomController.getRoomById(roomId), MovieController.getMovieByName(movie), ticketPriceValue);
            txtMovie.clear();
            txtDate.clear();
            txtTime.clear();
            txtRoom.clear();
            txtPrice.clear();
            ClientControlController.mostrarPopUp("cadastrado");
        }
    }

}
