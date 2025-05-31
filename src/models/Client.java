package models;

import structures.list.GenericDynamicList;

import java.time.LocalDate;

public class Client {
    private static int _idGenerator = 1;
    private int id;
    private String name;
    private String email;
    private LocalDate birthday;
    private GenericDynamicList<Ticket> purchasingHistory;
    
    public Client(String name, String email, LocalDate birthday) {
        this.name = name;
        this.email = email;
        this.birthday = birthday;
        purchasingHistory = new GenericDynamicList<>();
        id = _idGenerator++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public GenericDynamicList<Ticket> getPurchasingHistory() {
        return purchasingHistory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    /**
     * Adiciona um novo ticket ao histórico de compras do cliente.
     *
     * @param ticket O ticket que acabou de ser comprado.
     */
    public void addTicketToHistory(Ticket ticket) {
        try {
            purchasingHistory.append(ticket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String printHistory(){
        String result = "";
        for (int i = 0; i < purchasingHistory.size(); i++) {
            result += purchasingHistory.get(i).toString() + "\n";
        }
        return result;
    }

    @Override
    public String toString() {
        return "Client " + id + ":" + 
        "\nName= " + name + 
        "\nEmail= "+ email + 
        "\nBirthday= "+ birthday + 
        "\nPurchasing History= " + purchasingHistory;
    }   
}
