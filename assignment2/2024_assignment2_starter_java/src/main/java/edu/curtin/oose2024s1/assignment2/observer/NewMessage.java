package edu.curtin.oose2024s1.assignment2.observer;
import edu.curtin.oose2024s1.assignment2.BikeInventory;
public interface NewMessage {
    public void update(String message, BikeInventory bikeInventory);
}
