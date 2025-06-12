package controller.viewcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import controller.viewcontroller.MainViews;

public class PopUpClientController {

    @FXML
    private Label lblMsg;

    private Stage stage;

    public void setMensagemPersonalizada(String mensagem) {
        lblMsg.setText("Cliente " + mensagem + " com Sucesso!");
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void backClientControl(ActionEvent event) {
        MainViews.changeScreen("clientControl", null);
        stage.close();
    }

}
