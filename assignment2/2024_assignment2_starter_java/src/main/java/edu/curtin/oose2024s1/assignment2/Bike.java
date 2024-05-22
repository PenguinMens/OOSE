package edu.curtin.oose2024s1.assignment2;
import edu.curtin.oose2024s1.assignment2.states.*;


public class Bike {
 

    private BikeState bikeState;
    private String email;

    public Bike(BikeState bikeState){
        this.bikeState = bikeState;
    }

    public void setState(BikeState bikeState)
    {
        this.bikeState = bikeState;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail() { return email;}
    


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
 
    }
}
