package services;

import models.Room;
import models.Session;
import repository.RoomRepository;
import structures.list.GenericDynamicList;

/**
 * Classe de serviço responsável pela lógica de negócios
 * relacionada às salas (Room) do cinema.
 */
public class RoomService {
    private final RoomRepository roomRepository;

    /**
     * Construtor do RoomService.
     *
     * @param roomRepository O repositório de salas.
     */
    public RoomService (RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }

    /**
     * Cria e adiciona uma nova sala ao sistema.
     *
     * @param totalSeat Quantidade total de assentos da sala.
     * @return true se a sala foi adicionada com sucesso.
     */
    public boolean addRoom(int totalSeat){
        if (totalSeat <= 0) {
            System.out.println("O número total de assentos deve ser maior que zero!");
            return false;
        }

        Room newRoom = new Room(totalSeat);
        roomRepository.add(newRoom);
        return true;
    }

    /**
     * Retorna todas as salas cadastradas.
     *
     * @return Lista de todas as salas.
     */
    public GenericDynamicList<Room> getAllRooms(){
        return roomRepository.getAll();
    }

    /**
     * Busca uma sala pelo ID.
     *
     * @param id O ID da sala.
     * @return A sala encontrada.
     * @throws IllegalArgumentException se o ID for menor ou igual a zero.
     * @throws RuntimeException         se não existir uma sala com esse ID.
     */
    public Room getRoomById(int id){
        if(id <= 0){
            throw new IllegalArgumentException("O ID deve ser maior que zero!");
        }

        Room room = roomRepository.getById(id);
        if(room == null){
            throw new RuntimeException("Nenhuma sala encontrada com o ID " + id);
        }

        return room;
    }

    /**
     * Remove uma sala pelo ID, se ela não tiver sessões pendentes.
     *
     * @param id O ID da sala a ser removida.
     * @return true se a sala foi removida; false se houver sessões na fila.
     * @throws IllegalArgumentException se o ID for menor ou igual a zero.
     */
    public boolean removeRoomById(int id){
        if(id <= 0){
            throw new IllegalArgumentException("O ID deve ser maior que zero!");
        }

        Room room = roomRepository.getById(id);
        if (room.getSessions().size() > 0) {
            System.out.println("Não é possível remover a sala " + id + " pois ela possui sessões agendadas!");
            return false;
        }

        return roomRepository.removeById(id);
    }

    /**
     * Enfileira (adiciona) uma sessão na sala indicada.
     *
     * @param roomId  ID da sala onde deseja agendar a sessão.
     * @param session A sessão a ser agendada.
     * @return true se a sessão foi agendada com sucesso; false se a sala não existir ou erro.
     * @throws IllegalArgumentException se o ID da sala for inválido.
     */
    public boolean addSessionToRoom(int roomId, Session session){
        if (roomId <= 0) {
            throw new IllegalArgumentException("O ID da sala deve ser maior que zero!");
        }

        Room room = roomRepository.getById(roomId);
        if (room == null) {
            System.out.println("Sala com o ID " + roomId + " não encontrada! Não foi possível agendar a sessão.");
            return false;
        }

        try {
            room.addSession(session);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Session removeNextSessionFromRoom(int roomId){
        if (roomId <= 0) {
            throw new IllegalArgumentException("O ID da sala deve ser maior que zero!");
        }

        Room room = roomRepository.getById(roomId);
        if (room == null) {
            System.out.println("Sala com o ID " + roomId + " não encontrada! Não foi possível remover a sessão.");
            return null;
        }

        return room.removeSession();
    }
}