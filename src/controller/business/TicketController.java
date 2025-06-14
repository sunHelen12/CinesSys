package controller.business;

import models.Client;
import repository.TicketRepository;
import services.ClientService;
import services.LoyaltyService;
import services.SessionService;
import services.TicketService;

/**
 * Classe responsável por controlar operações relacionadas aos tickets]
 *
 * @author Vinícius Nunes de Andrade
 * @version 1.0
 * @since 13-06-2025
 */
public class TicketController {
    private static final TicketService ticketService = new TicketService(new TicketRepository());

    /**
     * Método responsável por realizar uma compra de um ingresso.
     *
     * @param clientId id do cliente realizando a compra
     * @param sessionId id da sessão que a compra será relizada
     * @param paymentMethod método de pagamento do ingresso
     */
    public static void purchaseTicket(int clientId, int sessionId, String paymentMethod) {
        ticketService.purchaseTicket(clientId, sessionId, paymentMethod);
    }
}
