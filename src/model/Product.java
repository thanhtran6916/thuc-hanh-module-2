package model;

public class Product {
    private String id;
    private String name;
    private double cost;
    private int quantity;
    private String description;

    public Product(String id, String name, double cost, int quantity, String description) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + cost + "," + quantity + "," + description;
    }
}
