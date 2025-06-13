package controller.viewcontroller;

import controller.business.RoomController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

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
        MainViews.changeScreen("occupationRelatory", RoomController.getRoomById(1));
    }

    @FXML
    void openRoom2(ActionEvent event) {
        MainViews.changeScreen("occupationRelatory", RoomController.getRoomById(2));
    }

    @FXML
    void openRoom3(ActionEvent event) {
        MainViews.changeScreen("occupationRelatory", RoomController.getRoomById(3));
    }

    @FXML
    void openRoom4(ActionEvent event) {
        MainViews.changeScreen("occupationRelatory", RoomController.getRoomById(4));
    }

    @FXML
    void openRoom5(ActionEvent event) {
        MainViews.changeScreen("occupationRelatory", RoomController.getRoomById(5));
    }

}
