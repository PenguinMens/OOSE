package edu.curtin.oose2024s1.assignment2;

import java.util.HashMap;
import java.util.Map;

import edu.curtin.oose2024s1.assignment2.states.CanBeSoldState;
import edu.curtin.oose2024s1.assignment2.states.EmptyState;

public class BikeInventory {
    private final int MAX_BIKES = 100;
    private int availableBikes=0;
    private int readyBikes=0;
    private int serviceBikes=0;
    // need to swap bikes to map or different arrays
    private Map<EstimationMethod, Reconcilation> reconcilations = new HashMap<>();
    private Bike[] bikes = new Bike[MAX_BIKES];

    public int getMAX_BIKES() {
        return MAX_BIKES;
    }

    public int getAvailableBikes() {
        return availableBikes;
    }

    public void setAvailableBikes(int availableBikes) {
        this.availableBikes = availableBikes;
    }

    public int getReadyBikes() {
        return readyBikes;
    }

    public void setReadyBikes(int readyBikes) {
        this.readyBikes = readyBikes;
    }

    public int getServiceBikes() {
        return serviceBikes;
    }

    public void setServiceBikes(int serviceBikes) {
        this.serviceBikes = serviceBikes;
    }

    public void newBike()
    {
        if (!isFull()) 
        {
            for(int i = 0; i < 100; i++)
            {
                if (bikes[i].get) {
                    
                }
            }
        }
        
    }
    private Boolean isFull()
    {
        return availableBikes + readyBikes + serviceBikes < 100;
    }
    public BikeInventory(){
        for(int i = 0; i < MAX_BIKES;i++){
            if( i % 2 == 0){
                bikes[i] = new Bike(new EmptyState());
            }
            else{
                bikes[i] = new Bike(new CanBeSoldState());
                availableBikes++;
            }
        }
    }

    
}
