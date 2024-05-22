package edu.curtin.oose2024s1.assignment2.observer;

import java.util.logging.Logger;

import edu.curtin.oose2024s1.assignment2.BikeInventory;

public class DropOff implements NewMessage{
    private String message;
    private static final Logger logger = Logger.getLogger(Delivery.class.getName());
    @Override
    public void update(String message,  BikeInventory bikeInventory){
        if(message.contains("DROP-OFF"))
        {
            logger.info("DropOff: " + message);
            bikeInventory.dropOffBike(message.split(" ")[1]);
        }
            
        this.message = message;
    }
}
