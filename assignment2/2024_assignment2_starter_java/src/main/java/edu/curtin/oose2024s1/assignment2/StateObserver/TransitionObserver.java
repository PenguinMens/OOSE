package edu.curtin.oose2024s1.assignment2.StateObserver;
import edu.curtin.oose2024s1.assignment2.Bike;
public interface TransitionObserver {
    void update(Bike bike, String fromState, String toState);
}