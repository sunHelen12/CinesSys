package controller.viewcontroller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class MainViews extends Application {
    private static Stage stage;
    private static Scene purchaseRelatoryScene;
    private static Scene clientHistoryScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        primaryStage.setTitle("CineSys");
        
        FXMLLoader loaderPurchaseRelatory = new FXMLLoader(getClass().getResource("/gui/PurchaseRelatory.fxml"));
        FXMLLoader loaderClientHistory = new FXMLLoader(getClass().getResource("/gui/ClientHistory.fxml"));
        
        Parent purchaseRelatory = loaderPurchaseRelatory.load(); 
        purchaseRelatoryScene = new Scene(purchaseRelatory);

        Parent clientHistory = loaderClientHistory.load(); 
        clientHistoryScene = new Scene(clientHistory);
        
        primaryStage.setScene(purchaseRelatoryScene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }    

    public static void changeScreen(String screen, Object userDataObject){
        switch (screen) {
            case "purchaseRelatory":
                stage.setScene(purchaseRelatoryScene);
                notifyAllListerners("purchaseRelatory", userDataObject);
                break;
            case "clientHistory":
                stage.setScene(clientHistoryScene);
                notifyAllListerners("clientHistory", userDataObject);
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
// javac -d out --module-path "C:\javafx-sdk-21.0.7\lib" --add-modules javafx.controls,javafx.fxml src/controller/viewcontroller/MainPurchaseHistory.java
//executar
// java --module-path "C:\javafx-sdk-21.0.7\lib" --add-modules javafx.controls,javafx.fxml -cp "out;src" controller.viewcontroller.MainPurchaseHistory

//compilar tudo
// javac -d out --module-path "C:\javafx-sdk-21.0.7\lib" --add-modules javafx.controls,javafx.fxml (Get-ChildItem -Recurse -Filter *.java -Path src).FullName
