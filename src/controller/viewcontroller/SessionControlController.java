package controller.viewcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import models.Session;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import controller.business.SessionController;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javafx.stage.Stage;
import controller.viewcontroller.PopUpSessionController;
import structures.list.GenericDynamicList; 
import java.util.stream.Collectors;

public class SessionControlController implements Initializable, MainViews.OnChangeScreen {

    @FXML
    private TableView<Session> table;
    @FXML
    private TableColumn<Session, Boolean> selectColumn;
    @FXML
    private TableColumn<Session, String> classification;
    @FXML
    private TableColumn<Session, String> room;
    @FXML
    private TableColumn<Session, String> duration;
    @FXML
    private TableColumn<Session, String> movieName;
    @FXML
    private TableColumn<Session, String> numberSeats;

    @FXML
    private Label date1;
    @FXML
    private Label date2;
    @FXML
    private Label date3;
    @FXML
    private Label date4;

    private final ObservableList<Session> selectedSessions = FXCollections.observableArrayList();
    private ObservableList<Session> sessionsForTable;
    private final Map<Session, SimpleBooleanProperty> sessionSelectionMap = new HashMap<>();
    private List<Session> allSessions; 

    /**
     * Inicializa o controlador.
     *
     * @param url            URL de localização do arquivo FXML, se necessário.
     * @param rb Conjunto de recursos localizados, se necessário.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");
        LocalDate dt1 = LocalDate.now();

        LocalDate dt2 = dt1.plusDays(1);
        LocalDate dt3 = dt1.plusDays(2);
        LocalDate dt4 = dt1.plusDays(3);

        date1.setText(dt1.format(formatter));
        date2.setText(dt2.format(formatter));
        date3.setText(dt3.format(formatter));
        date4.setText(dt4.format(formatter));

        sessionsForTable = FXCollections.observableArrayList();

        room.setCellValueFactory(cell -> new SimpleStringProperty(String.valueOf((cell.getValue().getRoom().getId()))));
        numberSeats.setCellValueFactory(
                cell -> new SimpleStringProperty(String.valueOf(cell.getValue().getRoom().getTotalSeat())));
        classification
                .setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getMovie().getClassification()));
        movieName.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getMovie().getTitle()));
        duration.setCellValueFactory(cell -> {
            int durationMin = cell.getValue().getMovie().getDuration();
            double durationHours = durationMin / 60.0;
            return new SimpleStringProperty(String.format("%.2f", durationHours));
        });

        selectColumn.setCellValueFactory(cellData -> {
            Session session = cellData.getValue();
            SimpleBooleanProperty selectedProp = sessionSelectionMap.computeIfAbsent(session, k -> {
                SimpleBooleanProperty prop = new SimpleBooleanProperty(false);
                prop.addListener((obs, oldValue, newValue) -> {
                    if (newValue) {
                        selectedSessions.add(session);
                    } else {
                        selectedSessions.remove(session);
                    }
                });
                return prop;
            });
            selectedProp.set(selectedSessions.contains(session));
            return selectedProp;
        });

        selectColumn.setCellFactory(column -> new TableCell<Session, Boolean>() {
            private final CheckBox checkBox = new CheckBox();
            private SimpleBooleanProperty currentProp;
            {
                checkBox.setOnAction(event -> {
                    if (currentProp != null) {
                        currentProp.set(checkBox.isSelected());
                    }
                });
            }

            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                    setGraphic(null);
                    if (currentProp != null) {
                        checkBox.selectedProperty().unbindBidirectional(currentProp);
                        currentProp = null;
                    }
                } else {
                    Session session = getTableRow().getItem();
                    SimpleBooleanProperty propFromMap = sessionSelectionMap.get(session);

                    if (propFromMap != null) {
                        if (currentProp != null) {
                            checkBox.selectedProperty().unbindBidirectional(currentProp);
                        }
                        currentProp = propFromMap;
                        checkBox.selectedProperty().bindBidirectional(currentProp);
                        setGraphic(checkBox);
                    } else {
                        setGraphic(null);
                    }
                }
            }
        });

        MainViews.addOnChangeScreenListener(this);
        GenericDynamicList<Session> tempGenericList = SessionController.getAllSessions();
        if (tempGenericList != null) {
            this.allSessions = new ArrayList<>();
            for (Session session : tempGenericList) {
                this.allSessions.add(session);
            }
        } else {
            this.allSessions = new ArrayList<>(); 
        }
        refreshTable();
    }

    /**
     * Chama o refreshTable() toda vez que a tela for chamada
     *
     * @param newScreen      tela de mudança
     * @param userDataObject dados passados
     */
    @Override
    public void onScreenChanged(String newScreen, Object userDataObject) {
        if (newScreen.equals("sessionControl")) {
            GenericDynamicList<Session> tempGenericList = SessionController.getAllSessions();
            if (tempGenericList != null) {
                this.allSessions = new ArrayList<>();
                for (Session session : tempGenericList) {
                    this.allSessions.add(session);
                }
            } else {
                this.allSessions = new ArrayList<>();
            }
            refreshTable();
        }
    }

    private void refreshTable() {
        refreshTable(this.allSessions != null ? this.allSessions : new ArrayList<>());
    }

    /**
     * Atualiza a tabela de sessões.
     */
    private void refreshTable(List<Session> sessionsToDisplay) {
        List<Session> currentlySelectedCopy = new ArrayList<>(selectedSessions);
        selectedSessions.clear();
        sessionsForTable.clear();

        if (sessionsToDisplay != null) {
            for (Session session : sessionsToDisplay) {
                sessionsForTable.add(session);

                SimpleBooleanProperty prop = sessionSelectionMap.computeIfAbsent(session, k -> {
                    SimpleBooleanProperty newProp = new SimpleBooleanProperty(false);
                    newProp.addListener((obs, oldValue, newValue) -> {
                        if (newValue) {
                            selectedSessions.add(session);
                        } else {
                            selectedSessions.remove(session);
                        }
                    });
                    return newProp;
                });

                boolean wasSelected = currentlySelectedCopy.contains(session);
                if (prop.get() != wasSelected) {
                    prop.set(wasSelected);
                }

                if (prop.get()) {
                    selectedSessions.add(session);
                }
            }
        }
        table.setItems(sessionsForTable);
        table.refresh();
    }

    /**
     * Filtra e exibe as sessões para a data fornecida.
     *
     * @param date A data para filtrar as sessões.
     */
    private void filterAndDisplaySessions(LocalDate date) {
        if (this.allSessions == null || this.allSessions.isEmpty()) { 
            GenericDynamicList<Session> tempGenericList = SessionController.getAllSessions();
            if (tempGenericList != null) {
                this.allSessions = new ArrayList<>();
                for (Session session : tempGenericList) {
                    this.allSessions.add(session);
                }
            } else {
                this.allSessions = new ArrayList<>();
            }
        }
        if (this.allSessions != null) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            List<Session> filteredSessions = this.allSessions.stream()
                    .filter(session -> {
                        try {
                            return LocalDate.parse(session.getDate(), dateFormatter).equals(date);
                        } catch (DateTimeParseException e) {
                            System.err.println(
                                    "Erro ao parsear data da sessão '" + session.getDate() + "': " + e.getMessage());
                            return false; 
                        }
                    })
                    .collect(Collectors.toList());
            refreshTable(filteredSessions);
        } else {
            refreshTable(new ArrayList<>());
        }
    }

    /**
     * Mostra uma janela de confirmação após a ação de exclusão.
     * * @param acao Ação realizada.
     */
    public static void mostrarPopUp(String acao) {
        try {
            FXMLLoader loader = new FXMLLoader(SessionControlController.class.getResource("/gui/PopUpSession.fxml"));
            Parent root = loader.load();

            PopUpSessionController controller = loader.getController();
            Stage stage = new Stage();
            controller.setStage(stage);
            controller.setMensagemPersonalizada(acao);

            stage.setScene(new Scene(root));
            stage.setTitle("Confirmação");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void registerSession(ActionEvent event) {
        MainViews.changeScreen("registerSession", null);
    }

    @FXML
    void changeSession(ActionEvent event) {

    }

    @FXML
    void deleteSession(ActionEvent event) {

    }

    /**
     * Método que abre a Tela Principal.
     *
     * @param event Evento ao apertar o botão, caso necessário.
     */
    @FXML
    void openHomeScreen(ActionEvent event) {
        MainViews.changeScreen("homeScreen", null);
    }

    /**
     * Método que abre a Tela de Controle de Clientes.
     *
     * @param event Evento ao apertar o botão, caso necessário.
     */
    @FXML
    void openClientControl(ActionEvent event) {
        MainViews.changeScreen("clientControl", null);
    }

    /**
     * Método que abre a Tela de Controle de Filmes.
     *
     * @param event Evento ao apertar o botão, caso necessário.
     */
    @FXML
    void openMovieControl(ActionEvent event) {
        MainViews.changeScreen("movieControl", null);
    }

    /**
     * Método que abre a Tela de Histórico de Compras.
     *
     * @param event Evento ao apertar o botão, caso necessário.
     */
    @FXML
    void openPurchaseRelatory(ActionEvent event) {
        MainViews.changeScreen("purchaseRelatory", null);
    }

    /**
     * Método que abre a Tela de Ocupação de Salas.
     *
     * @param event Evento ao apertar o botão, caso necessário.
     */
    @FXML
    void openRoomOccupation(ActionEvent event) {
        MainViews.changeScreen("roomOccupation", null);
    }

    /**
     * Método que abre a Tela de Controle de Sessões.
     *
     * @param event Evento ao apertar o botão, caso necessário.
     */
    @FXML
    void openSessionControl(ActionEvent event) {
        MainViews.changeScreen("sessionControl", null);
    }

    @FXML
    void openDate1(ActionEvent event) {
        filterAndDisplaySessions(LocalDate.now());
    }

    @FXML
    void openDate2(ActionEvent event) {
        filterAndDisplaySessions(LocalDate.now().plusDays(1));
    }

    @FXML
    void openDate3(ActionEvent event) {
        filterAndDisplaySessions(LocalDate.now().plusDays(2));
    }

    @FXML
    void openDate4(ActionEvent event) {
        filterAndDisplaySessions(LocalDate.now().plusDays(3));
    }
}