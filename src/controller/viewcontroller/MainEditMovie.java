package controller.viewcontroller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
public class MainEditMovie extends Application{
		 @Override
		    public void start(Stage primaryStage) throws Exception {
			 	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MovieEdit.fxml"));
			 	Parent root = loader.load();
		        primaryStage.setTitle("CineSys - Filmes");
		        primaryStage.setScene(new Scene(root));
		        primaryStage.show();
		    }

		    public static void main(String[] args) {
		        launch(args);
		    }

	}

