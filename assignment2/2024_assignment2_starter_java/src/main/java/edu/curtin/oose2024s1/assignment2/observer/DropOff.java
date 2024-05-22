package edu.curtin.oose2024s1.assignment2.observer;

import edu.curtin.oose2024s1.assignment2.BikeInventory;

public class DropOff implements NewMessage{
    private String message;

    @Override
    public void update(String message,  BikeInventory bikeInventory){
        if(message.contains("DROP-OFF"))
        {
            System.out.println("    DropOff: " + message);
        }
            
        this.message = message;
    }
}
