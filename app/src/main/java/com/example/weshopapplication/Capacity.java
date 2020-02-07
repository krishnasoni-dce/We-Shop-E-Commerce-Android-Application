package com.example.weshopapplication;

// Author of Application: Sabin Constantin Lungu
// Purpose of Class: To store the data for the capacities.
// Date of last modification: 07/02/2020
// Any errors ? NO

public class Capacity { // Capacity class
    private int index;
    private int productCapacity;

    public Capacity(int index, int productCapacity) { // Parameter constructor. Method called when the object is being created and added on the heap
        this.index = index;
        this.productCapacity = productCapacity;
    }

    public int getIndex() { // Method returns the index
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getProductCapacity() {
        return productCapacity;
    }

    public void setProductCapacity(int productCapacity) {
        this.productCapacity = productCapacity;
    }

    @Override
    public String toString() {
        return " " + this.productCapacity;
    }
}
