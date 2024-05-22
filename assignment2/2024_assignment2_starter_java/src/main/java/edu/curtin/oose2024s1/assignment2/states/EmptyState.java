package edu.curtin.oose2024s1.assignment2.states;

import java.util.logging.Logger;

import edu.curtin.oose2024s1.assignment2.Bike;
import edu.curtin.oose2024s1.assignment2.observer.Delivery;

public class EmptyState implements BikeState{

    private static final Logger logger = Logger.getLogger(Delivery.class.getName());

    @Override
    public void purchased_online(Bike bike, String email) {  }

    @Override
    public void purchased_in_store(Bike bike) {}

    @Override
    public void drop_off(Bike bike, String email) {
        logger.info(() -> "A bike has been delivered from , " +  email);
        bike.setState(new ServiceState());
    }

    @Override
    public void run(Bike bike) {}

    @Override
    public void pick_up(Bike bike, String email) {}


    @Override
    public void delivery(Bike bike) {
        // TODO Auto-generated method stub
        logger.info("A bike has been delivered");
        bike.setState(new CanBeSoldState());
    }

    
}
