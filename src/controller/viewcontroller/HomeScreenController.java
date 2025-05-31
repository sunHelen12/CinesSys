package controller.viewcontroller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeScreenController implements Initializable {
    @FXML
    private ImageView closeButton;

    @FXML
    void openClientControl(ActionEvent event) {
        MainViews.changeScreen("clientControl", null); 
    }

    @FXML
    void openRegisterClient(ActionEvent event) {
        MainViews.changeScreen("registerClient", null);
    }

    @FXML
    void openMovieControl(ActionEvent event) {
        MainViews.changeScreen("movieControl", null);
    }

    @FXML
    void openOccupationRelatory(ActionEvent event) {
        MainViews.changeScreen("roomOccupation", null);
    }

    @FXML
    void openPurchaseControl(ActionEvent event) {
        MainViews.changeScreen("purchaseRelatory", null);
    }

    @FXML
    void openPurchaseRelatory(ActionEvent event) {
        MainViews.changeScreen("purchaseRelatory", null);
    }

    @FXML
    void openSessionControl(ActionEvent event) {
        //MainViews.changeScreen("sessionControl", null);
    }    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

}