package edu.curtin.oose2024s1.assignment2;
import edu.curtin.oose2024s1.assignment2.observer.Delivery;
import edu.curtin.oose2024s1.assignment2.observer.*;


import java.util.ArrayList;
import java.util.List;
public class BikeShop {
    List<NewMessage> observers = new ArrayList<NewMessage>();
    BikeInventory bikeInventory;
    public BikeShop(BikeInventory bikeInventory){
        
        // BikeShopInput inp = new BikeShopInput(123);  // Seed for the random number generator
        observers.add(new Delivery());
        observers.add(new DropOff());
        observers.add(new PickUp());
        observers.add(new Online());
        observers.add(new InStore());   
        this.bikeInventory = bikeInventory;
    }
    
    

    public void newMessage(String message){
   
        for(NewMessage observer : observers){
            observer.update(message,bikeInventory);
        }
    }
}
