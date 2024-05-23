package edu.curtin.oose2024s1.assignment2.observer;
import edu.curtin.oose2024s1.assignment2.StateObserver.BikeInventory;
public interface NewMessage {
    public void update(String message, BikeInventory bikeInventory);
}
