package edu.curtin.oose2024s1.assignment2.states;

import java.util.logging.Logger;

import edu.curtin.oose2024s1.assignment2.Bike;
import edu.curtin.oose2024s1.assignment2.observer.Delivery;

public class ServiceState implements BikeState{
    private int days;
    private static final Logger logger = Logger.getLogger(Delivery.class.getName());
    public ServiceState() {
        this.days = 0;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    @Override
    public void purchased_online(Bike bike, String email) {}

    @Override
    public void purchased_in_store(Bike bike) {}

    @Override
    public void drop_off(Bike bike, String email) {}

    @Override
    public void run(Bike bike) {
        days++;
        if(days == 2)
        {
            bike.setState(new PickUpState());

        }
    }

    @Override
    public void pick_up(Bike bike, String email) {}


    @Override
    public void delivery(Bike bike) {}


}
