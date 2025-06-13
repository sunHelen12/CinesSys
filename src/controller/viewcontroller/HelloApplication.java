package controller.viewcontroller;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.stage.Modality;

import java.io.IOException;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("CineSys - Controle de Sessões");

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #0D0D2B;");

        // Topo: título e datas
        VBox topBox = new VBox();
        Label title = new Label("Controle de Sessões - Selecione para excluir ou alterar");
        title.setTextFill(Color.WHITE);
        title.setFont(new Font("Arial", 20));
        title.setPadding(new Insets(10, 0, 5, 10));

        HBox dateBox = new HBox(10);
        dateBox.setPadding(new Insets(10));
        String[] dates = {"23/09", "24/09", "25/09", "26/09"};
        for (String date : dates) {
            Button dateBtn = new Button(date);
            dateBtn.setStyle("-fx-background-color: orange; -fx-text-fill: black;");
            dateBox.getChildren().add(dateBtn);
        }

        topBox.getChildren().addAll(title, dateBox);
        root.setTop(topBox);

        // Centro: Filmes e sessões
        HBox movieBox = new HBox(20);
        movieBox.setPadding(new Insets(20));
        movieBox.setAlignment(Pos.CENTER);

        movieBox.getChildren().addAll(
                createMovieCard("Filme 1", "1h45min", "+16"),
                createMovieCard("Filme 4", "2h05min", "+18"),
                createMovieCard("Filme 3", "1h25min", "+16")
        );

        VBox centerBox = new VBox(10);
        centerBox.setAlignment(Pos.TOP_CENTER);
        centerBox.getChildren().add(movieBox);

        GridPane sessionGrid = new GridPane();
        sessionGrid.setHgap(20);
        sessionGrid.setVgap(10);
        sessionGrid.setPadding(new Insets(10));
        sessionGrid.setAlignment(Pos.CENTER_LEFT);

        // Sessões exemplo
        sessionGrid.add(createSessionInfo("Sala 2", 200, "08:00", "23:00"), 0, 0);
        sessionGrid.add(createSessionInfo("Sala 3", 150, "15:00"), 0, 1);
        sessionGrid.add(createSessionInfo("Sala 1", 250, "09:30", "23:30"), 1, 0);
        sessionGrid.add(createSessionInfo("Sala 2", 200, "15:00"), 1, 1);
        sessionGrid.add(createSessionInfo("Sala 5", 150, "10:00", "15:00", "21:00"), 2, 0);

        centerBox.getChildren().add(sessionGrid);
        root.setCenter(centerBox);

        // Inferior: botões
        HBox bottomBox = new HBox(20);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setPadding(new Insets(15));
        Button cadastrar = new Button("Cadastrar");
        Button excluir = new Button("Excluir");
        Button alterar = new Button("Alterar");
        cadastrar.setOnAction(e -> showSessionForm("Cadastrar Sessão"));
        alterar.setOnAction(e -> showSessionForm("Alterar Sessão"));
        excluir.setOnAction(e -> {
            showAlert("Sessão Excluída com sucesso!");
        });

        for (Button btn : new Button[]{cadastrar, excluir, alterar}) {
            btn.setStyle("-fx-background-color: orange; -fx-text-fill: black;");
            btn.setPrefWidth(100);
        }

        bottomBox.getChildren().addAll(cadastrar, excluir, alterar);
        root.setBottom(bottomBox);

        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createMovieCard(String name, String duration, String rating) {
        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(10));
        box.setStyle("-fx-border-color: white; -fx-border-width: 1px;");

        ImageView image = new ImageView(); // Placeholder for movie image
        image.setFitWidth(100);
        image.setFitHeight(100);
        image.setStyle("-fx-background-color: white;");

        Label title = new Label(name + " " + duration);
        title.setTextFill(Color.WHITE);

        Label classification = new Label("Classificação: " + rating);
        classification.setTextFill(Color.WHITE);

        Button btn = new Button("Vender Ingresso");
        btn.setStyle("-fx-background-color: orange; -fx-text-fill: black;");

        box.getChildren().addAll(image, title, classification, btn);
        return box;
    }

    private void showSessionForm(String action) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle(action);

        VBox dialogVBox = new VBox(15);
        dialogVBox.setPadding(new Insets(20));
        dialogVBox.setAlignment(Pos.CENTER);
        dialogVBox.setStyle("-fx-background-color: #1a1a40;");


        Label titleLabel = new Label(action);
        titleLabel.setTextFill(Color.ORANGE);
        titleLabel.setFont(Font.font("Arial", 16));

        TextField salaField = new TextField();
        salaField.setPromptText("Sala");
        salaField.setStyle("-fx-background-color: #333; -fx-text-fill: white;");

        TextField assentosField = new TextField();
        assentosField.setPromptText("Quantidade de Assentos");
        assentosField.setStyle("-fx-background-color: #333; -fx-text-fill: white;");

        TextField horariosField = new TextField();
        horariosField.setPromptText("Horários (ex: 08:00, 14:00)");
        horariosField.setStyle("-fx-background-color: #333; -fx-text-fill: white;");


        Button confirmarBtn = new Button("Confirmar");
        confirmarBtn.setStyle("-fx-background-color: orange; -fx-text-fill: black;");
        confirmarBtn.setOnAction(e -> {
            String msg = action.contains("Cadastrar") ? "Sessão cadastrada com sucesso!" : "Sessão alterada com sucesso!";
            showAlert(msg);
            dialog.close();
        });

        dialogVBox.getChildren().addAll(
                titleLabel,
                salaField,
                assentosField,
                horariosField,
                confirmarBtn
        );

        Scene dialogScene = new Scene(dialogVBox, 300, 250);
        dialog.setScene(dialogScene);
        dialog.showAndWait();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

    }

    private VBox createSessionInfo(String room, int seats, String... times) {
        VBox box = new VBox(5);
        Label sala = new Label(room);
        sala.setTextFill(Color.ORANGE);
        Label lugares = new Label("Assentos: " + seats);
        lugares.setTextFill(Color.WHITE);
        box.getChildren().addAll(sala, lugares);

        for (String time : times) {
            HBox timeBox = new HBox(5);
            CheckBox cb = new CheckBox(time);
            cb.setTextFill(Color.WHITE);
            Label icon = new Label("📁"); // placeholder
            icon.setTextFill(Color.ORANGE);
            timeBox.getChildren().addAll(cb, icon);
            box.getChildren().add(timeBox);
        }

        return box;
    }

    public static void main(String[] args) {
        launch(args);
    }

}