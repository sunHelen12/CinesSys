package controller.viewcontroller;

import javafx.event.ActionEvent;
import models.Room;
import javafx.fxml.FXML;
import controller.viewcontroller.MainViews;

public class RoomOccupationController {

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

    @FXML
    void openRoom1(ActionEvent event) {
        MainViews.changeScreen("occupationRelatory", Room.room1);
    }

    @FXML
    void openRoom2(ActionEvent event) {
        MainViews.changeScreen("occupationRelatory", Room.room2);
    }

    @FXML
    void openRoom3(ActionEvent event) {
        MainViews.changeScreen("occupationRelatory", Room.room3);
    }

    @FXML
    void openRoom4(ActionEvent event) {
        MainViews.changeScreen("occupationRelatory", Room.room4);
    }

    @FXML
    void openRoom5(ActionEvent event) {
        MainViews.changeScreen("occupationRelatory", Room.room5);
    }

}
