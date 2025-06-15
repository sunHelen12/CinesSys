package controller.business;

import enums.PaymentMethod;
import models.*;

import org.junit.Before;
import org.junit.Test;

import structures.list.GenericDynamicList;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class ClientControllerTest {

    @Before
    public void setup() {
        ClientController.removeAllClients();
        Client.resetIdGenerator();
    }

    @Test
    public void testInitialClientRepositoryIsEmpty() {
        assertEquals(0, ClientController.getAllClients().size());
    }

    @Test
    public void testAddClient() {
        ClientController.addClient("Name", "email@email.com", "01-01-2000");
        assertEquals(1, ClientController.getAllClients().size());
    }

    @Test
    public void testAddMultipleClients() {
        for (int i = 0; i < 5; i++) {
            ClientController.addClient("Name" + i, "email" + i + "@mail.com", "01-01-2000");
        }
        assertEquals(5, ClientController.getAllClients().size());
    }

    @Test
    public void testGetClientById() {
        ClientController.addClient("João", "joao@email.com", "01-01-1990");
        Client client = ClientController.getAllClients().get(0);
        assertEquals("João", ClientController.getClientById(client.getId()).getName());
    }

    @Test
    public void testAddTicketToClient() {
        ClientController.addClient("Teste", "teste@email.com", "01-01-2000");
        Client client = ClientController.getAllClients().get(0);
        Movie movie = new Movie("Filme", "Ação", 120, "Diretor", "Sinopse");
        Session session = new Session(LocalDate.now(), LocalTime.now(), RoomController.getRoomById(1), movie, 30.0);
        Ticket ticket = new Ticket(client, session, session.getTicketValue(), PaymentMethod.CREDIT_CARD);

        ClientController.addTicketToClient(client.getId(), ticket);

        GenericDynamicList<Ticket> history = ClientController.getClientHistory(client.getId());
        assertEquals(1, history.size());
        assertEquals(ticket, history.get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddClientWithEmptyName() {
        ClientController.addClient("", "email@test.com", "01-01-2000");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddClientWithEmptyEmail() {
        ClientController.addClient("Nome", "", "01-01-2000");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetClientByInvalidId() {
        ClientController.getClientById(0);
    }

    @Test(expected = RuntimeException.class)
    public void testGetClientByNonExistentId() {
        ClientController.getClientById(999);
    }

    @Test
    public void testUpdateClientSuccessfully() {
        ClientController.addClient("Antigo Nome", "antigo@email.com", "01-01-1990");
        Client client = ClientController.getAllClients().get(0);

        ClientController.updateClient(client.getId(), "Novo Nome", "novo@email.com", "05-05-1995");
        Client updatedClient = ClientController.getClientById(client.getId());

        assertEquals("Novo Nome", updatedClient.getName());
        assertEquals("novo@email.com", updatedClient.getEmail());
        assertEquals("05-05-1995", updatedClient.getBirthday());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateNonExistentClient() {
        ClientController.updateClient(999, "Nome", "email@email.com", "01-01-1990");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateClientWithEmptyName() {
        ClientController.addClient("Nome", "email@email.com", "01-01-1990");
        Client client = ClientController.getAllClients().get(0);

        ClientController.updateClient(client.getId(), "", "novo@email.com", "01-01-1990");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateClientWithEmptyEmail() {
        ClientController.addClient("Nome", "email@email.com", "01-01-1990");
        Client client = ClientController.getAllClients().get(0);

        ClientController.updateClient(client.getId(), "Nome Atualizado", "", "01-01-1990");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullTicketToClient() {
        ClientController.addClient("Fulano", "fulano@mail.com", "01-01-2000");
        Client client = ClientController.getAllClients().get(0);
        ClientController.addTicketToClient(client.getId(), null);
    }

    @Test
    public void testGetEmptyClientHistory() {
        ClientController.addClient("Sem Compras", "email@email.com", "31-12-1999");
        Client client = ClientController.getAllClients().get(0);
        assertEquals(0, ClientController.getClientHistory(client.getId()).size());
    }

    @Test
    public void testCalculateDiscount() {
        ClientController.addClient("Fulano", "fulano@mail.com", "01-01-2000");
        Client client = ClientController.getAllClients().get(0);
        ClientController.addTicketToClient(
            client.getId(), 
            new Ticket(
                client, 
                new Session(LocalDate.now(), LocalTime.now(), RoomController.getRoomById(1), new Movie("Filme", "Ação", 120, "Diretor", "Sinopse"), 30.0), 
                30.0, 
                PaymentMethod.CREDIT_CARD
            )
        );
        ClientController.addTicketToClient(client.getId(), new Ticket(client, new Session(LocalDate.now(), LocalTime.now(), RoomController.getRoomById(2), new Movie("Filme", "Ação", 120, "Diretor", "Sinopse"), 30.0), 30.0, PaymentMethod.CREDIT_CARD));
    }
}