package edu.curtin.oose2024s1.assignment2.states;

import edu.curtin.oose2024s1.assignment2.Bike;

public class PickUpState implements BikeState {

    
    @Override
    public void purchased_online(Bike bike, String email) {   }

    @Override
    public void purchased_in_store(Bike bike) {}

    @Override
    public void drop_off(Bike bike, String email) {}

    @Override
    public void run(Bike bike) {}

    @Override
    public void pick_up(Bike bike, String email) {
        if(email.equals(bike.getEmail())){
            bike.setState(new EmptyState());
        }
    }


    @Override
    public void delivery(Bike bike) {}

    
}
