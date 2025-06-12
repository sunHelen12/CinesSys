package services;

import models.Movie;
import org.junit.Before;
import org.junit.Test;
import repository.MovieRepository;
import structures.list.GenericDynamicList;

import static org.junit.Assert.*;

public class MovieServiceTest {
    private MovieService movieService;

    @Before
    public void setUp() {
        movieService = new MovieService(new MovieRepository());
    }

    @Test
    public void testAddMovieSuccessfully() {
        String result = movieService.addMovie("Inception", "Sci-Fi", 148, "PG-13", "A mind-bending thriller.");

        assertEquals("Filme adicionado com sucesso!", result);
        GenericDynamicList<Movie> allMovies = movieService.getAllMovies();
        assertEquals(1, allMovies.size());
        assertEquals("Inception", allMovies.get(0).getTitle());
    }

    @Test
    public void testAddMovieWithDuplicateTitleReturnsMessage() {
        movieService.addMovie("Inception", "Sci-Fi", 148, "PG-13", "First movie.");
        String result = movieService.addMovie("Inception", "Action", 120, "R", "Duplicate movie.");

        assertEquals("Filme não foi adicionado.", result);
        assertEquals(1, movieService.getAllMovies().size()); // Ainda só 1 filme
    }

    @Test(expected = IllegalAccessError.class)
    public void testAddMovieWithNullTitleThrowsException() {
        movieService.addMovie(null, "Drama", 90, "PG", "Null title.");
    }

    @Test(expected = IllegalAccessError.class)
    public void testAddMovieWithEmptyTitleThrowsException() {
        movieService.addMovie("", "Drama", 90, "PG", "Empty title.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddMovieWithInvalidDurationThrowsException() {
        movieService.addMovie("Bad Movie", "Horror", 0, "PG-13", "Invalid duration.");
    }

    @Test
    public void testGetMovieByIdSuccessfully() {
        movieService.addMovie("Gladiator", "Action", 155, "R", "Roman general story");
        Movie movie = movieService.getAllMovies().get(0);

        Movie foundMovie = movieService.getMovieById(movie.getId());

        assertNotNull(foundMovie);
        assertEquals("Gladiator", foundMovie.getTitle());
    }

    @Test(expected = IllegalAccessError.class)
    public void testGetMovieByIdWithNonexistentIdThrowsException() {
        movieService.getMovieById(999); // ID que nunca existiu
    }

    @Test
    public void testRemoveMovieByIdSuccessfully() {
        movieService.addMovie("The Matrix", "Sci-Fi", 136, "R", "Neo's story");
        Movie movie = movieService.getAllMovies().get(0);

        boolean removed = movieService.removeMovieById(movie.getId());

        assertTrue(removed);
        assertEquals(0, movieService.getAllMovies().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetMovieByIdWithInvalidIdThrowsException() {
        movieService.getMovieById(0);
    }

    @Test(expected = RuntimeException.class)
    public void testRemoveMovieByIdWithNonexistentIdThrowsException() {
        movieService.removeMovieById(1234);
    }

    @Test
    public void testGetAllMoviesReturnsCorrectList() {
        movieService.addMovie("Movie A", "Genre A", 100, "PG", "Synopsis A");
        movieService.addMovie("Movie B", "Genre B", 120, "PG-13", "Synopsis B");

        GenericDynamicList<Movie> allMovies = movieService.getAllMovies();

        assertEquals(2, allMovies.size());
        assertEquals("Movie A", allMovies.get(0).getTitle());
        assertEquals("Movie B", allMovies.get(1).getTitle());
    }

    @Test
    public void testUpdateMovieSuccessfully() {
        String result = movieService.addMovie("Inception", "Sci-Fi", 148, "PG-13", "A mind-bending thriller");
        assertEquals("Filme adicionado com sucesso!", result);

        Movie addedMovie = movieService.getAllMovies().get(0);

        String updateResult = movieService.updateMovie(
                addedMovie.getId(),
                "Inception Updated",
                "Action",
                150,
                "R",
                "Updated synopsis"
        );
        assertEquals("Filme adicionado com sucesso!", updateResult);

        Movie updatedMovie = movieService.getMovieById(addedMovie.getId());
        assertEquals("Inception Updated", updatedMovie.getTitle());
        assertEquals("Action", updatedMovie.getGenre());
        assertEquals(150, updatedMovie.getDuration());
        assertEquals("R", updatedMovie.getClassification());
        assertEquals("Updated synopsis", updatedMovie.getSynopsis());
    }

    @Test(expected = IllegalAccessError.class)
    public void testUpdateMovieWithNonexistentIdThrowsException() {
        movieService.updateMovie(999, "Title", "Genre", 120, "PG", "Synopsis");
    }

    @Test(expected = IllegalAccessError.class)
    public void testUpdateMovieWithBlankTitleThrowsException() {
        movieService.addMovie("Interstellar", "Sci-Fi", 169, "PG-13", "Space exploration");
        Movie movie = movieService.getAllMovies().get(0);
        movieService.updateMovie(movie.getId(), "   ", "Sci-Fi", 169, "PG-13", "Space exploration");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateMovieWithZeroDurationThrowsException() {
        movieService.addMovie("Avatar", "Adventure", 162, "PG-13", "Pandora planet");
        Movie movie = movieService.getAllMovies().get(0);
        movieService.updateMovie(movie.getId(), "Avatar", "Adventure", 0, "PG-13", "Pandora planet");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateMovieWithNegativeDurationThrowsException() {
        movieService.addMovie("Titanic", "Drama", 195, "PG-13", "Ship sinks");
        Movie movie = movieService.getAllMovies().get(0);
        movieService.updateMovie(movie.getId(), "Titanic", "Drama", -150, "PG-13", "Ship sinks");
    }

    @Test
    public void testUpdateMovieWithSameValuesDoesNotChangeData() {
        movieService.addMovie("Joker", "Drama", 122, "R", "Origin of Joker");
        Movie movie = movieService.getAllMovies().get(0);

        // Atualiza com os mesmos valores
        String result = movieService.updateMovie(movie.getId(), "Joker", "Drama", 122, "R", "Origin of Joker");
        assertEquals("Filme adicionado com sucesso!", result);

        Movie sameMovie = movieService.getMovieById(movie.getId());
        assertEquals("Joker", sameMovie.getTitle());
        assertEquals("Drama", sameMovie.getGenre());
        assertEquals(122, sameMovie.getDuration());
        assertEquals("R", sameMovie.getClassification());
        assertEquals("Origin of Joker", sameMovie.getSynopsis());
    }
}
