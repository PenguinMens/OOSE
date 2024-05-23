package edu.curtin.oose2024s1.assignment2;
import edu.curtin.oose2024s1.assignment2.observer.Delivery;
import edu.curtin.oose2024s1.assignment2.StateObserver.BikeInventory;
import edu.curtin.oose2024s1.assignment2.observer.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.HashMap;
public class BikeShop {
    public static final float BIKECOST = 500.0f;
    public static final float BIKESELLVALUE = 1000.0f;
    public static final float BIKESERVICE = 100.0f;
    List<NewMessage> observers = new ArrayList<NewMessage>();
    BikeInventory bikeInventory;
    private static final Logger logger = Logger.getLogger(Delivery.class.getName());
    private Map<String, Integer> messageMap;
    public BikeShop(BikeInventory bikeInventory){
        
        // BikeShopInput inp = new BikeShopInput(123);  // Seed for the random number generator
        observers.add(new Delivery());
        observers.add(new DropOff());
        observers.add(new PickUp());
        observers.add(new Online());
        observers.add(new InStore());   
        this.bikeInventory = bikeInventory;
        messageMap = new HashMap<String, Integer>();
        messageMap.put("Not enough space", 0);
        messageMap.put("Not enough cash", 0);
        messageMap.put("No bikes left", 0);
        messageMap.put("No bike matching", 0);
        messageMap.put("Bike not ready", 0);
        messageMap.put("Invalid message (parsing error)", 0);
    }
    
    

    public void newMessage(String message){
   
        for(NewMessage observer : observers){
            observer.update(message,bikeInventory);
        }
    }


}
