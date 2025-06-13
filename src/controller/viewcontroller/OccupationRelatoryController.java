package controller.viewcontroller;

import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controller.bussines.RoomController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import models.*;

public class OccupationRelatoryController implements Initializable {
    private static Room room;
    private String selecionado;

    @FXML
    private Label roomName;

    @FXML
    private Label totalSeat;

    @FXML
    private VBox containerFiltragem;
    
    @FXML
    private ComboBox<String> filtroOcupacao;

    private List<String> filter = new ArrayList<>();
    private ObservableList<String> items;
    
    @FXML
    void backRoomOccupation(ActionEvent event) {
        MainViews.changeScreen("roomOccupation", null);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MainViews.addOnChangeScreenListener(new MainViews.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object userDataObject) {
                if (userDataObject instanceof Room) {
                    room = (Room) userDataObject;
                    updateRoomSpecificUI();
                }
            }
        });
        
        try {
            inicializarSessoesParaTeste();
        } catch (Exception e) {
            e.printStackTrace();
        }
        addFilter();
    }

    private void updateRoomSpecificUI() {
        if (room != null) { 
            totalSeat.setText(room.getTotalSeat()+"");
            if (room.getId() == 1) {
                roomName.setText("Sala 1"); 
            } else if (room.getId() == 2) {
                roomName.setText("Sala 2");
            } else if (room.getId() == 3) {
                roomName.setText("Sala 3");
            } else if (room.getId() == 4) {
                roomName.setText("Sala 4");
            } else if (room.getId() == 5) {
                roomName.setText("Sala 5");
            }
        } else {
            roomName.setText("Sala (N/A)"); 
        }
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
            
            if (room != null) {
                if (room.getId() == 1) {
                    mostrarFiltragem1();
                } else if (room.getId() == 2) {
                    mostrarFiltragem2();
                } else if (room.getId() == 3) {
                    mostrarFiltragem3();
                } else if (room.getId() == 4) {
                    mostrarFiltragem4();
                } else if (room.getId() == 5) {
                    mostrarFiltragem5();
                }
            } else {
                System.err.println("Erro: Não foi possível aplicar filtro, 'room' é nulo.");
            }
        });        
    }

    public void mostrarFiltragem1() {
        containerFiltragem.getChildren().clear();

        if ("Filme".equals(selecionado)) {
            for (Session session : RoomController.getRoomById(1).getSessions()) {
                Label labelAux = new Label(
                        session.getMovie().getTitle() + " - " + "Ocupação Média: " + 
                        ((RoomController.getRoomById(1).getTotalSeat() - session.getTotalAvailableSeats()) / RoomController.getRoomById(1).getTotalSeat()) +"\n");
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

    public void mostrarFiltragem2() {
    }

    public void mostrarFiltragem3() {
    }

    public void mostrarFiltragem4() {
    }

    public void mostrarFiltragem5() {
    }
    //Gambiarras de Teste

    // Simulação de uma base de dados
    private final List<Session> sessoesDeTeste = new ArrayList<>();

    //Remover depois
    private void inicializarSessoesParaTeste() throws Exception{
        sessoesDeTeste.add(new Session(LocalDate.now(), LocalTime.now(), RoomController.getRoomById(1), new Movie("Filme 1", "Drama", 231, "10", "Sinopse"), 18.5));
        sessoesDeTeste.add(
                new Session(LocalDate.now(), LocalTime.now(), RoomController.getRoomById(1), new Movie("Filme 2", "Ação", 198, "9.9", "Sinopse"), 25.5));

        for(Session session:sessoesDeTeste){
            RoomController.getRoomById(1).addSession(session);
        }

    }
}
