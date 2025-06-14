package controller.business;

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
     * @param paymentMethod método de pagamento do ingresso.
     * @param clientService objeto para realizar a lógica de adicionar o Ticket ao Cliente
     * @param sessionService objeto para realizar a lógica de diminuir a quantidade de assentos disponíveis da sessão
     * @param loyaltyService objeto para calcular o desconto do cliente baseado em seus pontos
     */
    public static void purchaseTicket(int clientId, int sessionId, String paymentMethod,
                                      ClientService clientService, SessionService sessionService,
                                      LoyaltyService loyaltyService) {
        ticketService.purchaseTicket(clientId, sessionId, paymentMethod, clientService, sessionService, loyaltyService);
    }
}
