package controller.viewcontroller;

import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controller.business.RoomController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Font;
import models.*;

/**
 * Classe responsável por controlar a tela de relatório de ocupação de salas.
 *
 * @author Maria Eduarda Santos Campos
 * @author Vinícius Nunes de Andrade
 * @since 28-05-2025
 * @version 3.0
 */
public class OccupationRelatoryController implements Initializable {
    private static Room room;
    private String selected;

    @FXML
    private Label roomName;

    @FXML
    private Label totalSeat;

    @FXML
    private VBox filterContainer;

    @FXML
    private ComboBox<String> filterOccupation;

    private List<String> filter = new ArrayList<>();
    private ObservableList<String> items;

    /**
     * Volta para a tela de ocupação de salas quando o botão "Voltar" é clicado.
     */
    @FXML
    void backRoomOccupation(ActionEvent event) {
        resetScreen();
        MainViews.changeScreen("roomOccupation", null);
    }

    private void resetScreen() {
        room = null; // Zera a referência à sala
        selected = null; // Zera o filtro selecionado

        roomName.setText(""); // Limpa o nome da sala
        totalSeat.setText(""); // Limpa o total de assentos
        filterContainer.getChildren().clear(); // Remove todos os filtros exibidos
        filterOccupation.getSelectionModel().clearSelection(); // Limpa seleção da ComboBox
    }

    /**
     * Inicializa o controlador.
     *
     * @param url URL de localização do arquivo FXML, se necessário.
     * @param rb Conjunto de recursos localizados, se necessário.
     */
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

    /**
     * tualiza a interface específica da sala.
     */
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

    /**
     * Adiciona um filtro para a ocupação de salas.
     */
    public void addFilter(){
        filter.add("Filme");
        filter.add("Data");
        filter.add("Horário de Sessão");

        items = FXCollections.observableArrayList(filter);
        filterOccupation.setItems(items);

        filterOccupation.setOnAction(event -> {
            selected = filterOccupation.getValue();
            System.out.println("Selecionado: " + selected);

            if (room != null) {
                showFilter(); // chamada única e genérica
            } else {
                System.err.println("Erro: Não foi possível aplicar filtro, 'room' é nulo.");
            }
        });
    }

    /**
     * Mostra os filtros para a sala 1.
     */
    public void showFilter() {
        filterContainer.getChildren().clear();

        if ("Filme".equals(selected)) {
            for (Session session : room.getSessions()) {
                String movieTitle = session.getMovie().getTitle();
                double occupation = ((double)(room.getTotalSeat() - session.getTotalAvailableSeats()) / room.getTotalSeat()) * 100;

                Text titleText = new Text(movieTitle + " ");
                titleText.setFont(Font.font("Arial", FontWeight.BOLD, 18));
                titleText.setFill(javafx.scene.paint.Color.web("#f2e8c6"));

                Text occupationText = new Text("- Ocupação Média: " + String.format("%.1f", occupation) + "%\n");
                occupationText.setFont(Font.font("Arial", 18));
                occupationText.setFill(javafx.scene.paint.Color.web("#f2e8c6"));

                TextFlow textFlow = new TextFlow(titleText, occupationText);

                filterContainer.getChildren().add(textFlow);
                System.out.println("Filme " + movieTitle + " adicionado");
            }
        } else if ("Data".equals(selected)) {
            // --- 1. Criação das Abas de Filtro de Data ---
            HBox tabs = new HBox(10); // HBox para agrupar os botões de data
            tabs.setPadding(new Insets(10, 0, 10, 0));

            Button hojeBtn = new Button("Hoje");
            Button semanaBtn = new Button("Esta semana");
            Button mesBtn = new Button("Este mês");
            Button anoBtn = new Button("Este ano");

            // Agrupa os botões em uma lista para facilitar a manipulação dos estilos
            List<Button> tabButtons = List.of(hojeBtn, semanaBtn, mesBtn, anoBtn);

            // Define os estilos para os estados ativo e inativo
            String activeTabStyle = "-fx-background-color: #af0e2c; -fx-text-fill: #f2e8c6; -fx-background-radius: 5; -fx-font-size: 18px;";
            String inactiveTabStyle = "-fx-background-color: transparent; -fx-text-fill: #f2e8c6; -fx-font-size: 16px;";

            // Define o estado inicial (ex: "Hoje" começa ativo)
            hojeBtn.setStyle(activeTabStyle);
            semanaBtn.setStyle(inactiveTabStyle);
            mesBtn.setStyle(inactiveTabStyle);
            anoBtn.setStyle(inactiveTabStyle);

            // --- 2. Criação da Caixa Vermelha com o Resultado ---
            VBox redBox = new VBox(10);
            redBox.setPadding(new Insets(10));
            redBox.setStyle("-fx-background-color: #af0e2c; -fx-background-radius: 10;");

            Text boldText = new Text("Ocupação média (todas as sessões): ");
            boldText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            boldText.setFill(javafx.scene.paint.Color.web("#f2e8c6"));

            double ocupacaoInicial = calcularOcupacaoPara("Hoje"); // Calcula para 'Hoje' (o botão inicial)
            Text ocupacaoText = new Text(String.format("%.1f", ocupacaoInicial) + "%");
            ocupacaoText.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
            ocupacaoText.setFill(javafx.scene.paint.Color.web("#f2e8c6"));

            TextFlow textFlow = new TextFlow(boldText, ocupacaoText);
            redBox.getChildren().add(textFlow);

            // --- 3. Adiciona ação para os botões (usa a redBox que já existe) ---
            for (Button btn : tabButtons) {
                btn.setOnAction(event -> {
                    // Atualiza o estilo dos botões
                    for (Button b : tabButtons) {
                        b.setStyle(inactiveTabStyle);
                    }
                    btn.setStyle(activeTabStyle);
                    btn.setFont(Font.font("Arial", FontWeight.BOLD, 18));

                    // Calcular a ocupação com base no botão clicado
                    String filtroSelecionado = btn.getText();
                    double ocupacaoCalculada = calcularOcupacaoPara(filtroSelecionado);

                    // Atualizar caixa de resultado (redBox)
                    redBox.getChildren().clear();
                    Text boldTextNovo = new Text("Ocupação média (todas as sessões): ");
                    boldTextNovo.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                    boldTextNovo.setFill(javafx.scene.paint.Color.web("#f2e8c6"));

                    Text ocupacaoTextNova = new Text(String.format("%.1f", ocupacaoCalculada) + "%");
                    ocupacaoTextNova.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
                    ocupacaoTextNova.setFill(javafx.scene.paint.Color.web("#f2e8c6"));

                    TextFlow textFlowNovo = new TextFlow(boldTextNovo, ocupacaoTextNova);
                    redBox.getChildren().add(textFlowNovo);

                    System.out.println("Filtro aplicado: " + filtroSelecionado + " | Ocupação média: " + ocupacaoCalculada + "%");
                });
            }

            tabs.getChildren().addAll(tabButtons);

            // --- 4. Adiciona tudo no contêiner principal ---
            filterContainer.getChildren().addAll(tabs, redBox);
        } else if ("Horário de Sessão".equals(selected)) {
            Label labelAux = new Label("Horário");
            labelAux.setStyle("-fx-font-family: Arial; -fx-text-fill: #f2e8c6;");
            filterContainer.getChildren().add(labelAux);
        }
    }

    private double calcularOcupacaoPara(String filtro) {
        if (room == null || room.getSessions().isEmpty()) return 0.0;

        LocalDate hoje = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // ajuste o formato se necessário

        int totalIngressosVendidos = 0;
        int totalIngressosOferecidos = 0;

        for (Session session : room.getSessions()) {
            LocalDate dataSessao;
            try {
                dataSessao = LocalDate.parse(session.getDate(), formatter);
            } catch (DateTimeParseException e) {
                System.err.println("Formato de data inválido na sessão: " + session.getDate());
                continue;
            }

            boolean incluir = false;

            switch (filtro) {
                case "Hoje":
                    incluir = dataSessao.equals(hoje);
                    break;
                case "Esta semana":
                    incluir = dataSessao.isAfter(hoje.with(java.time.DayOfWeek.MONDAY).minusDays(1)) &&
                            dataSessao.isBefore(hoje.with(java.time.DayOfWeek.SUNDAY).plusDays(1));
                    break;
                case "Este mês":
                    incluir = dataSessao.getMonth() == hoje.getMonth() &&
                            dataSessao.getYear() == hoje.getYear();
                    break;
                case "Este ano":
                    incluir = dataSessao.getYear() == hoje.getYear();
                    break;
            }

            if (incluir) {
                int vendidos = room.getTotalSeat() - session.getTotalAvailableSeats();
                totalIngressosVendidos += vendidos;
                totalIngressosOferecidos += room.getTotalSeat();
            }
        }

        if (totalIngressosOferecidos == 0) return 0.0;

        return ((double) totalIngressosVendidos / totalIngressosOferecidos) * 100.0;
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
