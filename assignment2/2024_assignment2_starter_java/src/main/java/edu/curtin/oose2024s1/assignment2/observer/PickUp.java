package edu.curtin.oose2024s1.assignment2.observer;

import java.util.logging.Logger;

import edu.curtin.oose2024s1.assignment2.BikeInventory;

public class PickUp implements NewMessage{

    private String message;
    private static final Logger logger = Logger.getLogger(Delivery.class.getName());
    @Override
    public void update(String message , BikeInventory bikeInventory){
        if(message.contains("PICK-UP"))
        {
            logger.info(() -> "Doing PICK-UP stuff: " + message);
            System.out.println("Doing PICK-UP stuff: " + message);
            bikeInventory.pickUpBike(message.split(" ")[1]);
        }
            
        this.message = message;
    }
    
}
