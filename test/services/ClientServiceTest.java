package services;

import enums.PaymentMethod;
import models.*;
import org.junit.Before;
import org.junit.Test;
import structures.list.GenericDynamicList;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class ClientServiceTest {

    @Before
    public void resetRepository() {
        ClientService.getAllClients().clear();
    }

    @Test
    public void testInitialClientRepositoryIsEmpty() {
        assertEquals(0, ClientService.getAllClients().size());
    }

    @Test
    public void testAddClient() {
        ClientService.addClient("Name", "email@email.com", "01-01-2000");
        assertEquals(1, ClientService.getAllClients().size());
    }

    @Test
    public void testAddMultipleClients() {
        for (int i = 0; i < 5; i++) {
            ClientService.addClient("Name" + i, "email" + i + "@mail.com", "01-01-2000");
        }
        assertEquals(5, ClientService.getAllClients().size());
    }

    @Test
    public void testGetClientById() {
        ClientService.addClient("João", "joao@email.com", "01-01-1990");
        Client client = ClientService.getAllClients().get(0);
        assertEquals("João", ClientService.getClientById(client.getId()).getName());
    }

    @Test
    public void testAddTicketToClient() {
        ClientService.addClient("Teste", "teste@email.com", "01-01-2000");
        Client client = ClientService.getAllClients().get(0);
        Movie movie = new Movie("Filme", "Ação", 120, "Diretor", "Sinopse");
        Session session = new Session(LocalDate.now(), LocalTime.now(), Room.room1, movie, 30.0);
        Ticket ticket = new Ticket(client, session, PaymentMethod.CREDIT_CARD);

        ClientService.addTicketToClient(client.getId(), ticket);

        GenericDynamicList<Ticket> history = ClientService.getClientHistory(client.getId());
        assertEquals(1, history.size());
        assertEquals(ticket, history.get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddClientWithEmptyName() {
        ClientService.addClient("", "email@test.com", "01-01-2000");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddClientWithEmptyEmail() {
        ClientService.addClient("Nome", "", "01-01-2000");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetClientByInvalidId() {
        ClientService.getClientById(0);
    }

    @Test(expected = RuntimeException.class)
    public void testGetClientByNonExistentId() {
        ClientService.getClientById(999);
    }

    @Test
    public void testUpdateClientSuccessfully() {
        ClientService.addClient("Antigo Nome", "antigo@email.com", "01-01-1990");
        Client client = ClientService.getAllClients().get(0);

        ClientService.updateClient(client.getId(), "Novo Nome", "novo@email.com", "05-05-1995");
        Client updatedClient = ClientService.getClientById(client.getId());

        assertEquals("Novo Nome", updatedClient.getName());
        assertEquals("novo@email.com", updatedClient.getEmail());
        assertEquals("05-05-1995", updatedClient.getBirthday());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateNonExistentClient() {
        ClientService.updateClient(999, "Nome", "email@email.com", "01-01-1990");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateClientWithEmptyName() {
        ClientService.addClient("Nome", "email@email.com", "01-01-1990");
        Client client = ClientService.getAllClients().get(0);

        ClientService.updateClient(client.getId(), "", "novo@email.com", "01-01-1990");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateClientWithEmptyEmail() {
        ClientService.addClient("Nome", "email@email.com", "01-01-1990");
        Client client = ClientService.getAllClients().get(0);

        ClientService.updateClient(client.getId(), "Nome Atualizado", "", "01-01-1990");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullTicketToClient() {
        ClientService.addClient("Fulano", "fulano@mail.com", "01-01-2000");
        Client client = ClientService.getAllClients().get(0);
        ClientService.addTicketToClient(client.getId(), null);
    }

    @Test
    public void testGetEmptyClientHistory() {
        ClientService.addClient("Sem Compras", "email@email.com", "31-12-1999");
        Client client = ClientService.getAllClients().get(0);
        assertEquals(0, ClientService.getClientHistory(client.getId()).size());
    }
}
