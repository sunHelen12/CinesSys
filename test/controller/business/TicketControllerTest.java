package controller.business;

import enums.PaymentMethod;
import models.*;
import org.junit.Before;
import org.junit.Test;

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
        MovieController.removeAllMovies();
        Movie.resetIdGenerator();
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
        TicketController.purchaseTicket(ClientController.getClientById(1).getId(), 0, PaymentMethod.PIX.toString());
    }

    @Test
    public void testGetAllTicketsWhenNoTicketsAdded() {
        assertTrue(TicketController.getAllTickets().isEmpty());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddMultipleSessions() {
        MovieController.addMovie("Movie", "Genre", 120, "PG", "Description");
        ClientController.addClient("Joao", "email@email.com", "12-10-2003");
        for (int i = 0; i < 5; i++) {
            SessionController.addSession("1"+i+"-12-2025", "1"+i+":00", RoomController.getRoomById(1), MovieController.getMovieById(1), 10.0);
            TicketController.purchaseTicket(ClientController.getClientById(1).getId(), i, PaymentMethod.CASH.toString());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetMovieByIdWithInvalidIdThrowsException() {
        TicketController.getTicketById(0);
    }

}
