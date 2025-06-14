package controller.viewcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SessionControlController {

    @FXML
    private TableColumn<?, ?> classification;

    @FXML
    private Label date1;

    @FXML
    private Label date2;

    @FXML
    private Label date3;

    @FXML
    private Label date4;

    @FXML
    private TableColumn<?, ?> duration;

    @FXML
    private TableColumn<?, ?> movieName;

    @FXML
    private TableColumn<?, ?> numberSeats;

    @FXML
    private Button openHomeScreen;

    @FXML
    private TableColumn<?, ?> room;

    @FXML
    private TableColumn<?, ?> select;

    @FXML
    private TableView<?> table;
    @FXML
    void changeSession(ActionEvent event) {

    }

    @FXML
    void deleteSession(ActionEvent event) {

    }

    @FXML
    void openClientControl(ActionEvent event) {

    }

    @FXML
    void openDate1(ActionEvent event) {

    }

    @FXML
    void openDate2(ActionEvent event) {

    }

    @FXML
    void openDate3(ActionEvent event) {

    }

    @FXML
    void openDate4(ActionEvent event) {

    }

    @FXML
    void openMovieControl(ActionEvent event) {

    }

    @FXML
    void openPurchaseRelatory(ActionEvent event) {

    }

    @FXML
    void openRoomRelatory(ActionEvent event) {

    }

    @FXML
    void openSessionControl(ActionEvent event) {

    }

    @FXML
    void registerSession(ActionEvent event) {
        MainViews.changeScreen("registerSession", null);
    }

}