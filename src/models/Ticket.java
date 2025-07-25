package models;

import enums.PaymentMethod;
import java.io.Serializable;

/**
 * Representa um ingresso para uma sessão de cinema.
 * 
 * @author Helen Santos Rocha
 * @since 25/05/2025
 * @version 4.0
 */
public class Ticket implements Serializable{

    private static int _idGenerator = 1;
    private int id;
    private double finalPrice;
    private Client client;
    private Session session;    
    private PaymentMethod paymentMethod;

    /**
     * Constrói um novo ingresso.
     *
     * @param client O cliente que comprou o ingresso.
     * @param session A sessão para a qual o ingresso é válido.
     * @param finalPrice O preço final do ingresso.
     * @param paymentMethod O método de pagamento utilizado.
     */
    public Ticket(Client client, Session session, double finalPrice, PaymentMethod paymentMethod) {
        this.client = client;
        this.session = session;
        this.finalPrice = finalPrice;
        this.paymentMethod = paymentMethod;
        this.id = _idGenerator++;
    }

    /**
     * Retorna o próximo ID a ser gerado.
     *
     * @return O próximo ID.
     */
    public static int get_idGenerator() {
        return _idGenerator;
    }

    /**
     * Retorna o ID do ingresso.
     *
     * @return O ID do ingresso.
     */
    public int getId() {
        return id;
    }

    /**
     * Retorna o preço final do ingresso.
     *
     * @return O preço final do ingresso.
     */
    public double getFinalPrice() {
        return finalPrice;
    }

    /**
     * Retorna o cliente que comprou o ingresso.
     *
     * @return O cliente que comprou o ingresso.
     */
    public Client getClient() {
        return client;
    }

    /**
     * Retorna a sessão para a qual o ingresso é válido.
     *
     * @return A sessão para a qual o ingresso é válido.
     */
    public Session getSession() {
        return session;
    }

    /**
     * Retorna o método de pagamento utilizado para comprar o ingresso.
     *
     * @return O método de pagamento utilizado.
     */
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Retorna uma representação em string do ingresso.
     *
     * @return Uma representação em string do ingresso.
     */
    @Override
    public String toString() {
        return "Ticket para " + session.getMovie().getTitle() +
                " | Cliente: " + client.getName() +
                " | Valor pago: R$" + String.format("%.2f", finalPrice);
    }

    /**
     * Define o ID do ingresso.
     */
    public static void resetIdGenerator() {
		_idGenerator = 1;
	}

    public static void updateIdGenerator(int ultimoId) {
        _idGenerator = ultimoId + 1;
    }
}
