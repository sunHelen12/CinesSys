package services;

import enums.PaymentMethod;
import models.*;
import repository.ClientRepository;
import repository.RoomRepository;

import org.junit.Before;
import org.junit.Test;

import controller.bussines.RoomController;
import structures.list.GenericDynamicList;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class ClientServiceTest {

    private ClientService clientService;

    @Before
    public void setup() {
        clientService = new ClientService(new ClientRepository());
    }

    @Test
    public void testInitialClientRepositoryIsEmpty() {
        assertEquals(0, clientService.getAllClients().size());
    }

    @Test
    public void testAddClient() {
        clientService.addClient("Name", "email@email.com", "01-01-2000");
        assertEquals(1, clientService.getAllClients().size());
    }

    @Test
    public void testAddMultipleClients() {
        for (int i = 0; i < 5; i++) {
            clientService.addClient("Name" + i, "email" + i + "@mail.com", "01-01-2000");
        }
        assertEquals(5, clientService.getAllClients().size());
    }

    @Test
    public void testGetClientById() {
        clientService.addClient("João", "joao@email.com", "01-01-1990");
        Client client = clientService.getAllClients().get(0);
        assertEquals("João", clientService.getClientById(client.getId()).getName());
    }

    @Test
    public void testAddTicketToClient() {
        RoomService roomService = new RoomService(new RoomRepository());
        clientService.addClient("Teste", "teste@email.com", "01-01-2000");
        Client client = clientService.getAllClients().get(0);
        Movie movie = new Movie("Filme", "Ação", 120, "Diretor", "Sinopse");
        Session session = new Session(LocalDate.now(), LocalTime.now(), roomService.getRoomById(1), movie, 30.0);
        Ticket ticket = new Ticket(client, session, session.getTicketValue(), PaymentMethod.CREDIT_CARD);

        clientService.addTicketToClient(client.getId(), ticket);

        GenericDynamicList<Ticket> history = clientService.getClientHistory(client.getId());
        assertEquals(1, history.size());
        assertEquals(ticket, history.get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddClientWithEmptyName() {
        clientService.addClient("", "email@test.com", "01-01-2000");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddClientWithEmptyEmail() {
        clientService.addClient("Nome", "", "01-01-2000");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetClientByInvalidId() {
        clientService.getClientById(0);
    }

    @Test(expected = RuntimeException.class)
    public void testGetClientByNonExistentId() {
        clientService.getClientById(999);
    }

    @Test
    public void testUpdateClientSuccessfully() {
        clientService.addClient("Antigo Nome", "antigo@email.com", "01-01-1990");
        Client client = clientService.getAllClients().get(0);

        clientService.updateClient(client.getId(), "Novo Nome", "novo@email.com", "05-05-1995");
        Client updatedClient = clientService.getClientById(client.getId());

        assertEquals("Novo Nome", updatedClient.getName());
        assertEquals("novo@email.com", updatedClient.getEmail());
        assertEquals("05-05-1995", updatedClient.getBirthday());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateNonExistentClient() {
        clientService.updateClient(999, "Nome", "email@email.com", "01-01-1990");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateClientWithEmptyName() {
        clientService.addClient("Nome", "email@email.com", "01-01-1990");
        Client client = clientService.getAllClients().get(0);

        clientService.updateClient(client.getId(), "", "novo@email.com", "01-01-1990");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateClientWithEmptyEmail() {
        clientService.addClient("Nome", "email@email.com", "01-01-1990");
        Client client = clientService.getAllClients().get(0);

        clientService.updateClient(client.getId(), "Nome Atualizado", "", "01-01-1990");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullTicketToClient() {
        clientService.addClient("Fulano", "fulano@mail.com", "01-01-2000");
        Client client = clientService.getAllClients().get(0);
        clientService.addTicketToClient(client.getId(), null);
    }

    @Test
    public void testGetEmptyClientHistory() {
        clientService.addClient("Sem Compras", "email@email.com", "31-12-1999");
        Client client = clientService.getAllClients().get(0);
        assertEquals(0, clientService.getClientHistory(client.getId()).size());
    }

    @Test
    public void testCalculateDiscount() {
        clientService.addClient("Fulano", "fulano@mail.com", "01-01-2000");
        Client client = clientService.getAllClients().get(0);
        clientService.addTicketToClient(
            client.getId(), 
            new Ticket(
                client, 
                new Session(LocalDate.now(), LocalTime.now(), RoomController.getRoomById(1), new Movie("Filme", "Ação", 120, "Diretor", "Sinopse"), 30.0), 
                30.0, 
                PaymentMethod.CREDIT_CARD
            )
        );
        clientService.addTicketToClient(client.getId(), new Ticket(client, new Session(LocalDate.now(), LocalTime.now(), new Room(1, 100, 100, 100), new Movie("Filme", "Ação", 120, "Diretor", "Sinopse"), 30.0), 30.0, PaymentMethod.CREDIT_CARD));
        clientService.addTicketTo
    }
}
