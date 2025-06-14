package controller.viewcontroller;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import models.Movie;
import javafx.event.ActionEvent;
import controller.business.MovieController;


/**
 * Classe responsável por controlar a tela de cadastro de um filme.
 * 
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

    /**
     * Reseta a tela de controle de filmes.
     * 
     * @param event evento de clique no botão de voltar
     */
    @FXML
    void backMovieControl(ActionEvent event) {
        MainViews.changeScreen("movieControl", null);
    }

    /**
     * Cadastra um filme no sistema.
     * 
     * @param event evento de clique no botão de cadastrar filme
     */
    @FXML
    void registerMovie(ActionEvent event) {
        String title = titleField.getText().trim();
        String genre = genreField.getText().trim();
        String duration = durationField.getText().trim();
        int drtn = Integer.parseInt(duration);
        String classification = ratingField.getText().trim();
        String synopsis = synopsisField.getText().trim();

        if (title.isEmpty() || genre.isEmpty() || duration.isEmpty() || classification.isEmpty() || synopsis.isEmpty()) {
            return;
        } else {
            MovieController.addMovie(title, genre, drtn, classification, synopsis);
            titleField.clear();
            genreField.clear();
            durationField.clear();
            ratingField.clear();
            synopsisField.clear();
            MovieControlController.mostrarPopUp("cadastrado");
        }
    }
}