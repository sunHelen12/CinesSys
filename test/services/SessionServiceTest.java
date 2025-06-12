package services;

import models.*;
import org.junit.Before;
import org.junit.Test;
import repository.SessionRepository;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class SessionServiceTest {
    private SessionService service;

    @Before
    public void setup() {
        service = new SessionService(new SessionRepository());
    }

    @Test
    public void testConstructorInitialState() {
        assertEquals(0, service.getAllSessions().size());
    }

    @Test
    public void testAddSession() {
        service.addSession(LocalDate.now(), LocalTime.now(), Room.room1, new Movie("Inception", "Sci-Fi", 148, "PG-13", "A mind-bending thriller."), 30.0);
        assertEquals(1, service.getAllSessions().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddSessionWithSameDate(){
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.of(10, 0);
        service.addSession(date, time, Room.room1, new Movie("Inception", "Sci-Fi", 148, "PG-13", "A mind-bending thriller."), 30.0);
        service.addSession(date, time, Room.room1, new Movie("Inception", "Sci-Fi", 148, "PG-13", "A mind-bending thriller."), 30.0);
    }

    @Test
    public void testAddSessionWithDifferentDatesSameRoomAndTime() {
        // Deve permitir sessões no mesmo horário e sala em datas diferentes
        LocalTime time = LocalTime.of(10, 0);
        Movie movie = new Movie("Movie", "Genre", 120, "PG", "Description");

        service.addSession(LocalDate.now(), time, Room.room1, movie, 20.0);
        service.addSession(LocalDate.now().plusDays(1), time, Room.room1, movie, 25.0);

        assertEquals(2, service.getAllSessions().size());
    }

    @Test
    public void testGetAllSessionsWhenNoSessionsAdded() {
        // Estado inicial: nenhuma sessão adicionada
        assertTrue(service.getAllSessions().isEmpty());
    }

    @Test
    public void testAddMultipleSessions() {
        Movie movie = new Movie("Inception", "Sci-Fi", 148, "PG-13", "A mind-bending thriller.");
        for (int i = 0; i < 5; i++) {
            service.addSession(LocalDate.now(), LocalTime.now().plusHours(i*2), Room.room1, movie, (i + 5.0) * 5);
        }
        assertEquals(5, service.getAllSessions().size());
    }

    @Test
    public void testGetSessionById() {
        service.addSession(LocalDate.now(), LocalTime.now(), Room.room4, new Movie("Gladiator", "Action", 155, "R", "Roman general story"), 40.0);
        Session session = service.getAllSessions().get(0);
        assertEquals("Gladiator", service.getSessionById(session.getId()).getMovie().getTitle());
    }

    @Test
    public void testGetSessionByInvalidId() {
        Session session = service.getSessionById(0);
        assertNull(session);
    }

    @Test
    public void testUpdateSessionSuccessfully() {
        Movie movie = new Movie("Original", "Drama", 100, "PG", "Original movie.");
        service.addSession(LocalDate.now(), LocalTime.now(), Room.room1, movie, 25.0);
        Session session = service.getAllSessions().get(0);

        Movie updatedMovie = new Movie("Atualizado", "Ação", 130, "18+", "Filme atualizado.");
        service.updateSession(session.getId(), LocalDate.now().plusDays(1), LocalTime.now().plusHours(2), Room.room2, updatedMovie, 40.0);

        Session updatedSession = service.getSessionById(session.getId());

        assertEquals("Atualizado", updatedSession.getMovie().getTitle());
        assertEquals(Room.room2, updatedSession.getRoom());
        assertEquals(40.0, updatedSession.getTicketValue(), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateNonExistentSession() {
        Movie movie = new Movie("Teste", "Drama", 90, "PG", "Teste");
        service.updateSession(999, LocalDate.now(), LocalTime.now(), Room.room3, movie, 50.0);
    }

    @Test
    public void testRemoveSessionByIdSuccessfully() {
        Movie movie = new Movie("Para Remover", "Suspense", 110, "PG-13", "Remover.");
        service.addSession(LocalDate.now(), LocalTime.now(), Room.room1, movie, 20.0);
        Session session = service.getAllSessions().get(0);

        Session removed = service.removeSession(session.getId());
        assertNotNull(removed);
        assertEquals(0, service.getAllSessions().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveSessionWithInvalidId() {
        service.removeSession(0);
    }
}
