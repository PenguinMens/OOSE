package edu.curtin.oose2024s1.assignment2.StateObserver;

import edu.curtin.oose2024s1.assignment2.Bike;
import edu.curtin.oose2024s1.assignment2.states.BikeState;

public interface StateTransistionCallback {
    void onStateTransition(Bike bike, BikeState oldState, BikeState newState);
}
