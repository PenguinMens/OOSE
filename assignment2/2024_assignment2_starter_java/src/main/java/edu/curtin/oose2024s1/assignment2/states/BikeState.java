package edu.curtin.oose2024s1.assignment2.states;

public interface BikeState {
    public void purchased_online(String email);
    public void purchased_in_store();
    public void drop_off(String email);
    public void run();
}
