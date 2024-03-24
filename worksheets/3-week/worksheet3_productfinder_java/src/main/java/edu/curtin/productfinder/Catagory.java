package edu.curtin.productfinder;
import java.util.ArrayList;
import java.util.List;

public class Catagory implements CatalogueItem {
    private String name;
    private List<CatalogueItem> items;

    public Catagory(String name) {
        this.name = name;
        this.items = new ArrayList<CatalogueItem>();
    }
    @Override
    public void display(String indent) {
        
        System.out.println(indent + name);   
        for (CatalogueItem item : items) {
            item.display(indent + "  ");
        }
    }

    @Override
    public double calcStockValue() {
        // TODO
        double total = 0;
        for (CatalogueItem item : items) {
            total += item.calcStockValue();
        }
        return total;
    }

    @Override
    public void findProducts(List<String> products, String searchTerm, double minPrice, double maxPrice) {
        for (CatalogueItem item : items) {
            item.findProducts(products, searchTerm, minPrice, maxPrice);
        }
    }
    
    public void addItem(CatalogueItem item) {
        items.add(item);
    }
    public void removeItem(CatalogueItem item) {
        items.remove(item);
    }

    @Override
    public CatalogueItem find(String name) {
        if(this.name.equals(name)) {
            return this;
        }
        for (CatalogueItem item : items) {
            CatalogueItem product = item.find(name);
            if (product != null) {
                return product;
            }
        }
        return null;
    }
}
