package controller.business;

import java.util.LinkedList;
import java.util.List;

import models.Client;
import models.Ticket;
import repository.ClientRepository;
import services.ClientService;
/**
 * Classe de controle para a lógica de negócio dos clientes.
 * @author Kaique Silva Sousa 
 * @since 11/06/2025
 * @version 1.0
 */
public class ClientController {
    
    private static final ClientService clientService = new ClientService(new ClientRepository());

    /**
     * Adiciona um novo cliente ao sistema chamando o método addClient da classe ClientService.
     * @param name Nome do cliente.
     * @param email Email do cliente.
     * @param birthday Data de nascimento do cliente.
     * @return Mensagem de sucesso ou erro.
    */
    public static String addClient(String name, String email, String birthday) {
        return clientService.addClient(name, email, birthday);
    }

    /**
     * Atualiza um cliente existente chamando o método updateClient da classe ClientService.
     * @param id ID do cliente a ser atualizado.
     * @param name Nome do cliente.
     * @param email Email do cliente.
     * @param birthday Data de nascimento do cliente.
     * @return Mensagem de sucesso ou erro.
    */
    public static String updateClient(int id, String name, String email, String birthday) {
        return clientService.updateClient(id, name, email, birthday);
    }

    /**
     * Remove um cliente existente chamando o método removeClient da classe ClientService.
     * @param id ID do cliente a ser removido.
     * @return Cliente removido.
    */
    public static Client removeClient(int id) {
        return clientService.removeClient(id);
    }

    /**
     * Seleciona um cliente pelo ID chamando o método getClientById da classe ClientService.
     * @param id ID do cliente.
     * @return Cliente selecionado.
    */
    public static Client getClientById(int id) {
        return clientService.getClientById(id);
    }

    /**
     * Adiciona um ticket ao histórico de compras de um cliente chamando o método addTicketToClient da classe ClientService.
     * @param clientId ID do cliente.
     * @param ticket Ticket a ser adicionado.
     * @return Mensagem de sucesso ou erro.
    */
    public static void addTicketToClient(int clientId, Ticket ticket) {
        clientService.addTicketToClient(clientId, ticket);
    }

    /**
     * Retorna uma lista com todos os clientes cadastrados chamando o método getAllClients da classe ClientService.
     * @return lista com todos os clientes cadastrados.
     */
    public static List<Client> getAllClients(){
        return (LinkedList<Client>) clientService.getAllClients();
    }
 
    /**
     * Pega o histórico de compras de um cliente chamando o método getClientHistory da classe ClientService.
     * @param clientId ID do cliente.
     * @return Histórico de compras do cliente.
     */
    public static List<Ticket> getClientHistory(int clientId) {
        return (LinkedList<Ticket>) clientService.getClientHistory(clientId);
    }

    /**
     * Remove todos os clientes cadastrados chamando o método removeAllClients da classe ClientService.
     */
    public static void removeAllClients() {
        clientService.removeAllClients();
    }

    /**
     * Remove um ticket específico do histórico de compras de um cliente.
     * Delega a chamada para o método correspondente em ClientService.
     * * @param clientId O ID do cliente.
     * @param ticket O objeto Ticket a ser removido do histórico.
     */
    public static void removeTicketFromHistory(int clientId, Ticket ticket) {
        clientService.removeTicketFromHistory(clientId, ticket);
    }

}
