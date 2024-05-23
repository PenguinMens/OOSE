package edu.curtin.oose2024s1.assignment2;
import java.util.ArrayList;
import java.util.List;

import edu.curtin.oose2024s1.assignment2.states.*;
import edu.curtin.oose2024s1.assignment2.StateObserver.*;

public class Bike {
 

    private BikeState bikeState;
    private String email;
    private List<TransitionObserver> transitionObservers = new ArrayList<>();

    public Bike(BikeState bikeState){
        this.bikeState = bikeState;
        
    }

    public void setState(BikeState bikeState)
    {
        String oldState = (this.bikeState != null) ? this.bikeState.getClass().getSimpleName() : "None";
        String newStateName = bikeState.getClass().getSimpleName();
        this.bikeState = bikeState;
        notifyObservers(oldState, newStateName);    
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail() { return email;}
    

       // Observer methods
       // =====================
    public void addTransitionObserver(TransitionObserver observer) {
        this.transitionObservers.add(observer);
    }

    public void removeTransitionObserver(TransitionObserver observer) {
        this.transitionObservers.remove(observer);
    }
    private void notifyObservers(String fromState, String toState) {
        for (TransitionObserver observer : transitionObservers ) {
            observer.update(this,fromState, toState);
        }
    }



    // State-dependent methods
    // =======================
    
    public void purchased_online(String email)
    {
        bikeState.purchased_online(this, email);
    }

    public void purchased_in_store()
    {
        bikeState.purchased_in_store(this);
    }

    public void drop_off(String email)
    {
        bikeState.drop_off(this, email);
    }

    public void pick_up(String email)
    {
        bikeState.pick_up(this, email);
    }

    public void run()
    {
        bikeState.run(this);
    }

    public void delivery()
    {
        bikeState.delivery(this);
    }


    
}

