package controller.viewcontroller;

import controller.business.RoomController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Classe responsável por controlar a tela de ocupação de salas.
 * 
 * @author Maria Eduarda Campos
 * @author Vinicius Nunes de Andrade
 * @since 31/05/2025
 * @version 2.0
 */
public class RoomOccupationController {

    /**
     * Retorna para tela principal.
     * 
     * @param event evento de clique no botão CineSys
     */
    @FXML
    void openHomeScreen(ActionEvent event) {
        MainViews.changeScreen("homeScreen", null);
    }

    /**
     * Retorna para tela de controle de clientes.

     * @param event evento de clique no botão de controle de clientes
     */
    @FXML
    void openClientControl(ActionEvent event) {
        MainViews.changeScreen("clientControl", null);
    }

    /**
     * Retorna para tela de controle de filmes.
     * 
     * @param event evento de clique no botão de controle de filmes
     */
    @FXML
    void openMovieControl(ActionEvent event) {
        MainViews.changeScreen("movieControl", null);
    }

    /**
     * Retorna para tela de relatório de compras.
     * 
     * @param event evento de clique no botão de relatório de compras
     */
    @FXML
    void openPurchaseHistory(ActionEvent event) {
        MainViews.changeScreen("purchaseRelatory", null);
    }

    /**
     * Retorna para tela de ocupação de salas.
     * 
     * @param event evento de clique no botão de ocupação de salas
     */
    @FXML
    void openRoomOccupation(ActionEvent event) {
        MainViews.changeScreen("roomOccupation", null);
    }

    /**
     * Retorna para tela de controle de sessões.
     * 
     * @param event evento de clique no botão de controle de sessões
     */
    @FXML
    void openSessionControl(ActionEvent event) {
        MainViews.changeScreen("sessionControl", null);
    }


    /**
     * Abre a tela de relatório de ocupação para a sala 1.
     * 
     * @param event evento de clique no botão para visualizar o relatório de ocupação da sala 1
     */
    @FXML
    void openRoom1(ActionEvent event) {
        MainViews.changeScreen("occupationRelatory", RoomController.getRoomById(1));
    }

    
    /**
     * Abre a tela de relatório de ocupação para a sala 2.
     * 
     * @param event evento de clique no botão para visualizar o relatório de ocupação da sala 2
     */
    @FXML
    void openRoom2(ActionEvent event) {
        MainViews.changeScreen("occupationRelatory", RoomController.getRoomById(2));
    }

    /**
     * Abre a tela de relatório de ocupação para a sala 3.
     * 
     * @param event evento de clique no botão para visualizar o relatório de ocupação da sala 3
     */
    @FXML
    void openRoom3(ActionEvent event) {
        MainViews.changeScreen("occupationRelatory", RoomController.getRoomById(3));
    }

    /**
     * Abre a tela de relatório de ocupação para a sala 4.
     * 
     * @param event evento de clique no botão para visualizar o relatório de ocupação da sala 4
     */
    @FXML
    void openRoom4(ActionEvent event) {
        MainViews.changeScreen("occupationRelatory", RoomController.getRoomById(4));
    }

    /**
     * Abre a tela de relatório de ocupação para a sala 5.
     * 
     * @param event evento de clique no botão para visualizar o relatório de ocupação da sala 5
     */
    @FXML
    void openRoom5(ActionEvent event) {
        MainViews.changeScreen("occupationRelatory", RoomController.getRoomById(5));
    }

}
