package services;

import enums.PaymentMethod;
import models.*;
import org.junit.Before;
import org.junit.Test;
import repository.ClientRepository;
import repository.MovieRepository;
import structures.list.GenericDynamicList;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class ClientServiceTest {


    @Before
    public void setup() {
        service = new ClientService(repository);
    }

    @Test
    public void testConstructorInitialState() {
        assertEquals(0, service.getAllClients().size());
    }

    @Test
    public void testAddClient() {
        service.addClient("Name", "email@email.com", "01-01-2000");
        assertEquals(1, service.getAllClients().size());
    }

    @Test
    public void testAddMultipleClients() {
        for (int i = 0; i < 5; i++) {
            service.addClient("Name" + i, "email" + i + "@mail.com", "01-01-2000");
        }
        assertEquals(5, service.getAllClients().size());
    }

    @Test
    public void testGetClientById() {
        service.addClient("João", "joao@email.com", "01-01-1990");
        Client client = service.getAllClients().get(0);
        assertEquals("João", service.getClientById(client.getId()).getName());
    }

    @Test
    public void testAddTicketToClient() {
        service.addClient("Teste", "teste@email.com", "01-01-2000");
        Client client = service.getAllClients().get(0);
        Movie movie = new Movie("Filme", "Ação", 120, "Diretor", "Sinopse");
        Session session = new Session(LocalDate.now(), LocalTime.now(), Room.room1, movie, 30.0);
        Ticket ticket = new Ticket(client, session, PaymentMethod.CREDIT_CARD);

        service.addTicketToClient(client.getId(), ticket);

        GenericDynamicList<Ticket> history = service.getClientHistory(client.getId());
        assertEquals(1, history.size());
        assertEquals(ticket, history.get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddClientWithEmptyName() {
        service.addClient("", "email@test.com", "01-01-2000");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddClientWithEmptyEmail() {
        service.addClient("Nome", "", "01-01-2000");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetClientByInvalidId() {
        service.getClientById(0);
    }

    @Test(expected = RuntimeException.class)
    public void testGetClientByNonExistentId() {
        service.getClientById(999);
    }

    @Test
    public void testUpdateClientSuccessfully() {
        service.addClient("Antigo Nome", "antigo@email.com", "01-01-1990");
        Client client = service.getAllClients().get(0);

        service.updateClient(client.getId(), "Novo Nome", "novo@email.com", "05-05-1995");
        Client updatedClient = service.getClientById(client.getId());

        assertEquals("Novo Nome", updatedClient.getName());
        assertEquals("novo@email.com", updatedClient.getEmail());
        assertEquals("05-05-1995", updatedClient.getBirthday());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateNonExistentClient() {
        service.updateClient(999, "Nome", "email@email.com", "01-01-1990");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateClientWithEmptyName() {
        service.addClient("Nome", "email@email.com", "01-01-1990");
        Client client = service.getAllClients().get(0);

        service.updateClient(client.getId(), "", "novo@email.com", "01-01-1990");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateClientWithEmptyEmail() {
        service.addClient("Nome", "email@email.com", "01-01-1990");
        Client client = service.getAllClients().get(0);

        service.updateClient(client.getId(), "Nome Atualizado", "", "01-01-1990");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullTicketToClient() {
        service.addClient("Fulano", "fulano@mail.com", "01-01-2000");
        Client client = service.getAllClients().get(0);
        service.addTicketToClient(client.getId(), null);
    }

    @Test
    public void testGetEmptyClientHistory() {
        service.addClient("Sem Compras", "email@email.com", "31-12-1999");
        Client client = service.getAllClients().get(0);
        assertEquals(0, service.getClientHistory(client.getId()).size());
    }
}
