package edu.curtin.oose2024s1.assignment2.states;
import edu.curtin.oose2024s1.assignment2.Bike;
import edu.curtin.oose2024s1.assignment2.StateObserver.*;


public interface BikeState {
    public void purchased_online(Bike bike, String email);
    public void purchased_in_store(Bike bike);
    public void drop_off(Bike bike,String email);
    public void delivery(Bike bike);
    public void pick_up(Bike bike, String email);
    public void run(Bike bike);
   
}
