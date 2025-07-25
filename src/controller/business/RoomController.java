package controller.business;

import java.util.LinkedList;
import java.util.List;

import models.Room;
import models.Session;
import repository.RoomRepository;
import services.RoomService;
/**
 * Classe de controle para a lógica de negócio das salas que já se inicia com 5 salas cadastradas.
 * @author Kaique Silva Sousa 
 * @since 11/06/2025
 * @version 1.0
 */
public class RoomController {

    private static final RoomService roomService = new RoomService(new RoomRepository());
    
    /**
     * Pega todas as salas cadastradas.
     */
    public static List<Room> getAllRooms() {
        return (LinkedList<Room>) roomService.getAllRooms();
    }

    /**
     * Pega uma sala existeente pelo seu ID.
     * @param id ID da sala.
     */
    public static Room getRoomById(int id) {
        return roomService.getRoomById(id);
    }

    /**
     * Adiciona uma sessão a uma sala existente.
     * @param roomId ID da sala.
     * @param session Sessão a ser adicionada.
     * @return true se a sessão foi adicionada com sucesso.
     */
    public static boolean addSessionToRoom(int roomId, Session session) {
        return roomService.addSessionToRoom(roomId, session);
    }
    
    /**
     * Remove a próxima sessão da sala.
     * @param roomId ID da sala.
     * @return Sessão removida.
     */
    public static Session removeNextSessionFromRoom(int roomId) {
        return roomService.removeNextSessionFromRoom(roomId);
    }

    /**
     * Salva os dados das salas persistindo as informações atuais.
     */
    public static void saveData() {
        roomService.saveData();
    }

    /**
     * Carrega os dados das salas do Service.
     */
    public static void loadData() {
        roomService.loadData();
    }

    /**
     * Retorna a quantidade de salas armazenadas.
     *
     * @return o número total de salas.
     */
    public static int getSize() {
        return roomService.getSize();
    }
}
