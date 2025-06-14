package controller.viewcontroller;

import controller.business.ClientController;
import controller.business.MovieController;
import controller.business.RoomController;
import controller.business.SessionController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.Movie;

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
    @FXML
    void backSessionController(ActionEvent event) {

    }

    @FXML
    void registerSession(ActionEvent event) {
        String date = txtDate.getText().trim();
        String time = txtTime.getText().trim();
        String room = txtRoom.getText().trim();
        String movie = txtMovie.getText().trim();
        String ticketPrice = txtPrice.getText().trim();

        if (date.isEmpty() || time.isEmpty() || room.isEmpty()) {
            return;
        }else{
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
