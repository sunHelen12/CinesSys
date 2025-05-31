package controller.viewcontroller;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PopUpMovieController {

    @FXML
    private Label mensagemLabel;

    private Stage stage;

    public void setMensagemPersonalizada(String mensagem) {
        mensagemLabel.setText("Filme " + mensagem + " com Sucesso!");
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void fecharPopUp() {
        if (stage != null) {
            stage.close();
        }
    }
}

