package edu.curtin.oose2024s1.assignment2.StateObserver;

import java.io.BufferedWriter;
import java.util.logging.Logger;

import edu.curtin.oose2024s1.assignment2.*;

public class BikeInventory implements TransitionObserver {
    private final int MAX_BIKES = 100;
    private int currentBikes = 0;
    private StateManager stateManager;
    private FinanceManager financeManager;
    private StatisticManager errorManager;
    private static final Logger logger = Logger.getLogger(BikeInventory.class.getName());
    private BufferedWriter writer; 
    public BikeInventory(StateManager stateManager, FinanceManager financeManager, StatisticManager errorManager, BufferedWriter writer) {
        this.stateManager = stateManager;
        this.financeManager = financeManager;
        this.errorManager = errorManager;
        this.writer = writer;
        initializeBikes();
    }

    private void initializeBikes() {
        for (int i = 0; i < MAX_BIKES / 2; i++) {
            Bike bike = new Bike(stateManager.getStateInstance("Empty"));
            bike.addTransitionObserver(this);
            bike.setCost(0);
            stateManager.addBike("Empty", bike);
        }

        for (int i = 0; i < MAX_BIKES / 2; i++) {
            Bike bike = new Bike(stateManager.getStateInstance("Available"));
            bike.setCost(BikeShop.BIKESELLVALUE);
            bike.addTransitionObserver(this);
            stateManager.addBike("Available", bike);
            currentBikes++;
        }
    }

    public int getCurrentBikes() {
        return currentBikes;
    }

    public int getMaximumBikes() {
        return MAX_BIKES;
    }

    public void newBike() {
        StringBuilder message = new StringBuilder("BIKE DELIVERY ATTEMPT ");
        if (stateManager.getBikes("Empty").size() < 10) {
            message.append("FAILURE: No empty spaces available");
            logger.info(message.toString());
            errorManager.incrementError(StatisticTypes.NOT_ENOUGH_SPACE);
            App.writeToFile(message.toString());
            return;
        }
        if (financeManager.getBalance() < 10000.0f) {
            message.append("FAILURE: Not enough cash available");
            logger.info(message.toString());
            errorManager.incrementError(StatisticTypes.NOT_ENOUGH_CASH);
            App.writeToFile(message.toString());
            return;
        }

        logger.info("Checking bike size: " + stateManager.getBikes("Empty").size());
        for (int i = 0; i < 10; i++) {
            Bike bike = stateManager.getBikes("Empty").remove(0);
            if (bike.getCost() != 0) {
                throw new IllegalStateException("Bike cost not set");
            }
            bike.delivery();
            stateManager.addBike("Available", bike);

            financeManager.deductMoney(BikeShop.BIKECOST);
            bike.setCost(BikeShop.BIKESELLVALUE);
            currentBikes++;
        }
        message.append("SUCCESS");
        App.writeToFile(message.toString());
    }

    public void sellBike() {
        StringBuilder message = new StringBuilder("BIKE ATTEMPTED TO BE SOLD IN PERSON ");
        if (stateManager.getBikes("Available").size() == 0) {
            message.append("FAILURE: No sellable bikes available");
            logger.info(message.toString());
            errorManager.incrementError(StatisticTypes.NO_BIKES_LEFT);
            App.writeToFile(message.toString());

        }
        else
        {
            Bike bike = stateManager.getBikes("Available").remove(0);
            bike.purchased_in_store();

            stateManager.addBike("Empty", bike);
            financeManager.addMoney(bike.getCost());
            bike.setCost(0);
            currentBikes--;
            message.append("SUCCESS");
        }
        
        App.writeToFile(message.toString());
    }

    public void dropOffBike(String message) {
        String email = message.split(" ")[1];
        StringBuilder messageBuilder = new StringBuilder("DROP OFF ATTEMPT BY " + email);
        if (stateManager.getBikes("Empty").size() == 0) {
            errorManager.incrementError(StatisticTypes.NO_BIKES_LEFT);
            messageBuilder.append(" FAILURE: No empty bikes available");
            logger.info(messageBuilder.toString());
            App.writeToFile(messageBuilder.toString());
            return;
        }
        Bike bike = stateManager.getBikes("Empty").remove(0);
        bike.drop_off(email);
        bike.setCost(BikeShop.BIKESERVICE);
        stateManager.addBike("Service", bike);

        currentBikes++;
        messageBuilder.append(" SUCCESS");
        App.writeToFile(messageBuilder.toString());
    }

    public void bikeIterate() {
        logger.info("Iterating bikes");
        for (int i = 0; i < stateManager.getBikes("Service").size(); i++) {
            stateManager.getBikes("Service").get(i).run();
        }
    }

    public void pickUpBike(String message) {
        String email = message.split(" ")[1];
        StringBuilder messageBuilder = new StringBuilder("PICK UP ATTEMPT BY " + email);
        Bike bike = stateManager.findBikeByEmail("Service", email);
        if (bike != null) {
            bike.pick_up(email);
            financeManager.addMoney(bike.getCost());
            bike.setCost(0);
            messageBuilder.append(" FAILURE: BIKE_NOT_READY");
            logger.info(messageBuilder.toString());
            errorManager.incrementError(StatisticTypes.BIKE_NOT_READY);
            App.writeToFile(messageBuilder.toString());
            return;
        }
        
        bike = stateManager.findBikeByEmail("Ready", email);
        if (bike != null) {
            bike.pick_up(email);
            financeManager.addMoney(bike.getCost());
            bike.setCost(0);
            stateManager.moveBike("Ready", "Empty", bike);
            currentBikes--;
            messageBuilder.append(" SUCCESS");
            App.writeToFile(messageBuilder.toString());
            return;
        } else {
            messageBuilder.append(" FAILURE: NO_BIKE_MATCHING");
            logger.info(messageBuilder.toString());
            errorManager.incrementError(StatisticTypes.NO_BIKE_MATCHING);
            App.writeToFile(messageBuilder.toString());
            return;
        }


    }

    public void sellBikeOnline(String message) {
        String email = message.split(" ")[1];
        StringBuilder messageBuilder = new StringBuilder("BIKE ATTEMPTED TO BE SOLD ONLINE BY " + email);
        if (stateManager.getBikes("Available").size() == 0) {
            messageBuilder.append(" FAILURE: No sellable bikes available");
            logger.info(messageBuilder.toString());
            errorManager.incrementError(StatisticTypes.NO_BIKES_LEFT);
            App.writeToFile(messageBuilder.toString());
            return;
        }
        Bike bike = stateManager.getBikes("Available").remove(0);
        bike.setState(stateManager.getStateInstance("PickUp"));
        bike.setEmail(email);

        stateManager.addBike("Ready", bike);
        financeManager.addMoney(bike.getCost());
        bike.setCost(0);
        messageBuilder.append(" SUCCESS");
        App.writeToFile(messageBuilder.toString());
    }

    @Override
    public void update(Bike bike, String fromState, String toState) {
        if ("ServiceState".equals(fromState) && "PickUpState".equals(toState)) {
            handleServiceToReadyTransition(bike);
        }
    }

    private void handleServiceToReadyTransition(Bike bike) {
        stateManager.moveBike("Service", "Ready", bike);
    }

    public String printStats() {
        return "Available Bikes: " + stateManager.getBikes("Available").size() + "\n" +
                "Service Bikes: " + stateManager.getBikes("Service").size() + "\n" +
                "Ready Bikes: " + stateManager.getBikes("Ready").size(); 


        // System.out.println("Available Bikes: " + stateManager.getBikes("Available").size());
        // System.out.println("Service Bikes: " + stateManager.getBikes("Service").size());
        // System.out.println("Ready Bikes: " + stateManager.getBikes("Ready").size());
    }
}
