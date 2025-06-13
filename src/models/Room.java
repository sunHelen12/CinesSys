package models;

import structures.queue.GenericDynamicQueue;

public class Room {
    private static int _idGenerator = 1;
    private int id;
    private int totalSeat;
    private GenericDynamicQueue<Session> sessions;

    public Room(int totalSeat){
        this.totalSeat = totalSeat;
        this.id = _idGenerator++;
        this.sessions = new GenericDynamicQueue<Session>();
    }

    public int getId() {
        return id;
    }

    public int getTotalSeat() {
        return totalSeat;
    }

    public void addSession(Session session) throws Exception{
        sessions.enqueue(session);
    }

    public Session removeSession(){
        return sessions.dequeue();
    }

    public GenericDynamicQueue<Session> getSessions() {
        return sessions;
    }

    @Override
    public String toString() {
        return "Room " + id + ":" + 
        "\nTotal Seat=" + totalSeat + 
        "\nSessions="+ sessions;
    }

    public static void resetIdGenerator() {
        _idGenerator = 1;
    }
}