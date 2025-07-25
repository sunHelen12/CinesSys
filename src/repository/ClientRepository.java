package repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

import models.Client;
/**
 * Classe que gerencia a coleção de clientes.
 * @author Vinícius Nunes de Andrade
 * @author Thiago Ferreira Ribeiro
 * @since 11/06/2025
 * @version 2.0
 */
public class ClientRepository {
    private List<Client> clients = new LinkedList<>();
    private final String FILE_PATH = "data/client.txt";

    /**
     * Adiciona um cliente ao repositório.
     *
     * @param client O cliente a ser adicionado.
     */
    public void add(Client client){
        try {
            clients.add(client);
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
     * Atualiza um cliente selecionado
     *
     * @param id do cliente a ser atualizada
     * @param client novo cliente que será atualizado
     */
    public void update(int id, Client client){
        if(getById(id) == null)
            throw new IllegalArgumentException("Cliente não existe!");
        clients.set(getIndex(id), client);
    }

    /**
     * Método auxiliar para pegar o index de uma certa sessão
     *
     * @param id do cliente
     * @return se o id existir, retorna o index requerido
     *         caso não existe, retorna -1
     */
    private int getIndex(int id){
        for(int i = 0; i < clients.size(); i++){
            if(clients.get(i).getId() == id){
                return i;
            }
        }
        return -1;
    }

    /**
     * Retorna todos os clientes cadastrados.
     *
     * @return Uma GenericDynamicList de clientes.
     */
    public List<Client> getAll(){
        return (LinkedList<Client>) clients;
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

    /**
     * Remove todos os clientes do repositório. Limpando a lista de clientes.
     */
    public void clear(){
        clients.clear();
    }

    public void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(clients);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadData() {
        File arquivo = new File(FILE_PATH);
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
                clients = (List<Client>) ois.readObject();
                Client.updateIdGenerator(getSize());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public int getSize() {
        return clients.size();
    }
}