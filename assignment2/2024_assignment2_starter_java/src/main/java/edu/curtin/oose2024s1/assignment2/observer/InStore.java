package edu.curtin.oose2024s1.assignment2.observer;

import java.util.logging.Logger;

import edu.curtin.oose2024s1.assignment2.Bike;
import edu.curtin.oose2024s1.assignment2.StateObserver.BikeInventory;

public class InStore implements NewMessage {
    
    private String message;
    private static final Logger logger = Logger.getLogger(Delivery.class.getName());
    @Override
    public void update(String message , BikeInventory bikeInventory){
        if(message.contains("IN-STORE"))
        {
            logger.info(() -> "Doing IN-STORE stuff: " + message);
            bikeInventory.sellBike();
        }
            
        this.message = message;
    }
}
