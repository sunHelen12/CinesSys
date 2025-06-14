package controller.business;

import models.Client;
import models.Ticket;
import repository.TicketRepository;
import services.ClientService;
import services.LoyaltyService;
import services.SessionService;
import services.TicketService;
import structures.list.GenericDynamicList;

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
    public static Ticket purchaseTicket(int clientId, int sessionId, String paymentMethod) {
        return ticketService.purchaseTicket(clientId, sessionId, paymentMethod);
    }

    /**
     * Reecupera todos os tickets cadastrados.
     * 
     * @return uma lista dinâmica contendo todos os tickets.
     */
    public static GenericDynamicList<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    /**
     * Recupera um ticket pelo seu ID.
     * 
     * @param id ID do ticket a ser recuperado.
     * @return o ticket correspondente ao ID fornecido.
     */
    public static Ticket getTicketById(int id) {
        return ticketService.getTicketById(id);
    }

    /**
     * Remove um ticket pelo seu ID.
     * 
     * @param id ID do ticket a ser removido.
     */
    public static void removeTicketById(int id) {
        ticketService.removeTicketById(id);
    }
}