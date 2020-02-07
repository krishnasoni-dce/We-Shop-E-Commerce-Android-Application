package com.example.weshopapplication;


// Author of Application: Sabin Constantin Lungu
// Purpose of Class: To store the data for the capacities.
// Date of last modification: 07/02/2020
// Any errors ? NO

public class Capacity { // Capacity class
    private int colour;
    private int productCapacity;

    public Capacity(int colour, int productCapacity) {
        this.colour = colour;
        this.productCapacity = productCapacity;
    }

    public int getColour() {
        return colour;
    }

    public void setColour(int colour) {
        this.colour = colour;
    }

    public int getProductCapacity() {
        return productCapacity;
    }

    public void setProductCapacity(int productCapacity) {
        this.productCapacity = productCapacity;
    }

    @Override
    public String toString() {
        return "Capacity{" +
                "colour=" + colour +
                ", productCapacity=" + productCapacity +
                '}';
    }
}
