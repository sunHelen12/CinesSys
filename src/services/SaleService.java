package services;

import enums.PaymentMethod;
import models.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Helen Santos Rocha
 * @since 11/06/2025
 * @version 1.0
 *
 * Classe responsável por realizar o processo de venda de ingressos em lote.
 * Cada venda respeita o sistema de fidelidade implementado no TicketService.
 */
public class SaleService {

    private final TicketService ticketService;
    private final ClientService clientService;
    private final SessionService sessionService;
    private final LoyaltyService loyaltyService;

    /**
     * Construtor com injeção dos serviços necessários.
     */
    public SaleService(
            TicketService ticketService,
            ClientService clientService,
            SessionService sessionService,
            LoyaltyService loyaltyService
    ) {
        this.ticketService = ticketService;
        this.clientService = clientService;
        this.sessionService = sessionService;
        this.loyaltyService = loyaltyService;
    }

    /**
     * Processa a venda de múltiplos ingressos para um cliente, usando as regras do TicketService.
     *
     * @param client O cliente que está comprando
     * @param session A sessão desejada
     * @param quantity Quantidade de ingressos
     * @param paymentMethod Método de pagamento escolhido
     * @return Lista com todos os tickets gerados
     * @throws Exception se não houver assentos ou ocorrer erro na geração de algum ticket
     */
    public List<Ticket> processSale(Client client, Session session, int quantity, PaymentMethod paymentMethod) throws Exception {

        if (session.getTotalAvailableSeats() < quantity) {
            throw new IllegalArgumentException("Venda excedida: não há assentos suficientes.");
        }

        // Atualiza os assentos
        session.setTotalAvailableSeats(session.getTotalAvailableSeats() - quantity);

        List<Ticket> tickets = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            Ticket ticket = ticketService.purchaseTicket(
                    client.getId(),
                    session.getId(),
                    paymentMethod.name(), // usa o nome para depois converter
                    clientService,
                    sessionService,
                    loyaltyService
            );
            tickets.add(ticket);
        }

        return tickets;
    }
}