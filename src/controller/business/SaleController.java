package controller.business;

import java.util.ArrayList;
import java.util.List;

import enums.PaymentMethod;
import models.Client;
import models.Session;
import models.Ticket;

/**
 * Classe responsável por controlar operações relacionadas às vendas de ingressos.
 * 
 * @author Helen Santos Rocha
 * @since 11/06/2025
 * @version 1.0
 */
public class SaleController {

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
    public static List<Ticket> processSale(Client client, Session session, int quantity, PaymentMethod paymentMethod) throws Exception {

        if (session.getTotalAvailableSeats() < quantity) {
            throw new IllegalArgumentException("Venda excedida: não há assentos suficientes.");
        }

        // Atualiza os assentos
        session.setTotalAvailableSeats(session.getTotalAvailableSeats() - quantity);

        List<Ticket> tickets = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            Ticket ticket = TicketController.purchaseTicket(
                    client.getId(),
                    session.getId(),
                    paymentMethod.name() // usa o nome para depois converter
            );
            tickets.add(ticket);
        }

        return tickets;
    }
    
}
