package models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Representa uma sessão de cinema no sistema CinesSys.
 * 
 * Uma sessão é composta por um filme, uma sala, data e horário de exibição,
 * duração, número de assentos disponíveis e valor do ingresso.
 * Cada sessão possui um ID único gerado automaticamente.
 * 
 * @author Carlos henrique moreira
 * @version 1.0
 */
public class Session {
    /** Gerador automático de IDs únicos para as sessões */
    private static int idGenerator = 1;
    
    /** ID único da sessão */
    private int id;
    
    /** Data da sessão */
    private LocalDate date;
    
    /** Horário de início da sessão */
    private LocalTime time;
    
    /** Duração da sessão em minutos */
    private int duration;
    
    /** Sala onde a sessão será exibida */
    private Room room;
    
    /** Número total de assentos disponíveis para a sessão */
    private int totalAvailableSeats;
    
    /** Filme que será exibido na sessão */
    private Movie movie;
    
    /** Valor do ingresso para a sessão */
    private Double ticketValue;

    /**
     * Construtor principal para criar uma nova sessão.
     * O número de assentos disponíveis é definido automaticamente
     * com base na capacidade total da sala.
     * 
     * @param date Data da sessão
     * @param time Horário de início da sessão
     * @param room Sala onde a sessão será exibida
     * @param movie Filme que será exibido
     * @param ticketValue Valor do ingresso
     */
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

    /**
     * Construtor alternativo para criar uma nova sessão com número
     * específico de assentos disponíveis.
     * 
     * @param date Data da sessão
     * @param time Horário de início da sessão
     * @param room Sala onde a sessão será exibida
     * @param movie Filme que será exibido
     * @param ticketValue Valor do ingresso
     * @param totalAvailableSeats Número específico de assentos disponíveis
     */
    public Session(LocalDate date, LocalTime time, Room room, Movie movie, Double ticketValue, int totalAvailableSeats) {
        this.id = idGenerator++;
        this.date = date;
        this.time = time;
        this.room = room;
        this.duration = movie.getDuration();
        this.totalAvailableSeats = totalAvailableSeats;
        this.movie = movie;
        this.ticketValue = ticketValue;
    }

    /**
     * Retorna a data da sessão formatada como string.
     * 
     * @return Data formatada no padrão "dd-MM-yyyy"
     */
    public String getDate() {
        return date.format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    /**
     * Define a data da sessão.
     * 
     * @param date Nova data da sessão
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Define o horário da sessão.
     * 
     * @param time Novo horário da sessão
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
     * Retorna o horário da sessão formatado como string.
     * 
     * @return Horário formatado no padrão "HH:mm:ss"
     */
    public String getTime() {
        return time.format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    /**
     * Retorna a duração da sessão em minutos.
     * 
     * @return Duração da sessão
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Retorna a sala onde a sessão será exibida.
     * 
     * @return Sala da sessão
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Define a sala da sessão.
     * 
     * @param room Nova sala da sessão
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     * Retorna o filme que será exibido na sessão.
     * 
     * @return Filme da sessão
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * Define o filme da sessão.
     * 
     * @param movie Novo filme da sessão
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    /**
     * Retorna o valor do ingresso da sessão.
     * 
     * @return Valor do ingresso
     */
    public Double getTicketValue() {
        return ticketValue;
    }

    /**
     * Define o valor do ingresso da sessão.
     * 
     * @param ticketValue Novo valor do ingresso
     */
    public void setTicketValue(Double ticketValue) {
        this.ticketValue = ticketValue;
    }

    /**
     * Retorna o número total de assentos disponíveis na sessão.
     * 
     * @return Número de assentos disponíveis
     */
    public int getTotalAvailableSeats() {
        return totalAvailableSeats;
    }

    /**
     * Define o número total de assentos disponíveis na sessão.
     * 
     * @param totalAvailableSeats Novo número de assentos disponíveis
     */
    public void setTotalAvailableSeats(int totalAvailableSeats) {
        this.totalAvailableSeats = totalAvailableSeats;
    }

    /**
     * Retorna o ID único da sessão.
     * 
     * @return ID da sessão
     */
    public int getId() {
        return id;
    }

    /**
     * Retorna uma representação em string da sessão com todas as informações principais.
     * Inclui ID, título do filme, assentos disponíveis, data, horário e valor do ingresso.
     * 
     * @return String formatada com os dados da sessão
     */
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
