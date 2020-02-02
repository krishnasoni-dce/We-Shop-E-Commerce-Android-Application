package com.example.weshopapplication;

public class Product {
    private String colour;
    private int quantity;


    public Product(String colour, int quantity) {
        this.colour = colour;
        this.quantity = quantity;
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

    @Override
    public String toString() {
        return "Product{" +
                "colour='" + colour + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
