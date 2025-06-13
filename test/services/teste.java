package services;

import repository.RoomRepository;

public class teste {

    public static void main(String[] args) {
        RoomService roomService = new RoomService(new RoomRepository());
        System.out.println(roomService.getRoomById(2).getTotalSeat());
    }
    
}
