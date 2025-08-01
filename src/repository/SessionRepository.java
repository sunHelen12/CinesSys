package repository;

import java.io.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import models.Client;
import models.Session;
import models.Ticket;

/**
 * Classe que gerencia as sessões (Session) do cinema.
 * Usamos uma LinkedList como "banco de dados".
 *
 * @author Thiago Ferreira Ribeiro
 * @since 11/06/2025
 * @version 2.0
 */
public class SessionRepository {
    private List<Session> sessions = new LinkedList<>();
    private final String FILE_PATH = "data/session.txt";


    /**
     * Adiciona uma nova sessão a lista.
     *
     * @param session A sessão a ser adicionada.
     */
    public void add(Session session){
        try {
            sessions.add(session);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Busca uma sessão pelo seu ID.
     *
     * @param id Identificador da sessão.
     * @return A sessão com o ID fornecido, ou null se não existir.
     */
    public Session getById(int id){
        for(int i = 0; i < sessions.size(); i++){
            if(sessions.get(i).getId() == id){
                return sessions.get(i);
            }
        }
        return null;
    }

    /**
     * Atualiza uma sessão selecionada
     *
     * @param id da sessão a ser atualizada
     * @param session nova sessão que será atualizada
     */
    public void update(int id, Session session){
        if(getById(id) == null)
            throw new IllegalArgumentException("Sessão não existe!");
        sessions.set(getIndex(id), session);
    }

    /**
     * Método auxiliar para pegar o index de uma certa sessão
     *
     * @param id da sessão
     * @return se o id existir, retorna o index requerido
     *         caso não existe, retorna -1
     */
    private int getIndex(int id){
        for(int i = 0; i < sessions.size(); i++){
            if(sessions.get(i).getId() == id){
                return i;
            }
        }
        return -1;
    }

    /**
     * Retorna todas as sessões agendadas para uma dada data.
     *
     * @param date A data pela qual se quer filtrar as sessões.
     * @return Uma LinkedList contendo todas as sessões cuja data
     *         seja igual à informada (pode retornar lista vazia se não houver nenhuma).
     */
    public List<Session> getByDate(LocalDate date){
        List<Session> sessionsByDate = new LinkedList<>();
        for(int i = 0; i < sessions.size(); i++){
            if (sessions.get(i).getDate().equals(date)) {
                try {
                    sessionsByDate.add(sessions.get(i));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return (LinkedList<Session>) sessionsByDate;
    }

    /**
     * Retorna todas as sessões cadastradas.
     *
     * @return A LinkedList contendo todas as sessões.
     */
    public List<Session> getAll(){
        return (LinkedList<Session>) sessions;
    }
    
    /**
     * Remove a sessão com o ID especificado.
     *
     * @param id Identificador da sessão a ser removida.
     * @return true se a sessão foi removida; false se não encontrou nenhuma com aquele ID.
     */
    public boolean removeById(int id){
        for(int i = 0; i < sessions.size(); i++){
            if (sessions.get(i).getId() == id) {
                try {
                    sessions.remove(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Salva a lista de sessões no arquivo especificado em FILE_PATH.
     * Utiliza serialização para persistir os dados.
     * Em caso de erro de IO, imprime o stack trace.
     */
    public void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(sessions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carrega a lista de sessões do arquivo especificado em FILE_PATH.
     * Se o arquivo existir, desserializa a lista de sessões e atualiza o gerador de IDs dos clientes.
     * Em caso de erro de IO ou de classe não encontrada, imprime o stack trace.
     */

    @SuppressWarnings("unchecked")
    public void loadData() {
        File arquivo = new File(FILE_PATH);
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
                sessions = (List<Session>) ois.readObject();
                Session.updateIdGenerator(getSize());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Retorna a quantidade de sessões cadastradas.
     *
     * @return O número de sessões na lista.
     */
    public int getSize() {
        return sessions.size();
    }

    /**
     * Remove todas as sessões cadastradas.
     */
    public void clear(){
        sessions.clear();
    }
}