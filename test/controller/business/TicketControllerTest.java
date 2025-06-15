package controller.business;

import enums.PaymentMethod;
import models.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Classe de testes unitários para a classe TicketController.
 *
 * @author Vinícius Nunes de Andrade
 * @author Kaique Silva sousa
 * @version 1.0
 * @since 15-06-2025
 */
public class TicketControllerTest {

    /**
     * Prepara o ambiente antes de cada teste.
     * Limpa todos os repositórios e reseta os geradores de ID dos modelos
     * para garantir a independência e a consistência dos testes.
     */
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

    /**
     * Testa se o repositório de tickets inicia vazio.
     */
    @Test
    public void testConstructorInitialState() {
        assertEquals(0, TicketController.getAllTickets().size());
    }

    /**
     * Testa a criação e adição bem-sucedida de um único ticket.
     */
    @Test
    public void testAddTicket() {
        SessionController.addSession("12-10-2025", "14:00", RoomController.getRoomById(1), new Movie("Inception", "Sci-Fi", 148, "PG-13", "A mind-bending thriller."), 30.0);
        ClientController.addClient("Joao", "email@email.com", "12-10-2003");
        TicketController.purchaseTicket(ClientController.getClientById(1).getId(), SessionController.getSessionById(1).getId(), PaymentMethod.CASH.toString());
        assertEquals(1, TicketController.getAllTickets().size());
    }


    /**
     * Testa se a tentativa de comprar um ticket para uma sessão inválida (ID 0)
     * lança a exceção esperada.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testAddTicketWithNullSession(){
        ClientController.addClient("Joao", "email@email.com", "12-10-2003");
        TicketController.purchaseTicket(ClientController.getClientById(1).getId(), 0, PaymentMethod.PIX.toString());
    }

    /**
     * Testa se a busca por todos os tickets retorna uma lista vazia
     * quando nenhum ticket foi adicionado.
     */
    @Test
    public void testGetAllTicketsWhenNoTicketsAdded() {
        assertTrue(TicketController.getAllTickets().isEmpty());
    }

    /**
     * Testa se a tentativa de comprar um ticket com ID de sessão inválido (zero)
     * dentro de um loop lança a exceção esperada.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testAddMultipleSessions() {
        MovieController.addMovie("Movie", "Genre", 120, "PG", "Description");
        ClientController.addClient("Joao", "email@email.com", "12-10-2003");
        for (int i = 0; i < 5; i++) {
            SessionController.addSession("1"+i+"-12-2025", "1"+i+":00", RoomController.getRoomById(1), MovieController.getMovieById(1), 10.0);
            TicketController.purchaseTicket(ClientController.getClientById(1).getId(), i, PaymentMethod.CASH.toString());
        }
    }

    /**
     * Testa se a busca por um ticket com ID inválido (zero)
     * lança a exceção esperada.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetMovieByIdWithInvalidIdThrowsException() {
        TicketController.getTicketById(0);
    }

}