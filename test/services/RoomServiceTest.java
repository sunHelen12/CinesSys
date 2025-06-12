package services;

import enums.PaymentMethod;
import models.*;
import org.junit.Before;
import org.junit.Test;
import repository.RoomRepository;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class RoomServiceTest {
    
    private RoomService roomService;

    @Before
    public void setup() {
        roomService = new RoomService(new RoomRepository());
    }

    @Test
    public void testConstructorInitialState() {
        assertEquals(0, roomService.getAllRooms().size());
    }

    @Test
    public void testAddRoom() {
        assertEquals(1, roomService.getAllRooms().size());
    }

    @Test
    public void testGetRoomById() {
        Room room = roomService.getRoomById(1);
        assertEquals(200, room.getTotalSeat());
    }
}
