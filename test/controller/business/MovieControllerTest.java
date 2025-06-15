package controller.business;

import models.Movie;
import org.junit.Before;
import org.junit.Test;
import structures.list.GenericDynamicList;

import static org.junit.Assert.*;

/**
 * Classe de testes unitários para a classe MovieController.
 *
 * @author Kaique Silva sousa
 * @author Vinícius Nunes de Andrade
 * @version 1.0
 * @since 14-06-2025
 */
public class MovieControllerTest {

    /**
     * Prepara o ambiente para cada teste, limpando o repositório
     * e resetando o gerador de IDs para garantir a independência dos testes.
     */
    @Before
    public void setUp() {
        MovieController.removeAllMovies();
        Movie.resetIdGenerator();
    }

    /**
     * Testa a adição bem-sucedida de um novo filme e verifica se ele
     * foi corretamente armazenado no repositório.
     */
    @Test
    public void testAddMovieSuccessfully() {
        String result = MovieController.addMovie("Inception", "Sci-Fi", 148, "PG-13", "A mind-bending thriller.");

        assertEquals("Filme adicionado com sucesso!", result);
        GenericDynamicList<Movie> allMovies = MovieController.getAllMovies();
        assertEquals(1, allMovies.size());
        assertEquals("Inception", allMovies.get(0).getTitle());
    }

    /**
     * Testa se a tentativa de adicionar um filme com um título que já existe
     * é impedida, mantendo a integridade dos dados.
     */
    @Test
    public void testAddMovieWithDuplicateTitleReturnsMessage() {
        MovieController.addMovie("Inception", "Sci-Fi", 148, "PG-13", "First movie.");
        String result = MovieController.addMovie("Inception", "Action", 120, "R", "Duplicate movie.");

        assertEquals("Filme não foi adicionado.", result);
        assertEquals(1, MovieController.getAllMovies().size()); // Ainda só 1 filme
    }

    /**
     * Testa se adicionar um filme com título nulo lança a exceção esperada.
     */
    @Test(expected = IllegalAccessError.class)
    public void testAddMovieWithNullTitleThrowsException() {
        MovieController.addMovie(null, "Drama", 90, "PG", "Null title.");
    }

    /**
     * Testa se adicionar um filme com título vazio lança a exceção esperada.
     */
    @Test(expected = IllegalAccessError.class)
    public void testAddMovieWithEmptyTitleThrowsException() {
        MovieController.addMovie("", "Drama", 90, "PG", "Empty title.");
    }

    /**
     * Testa se adicionar um filme com duração inválida (zero) lança uma exceção.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddMovieWithInvalidDurationThrowsException() {
        MovieController.addMovie("Bad Movie", "Horror", 0, "PG-13", "Invalid duration.");
    }

    /**
     * Testa a busca bem-sucedida de um filme pelo seu ID.
     */
    @Test
    public void testGetMovieByIdSuccessfully() {
        MovieController.addMovie("Gladiator", "Action", 155, "R", "Roman general story");
        Movie movie = MovieController.getAllMovies().get(0);

        Movie foundMovie = MovieController.getMovieById(movie.getId());

        assertNotNull(foundMovie);
        assertEquals("Gladiator", foundMovie.getTitle());
    }

    /**
     * Testa se a busca por um filme com ID que não existe lança a exceção correta.
     */
    @Test(expected = IllegalAccessError.class)
    public void testGetMovieByIdWithNonexistentIdThrowsException() {
        MovieController.getMovieById(999); // ID que nunca existiu
    }

    /**
     * Testa a remoção bem-sucedida de um filme pelo seu ID.
     */
    @Test
    public void testRemoveMovieByIdSuccessfully() {
        MovieController.addMovie("The Matrix", "Sci-Fi", 136, "R", "Neo's story");
        Movie movie = MovieController.getAllMovies().get(0);

        boolean removed = MovieController.removeMovieById(movie.getId());

        assertTrue(removed);
        assertEquals(0, MovieController.getAllMovies().size());
    }

    /**
     * Testa se a busca por um filme com ID inválido (zero) lança uma exceção.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetMovieByIdWithInvalidIdThrowsException() {
        MovieController.getMovieById(0);
    }

    /**
     * Testa se a tentativa de remover um filme com ID inexistente lança uma exceção.
     */
    @Test(expected = RuntimeException.class)
    public void testRemoveMovieByIdWithNonexistentIdThrowsException() {
        MovieController.removeMovieById(1234);
    }

    /**
     * Testa se o método que retorna todos os filmes funciona corretamente
     * após a adição de múltiplos filmes.
     */
    @Test
    public void testGetAllMoviesReturnsCorrectList() {
        MovieController.addMovie("Movie A", "Genre A", 100, "PG", "Synopsis A");
        MovieController.addMovie("Movie B", "Genre B", 120, "PG-13", "Synopsis B");

        GenericDynamicList<Movie> allMovies = MovieController.getAllMovies();

        assertEquals(2, allMovies.size());
        assertEquals("Movie A", allMovies.get(0).getTitle());
        assertEquals("Movie B", allMovies.get(1).getTitle());
    }

    /**
     * Testa a atualização bem-sucedida de todos os dados de um filme.
     */
    @Test
    public void testUpdateMovieSuccessfully() {
        String result = MovieController.addMovie("Inception", "Sci-Fi", 148, "PG-13", "A mind-bending thriller");
        assertEquals("Filme adicionado com sucesso!", result);

        Movie addedMovie = MovieController.getAllMovies().get(0);

        String updateResult = MovieController.updateMovie(
                addedMovie.getId(),
                "Inception Updated",
                "Action",
                150,
                "R",
                "Updated synopsis"
        );
        assertEquals("Filme adicionado com sucesso!", updateResult);

        Movie updatedMovie = MovieController.getMovieById(addedMovie.getId());
        assertEquals("Inception Updated", updatedMovie.getTitle());
        assertEquals("Action", updatedMovie.getGenre());
        assertEquals(150, updatedMovie.getDuration());
        assertEquals("R", updatedMovie.getClassification());
        assertEquals("Updated synopsis", updatedMovie.getSynopsis());
    }

    /**
     * Testa se a tentativa de atualizar um filme com ID inexistente lança uma exceção.
     */
    @Test(expected = IllegalAccessError.class)
    public void testUpdateMovieWithNonexistentIdThrowsException() {
        MovieController.updateMovie(999, "Title", "Genre", 120, "PG", "Synopsis");
    }

    /**
     * Testa se a tentativa de atualizar um filme com um título em branco lança uma exceção.
     */
    @Test(expected = IllegalAccessError.class)
    public void testUpdateMovieWithBlankTitleThrowsException() {
        MovieController.addMovie("Interstellar", "Sci-Fi", 169, "PG-13", "Space exploration");
        Movie movie = MovieController.getAllMovies().get(0);
        MovieController.updateMovie(movie.getId(), "   ", "Sci-Fi", 169, "PG-13", "Space exploration");
    }

    /**
     * Testa se a atualização de um filme para uma duração zero lança uma exceção.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateMovieWithZeroDurationThrowsException() {
        MovieController.addMovie("Avatar", "Adventure", 162, "PG-13", "Pandora planet");
        Movie movie = MovieController.getAllMovies().get(0);
        MovieController.updateMovie(movie.getId(), "Avatar", "Adventure", 0, "PG-13", "Pandora planet");
    }

    /**
     * Testa se a atualização de um filme para uma duração negativa lança uma exceção.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateMovieWithNegativeDurationThrowsException() {
        MovieController.addMovie("Titanic", "Drama", 195, "PG-13", "Ship sinks");
        Movie movie = MovieController.getAllMovies().get(0);
        MovieController.updateMovie(movie.getId(), "Titanic", "Drama", -150, "PG-13", "Ship sinks");
    }

    /**
     * Testa se a atualização de um filme com seus próprios valores mantém
     * os dados inalterados e retorna sucesso.
     */
    @Test
    public void testUpdateMovieWithSameValuesDoesNotChangeData() {
        MovieController.addMovie("Joker", "Drama", 122, "R", "Origin of Joker");
        Movie movie = MovieController.getAllMovies().get(0);

        // Atualiza com os mesmos valores
        String result = MovieController.updateMovie(movie.getId(), "Joker", "Drama", 122, "R", "Origin of Joker");
        assertEquals("Filme adicionado com sucesso!", result);

        Movie sameMovie = MovieController.getMovieById(movie.getId());
        assertEquals("Joker", sameMovie.getTitle());
        assertEquals("Drama", sameMovie.getGenre());
        assertEquals(122, sameMovie.getDuration());
        assertEquals("R", sameMovie.getClassification());
        assertEquals("Origin of Joker", sameMovie.getSynopsis());
    }
}