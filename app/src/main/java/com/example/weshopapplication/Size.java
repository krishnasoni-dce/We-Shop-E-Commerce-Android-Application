package com.example.weshopapplication;

public class Size {
    private int index;
    private String productSize;

    public Size(int index, String productSize) {
        this.index = index;
        this.productSize = productSize;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    @Override
    public String toString() {
        return this.productSize;
    }
}
