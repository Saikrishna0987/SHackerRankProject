package com.company.model;

public class Ingredients {
    private  String item;
    private  double quantity;
    private double price;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Ingredients{" +
                "item='" + item + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
