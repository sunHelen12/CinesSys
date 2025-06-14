package controller.viewcontroller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import models.*;
import controller.business.MovieController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class EditMovieController implements Initializable{

    @FXML private TextField titleField;
    @FXML private TextField genreField;
    @FXML private TextField durationField;
    @FXML private TextField ratingField;
    @FXML private TextField synopsisField;

    private Movie selectedMovie;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MainViews.addOnChangeScreenListener(new MainViews.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object userDataObject) {
                if (userDataObject instanceof Movie) {
                   selectedMovie = (Movie) userDataObject;
                }

            }
        });
    }

    @FXML
    void backMovieControl(ActionEvent event) {
        MainViews.changeScreen("movieControl", null);
    }

    @FXML
    void edit(ActionEvent event) {
        String title = titleField.getText().trim();
        String genre = genreField.getText().trim();
        String duration = durationField.getText().trim();
        int drtn = Integer.parseInt(duration);
        String classificatio = ratingField.getText().trim();
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