package controller.business;

import enums.PaymentMethod;
import models.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SaleControllerTest {

    private Client client;
    private Session session;
    private Movie movie;

    @Before
    public void setUp() {
        ClientController.addClient("João", "joao@email.com", "12-10-2003");
        movie = new Movie("Matrix", "Sci-Fi", 136, "R", "Neo discovers reality.");
        session = new Session(LocalDate.now(), LocalTime.now(), RoomController.getRoomById(1), movie, 20.0, 10);
        Room.resetIdGenerator();
        Client.resetIdGenerator();
    }

    @Test
    public void testSuccessfulTicketSale() throws Exception {
        int quantity = 3;
        List<Ticket> tickets = SaleController.processSale(client, session, quantity, PaymentMethod.CASH);

        assertEquals(quantity, tickets.size());
        assertEquals(7, session.getTotalAvailableSeats()); // 10 - 3 = 7
        assertEquals(quantity, client.getPurchasingHistory().size()); // Histórico do cliente atualizado
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTicketSaleExceedingAvailableSeats() throws Exception {
        int quantity = 15; // Mais do que os 10 disponíveis
        SaleController.processSale(client, session, quantity, PaymentMethod.CASH);
    }

    @Test
    public void testTicketAttributesAfterSale() throws Exception {
        int quantity = 1;
        List<Ticket> tickets = SaleController.processSale(client, session, quantity, PaymentMethod.PIX);

        Ticket ticket = tickets.get(0);

        assertEquals(client, ticket.getClient());
        assertEquals(session, ticket.getSession());
        assertEquals(PaymentMethod.PIX, ticket.getPaymentMethod());
    }

    @Test
    public void testMultipleSalesReduceAvailableSeatsCorrectly() throws Exception {
        // Primeira venda de 4 ingressos
        SaleController.processSale(client, session, 4, PaymentMethod.CASH);
        assertEquals(6, session.getTotalAvailableSeats());

        // Segunda venda de 3 ingressos
        SaleController.processSale(client, session, 3, PaymentMethod.DEBIT_CARD);
        assertEquals(3, session.getTotalAvailableSeats());
    }

    @Test
    public void testClientTicketHistoryIsUpdated() throws Exception {
        assertEquals(0, client.getPurchasingHistory().size());
        SaleController.processSale(client, session, 2, PaymentMethod.CASH);
        assertEquals(2, client.getPurchasingHistory().size());
    }
}
