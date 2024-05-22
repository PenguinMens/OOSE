package edu.curtin.oose2024s1.assignment2.states;

import java.util.logging.Logger;

import edu.curtin.oose2024s1.assignment2.Bike;
import edu.curtin.oose2024s1.assignment2.observer.Delivery;
import edu.curtin.oose2024s1.assignment2.StateObserver.StateTransistionCallback;
public class CanBeSoldState implements BikeState{

    private static final Logger logger = Logger.getLogger(Delivery.class.getName());
    private StateTransistionCallback stateTransistionCallback;
    @Override
    public void purchased_online(Bike bike, String email) {
        logger.info("A bike has been purchased onlinse");
        bike.setState(new PickUpState());
        bike.setEmail(email);

    }

    @Override
    public void purchased_in_store(Bike bike) {
        logger.info("A bike has been purchased Instore");
        bike.setState(new EmptyState());
        
    }

    @Override
    public void drop_off(Bike bike, String email) {}

    @Override
    public void run(Bike bike) {}
    
    @Override
    public void pick_up(Bike bike, String email) {}

    @Override
    public void delivery(Bike bike) {}

    @Override
    public void setStateTransitionCallback(StateTransistionCallback callback) {
        // TODO Auto-generated method stub
        
    }


}
