package com.example.weshopapplication;

// Author of Application: Sabin Constantin Lungu
// Purpose of Class: To store the data for the capacities.
// Date of last modification: 07/02/2020
// Any errors ? NO

import java.io.Serializable;

public class Capacity implements Serializable { // Capacity class
    private int index;
    private String productCapacity;

    public Capacity(int index, String productCapacity) {
        this.index = index;
        this.productCapacity = productCapacity;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getProductCapacity() {
        return productCapacity;
    }

    public void setProductCapacity(String productCapacity) {
        this.productCapacity = productCapacity;
    }

    @Override
    public String toString() {
        return " " + this.productCapacity;
    }
}
