package controller.viewcontroller;

import java.net.URL;
import java.util.ResourceBundle;

import controller.business.MovieController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import models.Movie;

/**
 * Classe responsável por controlar a tela de alteração de um Filme.
 * @author Gabryelle Beatriz Duarte Moraes
 * @since 14/06/2024
 * @version 1.0
 */
public class EditMovieController implements Initializable{

    @FXML private TextField titleField;
    @FXML private TextField genreField;
    @FXML private TextField durationField;
    @FXML private TextField ratingField;
    @FXML private TextField synopsisField;

    private static Movie selectedMovie;
    
    /**
     * Inicializa o controlador.
     * 
     * @param url URL de localização do arquivo FXML, se necessário.
     * @param resourceBundle Conjunto de recursos localizados, se necessário.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MainViews.addOnChangeScreenListener(new MainViews.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object userDataObject) {
                if (userDataObject instanceof Movie) {
                   selectedMovie = (Movie) userDataObject;
                    titleField.setText(selectedMovie.getTitle());
                    genreField.setText(selectedMovie.getGenre());
                    durationField.setText(String.valueOf(selectedMovie.getDuration())); 
                    ratingField.setText(selectedMovie.getClassification()); 
                    synopsisField.setText(selectedMovie.getSynopsis());
                }
            }
        });
    }
   
    /**
     * Método que é chamado quando o botão "Voltar" é clicado e retorna para a tela anterior.
     * 
     * @param event Evento de ação do botão.
     */
    void backMovieControl(ActionEvent event) {
        MainViews.changeScreen("movieControl", null);
    }

    /**
     * Método que é chamado quando o botão "Editar" é clicado e edita as informações do Filme.
     * 
     * @param event Evento de ação do botão.
     */
    @FXML
    void edit(ActionEvent event) {
        String title = titleField.getText().trim();
        String genre = genreField.getText().trim();
        String duration = durationField.getText().trim();
        int drtn = Integer.parseInt(duration);
        String classification = ratingField.getText().trim();
        String synopsis = synopsisField.getText().trim();

        MovieController.updateMovie(selectedMovie.getId(), title, genre, drtn, classification, synopsis);
        titleField.clear();
        genreField.clear();
        durationField.clear();
        ratingField.clear();
        synopsisField.clear();
        MovieControlController.mostrarPopUp("alterado");
    }
}