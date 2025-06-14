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
 * Classe responsável por controlar a tela de cadastro de um filme.
 * @author Gabryelle Beatriz Duarte Moraes
 * @since 01/06/2024
 * @version 2.0
 */
public class RegisterMovieController {

    @FXML private TextField titleField;
    @FXML private TextField genreField;
    @FXML private TextField durationField;
    @FXML private TextField ratingField;
    @FXML private TextField synopsisField;

    private final MovieRepository repository = new MovieRepository();

    @FXML
    private void handleSubmit() {
        try {
            String title = titleField.getText();
            String genre = genreField.getText();
            int duration = Integer.parseInt(durationField.getText());
            String rating = ratingField.getText();
            String synopsis = synopsisField.getText();

            Movie movie = new Movie(title, genre, duration, rating, synopsis);
            repository.add(movie);

            mostrarPopUp("cadastrado");

        } catch (Exception e) {
            e.printStackTrace(); // substitua por pop-up de erro se necessário
        }
    }

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