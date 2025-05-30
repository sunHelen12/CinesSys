package controller.viewcontroller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import java.time.LocalDate;
import models.*;

public class OccupationRelatoryController implements Initializable {
    private String selecionado;

    @FXML
    private VBox containerFiltragem;
    
    @FXML
    private ComboBox<String> filtroOcupacao;

    private List<String> filter = new ArrayList<>();
    private ObservableList<String> items;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            inicializarSessoesParaTeste();
        } catch (Exception e) {
            e.printStackTrace();
        }
        addFilter();
    }

    public void addFilter(){
        filter.add("Filme");
        filter.add("Data");
        filter.add("Horário de Sessão");

        items = FXCollections.observableArrayList(filter);

        filtroOcupacao.setItems(items);

        filtroOcupacao.setOnAction(event -> {
            selecionado = filtroOcupacao.getValue();
            System.out.println("Selecionado: " + selecionado);
            mostrarFiltragem();
        });        
    }

    public void mostrarFiltragem() {
        containerFiltragem.getChildren().clear();

        if ("Filme".equals(selecionado)) {
            for (Session session : Room.room1.getSessions()) {
                Label labelAux = new Label(
                        session.getMovie().getTitle() + " - " + "Ocupação Média: " + 
                        ((Room.room1.getTotalSeat() - session.getTotalAvailableSeats()) / Room.room1.getTotalSeat()) +"\n");
                labelAux.setStyle("-fx-font-family: Arial; -fx-text-fill: #f2e8c6;");
                containerFiltragem.getChildren().add(labelAux);
                System.out.println("Filme X adicionado");
            }
        } else if ("Data".equals(selecionado)) {
            Label labelAux = new Label("Data");
            labelAux.setStyle("-fx-font-family: Arial; -fx-text-fill: #f2e8c6;");
            containerFiltragem.getChildren().add(labelAux);
        } else if ("Horário de Sessão".equals(selecionado)) {
            Label labelAux = new Label("Horário");
            labelAux.setStyle("-fx-font-family: Arial; -fx-text-fill: #f2e8c6;");
            containerFiltragem.getChildren().add(labelAux);
        }
    }

    //Gambiarras de Teste

    // Simulação de uma base de dados
    private final List<Session> sessoesDeTeste = new ArrayList<>();

    //Remover depois
    private void inicializarSessoesParaTeste() throws Exception{
        sessoesDeTeste.add(new Session(LocalDate.now(), Room.room1, new Movie("Filme 1", "Drama", 231, "10", "Sinopse"), 18.5));
        sessoesDeTeste.add(
                new Session(LocalDate.now(), Room.room1, new Movie("Filme 2", "Ação", 198, "9.9", "Sinopse"), 25.5));

        for(Session session:sessoesDeTeste){
            Room.room1.addSession(session);
        }

    }
}
