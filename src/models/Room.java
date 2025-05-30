package models;

import structures.queue.GenericDynamicQueue;

public class Room {
    public static final Room room1 = new Room(200);
    public static final Room room2 = new Room(150);
    public static final Room room3 = new Room(170);
    public static final Room room4 = new Room(100);
    public static final Room room5 = new Room(120);
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

    @Override
    public String toString() {
        return "Room " + id + ":" + 
        "\nTotal Seat=" + totalSeat + 
        "\nSessions="+ sessions;
    }  
}