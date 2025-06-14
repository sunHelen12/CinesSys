package controller.viewcontroller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import models.*;

/**
 * Classe responsável por controlar a tela de alteração de um cliente.
 * @author
 * @since
 * @version
 */
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

    /**
     * Volta para a tela anterior.
     * 
     * @param event
     */
    @FXML
    void backPurchase(ActionEvent event) {
        MainViews.changeScreen("purchaseRelatory", null);
    }

    /**
     * Inicializa o controlador.
     * 
     * @param url O URL de onde o controlador foi carregado.
     * @param rb O ResourceBundle associado ao controlador.
     */
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

    /**
     * Acessa os dados do cliente.
     */
    private void accessClientData(){
        
        lnlNome.setText(client.getName());
        lblID.setText(String.valueOf(client.getId()));
        lblEmail.setText(client.getEmail());
        lblNascimento.setText(client.getBirthday());
        lblNumIngressos.setText(String.valueOf(client.getPurchasingHistory().size()));

        for (Ticket ticket : client.getPurchasingHistory()) {
            Label labelMovie = new Label(ticket.getSession().getMovie().getTitle());
            labelMovie.setStyle(
                    "-fx-text-fill: #f2e8c6 !important; -fx-font-family: Arial !important; -fx-font-size: 14px;");
            Label labelSession = new Label("Sessão → " + ticket.getSession().getDate() + " às " + ticket
                    .getSession().getTime() + " (Sala "+ ticket.getSession().getRoom().getId() +")");
            labelSession.setStyle(
                    "-fx-text-fill: #f2e8c6 !important; -fx-font-family: Arial !important; -fx-font-size: 14px;");
            Label labelAccess = new Label("Acessar Registro de Compras: ");
            Label labelEmpty = new Label("");
            labelAccess.setStyle(
                    "-fx-text-fill: #f2e8c6 !important; -fx-font-family: Arial !important; -fx-font-size: 14px;");

            //Image imagem = new Image("@../../assets/img/pasta_cor_BB0029.png");
            //ImageView imageView = new ImageView(imagem);
            //imageView.setFitWidth(40); 
            //imageView.setFitHeight(40);

            Button botaoAcessar = new Button("Registro");
            botaoAcessar.setUserData(ticket);
            //botaoAcessar.setGraphic(imageView); 
            botaoAcessar.setStyle("-fx-background-color:  #D92550; -fx-padding: 0; -fx-border-color:  #D92550; -fx-text-fill: #f2e8c6; -fx-font-weight: bold;");                  

            botaoAcessar.setOnAction(event -> {
                Ticket ticketSelecionado = (Ticket) ((Button) event.getSource()).getUserData();
                MainViews.changeScreen("purchaseRecord", ticketSelecionado);
            });

            VBox caixaEntradaResultado = new VBox(5);
            caixaEntradaResultado.setPadding(new Insets(0, 0, 10, 0));
            caixaEntradaResultado.getChildren().addAll(labelMovie, labelSession, labelAccess, botaoAcessar, labelEmpty);

            containerResultados.getChildren().add(caixaEntradaResultado);
        }
    }
}
