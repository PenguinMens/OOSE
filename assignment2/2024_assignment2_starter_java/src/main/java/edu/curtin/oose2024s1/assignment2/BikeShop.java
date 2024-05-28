package edu.curtin.oose2024s1.assignment2;
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
    private static final Logger logger = Logger.getLogger(BikeShop.class.getName());

    public BikeShop(BikeInventory bikeInventory){
        this.bikeInventory = bikeInventory;




    }
    
    public void addNewMessageObserver(NewMessage observer){
        observers.add(observer);
    }

    public void newMessage(String message){
   
        for(NewMessage observer : observers){
            observer.update(message,bikeInventory);
        }
    }


}
