package controller.business;

import models.Movie;
import models.Room;
import models.Session;
import repository.SessionRepository;
import services.SessionService;

import java.time.LocalDate;
import java.time.LocalTime;

public class SessionController {

    private SessionRepository repository = new SessionRepository();
    private SessionService service = new SessionService(repository);

    public void registerSession(LocalDate date, LocalTime time, Room room, Movie movie, Double ticketValue) {// Simple ID generation
        service.addSession(date, time, room, movie, ticketValue);
    }

    public void updateSession(LocalDate date, LocalTime time, Room room, Movie movie, Double ticketValue, int id) {
        if(repository.getById(id) == null)
            throw new IllegalArgumentException("Sessão não encontrada!");
        service.updateSession(id, date, time, room, movie, ticketValue);
    }

    public Session deleteSession(int sessionId) {
        Session session = repository.getById(sessionId);
        repository.removeById(sessionId);

        return session;
    }
}




