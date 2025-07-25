package controller.business;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

import models.Ticket;
import repository.TicketRepository;
import services.TicketService;
/**
 * Classe responsável por controlar operações relacionadas aos tickets
 *
 * @author Vinícius Nunes de Andrade 
 * @author Kaique Silva Sousa
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
    public static List<Ticket> getAllTickets() {
        return (LinkedList<Ticket>) ticketService.getAllTickets();
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


    /**
     * Salva os dados dos tickets persistindo-os em arquivo.
     */
    public static void saveData() {
        ticketService.saveData();
    }

    /**
     * Carrega os dados dos tickets a partir do arquivo persistido.
     */
    public static void loadData() {
        ticketService.loadData();
    }

    /**
     * Retorna a quantidade total de tickets cadastrados.
     *
     * @return o número de tickets cadastrados.
     */
    public static int getSize() {
        return ticketService.getSize();
    }

    /**
     * Remove todos os tickets.
     */
    public static void removeAllTickets() {
        ticketService.removeAllTickets();
    }

}