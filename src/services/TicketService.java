package services;

import enums.PaymentMethod;
import models.Client;
import models.Session;
import models.Ticket;
import repository.TicketRepository;
import structures.list.GenericDynamicList;

/**
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
    public boolean addTicket(Client client, Session session, PaymentMethod paymentMethod){
        //Verificações básicas
        if(client == null){
            throw new IllegalAccessError("O nome do cliente não pode ser vazio!");
        }
        if (session == null) {
            throw new IllegalArgumentException("O ticket deve ter uma sessão!");
        }
        if (paymentMethod == null) {
            throw new IllegalArgumentException("O ticket deve ter um método de pagamento!");
        }

        //Cria o ticket e envia para o Repository
        Ticket ticket = new Ticket(client, session, paymentMethod);
        ticketRepository.add(ticket);
        return true;
    }

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
}