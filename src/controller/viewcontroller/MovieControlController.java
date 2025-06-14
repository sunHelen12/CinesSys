package controller.viewcontroller;

import controller.business.MovieController;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.stage.Stage;
import models.Movie;
import structures.list.GenericDynamicList;
import javafx.fxml.Initializable;
import java.util.ArrayList; 
import java.util.List;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Classe responsável por controlar a tela de alteração de um cliente.
 * @author Gabryelle Beatriz Duarte Moraes
 * 
 * @since 14/06/2024
 * @version 1.0
 */
public class MovieControlController implements Initializable, MainViews.OnChangeScreen {

    /**
     * Inicializa o controlador da tela de controle de filmes.
     */
    @FXML
    private TableView<Movie> movieTable;
    @FXML
    private TableColumn<Movie, Boolean> selectColumn;
    @FXML
    private TableColumn<Movie, String> titleColumn;
    @FXML
    private TableColumn<Movie, String> genreColumn;
    @FXML
    private TableColumn<Movie, String> durationColumn;
    @FXML
    private TableColumn<Movie, String> ratingColumn;
    @FXML
    private TableColumn<Movie, String> synopsisColumn;

    private final ObservableList<Movie> selectedMovies = FXCollections.observableArrayList();
    private ObservableList<Movie> moviesForTable;

    /**
     * Inicializa o controlador.
     * 
     * @param url URL de localização do arquivo FXML, se necessário.
     * @param resourceBundle Conjunto de recursos localizados, se necessário.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        moviesForTable = FXCollections.observableArrayList();

        titleColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTitle()));
        genreColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getGenre()));
        ratingColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getClassification()));
        synopsisColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getSynopsis()));

        durationColumn.setCellValueFactory(cell -> {
            int durationMin = cell.getValue().getDuration();
            double durationHours = durationMin / 60.0;
            return new SimpleStringProperty(String.format("%.2f", durationHours));
        });

        selectColumn.setCellValueFactory(cellData -> {
            Movie movie = cellData.getValue();
            SimpleBooleanProperty selected = new SimpleBooleanProperty(selectedMovies.contains(movie));
            selected.addListener((obs, wasSelected, isNowSelected) -> {
                if (isNowSelected) {
                    selectedMovies.add(movie);
                } else {
                    selectedMovies.remove(movie);
                }
            });
            return selected;
        });
        selectColumn.setCellFactory(CheckBoxTableCell.forTableColumn(selectColumn));

        MainViews.addOnChangeScreenListener(this);
    }

    @Override
    public void onScreenChanged(String newScreen, Object userDataObject) {
        if (newScreen.equals("movieControl")) {
            refreshTable();
        }
    }

    /**
     * Atualiza a tabela de filmes.
     */
    private void refreshTable() {
        moviesForTable.clear();
        GenericDynamicList<Movie> currentMovies = MovieController.getAllMovies();

        if (currentMovies != null) {
            for (Movie movie : currentMovies) {
                moviesForTable.add(movie);
            }
        }
        movieTable.setItems(moviesForTable);
    }

    /**
     * Mostra uma janela de confirmação após a ação de exclusão.
     * * @param acao Ação realizada.
     */
    public static void mostrarPopUp(String acao) {
        try {
            FXMLLoader loader = new FXMLLoader(MovieControlController.class.getResource("/gui/PopUpMovies.fxml"));
            Parent root = loader.load();

            PopUpMovieController controller = loader.getController();
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

    /**
     * Método chamado quando o botão "Cadastrar Filme" é clicado e muda para a tela de cadastro de filmes.
     * @param event evento de clique do botão
     */
    @FXML
    void registerMovie(ActionEvent event) {
        MainViews.changeScreen("registerMovie", null);
    }

    /**
     * Método chamado quando o botão "Excluir Filme" é clicado e chama o método handleDelete.
     * @param event evento de clique do botão
     */
    @FXML
    void deleteMovie(ActionEvent event) {
        if (selectedMovies.isEmpty()) {
            System.out.println("Nenhum filme selecionado para exclusão.");
            return;
        }
        List<Movie> moviesToDelete = new ArrayList<>(selectedMovies);
        for (Movie movie : moviesToDelete) {
            MovieController.removeMovieById(movie.getId());
        }
        selectedMovies.clear();
        refreshTable();
        mostrarPopUp("excluído");
    }

    /**
     * Método chamado quando o botão "Editar" é clicado.
     * @param event evento de clique do botão
     */
    @FXML
    void editMovie(ActionEvent event) {
        if (!selectedMovies.isEmpty()) {
            Movie movieToEdit = selectedMovies.get(0);
            MainViews.changeScreen("movieEdit", movieToEdit);
            mostrarPopUp("alterado");
        }
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
    void openPurchaseHistory(ActionEvent event) {
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
        // MainViews.changeScreen("sessionControl", null);
    }
}