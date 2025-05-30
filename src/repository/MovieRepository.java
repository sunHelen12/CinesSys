package repository;

import java.util.ArrayList;
import java.util.List;

import models.Movie;
import structures.list.GenericDynamicList;

/**
 * Classe que manipula diretamente a lista de filmes.
 * Utiliza a estrutura GenericDynamicList como um banco de dados para armazenar os filmes.
 */
public class MovieRepository {
    private GenericDynamicList<Movie> movies = new GenericDynamicList<>();
    
    /**
     * Adiciona um filme à lista de filmes
     * 
     * @param movie O filme que será adicionado à lista.
     */
    public void add(Movie movie){
        try {
            movies.append(movie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retorna um filme a partir do ID
     * 
     * @param id O identificador único do filme.
     * @return O filme com o ID fornecido ou {@code null} se não for encontrado
     */
    public Movie getById(int id){
        for(int i = 0; i < movies.size(); i++){
            if (movies.get(i).getId() == id) {
                return movies.get(i);
            }
        }
        return null;
    }

    /**
     * Retorna todos os filmes armazenados.
     * 
     * @return Uma lista contendo todos os filmes.
     */
    public List<Movie> getAll(){
        List<Movie> list = new ArrayList<>();
        for (int i = 0; i < movies.size(); i++) {
            list.add(movies.get(i));
        }
        return list;
    }

    /**
     * Remove um filme da lista com base no ID fornecido.
     * 
     * @param id O identificador único do filme a ser removido.
     * @return {@code true} se o filme foi encontrado e removido; {@code false} caso contrário.
     */
    public boolean removeById(int id){
        for(int i = 0; i < movies.size(); i++){
            if (movies.get(i).getId() == id) {
                movies.remove(i);
                return true;
            }
        }
        return false;
    }
}