package repository;

import models.Ticket;
import structures.list.GenericDynamicList;

/**
 * Repositório para gerenciar os dados dos tickets.
 * Responsável por armazenar, recuperar e remover tickets.
 */
public class TicketRepository {
    private GenericDynamicList<Ticket> tickets = new GenericDynamicList<>();

    /**
     * Adiciona um ticket ao repositório.
     *
     * @param ticket O ticket a ser adicionado.
     */
    public void add(Ticket ticket){
        try {
            tickets.append(ticket);
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
     * Retorna todos os tickets armazenados no repositório.
     *
     * @return Uma lista dinâmica contendo todos os tickets.
     */
    public GenericDynamicList<Ticket> getAll(){
        return tickets;
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
}