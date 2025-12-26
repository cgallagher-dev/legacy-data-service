package com.liquorstore.model;

// holds inventory item data
public class Product {

    // basic fields
    private int id;
    private String name;
    private String brand;
    private String type;
    private double price;
    private int quantityOnHand;
    
    // links product to owner
    private int userId;

    // default constructor
    public Product() {
    }

    // constructor for new product (no id yet)
    public Product(String name, String brand, String type, double price, int quantityOnHand, int userId) {
        this.name = name;
        this.brand = brand;
        this.type = type;
        this.price = price;
        this.quantityOnHand = quantityOnHand;
        this.userId = userId;
    }
    
    // constructor for existing product (from database with id)
    public Product(int id, String name, String brand, String type, double price, int quantityOnHand, int userId) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.type = type;
        this.price = price;
        this.quantityOnHand = quantityOnHand;
        this.userId = userId;
    }

    // getters and setters
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantityOnHand() {
        return quantityOnHand;
    }

    public void setQuantityOnHand(int quantityOnHand) {
        this.quantityOnHand = quantityOnHand;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}