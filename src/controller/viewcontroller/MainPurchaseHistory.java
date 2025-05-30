package controller.viewcontroller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainPurchaseHistory extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/PurchaseRelatory.fxml"));
        Parent root = loader.load(); // aqui a injeção @FXML funciona corretamente

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Relatório de Compras");
        stage.show();
    }    

    public static void main(String[] args) {
        launch(args);
    }
}

//compilar
// javac -d out --module-path "C:\javafx-sdk-21.0.7\lib" --add-modules javafx.controls,javafx.fxml src/controller/viewcontroller/MainPurchaseHistory.java
//executar
// java --module-path "C:\javafx-sdk-21.0.7\lib" --add-modules javafx.controls,javafx.fxml -cp "out;src" controller.viewcontroller.MainPurchaseHistory

//compilar tudo
// javac -d out --module-path "C:\javafx-sdk-21.0.7\lib" --add-modules javafx.controls,javafx.fxml (Get-ChildItem -Recurse -Filter *.java -Path src).FullName
