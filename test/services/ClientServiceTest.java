package services;

import enums.PaymentMethod;
import models.*;
import org.junit.Before;
import org.junit.Test;
import repository.ClientRepository;
import structures.list.GenericDynamicList;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class ClientServiceTest {
    private ClientService service;

    @Before
    public void setup() {
        ClientRepository repository = new ClientRepository();
        service = new ClientService(repository);
    }

    @Test
    public void testConstructorInitialState() {
        assertEquals(0, service.getAllClients().size());
    }

    @Test
    public void testAddClient() {
        service.addClient("Name", "email", LocalDate.of(2000, 1, 1));
        assertEquals(1, service.getAllClients().size());
    }

    @Test
    public void testAddMultipleClients() {
        for (int i = 0; i < 5; i++) {
            service.addClient("Name" + i, "email" + i + "@mail.com", LocalDate.of(2000, 1, 1));
        }
        assertEquals(5, service.getAllClients().size());
    }

    @Test
    public void testGetClientById() {
        service.addClient("João", "joao@email.com", LocalDate.of(1990, 1, 1));
        Client client = service.getAllClients().get(1);
        assertEquals("João", service.getClientById(client.getId()).getName());
    }

    @Test
    public void testAddTicketToClient() {
        service.addClient("Teste", "teste@email.com", LocalDate.of(2000, 1, 1));
        Client client = service.getAllClients().get(1);
        Movie movie = new Movie("Filme", "Ação", 120, "Diretor", "Sinopse");
        Session session = new Session(LocalDate.now(), LocalTime.now(), Room.room1, movie, 30.0);
        Ticket ticket = new Ticket(client, session, PaymentMethod.CREDIT_CARD);

        service.addTicketToClient(client.getId(), ticket);

        GenericDynamicList<Ticket> history = service.getClientHistory(client.getId());
        assertEquals(1, history.size());
        assertEquals(ticket, history.get(1));
    }

    @Test(expected = IllegalAccessError.class)
    public void testAddClientWithEmptyName() {
        service.addClient("", "email@test.com", LocalDate.of(2000, 1, 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddClientWithEmptyEmail() {
        service.addClient("Nome", "", LocalDate.of(2000, 1, 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetClientByInvalidId() {
        service.getClientById(0);
    }

    @Test(expected = RuntimeException.class)
    public void testGetClientByNonExistentId() {
        service.getClientById(999);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullTicketToClient() {
        service.addClient("Fulano", "fulano@mail.com", LocalDate.of(2000, 1, 1));
        Client client = service.getAllClients().get(1);
        service.addTicketToClient(client.getId(), null);
    }

    @Test
    public void testGetEmptyClientHistory() {
        service.addClient("Sem Compras", "email@email.com", LocalDate.of(1999, 12, 31));
        Client client = service.getAllClients().get(1);
        assertEquals(0, service.getClientHistory(client.getId()).size());
    }
}
