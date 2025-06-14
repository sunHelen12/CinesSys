package controller.viewcontroller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PopUpDiscountController {

    @FXML
    private Label labelDiscount;

    @FXML
    private Button backButton;

    public void setDiscount(double discountPercentage) {
        labelDiscount.setText(String.format("Desconto aplicado: %.0f%%", discountPercentage));
    }

    @FXML
    private void handleBack() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}