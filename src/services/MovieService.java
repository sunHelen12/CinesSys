package services;

import models.Movie;
import repository.MovieRepository;
import structures.list.GenericDynamicList;

/**
 * Classe Service responsável pela lógica denegícios relacionada a filmes.
 */
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    /**
     * Adiciona um novo filme
     * 
     * @param title
     * @param genre
     * @param duration
     * @param classification
     * @param synopsis
     * @return true se o filme foi adicionado com sucesso, ou false se já existe um filme com o mesmo título ou com duração menor que zero
     */
    public boolean addMovie(String title, String genre, int duration, String classification, String synopsis){
        //Verificações básicas
        if(title == null || title.isBlank()){
            throw new IllegalAccessError("O título do filme não pode ser vazio!");
        }
        if (duration <= 0) {
            throw new IllegalArgumentException("A duração do filme deve ser maior que zero!");
        }

        //Verifica se já existe um filme com o mesmo título
        GenericDynamicList<Movie> existingMovies = movieRepository.getAll();
        for(int i = 0; i < existingMovies.size(); i++){
            if(existingMovies.get(i).getTitle().equalsIgnoreCase(title)){
                return false; //Indica filme duplicado
            }
        }

        //Cria o filme e envia para o Repository
        Movie movie = new Movie(title, genre, duration, classification, synopsis);
        movieRepository.add(movie);
        return true;
    }

    /**
     * Retorna a lista de todos os filmes cadastrados.
     * 
     * @return Lista de filmes.
     */
    public GenericDynamicList<Movie> getAllMovies(){
        return movieRepository.getAll();
    }

    /**
     * Retorna um filme pelo ID fornecido.
     * 
     * @param id O ID do filme.
     * @return O filme encontrado ou null caso não exista
     * @throws IllegalArgumentException se o ID for menor ou igual a zero.
     * @throws RuntimeException         se não existir um filme com esse ID.
     */
    public Movie getMovieById(int id){
        if(id <= 0){
            throw new IllegalArgumentException("O ID deve ser maior que zero!");
        }

        Movie movie = movieRepository.getById(id);
        if(movie == null){
            throw new RuntimeException("Nenhum filme encontrado com o ID " + id);
        }

        return movieRepository.getById(id);
    }

    /**
     * Remove o filme pelo ID fornecido.
     * 
     * @param id O ID do filme.
     * @return true se o filme foi removido, false se não foi encontrado.
     * @throws IllegalArgumentException se o ID for menor ou igual a zero.
     */
    public boolean removeMovieById(int id){
        if(id <= 0){
            throw new IllegalArgumentException("O ID deve ser maior que zero!");
        }

        boolean removed = movieRepository.removeById(id);
        if (!removed) {
            throw new RuntimeException("Nenhum filme encontrado com o ID" + id);
        }

        return removed;
    }
}