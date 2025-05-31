package repository;

import models.Session;
import structures.list.GenericDynamicList;

/**
 * Classe que gerencia as sessões (Session) do cinema.
 * Usamos uma GenericDynamicList como "banco de dados".
 */
public class SessionRepository {
    private GenericDynamicList<Session> sessions = new GenericDynamicList<>();

    /**
     * Adiciona uma nova sessão a lista.
     *
     * @param session A sessão a ser adicionada.
     */
    public void add(Session session){
        try {
            sessions.append(session);
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
     * Retorna todas as sessões cadastradas.
     *
     * @return A GenericDynamicList contendo todas as sessões.
     */
    public GenericDynamicList<Session> getAll(){
        return sessions;
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
}