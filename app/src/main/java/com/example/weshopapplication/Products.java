package com.example.weshopapplication;

public class Products {
    private int productID;
    private TechActivity.Colours colour;
    private TechActivity.Quantities quantity;

    public Products(int productID, TechActivity.Colours colour, TechActivity.Quantities quantity) {
        this.productID = productID;
        this.colour = colour;
        this.quantity = quantity;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public TechActivity.Colours getColour() {
        return colour;
    }

    public void setColour(TechActivity.Colours colour) {
        this.colour = colour;
    }

    public TechActivity.Quantities getQuantity() {
        return quantity;
    }

    public void setQuantity(TechActivity.Quantities quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Products{" +
                "productID=" + productID +
                ", colour=" + colour +
                ", quantity=" + quantity +
                '}';
    }
}
