package controller.viewcontroller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;

public class MainViews extends Application {
    private static Stage stage;
    private static Scene homeScreenScene;
    private static Scene changeClientScene;
    private static Scene clientControlScene;
    private static Scene clientHistoryScene;
    private static Scene movieControlScene;
    private static Scene movieEditScene;
    private static Scene occupationRelatoryScene;
    private static Scene popUpClientScene;
    private static Scene popUpMoviesScene;
    private static Scene purchaseRelatoryScene;
    private static Scene registerClientScene;
    private static Scene registerMovieScene;
    private static Scene roomOccupationScene;
    private static Scene purchaseRecordScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        primaryStage.setTitle("CineSys");
        
        FXMLLoader loaderHomeScreen = new FXMLLoader(getClass().getResource("/gui/HomeScreen.fxml"));
        FXMLLoader loaderChangeClient = new FXMLLoader(getClass().getResource("/gui/ChangeClient.fxml"));
        FXMLLoader loaderClientControl = new FXMLLoader(getClass().getResource("/gui/ClientControl.fxml"));
        FXMLLoader loaderClientHistory = new FXMLLoader(getClass().getResource("/gui/ClientHistory.fxml"));
        FXMLLoader loaderMovieControl = new FXMLLoader(getClass().getResource("/gui/MovieControl.fxml"));
        FXMLLoader loaderMovieEdit = new FXMLLoader(getClass().getResource("/gui/MovieEdit.fxml"));
        FXMLLoader loaderOccupationRelatory = new FXMLLoader(getClass().getResource("/gui/OccupationRelatory.fxml"));
        FXMLLoader loaderPopUpClient = new FXMLLoader(getClass().getResource("/gui/PopUpClient.fxml"));
        FXMLLoader loaderPopUpMovies = new FXMLLoader(getClass().getResource("/gui/PopUpMovies.fxml"));
        FXMLLoader loaderPurchaseRelatory = new FXMLLoader(getClass().getResource("/gui/PurchaseRelatory.fxml"));
        FXMLLoader loaderRegisterClient = new FXMLLoader(getClass().getResource("/gui/RegisterClient.fxml"));
        FXMLLoader loaderRegisterMovie = new FXMLLoader(getClass().getResource("/gui/RegisterMovie.fxml"));
        FXMLLoader loaderRoomOccupation = new FXMLLoader(getClass().getResource("/gui/RoomOccupation.fxml"));
        FXMLLoader loaderPurchaseRecord = new FXMLLoader(getClass().getResource("/gui/PurchaseRecord.fxml"));

        Parent homeScreen = loaderHomeScreen.load();
        homeScreenScene = new Scene(homeScreen);
        
        Parent changeClient = loaderChangeClient.load();
        changeClientScene = new Scene(changeClient);

        Parent clientControl = loaderClientControl.load();
        clientControlScene = new Scene(clientControl);

        Parent clientHistory = loaderClientHistory.load();
        clientHistoryScene = new Scene(clientHistory);

        Parent movieControl = loaderMovieControl.load();
        movieControlScene = new Scene(movieControl);

        Parent movieEdit = loaderMovieEdit.load();
        movieEditScene = new Scene(movieEdit);

        Parent occupationRelatory = loaderOccupationRelatory.load();
        occupationRelatoryScene = new Scene(occupationRelatory);

        Parent popUpClient = loaderPopUpClient.load();
        popUpClientScene = new Scene(popUpClient);

        Parent popUpMovies = loaderPopUpMovies.load();
        popUpMoviesScene = new Scene(popUpMovies);

        Parent purchaseRelatory = loaderPurchaseRelatory.load();
        purchaseRelatoryScene = new Scene(purchaseRelatory);

        Parent registerClient = loaderRegisterClient.load();
        registerClientScene = new Scene(registerClient);

        Parent registerMovie = loaderRegisterMovie.load();
        registerMovieScene = new Scene(registerMovie);

        Parent roomOccupation = loaderRoomOccupation.load();
        roomOccupationScene = new Scene(roomOccupation);

        Parent purchaseRecord = loaderPurchaseRecord.load();
        purchaseRecordScene = new Scene(purchaseRecord);
        
        primaryStage.setScene(homeScreenScene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }    

    public static void changeScreen(String screen, Object userDataObject){
        switch (screen) {
            case "changeClient":
                stage.setScene(changeClientScene);
                notifyAllListerners("changeClient", userDataObject);
                break;
            case "purchaseRecord":
                stage.setScene(purchaseRecordScene);
                notifyAllListerners("purchaseRecord", userDataObject);
              break;
            case "clientControl":
                stage.setScene(clientControlScene);
                notifyAllListerners("clientControl", userDataObject);
                break;
            case "clientHistory":
                stage.setScene(clientHistoryScene);
                notifyAllListerners("clientHistory", userDataObject);
                break;
            case "homeScreen":
                stage.setScene(homeScreenScene);
                notifyAllListerners("homeScreen", userDataObject);
                break;
            case "movieControl":
                stage.setScene(movieControlScene);
                notifyAllListerners("movieControl", userDataObject);
                break;
            case "movieEdit":
                stage.setScene(movieEditScene);
                notifyAllListerners("movieEdit", userDataObject);
                break;
            case "occupationRelatory":
                stage.setScene(occupationRelatoryScene);
                notifyAllListerners("occupationRelatory", userDataObject);
                break;
            case "popUpClient":
                stage.setScene(popUpClientScene);
                notifyAllListerners("popUpClient", userDataObject);
                break;
            case "popUpMovies":
                stage.setScene(popUpMoviesScene);
                notifyAllListerners("popUpMovies", userDataObject);
                break;
            case "purchaseRelatory":
                stage.setScene(purchaseRelatoryScene);
                notifyAllListerners("purchaseRelatory", userDataObject);
                break;
            case "registerClient":
                stage.setScene(registerClientScene);
                notifyAllListerners("registerClient", userDataObject);
                break;
            case "registerMovie":
                stage.setScene(registerMovieScene);
                notifyAllListerners("registerMovie", userDataObject);
                break;
            case "roomOccupation":
                stage.setScene(roomOccupationScene);
                notifyAllListerners("roomOccupation", userDataObject);
                break;
            default:
                break;
        }        
    }

    public static void main(String[] args) {
        launch(args);
    }

    //Processamento de Dados na Troca de Tela
    private static List<OnChangeScreen> listeners = new ArrayList<>();

    public static interface OnChangeScreen{
        void onScreenChanged(String newScreen, Object userDataObject);
    }

    public static void addOnChangeScreenListener(OnChangeScreen newListener){
        listeners.add(newListener);
    }

    private static void notifyAllListerners(String newScreen, Object userDataObject){
        for(OnChangeScreen l:listeners)
            l.onScreenChanged(newScreen, userDataObject);
    }
}

//compilar
// javac -d out --module-path "C:\javafx-sdk-21.0.7\lib" --add-modules javafx.controls,javafx.fxml src/controller/viewcontroller/MainViews.java
//executar
// java --module-path "C:\javafx-sdk-21.0.7\lib" --add-modules javafx.controls,javafx.fxml -cp "out;src" controller.viewcontroller.MainViews

//compilar tudo
// javac -d out --module-path "C:\javafx-sdk-21.0.7\lib" --add-modules javafx.controls,javafx.fxml (Get-ChildItem -Recurse -Filter *.java -Path src).FullName
