package com.example.weshopapplication;

public class Products {
    private int productID;
    private String productName;
    private String colour;
    private int quantity;
    private String cost;

    public Products(int productID, String productName, String colour, int quantity, String cost) {
        this.productID = productID;
        this.productName = productName;
        this.colour = colour;
        this.quantity = quantity;
        this.cost = cost;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Products{" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", colour='" + colour + '\'' +
                ", quantity=" + quantity +
                ", cost='" + cost + '\'' +
                '}';
    }
}
