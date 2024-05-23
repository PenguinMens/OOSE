package edu.curtin.oose2024s1.assignment2.StateObserver;

import java.util.logging.Logger;

import edu.curtin.oose2024s1.assignment2.*;
import edu.curtin.oose2024s1.assignment2.observer.Delivery;


public class BikeInventory implements TransitionObserver {
    private final int MAX_BIKES = 100;
    private int currentBikes = 0;
    private StateManager stateManager;

    private static final Logger logger = Logger.getLogger(Delivery.class.getName());
    public BikeInventory(StateManager stateManager) {
        this.stateManager = stateManager;
        initializeBikes();
    }

    private void initializeBikes() {
        for (int i = 0; i < MAX_BIKES; i++) {
            Bike bike = new Bike(stateManager.getStateInstance("Empty"));
            bike.addTransitionObserver(this);
            stateManager.addBike("Empty", bike);
        }

        for (int i = 0; i < 50; i++) {
            newBike();
        }
    }

    public int getCurrentBikes() {
        return currentBikes;
    }

    public int getMaximumBikes() {
        return MAX_BIKES;
    }

    public void newBike() {
        if(stateManager.getBikes("Empty").size() == 0){
            logger.info("No empty bikes available");
            return;
        }
        Bike bike = stateManager.getBikes("Empty").remove(0);
        bike.delivery();
        stateManager.addBike("Available", bike);
        currentBikes++;
    }

    public void sellBike() {
        if(stateManager.getBikes("Available").size() == 0){
            logger.info("No Sellable bikes available");
            return;
        }
        Bike bike = stateManager.getBikes("Available").remove(0);
        bike.purchased_in_store();
        stateManager.addBike("Empty", bike);
        currentBikes--;
    }

    public void dropOffBike(String email) {
        if(stateManager.getBikes("Empty").size() == 0){
            logger.info("No empty bikes available");
            return;
        }
        Bike bike = stateManager.getBikes("Empty").remove(0);
        bike.drop_off(email);
        stateManager.addBike("Service", bike);
        currentBikes++;
    }

    public void bikeIterate() {

        for(int i= 0; i<stateManager.getBikes("Service").size(); i++){
            stateManager.getBikes("Service").get(i).run();
        }
  
    }

    public void pickUpBike(String email) {
        Bike bike = stateManager.findBikeByEmail("Ready", email);
        if (bike != null) {
            bike.pick_up(email);
            System.out.println("########################3Bike picked up by " + email);
            stateManager.moveBike("Ready", "Empty", bike);
            currentBikes--;
        }
    }

    public void sellBikeOnline(String email) {
        if(stateManager.getBikes("Available").size() == 0){
            logger.info("No Sellable bikes available");
            return;
        }
        Bike bike = stateManager.getBikes("Available").remove(0);
        bike.setState(stateManager.getStateInstance("PickUp"));
        bike.setEmail(email);
        stateManager.addBike("Ready", bike);
    }

    @Override
    public void update(Bike bike, String fromState, String toState) {
        System.out.println("Bike state transitioned from " + fromState + " to " + toState);
        if ("ServiceState".equals(fromState) && "PickUpState".equals(toState)) {
            handleServiceToReadyTransition(bike);
        }
    }

    private void handleServiceToReadyTransition(Bike bike) {
        stateManager.moveBike("Service", "Ready", bike);
        System.out.println("Bike transitioned from Service to Ready");
    }

    public void printStats() {
        System.out.println("Current Bikes: " + currentBikes);
        System.out.println("Available Bikes: " + stateManager.getBikes("Available").size());
        System.out.println("Ready Bikes: " + stateManager.getBikes("Ready").size());
        System.out.println("Service Bikes: " + stateManager.getBikes("Service").size());
        System.out.println("Empty Bikes: " + stateManager.getBikes("Empty").size());
    }
}