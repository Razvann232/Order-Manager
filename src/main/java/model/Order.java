package model;

/**
 * An order was placed by a specific client and includes a list of products
 * he successfully purchased from the warehouse.
 *
 * It is characterized by orderId, clientId, price.
 * The products purchased are stored in the database via the products_order table.
 * @author Pinzariu Razvan-George
 */
public class Order {
    private int orderId;
    private int clientId;
    private float price;

    public Order(int clientId) {
        this.clientId = clientId;
    }

    public Order(int clientId, float price) {
        this.clientId = clientId;
        this.price = price;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public float getPrice(){
        return price;
    }

    public void setPrice(float price){
        this.price = price;
    }
}
