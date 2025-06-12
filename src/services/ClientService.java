package services;

import java.time.LocalDate;

import models.Client;
import models.Ticket;
import repository.ClientRepository;
import structures.list.GenericDynamicList;

/**
 * Classe de serviço responsável pela lógica de negócio dos clientes.
 *
 * @author Vinícius Nunes de Andrade
 * @author Thiago Ferreira Ribeiro
 * @author Kaique Silva Sousa
 * @version 2.0
 * @since 11/06/2025
 */
public class ClientService {
    private static final ClientRepository clientRepository = new ClientRepository();

    /**
     * Cria e adiciona um novo cliente ao sistema.
     *
     * @param name     Nome do cliente (não pode ser vazio).
     * @param email    Email do cliente (não pode ser vazio).
     * @param birthday Data de nascimento (não pode ser nula e deve ser no passado e no formato dd-mm-yyyy).
     * @return Uma string falando que o cliente foi adicionado.
     * @throws IllegalArgumentException se algum dado estiver inválido.
     */
    public static String addClient(String name, String email, String birthday) {
        if (name == null || birthday == null || email == null) {
            throw new IllegalArgumentException("Nome, data de nascimento e email não podem ser nulos.");
        } else if (name.isEmpty() || birthday.isEmpty() || email.isEmpty()) {
            throw new IllegalArgumentException("Nome, data de nascimento e email não podem ser vazios.");
        } else if (!email.contains("@")) {
            throw new IllegalArgumentException("Email inválido.");
        } else if (!birthday.matches("\\d{2}-\\d{2}-\\d{4}")) {
            throw new IllegalArgumentException("Data de nascimento deve estar no formato dd-mm-yyyy.");
        }

        LocalDate birthDateParsed = LocalDate.parse(birthday, java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        clientRepository.add(new Client(name, email, birthDateParsed));

        return "Cliente registrado com sucesso!";
    }

    /**
     * Retorna todos os clientes cadastrados.
     *
     * @return Uma GenericDynamicList contendo todos os clientes.
     */
    public static GenericDynamicList<Client> getAllClients() {
        return clientRepository.getAll();
    }

    /**
     * Método que atualiza um certo cliente.
     *
     * @param id       do cliente a ser atualizado
     * @param name     Nome do cliente (não pode ser vazio).
     * @param email    Email do cliente (não pode ser vazio).
     * @param birthday Data de nascimento (não pode ser nula e deve ser no passado e no formato dd-mm-yyyy).
     * @throws IllegalArgumentException se algum dado estiver inválido.
     * @return Uma string falando que o cliente foi atualizado.
     */
    public static String updateClient(int id, String name, String email, String birthday) {
        Client client = clientRepository.getById(id);
        if (client == null)
            throw new IllegalArgumentException("O cliente selecionado não existe!");
        if (name == null || birthday == null || email == null) {
            throw new IllegalArgumentException("Nome, data de nascimento e email não podem ser nulos.");
        } else if (name.isEmpty() || birthday.isEmpty() || email.isEmpty()) {
            throw new IllegalArgumentException("Nome, data de nascimento e email não podem ser vazios.");
        } else if (!email.contains("@")) {
            throw new IllegalArgumentException("Email inválido.");
        } else if (!birthday.matches("\\d{2}-\\d{2}-\\d{4}")) {
            throw new IllegalArgumentException("Data de nascimento deve estar no formato dd-mm-yyyy.");
        }

        LocalDate birthDateParsed = LocalDate.parse(birthday, java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        // Atualizando o próprio objeto:
        client.setName(name);
        client.setEmail(email);
        client.setBirthday(birthDateParsed);

        return "Cliente atualizado com sucesso!";
    }

    /**
     * Remove um cliente do sistema pelo ID.
     *
     * @param id
     * @return O cliente removido.
     * @throws IllegalArgumentException se o ID for inválido ou
     */
    public static Client removeClient(int id) {
        Client client = clientRepository.getById(id);
        if (client == null)
            throw new IllegalArgumentException("Cliente não encontrado!");
        clientRepository.removeById(id);
        return client;
    }

    /**
     * Busca um cliente pelo ID.
     *
     * @param id ID do cliente.
     * @return O cliente encontrado.
     * @throws IllegalArgumentException se o ID for inválido ou
     *                                  {@code RuntimeException} se não encontrar o cliente.
     */
    public static Client getClientById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("O ID deve ser maior que zero!");
        }

        Client client = clientRepository.getById(id);
        if (client == null) {
            throw new RuntimeException("Nenhum cliente encontrado com o ID " + id);
        }

        return clientRepository.getById(id);
    }

    /**
     * Adiciona um ticket ao histórico de compras de um cliente.
     *
     * @param clientId ID do cliente que está comprando.
     * @param ticket   O objeto Ticket a ser adicionado.
     * @throws IllegalArgumentException se o cliente não existir ou se o ticket for nulo.
     */
    public static void addTicketToClient(int clientId, Ticket ticket) {
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
    public static GenericDynamicList<Ticket> getClientHistory(int clientId) {
        Client client = getClientById(clientId);
        return client.getPurchasingHistory();
    }
}