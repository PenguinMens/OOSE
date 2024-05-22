package edu.curtin.oose2024s1.assignment2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.curtin.oose2024s1.assignment2.states.BikeState;
import edu.curtin.oose2024s1.assignment2.states.CanBeSoldState;
import edu.curtin.oose2024s1.assignment2.states.EmptyState;
import edu.curtin.oose2024s1.assignment2.states.PickUpState;
import edu.curtin.oose2024s1.assignment2.states.ServiceState;
import edu.curtin.oose2024s1.assignment2.Errors.*;
import edu.curtin.oose2024s1.assignment2.StateObserver.StateTransistionCallback;
public class BikeInventory implements StateTransistionCallback{
    private  final int MAX_BIKES = 100;
    private int currentBikes = 0;
    // need to swap bikes to map or different arrays
    private List<Bike> availableBikes = new ArrayList<Bike>();
    private List<Bike> readyBikes = new ArrayList<Bike>();
    private List<Bike> serviceBikes = new ArrayList<Bike>();
    private List<Bike> emptyBikes = new ArrayList<Bike>();
    
    public int getCurrentBikes()
    {
        return currentBikes;
    }
    public int  getMaximumBikes()
    {
        return MAX_BIKES;
    }

  

    public void newBike() 
    {
        if (emptyBikes.size() > 0) {
            Bike bike = emptyBikes.remove(0);
            bike.delivery();
            availableBikes.add(bike);
            currentBikes++;
        } 
        // else {
        //     throw new FullInventoryError("Bike Inventory is full");
        // }
    }


    public void sellBike()
    {
        if (availableBikes.size() > 0) {
            Bike bike = availableBikes.remove(0);
            bike.purchased_in_store();
            emptyBikes.add(bike);
            currentBikes--;
        }
    }

    public void dropOffBike(String email)
    {
        if (emptyBikes.size() > 0) {
            System.out.printf("Bike has been dropped off by %s\n", email);
            Bike bike = emptyBikes.remove(0);
            System.out.printf("     Bike current email %s\n", bike.getEmail());
            bike.drop_off(email);
            System.out.printf("     Bike NEW email %s\n",  bike.getEmail());
            serviceBikes.add(bike);
            currentBikes++;
        }
    }

    public void bikeIteratre()
    {
        int index = 0;
        if (serviceBikes.size() > 0) {

            for(Bike bike : serviceBikes){
                
                Bike temp = bike;
                bike.run();
                System.out.printf("Bike email before %s and after %s: \n" ,temp.getEmail(), bike.getEmail());
            }
            // System.out.printf("Comparing if bikeemail %s is the same as %s", serviceBikes.get(index).getEmail(), email);
            Bike bike = serviceBikes.remove(index);
            bike.setState(new PickUpState());
            readyBikes.add(bike);
        }
    }

    public void pickUpBike(String email)
    {
        // int index = 0;
        // if (serviceBikes.size() > 0) {
        //     for(Bike bike : serviceBikes){
        //         System.out.printf("Comparing if bikeemail %s is the same as %s\n", serviceBikes.get(index).getEmail(), email);       
        //         if(bike.getEmail().equals(email)){
        //             break;
        //         }
        //         index++;
        //     }
            

        //     if(index == serviceBikes.size()){
        //         System.out.println("Bike not found");
        //         return;
        //     }
        //     Bike bike = serviceBikes.remove(index);
        //     bike.setState(new EmptyState());
        //     emptyBikes.add(bike);
        //     currentBikes--;
        // }

    }

    public void sellBikeOnline(String email)
    {
        if (availableBikes.size() > 0) {
            Bike bike = availableBikes.remove(0);
            bike.setState(new PickUpState());
            bike.setEmail(email);
            readyBikes.add(bike);

        }
    }

    public void iterateService(String email)
    {
        for(Bike bike : serviceBikes){
            if(bike.getEmail().equals(email)){
                bike.run();
            }
        }
    }

    public BikeInventory(){
        for(int i = 0; i < MAX_BIKES;i++){
            emptyBikes.add(new Bike(new EmptyState()));
        }
        for(int i = 0; i < 50;i++){
            newBike();
            // try{
            //     newBike();
            // }
            // catch(FullInventoryError e){
            //     System.out.println(e.getMessage());
            // }
            
        }
    }

    public void print_stats()
    {
        System.out.println("Current Bikes: " + currentBikes);
        System.out.println("Available Bikes: " + availableBikes.size());
        System.out.println("Ready Bikes: " + readyBikes.size());
        System.out.println("Service Bikes: " + serviceBikes.size());
        System.out.println("Empty Bikes: " + emptyBikes.size());
    }
    @Override
    public void onStateTransition(Bike bike, BikeState oldState, BikeState newState) {
        // TODO Auto-generated method stub
        System.out.println("State transition: " + bike.getEmail() + " - " + oldState.getClass().getSimpleName() + " -> " + newState.getClass().getSimpleName());
    }
}
