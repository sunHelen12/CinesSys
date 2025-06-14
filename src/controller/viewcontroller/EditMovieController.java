package controller.viewcontroller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import models.Movie;
import repository.MovieRepository;

/**
 * Classe responsável por controlar a tela de alteração de um cliente.
 * @author
 * @since
 * @version
 */
public class EditMovieController {

    @FXML private TextField titleField;
    @FXML private TextField genreField;
    @FXML private TextField durationField;
    @FXML private TextField ratingField;
    @FXML private TextField synopsisField;

    private Movie selectedMovie;
    private final MovieRepository repository = new MovieRepository();

    /**
     * seta os dados do filme selecionado
     * 
     * @param movie
     */
    public void setMovie(Movie movie) {
        this.selectedMovie = movie;
        titleField.setText(movie.getTitle());
        genreField.setText(movie.getGenre());
        durationField.setText(String.valueOf(movie.getDuration()));
        ratingField.setText(movie.getClassification());
        synopsisField.setText(movie.getSynopsis());
    }

    /**
     * Salva as alterações do filme
     */
    @FXML
    private void handleEdit() {
        try {
            selectedMovie.setTitle(titleField.getText());
            selectedMovie.setGenre(genreField.getText());
            selectedMovie.setDuration(Integer.parseInt(durationField.getText()));
            selectedMovie.setClassification(ratingField.getText());
            selectedMovie.setSynopsis(synopsisField.getText());

            mostrarPopUp("alterado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Mostra uma janela de confirmação
     * 
     * @param acao
     */
    private void mostrarPopUp(String acao) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/PopUpMovies.fxml"));
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
}