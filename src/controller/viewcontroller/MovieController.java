package controller.viewcontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import models.Movie;
import repository.MovieRepository;

public class MovieController {

    @FXML private TableView<Movie> movieTable;
    @FXML private TableColumn<Movie, String> titleColumn;
    @FXML private TableColumn<Movie, String> genreColumn;
    @FXML private TableColumn<Movie, Integer> durationColumn;
    @FXML private TableColumn<Movie, String> ratingColumn;
    @FXML private TableColumn<Movie, String> synopsisColumn;

    private final MovieRepository repository = new MovieRepository();


    @FXML
    public void initialize() {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("classification"));
        synopsisColumn.setCellValueFactory(new PropertyValueFactory<>("synopsis"));

        refreshTable();
    }

    private void refreshTable() {
        ObservableList<Movie> movies = FXCollections.observableArrayList(repository.getAll());
        movieTable.setItems(movies);
    }

    @FXML
    private void handleDelete() {
        Movie selected = movieTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            repository.removeById(selected.getId());
            refreshTable();
            mostrarPopUp("excluído");
        }
    }

    private void mostrarPopUp(String acao) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/pop_up.fxml"));
            Parent root = loader.load();

            PopUpController controller = loader.getController();
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
}

