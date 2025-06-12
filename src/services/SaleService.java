package services;

import enums.PaymentMethod;
import models.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por realizar o processo de venda de ingressos.
 *
 * @author Helen Santos Rocha
 * @since 11-06-2025
 * @version 1.0
 */

public class SaleService {

    /**
     * Realiza a venda de uma quantidade de ingressos para um cliente em uma determinada sessão.
     *
     * @param client O cliente que está realizando a compra.
     * @param session A sessão de cinema para a qual os ingressos estão sendo comprados.
     * @param quantity A quantidade de ingressos que o cliente deseja adquirir.
     * @param paymentMethod O método de pagamento escolhido pelo cliente.
     * @return Uma lista contendo todos os ingressos gerados para essa venda.
     * @throws IllegalArgumentException Se não houver assentos suficientes disponíveis na sessão.
     * @throws Exception Caso ocorra alguma falha ao adicionar o ticket ao histórico do cliente.
     *
     */
    public List<Ticket> efetuarVenda(Client client, Session session, int quantity, PaymentMethod paymentMethod) throws Exception {

        // Valida se há assentos disponíveis suficientes.
        if (session.getTotalAvailableSeats() < quantity) {
            throw new IllegalArgumentException("Venda excedida: não há assentos suficientes.");
        }

        // Atualiza a quantidade de assentos disponíveis na sessão.
        session.setTotalAvailableSeats(session.getTotalAvailableSeats() - quantity);

        // Lista para armazenar os ingressos gerados.
        List<Ticket> tickets = new ArrayList<>();

        // Para cada ingresso a ser vendido, cria um novo objeto Ticket.
        for (int i = 0; i < quantity; i++) {
            Ticket ticket = new Ticket(client, session, paymentMethod);

            // Adiciona o ingresso ao histórico de compras do cliente.
            client.addTicketToHistory(ticket);

            // Adiciona o ingresso à lista de ingressos gerados.
            tickets.add(ticket);
        }

        // Retorna todos os ingressos criados nesta venda.
        return tickets;
    }
}
