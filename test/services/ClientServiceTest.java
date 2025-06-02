package services;

import enums.PaymentMethod;
import models.*;
import org.junit.Test;
import repository.ClientRepository;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class ClientServiceTest {
    private static final ClientRepository repository = new ClientRepository();
    private static final ClientService service = new ClientService(repository);
    @Test
    public void testConstructor(){
        assertEquals(repository.getAll().print(), service.getAllClients().print());
    }
    @Test
    public void testAddClient(){
        service.addClient("Name", "email", LocalDate.now());
        assertEquals(repository.getAll().print(), service.getAllClients().print());
    }
    @Test
    public void testAddMultipleClients(){
        service.addClient("Name", "email", LocalDate.now());
        service.addClient("Name", "email", LocalDate.now());
        service.addClient("Name", "email", LocalDate.now());
        service.addClient("Name", "email", LocalDate.now());
        service.addClient("Name", "email", LocalDate.now());
        assertEquals(repository.getAll().print(), service.getAllClients().print());
    }

    @Test
    public void testGetClientById(){
        ClientRepository repo1 = new ClientRepository();
        ClientService serv1 = new ClientService(repo1);
        serv1.addClient("João", "joão@email.com", LocalDate.now());
        assertEquals("João", serv1.getClientById(1).getName());
    }

    @Test
    public void testAddTicketToClient(){
        Ticket ticket = new Ticket(service.getClientById(1),
                                   new Session(LocalDate.now(), LocalTime.now(), Room.room1, new Movie("a", "a", 90, "a", "a"), 40.0)
                                   PaymentMethod.CASH);
        service.addTicketToClient(1, ticket);
        assertEquals(ticket, service.getClientHistory(1).get(1));
    }
}
