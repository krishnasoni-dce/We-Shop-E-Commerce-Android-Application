package com.example.weshopapplication;

// Author of Application: Sabin Constantin Lungu 40397517
// Purpose of Application: To store data regarding the products when they are added to basket, the data will get displayed in a list view
// Date of Last Modification: 07/02/2020
// Any Errors? No

public class Products { // Products Class
    private int productID; // The Product ID
    private String productName; // The Product Name
    private String colour; // Product Colour
    private int quantity;
    private String cost;
    private int productCapacity;

    public Products(int productID, String productName, String colour, int quantity, String cost, int productCapacity) {
        this.productID = productID;
        this.productName = productName;
        this.colour = colour;
        this.quantity = quantity;
        this.cost = cost;
        this.productCapacity = productCapacity;
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

    public int getProductCapacity() {
        return productCapacity;
    }

    public void setProductCapacity(int productCapacity) {
        this.productCapacity = productCapacity;
    }

    @Override
    public String toString() {
        return "Products{" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", colour='" + colour + '\'' +
                ", quantity=" + quantity +
                ", cost='" + cost + '\'' +
                ", productCapacity=" + productCapacity +
                '}';
    }
}
