package repository;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import models.Client;
import models.Session;
import models.Ticket;

/**
 * Repositório para gerenciar os dados dos tickets.
 * Responsável por armazenar, recuperar e remover tickets.
 * @author Vinícius Nunes de Andrade
 * @author Thiago Ferreira Ribeiro
 * @since 11/06/2025
 * @version 2.0
 */
public class TicketRepository {
    private List<Ticket> tickets = new LinkedList<>();
    private final String FILE_PATH = "data/ticket.txt";


    /**
     * Adiciona um ticket ao repositório.
     *
     * @param ticket O ticket a ser adicionado.
     */
    public void add(Ticket ticket){
        try {
            tickets.add(ticket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Busca um ticket pelo ID.
     *
     * @param id O ID do ticket a ser buscado.
     * @return O ticket correspondente ao ID ou {@code null} se não encontrado.
     */
    public Ticket getById(int id){
        for(int i = 0; i < tickets.size(); i++){
            if(tickets.get(i).getId() == id){
                return tickets.get(i);
            }
        }
        return null;
    }

    /**
     * Atualiza um ticket selecionado
     *
     * @param id do ticket a ser atualizada
     * @param ticket novo ticket que será atualizado
     */
    public void update(int id, Ticket ticket){
        if(getById(id) == null)
            throw new IllegalArgumentException("Sessão não existe!");
        tickets.set(getIndex(id), ticket);
    }

    /**
     * Método auxiliar para pegar o index de um certo ticket
     *
     * @param id da sessão
     * @return se o id existir, retorna o index requerido
     *         caso não existe, retorna -1
     */
    private int getIndex(int id){
        for(int i = 0; i < tickets.size(); i++){
            if(tickets.get(i).getId() == id){
                return i;
            }
        }
        return -1;
    }

    /**
     * Retorna todos os tickets armazenados no repositório.
     *
     * @return Uma lista dinâmica contendo todos os tickets.
     */
    public List<Ticket> getAll(){
        return (LinkedList<Ticket>) tickets;
    }

    /**
     * Remove um ticket pelo ID.
     *
     * @param id O ID do ticket a ser removido.
     * @return {@code true} se o ticket foi removido com sucesso, {@code false} caso contrário.
     */
    public boolean removeById(int id){
        for(int i = 0; i < tickets.size(); i++){
            if (tickets.get(i).getId() == id) {
                try {
                    tickets.remove(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Remove todos os tickets do repositório.
     */
    public void clear(){
        tickets.clear();
    }

   /**
    * Salva a lista de tickets no arquivo especificado em FILE_PATH.
    * Utiliza serialização para persistir os dados dos tickets.
    * Em caso de erro de E/S, imprime o stack trace.
    */
   public void saveData() {
       try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
           oos.writeObject(tickets);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

   /**
    * Carrega os dados dos tickets a partir do arquivo especificado.
    * Se o arquivo existir, os tickets são desserializados e carregados na lista.
    * Também atualiza o gerador de IDs do cliente com base na quantidade de tickets carregados.
    *
    * Suprime o aviso de conversão não verificada devido à desserialização.
    */
   @SuppressWarnings("unchecked")
   public void loadData() {
       File arquivo = new File(FILE_PATH);
       if (arquivo.exists()) {
           try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
               tickets = (List<Ticket>) ois.readObject();
               Ticket.updateIdGenerator(getSize());
           } catch (IOException | ClassNotFoundException e) {
               e.printStackTrace();
           }
       }
   }

    /**
     * Retorna a quantidade de tickets armazenados no repositório.
     *
     * @return o número total de tickets.
     */
    public int getSize() {
        return tickets.size();
    }
}