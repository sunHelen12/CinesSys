package controller.business;

import models.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SessionControllerTest {

    @Before
    public void setup() {
        SessionController.removeAllSessions();
        Session.resetIdGenerator();
    }

    @Test
    public void testConstructorInitialState() {
        assertEquals(0, SessionController.getAllSessions().size());
    }

    @Test
    public void testAddSession() {
        SessionController.addSession("12-10-2025", "14:00", RoomController.getRoomById(1), new Movie("Inception", "Sci-Fi", 148, "PG-13", "A mind-bending thriller."), 30.0);
        assertEquals(1, SessionController.getAllSessions().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddSessionWithSameDate(){
        
        String date = "12-10-2025";
        String time = "14:00";
        SessionController.addSession(date, time, RoomController.getRoomById(1), new Movie("Inception", "Sci-Fi", 148, "PG-13", "A mind-bending thriller."), 30.0);
        SessionController.addSession(date, time, RoomController.getRoomById(1), new Movie("Inception", "Sci-Fi", 148, "PG-13", "A mind-bending thriller."), 30.0);
    }

    @Test
    public void testAddSessionWithDifferentDatesSameRoomAndTime() {
        // Deve permitir sessões no mesmo horário e sala em datas diferentes
        String time = "14:00";
        Movie movie = new Movie("Movie", "Genre", 120, "PG", "Description");

        SessionController.addSession("12-07-2025", time, RoomController.getRoomById(1), movie, 20.0);
        SessionController.addSession("13-07-2025", time, RoomController.getRoomById(1), movie, 25.0);

        assertEquals(2, SessionController.getAllSessions().size());
    }

    @Test
    public void testGetAllSessionsWhenNoSessionsAdded() {
        // Estado inicial: nenhuma sessão adicionada
        assertTrue(SessionController.getAllSessions().isEmpty());
    }

    @Test
    public void testAddMultipleSessions() {
        Movie movie = new Movie("Inception", "Sci-Fi", 148, "PG-13", "A mind-bending thriller.");
        for (int i = 0; i < 5; i++) {
            SessionController.addSession("1"+i+"-08-2025", "14:02", RoomController.getRoomById(1), movie, 30.0);
        }
        assertEquals(5, SessionController.getAllSessions().size());
    }

    @Test
    public void testGetSessionById() {
        SessionController.addSession("12-10-2025", "14:00", RoomController.getRoomById(4), new Movie("Gladiator", "Action", 155, "R", "Roman general story"), 40.0);
        Session session = SessionController.getAllSessions().get(0);
        assertEquals("Gladiator", SessionController.getSessionById(session.getId()).getMovie().getTitle());
    }

    @Test
    public void testGetSessionByInvalidId() {
        Session session = SessionController.getSessionById(0);
        assertNull(session);
    }

    @Test
    public void testUpdateSessionSuccessfully() {
        Movie movie = new Movie("Original", "Drama", 100, "PG", "Original movie.");
        SessionController.addSession("12-10-2025", "14:00", RoomController.getRoomById(1), movie, 25.0);
        Session session = SessionController.getAllSessions().get(0);

        Movie updatedMovie = new Movie("Atualizado", "Ação", 130, "18+", "Filme atualizado.");
        SessionController.updateSession(session.getId(), "13-10-2025", "15:50", RoomController.getRoomById(2), updatedMovie, 40.0);

        Session updatedSession = SessionController.getSessionById(session.getId());

        assertEquals("Atualizado", updatedSession.getMovie().getTitle());
        assertEquals(RoomController.getRoomById(2), updatedSession.getRoom());
        assertEquals(40.0, updatedSession.getTicketValue(), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateNonExistentSession() {
        Movie movie = new Movie("Teste", "Drama", 90, "PG", "Teste");
        SessionController.updateSession(999, "12-10-2025", "14:00", RoomController.getRoomById(3), movie, 50.0);
    }

    @Test
    public void testRemoveSessionByIdSuccessfully() {
        Movie movie = new Movie("Para Remover", "Suspense", 110, "PG-13", "Remover.");
        SessionController.addSession("12-10-2025", "14:00", RoomController.getRoomById(2), movie, 20.0);
        Session session = SessionController.getAllSessions().get(0);

        Session removed = SessionController.removeSession(session.getId());
        assertNotNull(removed);
        assertEquals(0, SessionController.getAllSessions().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveSessionWithInvalidId() {
        SessionController.removeSession(0);
    }
}
