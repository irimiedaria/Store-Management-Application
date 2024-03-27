package model;
public class Orders {
    private Integer ID;
    private Integer idClient;
    private Integer idProdus;
    private Integer quantity;

    public Orders() {
    }

    public Orders(Integer idClient, Integer idProdus, Double price, Integer quantity) {
        this.idClient = idClient;
        this.idProdus = idProdus;
        this.quantity = quantity;
    }

    public Orders(Integer idClient, Integer idProdus, Integer quantity) {
        this.idClient=idClient;
        this.idProdus=idProdus;
        this.quantity=quantity;
    }



    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public Integer getIdProdus() {
        return idProdus;
    }

    public void setIdProdus(Integer idProdus) {
        this.idProdus = idProdus;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}