package edu.curtin.oose2024s1.assignment2.observer;

import edu.curtin.oose2024s1.assignment2.Bike;
import edu.curtin.oose2024s1.assignment2.BikeInventory;

public class InStore implements NewMessage {
    
    private String message;

    @Override
    public void update(String message , BikeInventory bikeInventory){
        if(message.contains("IN-STORE"))
        {
            System.out.println("    InStore: " + message);
        }
            
        this.message = message;
    }
}
