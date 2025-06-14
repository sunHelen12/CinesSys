package services;

import enums.PaymentMethod;
import models.*;
import org.junit.Before;
import org.junit.Test;

import controller.business.RoomController;
import repository.RoomRepository;
import repository.TicketRepository;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class TicketServiceTest {
    private TicketService ticketService;

    @Before
    public void setup() {
        ticketService = new TicketService(new TicketRepository());
    }

    @Test
    public void testConstructorInitialState() {
        assertEquals(0, ticketService.getAllTickets().size());
    }

    @Test
    public void testAddTicket() {
        Session session = new Session(LocalDate.now(), LocalTime.now(),RoomController.getRoomById(1), new Movie("Inception", "Sci-Fi", 148, "PG-13", "A mind-bending thriller."), 30.0);
        Client client = new Client("Joao", "email@email.com", LocalDate.of(1999, 1, 1));
        ticketService.addTicket(client, session, PaymentMethod.CASH);
        assertEquals(1, ticketService.getAllTickets().size());
    }


    @Test (expected = IllegalArgumentException.class)
    public void testAddTicketWithNullSession(){
        Client client = new Client("Joao", "email@email.com", LocalDate.of(1999, 1, 1));
        ticketService.addTicket(client, null, PaymentMethod.PIX);
    }

    @Test (expected = IllegalAccessError.class)
    public void testAddTicketWithNullClient(){
        Session session = new Session(LocalDate.now(), LocalTime.now(), RoomController.getRoomById(1), new Movie("Inception", "Sci-Fi", 148, "PG-13", "A mind-bending thriller."), 30.0);
        ticketService.addTicket(null, session, PaymentMethod.PIX);
    }

    @Test
    public void testGetAllTicketsWhenNoTicketsAdded() {
        assertTrue(ticketService.getAllTickets().isEmpty());
    }

    @Test
    public void testAddMultipleSessions() {
        Movie movie = new Movie("Inception", "Sci-Fi", 148, "PG-13", "A mind-bending thriller.");
        Client client = new Client("Maria", "maria@email.com", LocalDate.of(2006, 1, 9));
        for (int i = 0; i < 5; i++) {
            Session session = new Session(LocalDate.now(), LocalTime.now().plusHours(i*2), RoomController.getRoomById(1), movie, (i + 5.0) * 5);
            ticketService.addTicket(client, session, PaymentMethod.PIX);
        }
        assertEquals(5, ticketService.getAllTickets().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetMovieByIdWithInvalidIdThrowsException() {
        ticketService.getTicketById(0);
    }

    @Test(expected = RuntimeException.class)
    public void testRemoveMovieByIdWithNonexistentIdThrowsException() {
        ticketService.removeTicketById(1234);
    }

}
