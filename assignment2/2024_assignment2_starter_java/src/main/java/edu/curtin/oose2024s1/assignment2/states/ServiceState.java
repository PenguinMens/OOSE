package edu.curtin.oose2024s1.assignment2.states;

import java.util.logging.Logger;

import edu.curtin.oose2024s1.assignment2.Bike;


public class ServiceState implements BikeState{
    private int days;

    private static final Logger logger = Logger.getLogger(ServiceState.class.getName());
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
        if(days == 2) // since we do ++ first
        {

            BikeState newState = new PickUpState();
           
            bike.setState(newState);

        }
    }

    @Override
    public void pick_up(Bike bike, String email) {}


    @Override
    public void delivery(Bike bike) {}


}
