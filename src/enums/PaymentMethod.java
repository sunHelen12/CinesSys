package enums;
/**
 * Enumeração que representa os métodos de pagamento disponíveis.
 * Cada método possui uma descrição legível para o usuário.
 * 
 * @author Helen Santos Rocha
 * @version 2.0
 */
public enum PaymentMethod {
    /**
     * Pagamento em dinheiro.
     */
    CASH("Cash"),

    /**
     * Pagamento com cartão de crédito.
     */
    CREDIT_CARD("Credit Card"),

    /**
     * Pagamento com cartão de débito.
     */
    DEBIT_CARD("Debit Card"),

    /**
     * Pagamento via Pix.
     */
    PIX("Pix");

    /**
     * Descrição legível do método de pagamento.
     */
    private String description;

    /**
     * Construtor do enum.
     * @param description Descrição do método de pagamento.
     */
    private PaymentMethod(String description){
        this.description = description;
    }

    /**
     * Retorna o método de pagamento correspondente à descrição informada.
     * @param desc Descrição do método de pagamento.
     * @return PaymentMethod correspondente.
     * @throws IllegalArgumentException se a descrição não corresponder a nenhum método.
     */
    public static PaymentMethod fromDescription(String desc){
        for (PaymentMethod pm : PaymentMethod.values()){
            if(pm.description.equalsIgnoreCase(desc)){
                return pm;
            }
        }
        throw  new IllegalArgumentException("Método de pagamento inválido: " + desc);
    }

    /**
     * Retorna a descrição do método de pagamento.
     * @return Descrição legível.
     */
    @Override
    public String toString(){
        return description;
    }
}

