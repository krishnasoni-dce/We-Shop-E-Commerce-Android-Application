package com.example.weshopapplication;

// Author of Application: Sabin Constantin Lungu 40397517
// Purpose of Application: To store data regarding the products when they are added to basket, the data will get displayed in a list view
// Date of Last Modification: 07/02/2020
// Any Errors? No

import java.io.Serializable;

public class Products implements Serializable { // Products Class
    private int productID; // The Product ID
    private String productName; // The Product Name
    private String colour; // Product Colour
    private int quantity;
    private String cost;
    private String size;

    public Products(int productID, String productName, String colour, int quantity, String cost, String size) {
        this.productID = productID;
        this.productName = productName;
        this.colour = colour;
        this.quantity = quantity;
        this.cost = cost;
        this.size = size;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() { // To string method returns all of the data from the instance
        return "Product Name : " + this.productName + "\n " + "Product Colour " + colour + "\n " + "Product Quantity : " + this.quantity + "\n " + this.cost
                + "Product Size : " + "\n " + this.size;
    }
}
