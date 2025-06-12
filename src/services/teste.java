package services;

import models.Room;
import repository.RoomRepository;

public class teste {
    
    public static void main(String[] args) {
        RoomService roomService = new RoomService(new RoomRepository());
        for (Room room : roomService.getAllRooms()) {
            System.out.println(room);
        }
    }
}
