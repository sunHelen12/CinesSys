package controller.viewcontroller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientHistoryController implements Initializable {
    @FXML
    private VBox vboxHistorico;
    
    @FXML
    private Label lblEmail;

    @FXML
    private Label lblID;

    @FXML
    private Label lblNascimento;

    @FXML
    private Label lblNumIngressos;

    @FXML
    private Label lnlNome;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Main.addOnChangeScreenListener(new Main.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object userDataObject) {
                System.out.println(newScreen + ", " + userDataObject);
            }
        });
    }
}
