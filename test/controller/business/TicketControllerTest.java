package controller.business;

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

public class TicketControllerTest {

    @Before
    public void setup() {
        TicketController.removeAllTickets();
        Ticket.get_idGenerator();
        SessionController.removeAllSessions();
        Session.resetIdGenerator();
        ClientController.removeAllClients();
        Client.resetIdGenerator();
    }

    @Test
    public void testConstructorInitialState() {
        assertEquals(0, TicketController.getAllTickets().size());
    }

    @Test
    public void testAddTicket() {
        SessionController.addSession("12-10-2025", "14:00", RoomController.getRoomById(1), new Movie("Inception", "Sci-Fi", 148, "PG-13", "A mind-bending thriller."), 30.0);
        ClientController.addClient("Joao", "email@email.com", "12-10-2003");
        TicketController.purchaseTicket(ClientController.getClientById(1).getId(), SessionController.getSessionById(1).getId(), PaymentMethod.CASH.toString());
        assertEquals(1, TicketController.getAllTickets().size());
    }


    @Test (expected = IllegalArgumentException.class)
    public void testAddTicketWithNullSession(){
        ClientController.addClient("Joao", "email@email.com", "12-10-2003");
        TicketController.addTicket(ClientController.getClientById(1).getId(), null, PaymentMethod.PIX);
    }

    @Test (expected = IllegalAccessError.class)
    public void testAddTicketWithNullClient(){
        Session session = new Session(LocalDate.now(), LocalTime.now(), RoomController.getRoomById(1), new Movie("Inception", "Sci-Fi", 148, "PG-13", "A mind-bending thriller."), 30.0);
        TicketController.addTicket(null, session, PaymentMethod.PIX);
    }

    @Test
    public void testGetAllTicketsWhenNoTicketsAdded() {
        assertTrue(TicketController.getAllTickets().isEmpty());
    }

    @Test
    public void testAddMultipleSessions() {
        Movie movie = new Movie("Inception", "Sci-Fi", 148, "PG-13", "A mind-bending thriller.");
        Client client = new Client("Maria", "maria@email.com", LocalDate.of(2006, 1, 9));
        for (int i = 0; i < 5; i++) {
            Session session = new Session(LocalDate.now(), LocalTime.now().plusHours(i*2), RoomController.getRoomById(1), movie, (i + 5.0) * 5);
            TicketController.addTicket(client, session, PaymentMethod.PIX);
        }
        assertEquals(5, TicketController.getAllTickets().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetMovieByIdWithInvalidIdThrowsException() {
        TicketController.getTicketById(0);
    }

    @Test(expected = RuntimeException.class)
    public void testRemoveMovieByIdWithNonexistentIdThrowsException() {
        TicketController.removeTicketById(1234);
    }

}
