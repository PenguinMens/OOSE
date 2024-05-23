package edu.curtin.oose2024s1.assignment2.observer;
import java.util.logging.Logger;

import edu.curtin.oose2024s1.assignment2.StateObserver.BikeInventory;
public class Delivery implements NewMessage {
    
    private String message;
    private static final Logger logger = Logger.getLogger(Delivery.class.getName());


    @Override
    public void update(String message ,  BikeInventory bikeInventory){
   
        logger.info(() -> "Checking if message is Deliver: " + message);
   
        if(message.contains("DELIVERY"))
        {

            bikeInventory.newBike();

        }
            
        this.message = message;
    }
}
