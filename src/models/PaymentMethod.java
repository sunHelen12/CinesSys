package models;

public enum PaymentMethod {
    CASH("Cash"),
    CREDIT_CARD("Credit Card"),
    DEBIT_CARD("Debit Card"),
    PIX("Pix");

    private String description;

    private PaymentMethod(String description){
        this.description = description;
    }
   
    // Método para que o usuário consiga buscar os métodos de pagamento de uma forma melhor

    public static PaymentMethod fromDescription(String desc){
        for (PaymentMethod pm : PaymentMethod.values()){
            if(pm.description.equalsIgnoreCase(desc)){
                return pm;
            }
        }
        throw  new IllegalArgumentException("Método de pagamento inválido: " + desc);
    }

    @Override
    public String toString(){
        return description;
    }


}
