package models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Session {
    private static int idGenerator = 1;
    private int id;
    private LocalDate date;
    private LocalTime time;
    private int duration;
    private Room room;
    private int totalAvailableSeats;
    private Movie movie;
    private Double ticketValue;

    public Session(LocalDate date, LocalTime time, Room room, Movie movie, Double ticketValue) {
        this.id = idGenerator++;
        this.date = date;
        this.time = time;
        this.room = room;
        this.duration = movie.getDuration();
        this.totalAvailableSeats = room.getTotalSeat();
        this.movie = movie;
        this.ticketValue = ticketValue;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalTime getTime() {
        return time;
    }

    public int getDuration() {
        return duration;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Double getTicketValue() {
        return ticketValue;
    }

    public void setTicketValue(Double ticketValue) {
        this.ticketValue = ticketValue;
    }

    public int getTotalAvailableSeats() {
        return totalAvailableSeats;
    }

    public void setTotalAvailableSeats(int totalAvailableSeats) {
        this.totalAvailableSeats = totalAvailableSeats;
    }

    public int getId() {
        return id;
    }
    @Override
    public String toString() {
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

        return "Session " + id + ":" +
                "\nMovie= (" + getMovie().getTitle() + ")" +
                "\nTotal Seat= " + getTotalAvailableSeats() +
                "\nStart date= " + date.format(formatDate)+
                "\nStart Time= " + time.format(timeFormat) +
                "\nTicket value= " + getTicketValue();
    }
}
