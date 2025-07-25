package services;

import java.util.LinkedList;
import java.util.List;

import controller.business.ClientController;
import controller.business.SessionController;
import enums.PaymentMethod;
import models.Client;
import models.Session;
import models.Ticket;
import repository.TicketRepository;
/**
 *
 * Serviço responsável por gerenciar operações relacionadas aos tickets.
 * Utiliza o {@link TicketRepository} como fonte de dados.
 *
 * @author Helen Santos Rocha
 * @author Thiago Ferreira Ribeiro
 * @since 11/06/2025
 * @version 1.0
 */
public class TicketService {
    private final TicketRepository ticketRepository;

    /**
     * Construtor para inicializar o serviço de tickets.
     *
     * @param ticketRepository O repositório de tickets.
     */
    public TicketService(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

     /**
     * Recupera todos os tickets armazenados.
     *
     * @return Uma lista dinâmica contendo todos os tickets.
     */
    public List<Ticket> getAllTickets(){
        return (LinkedList<Ticket>) ticketRepository.getAll();
    }

    /**
     * Busca um ticket pelo ID.
     *
     * @param id O ID do ticket a ser buscado.
     * @return O ticket correspondente ao ID.
     * @throws IllegalArgumentException Se o ID for menor ou igual a zero.
     * @throws RuntimeException         Se nenhum ticket for encontrado com o ID fornecido.
     */
    public Ticket getTicketById(int id){
        if(id <= 0){
            throw new IllegalArgumentException("O ID deve ser maior que zero!");
        }

        Ticket ticket = ticketRepository.getById(id);
        if(ticket == null){
            throw new RuntimeException("Nenhum ticket encontrado com o ID " + id);
        }

        return ticket;
    }

    /**
     * Remove um ticket pelo ID.
     *
     * @param id O ID do ticket a ser removido.
     */
    public void removeTicketById(int id){
        ticketRepository.removeById(id);
    }

    public Ticket purchaseTicket(int clientId, int sessionId, String paymentMethod) {

        // Buscar cliente
        Client client = ClientController.getClientById(clientId);
        if (client == null) {
            throw new IllegalArgumentException("Cliente com ID " + clientId + " não encontrado.");
        }

        // Buscar sessão
        Session session = SessionController.getSessionById(sessionId);
        if (session == null) {
            throw new IllegalArgumentException("Sessão com ID " + sessionId + " não encontrada.");
        }
        if (session.getTotalAvailableSeats() <= 0) {
            throw new IllegalStateException("Não há assentos disponíveis para a sessão " + sessionId + ".");
        }

        // Validar método de pagamento
        PaymentMethod method;
        try {
            method = PaymentMethod.fromDescription(paymentMethod);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Método de pagamento inválido: " + paymentMethod);
        }

        // Criar ticket com desconto
        Ticket ticket = new Ticket(client, session, session.getTicketValue(), method);

        // Adicionar ticket no repositório
        ticketRepository.add(ticket);

        // Adicionar ticket ao cliente
        client.addTicketToHistory(ticket);

        // Atualizando assentos disponíveis da sessão
        session.setTotalAvailableSeats(session.getTotalAvailableSeats()-1);

        return ticket;
    }

   /**
    * Salva os dados dos tickets no repositório.
    */
   public void saveData() {
       ticketRepository.saveData();
   }

   /**
    * Carrega os dados dos tickets do repositório.
    */
   public void loadData() {
       ticketRepository.loadData();
   }

   /**
    * Retorna a quantidade de tickets armazenados.
    *
    * @return o número total de tickets.
    */
   public int getSize() {
       return ticketRepository.getSize();
   }

    /**
     * Remove todos os tickets.
     */
    public void removeAllTickets(){
        ticketRepository.clear();
    }
}