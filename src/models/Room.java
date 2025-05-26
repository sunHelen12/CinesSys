package model;

public class Room {
    private static int _idGenerator = 1;
    private int id;
    private int totalSeat;
    private CircularStaticQueue<Session> sessions;

    public Room(int totalSeat){
        this.totalSeat = totalSeat;
        this.id = _idGenerator++;
        this.sessions = new CircularStaticQueue<Session>();
    }

    public int getId() {
        return id;
    }

    public int getTotalSeat() {
        return totalSeat;
    }

    public void addSession(Session session){
        sessions.enqueue(session);
    }

    public Session removeSession(){
        return sessions.dequeue();
    }

    @Override
    public String toString() {
        return "Room " + id + ":" + 
        "\nTotal Seat=" + totalSeat + 
        "\nSessions="+ sessions;
    }  
}