package models;

public class Ticket {

    private static int _idGenerator = 1;
    private int id;
    private Client client;
    private Session session;    
    private PaymentMethod paymentMethod;

    public Ticket(Client client, Session session, PaymentMethod paymentMethod, int id) {        
        this.client = client;
        this.session = session;
        this.paymentMethod = paymentMethod;
        this.id = _idGenerator++;
    }

    public static int get_idGenerator() {
        return _idGenerator;
    }

    public int getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public Session getSession() {
        return session;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

      @Override
    public String toString() {
        return "Ticket " + id + ":" + 
               "\nClient= " + client.getName() + 
               "\nSession= " + session.getMovie().getTitle() + 
               "\nDate= " + session.getDate() + 
               "\nDuration= " + session.getDuration() +
               "\nPayment Method= " + paymentMethod;
    }    
    
}