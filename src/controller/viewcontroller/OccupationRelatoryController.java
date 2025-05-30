package controller.viewcontroller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

public class OccupationRelatoryController implements Initializable {

    @FXML
    private ComboBox<String> filtroOcupacao;

    private List<String> filter = new ArrayList<>();
    private ObservableList<String> items;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addFilter();
    }

    public void addFilter(){
        filter.add("Filme");
        filter.add("Data");
        filter.add("Horário de Sessão");

        items = FXCollections.observableArrayList(filter);

        filtroOcupacao.setItems(items);
    }
}
