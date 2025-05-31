package repository;

import models.Client;
import structures.list.GenericDynamicList;

/**
 * Classe que gerencia a coleção de clientes.
 */
public class ClientRepository {
    private GenericDynamicList<Client> clients = new GenericDynamicList<>();

    /**
     * Adiciona um cliente ao repositório.
     *
     * @param client O cliente a ser adicionado.
     */
    public void add(Client client){
        try {
            clients.append(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Busca um cliente pelo ID.
     *
     * @param id ID do cliente.
     * @return O cliente correspondente, ou null se não encontrado.
     */
    public Client getById(int id){
        for(int i = 0; i < clients.size(); i++){
            if(clients.get(i).getId() == id){
                return clients.get(i);
            }
        }
        return null;
    }

    /**
     * Retorna todos os clientes cadastrados.
     *
     * @return Uma GenericDynamicList de clientes.
     */
    public GenericDynamicList<Client> getAll(){
        return clients;
    }

    /**
     * Remove um cliente pelo ID informado.
     *
     * @param id ID do cliente a ser removido.
     * @return true se a remoção for bem-sucedida, false caso não exista cliente com esse ID.
     */
    public boolean removeById(int id){
        for(int i = 0; i < clients.size(); i++){
            if (clients.get(i).getId() == id) {
                try {
                    clients.remove(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
        }
        return false;
    }
}