package edu.curtin.oose2024s1.assignment2.observer;

import edu.curtin.oose2024s1.assignment2.BikeInventory;

public class Online implements NewMessage{
    
    private String message;

    @Override
    public void update(String message, BikeInventory bikeInventory){
        if(message.contains("ONLINE"))
        {
            System.out.println("    Online: " + message);
        }
        
        this.message = message;
    }
}
