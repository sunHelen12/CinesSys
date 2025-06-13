package services;

import enums.PaymentMethod;
import models.Client;
import models.Session;
import models.Ticket;
import repository.TicketRepository;
import structures.list.GenericDynamicList;

/**
 * @author Helen Santos Rocha
 * @author Thiago Ferreira Ribeiro
 * @since 11/06/2025
 * @version 2.0
 *
 * Serviço responsável por gerenciar operações relacionadas aos tickets.
 * Utiliza o {@link TicketRepository} como fonte de dados.
 */
public class TicketService {
    private final TicketRepository ticketRepository;

    /**
     * Construtor para inicializar o serviço de tickets.
     *
     * @param ticketRepository O repositório de tickets.
     */
    public TicketService(TicketRepository ticketRepository){
        this.ticketRepository = new TicketRepository();
    }

    /**
     * Cria e adiciona um ticket ao repositório.
     *
     * @param client        O cliente associado ao ticket.
     * @param session       A sessão relacionada ao ticket.
     * @param paymentMethod O método de pagamento usado para o ticket.
     * @return {@code true} se o ticket foi adicionado com sucesso.
     * @throws IllegalArgumentException Se algum argumento for inválido.
     */
     /**
     * Recupera todos os tickets armazenados.
     *
     * @return Uma lista dinâmica contendo todos os tickets.
     */
    public GenericDynamicList<Ticket> getAllTickets(){
        return ticketRepository.getAll();
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

    public Ticket purchaseTicket(int clientId, int sessionId, String paymentMethod,
                                 ClientService clientService, SessionService sessionService,
                                 LoyaltyService loyaltyService) {

        // Buscar cliente
        Client client = clientService.getClientById(clientId);
        if (client == null) {
            throw new IllegalArgumentException("Cliente com ID " + clientId + " não encontrado.");
        }

        // Buscar sessão
        Session session = sessionService.getSessionById(sessionId);
        if (session == null) {
            throw new IllegalArgumentException("Sessão com ID " + sessionId + " não encontrada.");
        }

        // Validar método de pagamento
        PaymentMethod method;
        try {
            method = PaymentMethod.fromDescription(paymentMethod);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Método de pagamento inválido: " + paymentMethod);
        }

        // Calcular desconto
        double discount = loyaltyService.calculateDiscount(clientId);
        double basePrice = 20.0; // Pode ser dinâmico no futuro
        double precoFinal = basePrice * (1 - discount / 100.0);

        // Criar ticket com desconto
        Ticket ticket = new Ticket(client, session, precoFinal, method);

        // Adicionar ticket no repositório
        ticketRepository.add(ticket);

        // Atualizar fidelidade do cliente
        loyaltyService.registerPoints(clientId, ticket);

        // Adicionar ticket no histórico do cliente (se não for automático)
        clientService.addTicketToClient(clientId, ticket);

        return ticket;
    }
}