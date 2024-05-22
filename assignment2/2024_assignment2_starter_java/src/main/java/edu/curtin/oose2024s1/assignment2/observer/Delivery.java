package edu.curtin.oose2024s1.assignment2.observer;
import java.util.logging.Logger;

import edu.curtin.oose2024s1.assignment2.BikeInventory;
public class Delivery implements NewMessage {
    
    private String message;
    private static final Logger logger = Logger.getLogger(Delivery.class.getName());


    @Override
    public void update(String message ,  BikeInventory bikeInventory){
   
        logger.info(() -> "Checking if message is Deliver: " + message);
   
        if(message.contains("DELIVERY"))
        {
            if(bikeInventory.getMaximumBikes() - bikeInventory.getCurrentBikes() >= 10)
            {
                for (int i = 0; i < 10; i++)
                { 
                    bikeInventory.newBike();
                }
            } 
        }
            
        this.message = message;
    }
}
