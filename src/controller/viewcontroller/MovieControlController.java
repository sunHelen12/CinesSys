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
import structures.list.GenericDynamicList; // Importação adicionada para GenericDynamicList
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Classe responsável por controlar a tela de alteração de um cliente.
 * 
 * @author Gabryelle Beatriz Duarte Moraes
 * @since 14/06/2024
 * @version 1.0
 */
public class MovieControlController implements Initializable {

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

    // Esta é a lista de filmes que você já está buscando
    GenericDynamicList<Movie> movies = MovieController.getAllMovies();
    private final ObservableList<Movie> selectedMovies = FXCollections.observableArrayList();
    // Esta será a ObservableList que a tabela usará para exibir todos os filmes.
    private ObservableList<Movie> moviesForTable; // Removido 'final' para inicializar no initialize

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicializa a ObservableList que será usada pela tabela
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
            // Inicializa o checkbox com base se o filme já está na lista de selecionados
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

        refreshTable(); // Chama refreshTable para popular a tabela quando a tela carrega
    }

    private void refreshTable() {
        // Limpa a lista da tabela para evitar duplicatas ao recarregar
        moviesForTable.clear();
        // Adiciona todos os filmes da sua GenericDynamicList 'movies' na ObservableList
        // da tabela
        // Assumindo que GenericDynamicList é Iterable ou tem um método paraToList()
        for (Movie movie : movies) { // Se GenericDynamicList implementa Iterable
            moviesForTable.add(movie);
        }
        // OU, se GenericDynamicList tem um método para converter para List:
        // moviesForTable.addAll(movies.toArrayList()); // Exemplo se o método for
        // 'toArrayList'
        // moviesForTable.addAll(movies.toList()); // Exemplo se o método for 'toList'

        // Define a ObservableList preenchida como a fonte de dados da tabela
        movieTable.setItems(moviesForTable);
    }

    @FXML
    private void handleDelete() {
        // Itera sobre os filmes selecionados para processar a exclusão
        for (Movie movie : selectedMovies) {
            // A linha abaixo apenas busca o filme, não o exclui do MovieController.
            // Você precisará de um método de exclusão no MovieController, por exemplo:
            // MovieController.deleteMovie(movie.getId());
            System.out.println("Simulando exclusão do filme com ID: " + movie.getId() + " - " + movie.getTitle());
            // Após a exclusão bem-sucedida no backend, remove da lista exibida na tabela
            moviesForTable.remove(movie);
        }
        selectedMovies.clear(); // Limpa a lista de filmes selecionados após a operação
        // Não é necessário chamar refreshTable() aqui se você remove diretamente de
        // moviesForTable,
        // pois a ObservableList já notificará a tabela.
        mostrarPopUp("excluído(s)");
    }

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

    @FXML
    void registerMovie(ActionEvent event) {
        MainViews.changeScreen("registerMovie", null);
    }

    @FXML
    void deleteMovie(ActionEvent event) {
        handleDelete(); // Chama o método handleDelete quando o botão de exclusão é clicado
    }

    @FXML
    void editMovie(ActionEvent event) {
        // Lógica para edição:
        if (!selectedMovies.isEmpty()) {
            Movie movieToEdit = selectedMovies.get(0); // Pega o primeiro filme selecionado
            // Você pode passar este filme para a tela de edição
            // Exemplo: MainViews.changeScreen("editMovieScreen", movieToEdit);
            System.out.println("Simulando edição do filme: " + movieToEdit.getTitle());
            mostrarPopUp("editado(s)");
        } else {
            System.out.println("Nenhum filme selecionado para editar.");
            // Opcional: mostrar um pop-up avisando para selecionar um filme
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
     * Método que abre a Tela de Controloe de Clientes.
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