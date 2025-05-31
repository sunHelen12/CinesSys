package services;

import models.Client;
import models.Ticket;
import repository.ClientRepository;
import structures.list.GenericDynamicList;

/**
 * Serviço que aplica regras de fidelização aos clientes.
 * 
 * - calcularDesconto: converte pontos acumulados em desconto (%).
 * - registrarPontos: soma pontos ao cliente cada vez que ele compra um ticket.
 */
public class LoyaltyService {
    private final ClientRepository clientRepository;
    
    /**
     * Construtor que recebe o ClientRepository para acessar e atualizar.
     *
     * @param clientRepository repositório de clientes
     */
    public LoyaltyService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    /**
     * Calcula o desconto para o cliente com base nos pontos acumulados.
     * 
     * Regra de exemplo:
     *  - Cada 10 pontos equivalem a 1% de desconto.
     *  - Máximo de 20% de desconto, independente dos pontos.
     *
     * @param clientId ID do cliente que receberá o desconto.
     * @return valor de desconto (porcentagem entre 0.0 e 20.0).
     * @throws IllegalArgumentException se o ID for inválido ou o cliente não existir.
     */
    public double calculateDiscount(int clientId){
        //Verificações básicas
        if (clientId <= 0) {
            throw new IllegalArgumentException("O ID do cliente deve ser maior que zero!");
        }
        Client client = clientRepository.getById(clientId);
        if (client == null) {
            throw new IllegalArgumentException("Cliente não encontrado com ID " + clientId);
        }

        //Pegando os pontos acumulados
        int pontos = client.getPoints();

        //Converte os pontos em porcentagem de desconto (10 pontos = 1% de desconto)
        double desconto = pontos/10;

        //Teto máximo de 20% de desconto
        if (desconto > 20.0) {
            desconto = 20.0;
        }

        return desconto;
    }

    /**
     * Registra os pontos de fidelidade para o cliente ao comprar um novo ticket.
     * 
     * Regra de exemplo:
     *  - A cada ticket comprado, o cliente ganha 5 pontos.
     *  - Esse método deve ser chamado sempre que um novo ticket for adicionado ao histórico.
     *
     * @param clientId ID do cliente que está comprando o ticket.
     * @param ticket   O objeto Ticket que acaba de ser comprado.
     * @throws IllegalArgumentException se o ID for inválido ou o cliente não existir.
     */
    public void registerPoints(int clientId, Ticket ticket){
        //Verificações básicas
        if (clientId <= 0) {
            throw new IllegalArgumentException("O ID do cliente deve ser maior que zero!");
        }
        Client client = clientRepository.getById(clientId);
        if (client == null) {
            throw new IllegalArgumentException("Cliente não encontrado com ID " + clientId);
        }

        boolean insertTicket = client.addTicketToHistory(ticket);
        if (!insertTicket) {
            // Se falhar ao inserir no histórico, aborta e não dá pontos
            throw new RuntimeException("Falha ao adicionar ticket ao histórico do cliente.");
        }

        //Regra de ganhar 5 pontos por ticket
        int pontosGanhos = 5;
        client.addPoints(pontosGanhos);
    }
}
