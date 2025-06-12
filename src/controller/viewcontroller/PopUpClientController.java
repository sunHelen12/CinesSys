import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PopUpClientController {

    @FXML
    private Label lblMsg;

    private Stage stage;

    public void setMensagemPersonalizada(String mensagem) {
        mensagemLabel.setText("Cliente " + mensagem + " com Sucesso!");
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void backClientControl(ActionEvent event) {
        stage.close();
    }

}
