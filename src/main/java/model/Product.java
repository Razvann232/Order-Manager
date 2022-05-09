package model;


/**
 * A product characterizes the elements available in the warehouse for purchase.
 * It is defined by id, name , quantity, weight and price.
 *
 * @author Pinzariu Razvan-George
 */
public class Product {
    private int id;
    private String name;
    private int quantity;
    private float weight;
    private float price;

    public Product(int id, String name, int quantity, float weight, float price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.weight = weight;
        this.price = price;
    }

    public Product(String name, int quantity, float weight, float price) {
        this.name = name;
        this.quantity = quantity;
        this.weight = weight;
        this.price = price;
    }

    public Product(){

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getWeight() {
        return weight;
    }

    public float getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String toString(){
        return id + " - " + name;
    }
}
