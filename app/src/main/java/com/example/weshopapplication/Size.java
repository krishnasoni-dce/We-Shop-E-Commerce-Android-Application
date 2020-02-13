package com.example.weshopapplication;

public class Size {
    private int index;
    private char productSize;

    public Size(int index, char productSize) {
        this.index = index;
        this.productSize = productSize;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public char getProductSize() {
        return productSize;
    }

    public void setProductSize(char productSize) {
        this.productSize = productSize;
    }

    @Override
    public String toString() {
        return "Size{" +
                "index=" + index +
                ", productSize=" + productSize +
                '}';
    }
}
