package controller.viewcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class UpdateSessionController {

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
        MainViews.changeScreen("sessionController", null);
    }

    @FXML
    void updateSession(ActionEvent event) {
        String date = txtDate.getText().trim();
        String time = txtTime.getText().trim();
        String room = txtRoom.getText().trim();
        String movie = txtMovie.getText().trim();
        String ticketPrice = txtPrice.getText().trim();


    }

}
