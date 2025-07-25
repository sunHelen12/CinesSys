package controller.viewcontroller;

import controller.business.RoomController;
import controller.business.SessionController;
import controller.business.TicketController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import controller.business.ClientController;
import services.SessionService;
import services.TicketService;

/**
 * Classe da tela principal do sistema onde é possível navegar entre as telas.
 * @author Maria Eduarda Campos
 * @author Vinicius Nunes de Andrade
 * @since 31-05-2025
 * @version 2
 */
public class MainViews extends Application {
    private static Stage stage;
    private static Scene homeScreenScene;
    private static Scene changeClientScene;
    private static Scene clientControlScene;
    private static Scene clientHistoryScene;
    private static Scene movieControlScene;
    private static Scene movieEditScene;
    private static Scene occupationRelatoryScene;
    private static Scene payPerSessionScene;
    private static Scene popUpClientScene;
    private static Scene popUpMoviesScene;
    private static Scene purchaseRecordScene;
    private static Scene purchaseRelatoryScene;
    private static Scene registerClientScene;
    private static Scene registerMovieScene;
    private static Scene registerSessionScene;
    private static Scene roomOccupationScene;
    private static Scene sellTicketScene;
    private static Scene sessionControlScene;
    private static Scene updateSessionScene;
    private static Scene popUpRegisteredSaleScene;
    private static Scene popUpDiscountScene;
    private static Scene popUpSessionScene;
    private static Scene oversoldScene;

    /**
     * Inicializa a aplicação.
     * * @param primaryStage esta é a tela principal da aplicação.
     * 
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // carregando os dados das entidades
        loadData();
        
        stage = primaryStage;
        primaryStage.setTitle("CineSys");
        saveAndClose(primaryStage);

        FXMLLoader loaderHomeScreen = new FXMLLoader(getClass().getResource("/gui/HomeScreen.fxml"));
        FXMLLoader loaderChangeClient = new FXMLLoader(getClass().getResource("/gui/ChangeClient.fxml"));
        FXMLLoader loaderClientControl = new FXMLLoader(getClass().getResource("/gui/ClientControl.fxml"));
        FXMLLoader loaderClientHistory = new FXMLLoader(getClass().getResource("/gui/ClientHistory.fxml"));
        FXMLLoader loaderMovieControl = new FXMLLoader(getClass().getResource("/gui/MovieControl.fxml"));
        FXMLLoader loaderMovieEdit = new FXMLLoader(getClass().getResource("/gui/MovieEdit.fxml"));
        FXMLLoader loaderPopUpClient = new FXMLLoader(getClass().getResource("/gui/PopUpClient.fxml"));
        FXMLLoader loaderPopUpMovies = new FXMLLoader(getClass().getResource("/gui/PopUpMovies.fxml"));
        FXMLLoader loaderPurchaseRelatory = new FXMLLoader(getClass().getResource("/gui/PurchaseRelatory.fxml"));
        FXMLLoader loaderRegisterClient = new FXMLLoader(getClass().getResource("/gui/RegisterClient.fxml"));
        FXMLLoader loaderRegisterMovie = new FXMLLoader(getClass().getResource("/gui/RegisterMovie.fxml"));
        FXMLLoader loaderRegisterSession = new FXMLLoader(getClass().getResource("/gui/RegisterSession.fxml"));
        FXMLLoader loaderPurchaseRecord = new FXMLLoader(getClass().getResource("/gui/PurchaseRecord.fxml"));
        FXMLLoader loaderSellTicket = new FXMLLoader(getClass().getResource("/gui/SellTicket.fxml"));
        FXMLLoader loaderSessionControl = new FXMLLoader(getClass().getResource("/gui/SessionControl.fxml"));
        FXMLLoader loaderPayPerSession = new FXMLLoader(getClass().getResource("/gui/PayPerSession.fxml"));
        FXMLLoader loaderUpdateSession = new FXMLLoader(getClass().getResource("/gui/UpdateSession.fxml"));
        FXMLLoader loaderPopUpRegisteredSale = new FXMLLoader(getClass().getResource("/gui/PopUpRegisteredSale.fxml"));
        FXMLLoader loaderPopUpSession = new FXMLLoader(getClass().getResource("/gui/PopUpSession.fxml"));
        FXMLLoader loaderOversold = new FXMLLoader(getClass().getResource("/gui/Oversold.fxml"));

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

        Parent registerSession = loaderRegisterSession.load();
        registerSessionScene = new Scene(registerSession);

        Parent sellTicket = loaderSellTicket.load();
        sellTicketScene = new Scene(sellTicket);

        Parent purchaseRecord = loaderPurchaseRecord.load();
        purchaseRecordScene = new Scene(purchaseRecord);

        Parent sessionControl = loaderSessionControl.load();
        sessionControlScene = new Scene(sessionControl);

        Parent payPerSession = loaderPayPerSession.load();
        payPerSessionScene = new Scene(payPerSession);

        Parent updateSession = loaderUpdateSession.load();
        updateSessionScene = new Scene(updateSession);

        Parent popUpRegisteredSale = loaderPopUpRegisteredSale.load();
        popUpRegisteredSaleScene = new Scene(popUpRegisteredSale);

        Parent popUpSession = loaderPopUpSession.load();
        popUpSessionScene = new Scene(popUpSession);

        Parent oversold = loaderOversold.load();
        oversoldScene = new Scene(oversold);

        primaryStage.setScene(homeScreenScene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    /**
     * Troca entre telas da aplicação quando o usuário clica em um botão que leva a
     * outra tela.
     * * @param screen é a tela que o usuário está.
     * 
     * @param userDataObject é o objeto que contém os dados que serão passados para
     *                       a tela.
     */
    public static void changeScreen(String screen, Object userDataObject) {
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
            case "registerSession":
                stage.setScene(registerSessionScene);
                notifyAllListerners("registerSession", userDataObject);
                break;
            case "roomOccupation":
                stage.setScene(roomOccupationScene);
                notifyAllListerners("roomOccupation", userDataObject);
                break;
            case "sellTicket":
                stage.setScene(sellTicketScene);
                notifyAllListerners("sellTicket", userDataObject);
                break;
            case "sessionControl":
                stage.setScene(sessionControlScene);
                notifyAllListerners("sessionControl", userDataObject);
                break;
            case "payPerSession":
                stage.setScene(payPerSessionScene);
                notifyAllListerners("payPerSession", userDataObject);
                break;
            case "updateSession":
                stage.setScene(updateSessionScene);
                notifyAllListerners("updateSession", userDataObject);
                break;
            case "popUpRegisteredSale":
                stage.setScene(popUpRegisteredSaleScene);
                notifyAllListerners("popUpRegisteredSale", userDataObject);
                break;
            case "popUpDiscount":
                stage.setScene(popUpDiscountScene);
                notifyAllListerners("popUpDiscount", userDataObject);
                break;
            case "popUpSession":
                stage.setScene(popUpSessionScene);
                notifyAllListerners("popUpSession", userDataObject);
                break;
            case "oversold":
                stage.setScene(oversoldScene);
                notifyAllListerners("oversold", userDataObject);
                break;
            default:
                break;
        }
    }

    public static void saveAndClose(Stage primaryStage) {
        // Evento ao clicar no X da janela
        primaryStage.setOnCloseRequest(event -> {
            System.out.println("Janela está sendo fechada!");;

            // salva os dados antes de fechar
            saveData();

            // confirmação de saída
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sair");
            alert.setHeaderText("Deseja realmente sair?");
            alert.setContentText("As alterações serão salvas.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isEmpty() || result.get() != ButtonType.OK) {
                event.consume(); // Impede que a janela feche
            }
        });
    }

    public static void loadData() {
        // Carrega os dados do arquivo
        ClientController.loadData();
        TicketController.loadData();
        SessionController.loadData();
        RoomController.loadData();
    }

    public static void saveData() {
        // Salva os dados no arquivo
        ClientController.saveData();
        TicketController.saveData();
        SessionController.saveData();
        RoomController.saveData();
    }

    /**
     * método principal da classe
     * * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    // Processamento de Dados na Troca de Tela
    private static List<OnChangeScreen> listeners = new ArrayList<>();

    /**
     * Interface para processamento de dados na troca de tela
     */
    public static interface OnChangeScreen {
        void onScreenChanged(String newScreen, Object userDataObject);
    }

    /**
     * Adiciona um novo listener para a troca de tela
     * * @param newListener novo listener
     */
    public static void addOnChangeScreenListener(OnChangeScreen newListener) {
        listeners.add(newListener);
    }

    /**
     * Notifica todos os listeners sobre a troca de tela
     * * @param newScreen Nova tela
     * 
     * @param userDataObject objeto de dados da troca de tela
     */
    private static void notifyAllListerners(String newScreen, Object userDataObject) {
        for (OnChangeScreen l : listeners)
            l.onScreenChanged(newScreen, userDataObject);
    }
}



//compilar tudo
// javac -d out --module-path "C:\javafx-sdk-21.0.7\lib" --add-modules javafx.controls,javafx.fxml (Get-ChildItem -Recurse -Filter *.java -Path src).FullName

//executar
// java --module-path "C:\javafx-sdk-21.0.7\lib" --add-modules javafx.controls,javafx.fxml -cp out controller.viewcontroller.MainViews