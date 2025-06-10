package controller.viewcontroller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import models.Client;
import java.net.URL;
import java.util.ResourceBundle;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import models.*;

public class ClientHistoryController implements Initializable {
    private static Client client;

    @FXML
    private VBox containerResultados;
    
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

    @FXML
    void backPurchase(ActionEvent event) {
        MainViews.changeScreen("purchaseRelatory", null);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MainViews.addOnChangeScreenListener(new MainViews.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object userDataObject) {
                if (userDataObject instanceof Client) {
                    client = (Client) userDataObject;
                    accessClientData(); 
                }
            }
        });
    }

    private void accessClientData(){
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
        
        lnlNome.setText(client.getName());
        lblID.setText(String.valueOf(client.getId()));
        lblEmail.setText(client.getEmail());
        lblNascimento.setText(client.getBirthday());
        lblNumIngressos.setText(String.valueOf(client.getPurchasingHistory().size()));

        for (Ticket ticket : client.getPurchasingHistory()) {
            Label labelMovie = new Label(ticket.getSession().getMovie().getTitle());
            labelMovie.setStyle(
                    "-fx-text-fill: #f2e8c6 !important; -fx-font-family: Arial !important; -fx-font-size: 14px;");
            Label labelSession = new Label("Sessão → " + ticket.getSession().getDate().format(formatDate) + " às" + ticket
                    .getSession().getTime().format(timeFormat) + " (Sala "+ ticket.getSession().getRoom().getId() +")");
            labelSession.setStyle(
                    "-fx-text-fill: #f2e8c6 !important; -fx-font-family: Arial !important; -fx-font-size: 14px;");
            Label labelAccess = new Label("Acessar Registro de Compras: ");
            labelAccess.setStyle(
                    "-fx-text-fill: #f2e8c6 !important; -fx-font-family: Arial !important; -fx-font-size: 14px;");

            //Image imagem = new Image("@../../assets/img/pasta_cor_BB0029.png");
            //ImageView imageView = new ImageView(imagem);
            //imageView.setFitWidth(40); 
            //imageView.setFitHeight(40);

            Button botaoAcessar = new Button();
            botaoAcessar.setUserData(ticket);
            //botaoAcessar.setGraphic(imageView); 
            botaoAcessar.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-border-color: transparent;");                    

            botaoAcessar.setOnAction(event -> {
                Ticket ticketSelecionado = (Ticket) ((Button) event.getSource()).getUserData();
                MainViews.changeScreen("purchaseRecord", ticketSelecionado);
            });

            VBox caixaEntradaResultado = new VBox(5);
            caixaEntradaResultado.setPadding(new Insets(0, 0, 10, 0));
            caixaEntradaResultado.getChildren().addAll(labelMovie, labelSession, labelAccess, botaoAcessar);

            containerResultados.getChildren().add(caixaEntradaResultado);
        }
    }
}
