package models;

import enums.PaymentMethod;

public class Ticket {

    private static int _idGenerator = 1;
    private int id;
    private double finalPrice;
    private Client client;
    private Session session;    
    private PaymentMethod paymentMethod;

    public Ticket(Client client, Session session, double finalPrice, PaymentMethod paymentMethod) {
        this.client = client;
        this.session = session;
        this.finalPrice = finalPrice;
        this.paymentMethod = paymentMethod;
        this.id = _idGenerator++;
    }



    public static int get_idGenerator() {
        return _idGenerator;
    }

    public int getId() {
        return id;
    }

    public double getFinalPrice() {
        return finalPrice;
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
        return "Ticket para " + session.getMovie().getTitle() +
                " | Cliente: " + client.getName() +
                " | Valor pago: R$" + String.format("%.2f", finalPrice);
    }
}