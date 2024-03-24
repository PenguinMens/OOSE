package edu.curtin.productfinder;

import java.util.List;

public class Product implements CatalogueItem {
    
    private double price;
    private String name;
    private int quantity;

    public Product(double price, String name, int quantity) {
        this.price = price;
        this.name = name;
        this.quantity = quantity;
    }

    @Override
    public double calcStockValue() {
        return price * quantity;
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + "$" + price + " - " + name);
    }

    @Override
    public void findProducts(List<String> products, String searchTerm, double minPrice, double maxPrice) {
        if (name.contains(searchTerm) && price >= minPrice && price <= maxPrice) {
            products.add("$" + price + " - " + name);
        }
    }

    @Override
    public CatalogueItem find(String name) {
        if (this.name.equals(name)) {
            return this;
        }
        return null;
    }

}
