package controller.business;

import enums.PaymentMethod;
import models.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Classe de testes unitários para a classe SaleController.
 *
 * @author Vinícius Nunes de Andrade
 * @author Kaique Silva sousa
 * @version 1.0
 * @since 15-06-2025
 */
public class SaleControllerTest {

    private Client client;
    private Session session;
    private Movie movie;

    /**
     * Prepara o ambiente para cada teste.
     * Cria um cliente, um filme e uma sessão padrão para serem utilizados
     * nos cenários de teste, garantindo um estado inicial limpo.
     */
    @Before
    public void setUp() {
        ClientController.addClient("João", "joao@email.com", "12-10-2003");
        movie = new Movie("Matrix", "Sci-Fi", 136, "R", "Neo discovers reality.");
        session = new Session(LocalDate.now(), LocalTime.now(), RoomController.getRoomById(1), movie, 20.0, 10);
        Room.resetIdGenerator();
        Client.resetIdGenerator();
    }

    /**
     * Testa o processo de uma venda de múltiplos ingressos bem-sucedida.
     * Verifica o número de tickets gerados, a atualização dos assentos
     * disponíveis na sessão e o histórico de compras do cliente.
     *
     * @throws Exception se ocorrer um erro durante o processo de venda.
     */
    @Test
    public void testSuccessfulTicketSale() throws Exception {
        int quantity = 3;
        List<Ticket> tickets = SaleController.processSale(client, session, quantity, PaymentMethod.CASH);

        assertEquals(quantity, tickets.size());
        assertEquals(7, session.getTotalAvailableSeats()); // 10 - 3 = 7
        assertEquals(quantity, client.getPurchasingHistory().size()); // Histórico do cliente atualizado
    }

    /**
     * Testa se a tentativa de comprar mais ingressos do que os disponíveis
     * na sessão lança a exceção esperada.
     *
     * @throws Exception é esperada ao tentar exceder os assentos.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testTicketSaleExceedingAvailableSeats() throws Exception {
        int quantity = 15; // Mais do que os 10 disponíveis
        SaleController.processSale(client, session, quantity, PaymentMethod.CASH);
    }

    /**
     * Verifica se os atributos do ticket gerado (cliente, sessão, método de pagamento)
     * estão corretos após uma venda bem-sucedida.
     *
     * @throws Exception se ocorrer um erro durante o processo de venda.
     */
    @Test
    public void testTicketAttributesAfterSale() throws Exception {
        int quantity = 1;
        List<Ticket> tickets = SaleController.processSale(client, session, quantity, PaymentMethod.PIX);

        Ticket ticket = tickets.get(0);

        assertEquals(client, ticket.getClient());
        assertEquals(session, ticket.getSession());
        assertEquals(PaymentMethod.PIX, ticket.getPaymentMethod());
    }

    /**
     * Testa se o número de assentos disponíveis na sessão é decrementado
     * corretamente após múltiplas vendas consecutivas.
     *
     * @throws Exception se ocorrer um erro durante o processo de venda.
     */
    @Test
    public void testMultipleSalesReduceAvailableSeatsCorrectly() throws Exception {
        // Primeira venda de 4 ingressos
        SaleController.processSale(client, session, 4, PaymentMethod.CASH);
        assertEquals(6, session.getTotalAvailableSeats());

        // Segunda venda de 3 ingressos
        SaleController.processSale(client, session, 3, PaymentMethod.DEBIT_CARD);
        assertEquals(3, session.getTotalAvailableSeats());
    }

    /**
     * Testa especificamente se o histórico de compras do cliente é
     * atualizado corretamente com o número de tickets vendidos.
     *
     * @throws Exception se ocorrer um erro durante o processo de venda.
     */
    @Test
    public void testClientTicketHistoryIsUpdated() throws Exception {
        assertEquals(0, client.getPurchasingHistory().size());
        SaleController.processSale(client, session, 2, PaymentMethod.CASH);
        assertEquals(2, client.getPurchasingHistory().size());
    }
}