package services;

import java.time.LocalDate;
import java.util.Objects;

import models.Client;
import models.Session;
import models.Ticket;
import repository.ClientRepository;
import structures.list.GenericDynamicList;

/**
 * Classe de serviço responsável pela lógica de negócio dos clientes.
 * @author Vinícius Nunes de Andrade
 * @author Thiago Ferreira Ribeiro
 * @since 11/06/2025
 * @version 2.0
 */
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    /**
     * Cria e adiciona um novo cliente ao sistema.
     *
     * @param name     Nome do cliente (não pode ser vazio).
     * @param email    Email do cliente (não pode ser vazio).
     * @param birthday Data de nascimento (não pode ser nula e deve ser no passado).
     * @throws IllegalArgumentException se algum dado estiver inválido.
     */
    public void addClient(String name, String email, LocalDate birthday){
        //Verificações básicas
        if(name == null || name.isBlank()){
            throw new IllegalAccessError("O nome do cliente não pode ser vazio!");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("O email do cliente não pode ser vazio!");
        }

        //Cria o cliente e envia para o Repository
        Client client = new Client(name, email, birthday);
        clientRepository.add(client);
    }

    /**
     * Retorna todos os clientes cadastrados.
     *
     * @return Uma GenericDynamicList contendo todos os clientes.
     */
    public GenericDynamicList<Client> getAllClients(){
        return clientRepository.getAll();
    }

    /**
     *
     * @param id do cliente a ser atualizado
     * @param name     Nome do cliente (não pode ser vazio).
     * @param email    Email do cliente (não pode ser vazio).
     * @param birthday Data de nascimento (não pode ser nula e deve ser no passado).
     * @throws IllegalArgumentException se algum dado estiver inválido.
     */
    public void updateClient(int id, String name, String email, LocalDate birthday){
        if(clientRepository.getById(id) == null)
            throw new IllegalArgumentException("A sessão selecionada não existe!");
        //Verificações básicas
        if(name == null || name.isBlank()){
            throw new IllegalAccessError("O nome do cliente não pode ser vazio!");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("O email do cliente não pode ser vazio!");
        }

        Client newClient = new Client(name, email, birthday);
        clientRepository.update(id, newClient);
    }
    /**
     * Busca um cliente pelo ID.
     *
     * @param id ID do cliente.
     * @return O cliente encontrado.
     * @throws IllegalArgumentException se o ID for inválido ou
     *                                  {@code RuntimeException} se não encontrar o cliente.
     */
    public Client getClientById(int id){
        if(id <= 0){
            throw new IllegalArgumentException("O ID deve ser maior que zero!");
        }

        Client movie = clientRepository.getById(id);
        if(movie == null){
            throw new RuntimeException("Nenhum filme encontrado com o ID " + id);
        }

        return clientRepository.getById(id);
    }

    /**
     * Adiciona um ticket ao histórico de compras de um cliente.
     *
     * @param clientId   ID do cliente que está comprando.
     * @param ticket     O objeto Ticket a ser adicionado.
     * @throws IllegalArgumentException se o cliente não existir ou se o ticket for nulo.
     */
    public void addTicketToClient(int clientId, Ticket ticket) {
        if (ticket == null) {
            throw new IllegalArgumentException("O ticket não pode ser nulo!");
        }
        Client client = getClientById(clientId);
        client.addTicketToHistory(ticket);
    }

    /**
     * Retorna o histórico de compras de um cliente específico.
     *
     * @param clientId ID do cliente.
     * @return Uma GenericDynamicList de Tickets do cliente.
     * @throws IllegalArgumentException se o ID for inválido ou cliente não existir.
     */
    public GenericDynamicList<Ticket> getClientHistory(int clientId) {
        Client client = getClientById(clientId);
        return client.getPurchasingHistory();
    }
}