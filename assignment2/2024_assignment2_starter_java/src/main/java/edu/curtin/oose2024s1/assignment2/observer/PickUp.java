package edu.curtin.oose2024s1.assignment2.observer;

import edu.curtin.oose2024s1.assignment2.BikeInventory;

public class PickUp implements NewMessage{

    private String message;

    @Override
    public void update(String message , BikeInventory bikeInventory){
        if(message.contains("PICK-UP"))
        {
            System.out.println("    Pick-UP: " + message);
        }
            
        this.message = message;
    }
    
}
