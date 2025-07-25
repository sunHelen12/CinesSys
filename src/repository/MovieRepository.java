package repository;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import models.Movie;
/**
 * Classe que manipula diretamente a lista de filmes.
 * Utiliza a estrutura LinkedList como um banco de dados para armazenar os filmes.
 * @author Vinícius Nunes de Andrade
 * @author Thiago Ferreira Ribeiro
 * @since 11/06/2025
 * @version 2.0
 */
public class MovieRepository {
    private List<Movie> movies = new LinkedList<>();
    private final String FILE_PATH = "data/movies.txt";
    /**
     * Adiciona um filme à lista de filmes
     * 
     * @param movie O filme que será adicionado à lista.
     */
    public void add(Movie movie){
        try {
            movies.add(movie);
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
     * Atualiza um filme selecionado
     *
     * @param id do filme a ser atualizado
     * @param movie nova sessão que será atualizada
     */
    public void update(int id, Movie movie){
        if(getById(id) == null)
            throw new IllegalArgumentException("Sessão não existe!");
        movies.set(getIndex(id), movie);
    }

    /**
     * Método auxiliar para pegar o index de um certo filme
     *
     * @param id da sessão
     * @return se o id existir, retorna o index requerido
     *         caso não existe, retorna -1
     */
    private int getIndex(int id){
        for(int i = 0; i < movies.size(); i++){
            if(movies.get(i).getId() == id){
                return i;
            }
        }
        return -1;
    }

    /**
     * Retorna todos os filmes armazenados.
     * 
     * @return Uma lista contendo todos os filmes.
     */
    public List<Movie> getAll(){
        return (LinkedList<Movie>) movies;
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

    /**
     * Retorna um filme com o mesmo nome fornecido.
     *
     * @param name nome do filme
     * @return filme com o mesmo nome
     */
    public Movie getMovieByName(String name){
        for (Movie movie : movies){
            if(movie.getTitle().trim().equalsIgnoreCase(name.trim())){
                return movie;
            }
        }
        return null;
    }

    /**
     * Remove todos os filmes da lista.
     */
    public void clear(){
        movies.clear();
    }

    public void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(movies);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadData() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
                movies = (List<Movie>) ois.readObject();
                // Atualiza o ID dos filmes, similar ao Client
                if (!movies.isEmpty()) {
                    int ultimoId = movies.get(movies.size() - 1).getId();
                    Movie.resetIdGenerator();
                    for (int i = 0; i < ultimoId; i++) {
                        // apenas para avançar o ID até o último
                        new Movie("temp", "temp", 1, "temp", "temp");
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public int getSize() {
        return movies.size();
    }
}