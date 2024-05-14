package edu.curtin.oose2024s1.assignment2;

public class Bike {
    public enum BikeState{
        AVAILABLE,
        SERVICE,
        PICKUP
    }

    private BikeState state = BikeState.AVAILABLE;
    private String email;

    public void purchased_online(String email){
        switch (state) {
            case AVAILABLE:
                state = BikeState.PICKUP;
                this.email = email;
                break;
            case SERVICE:
                throw new IllegalStateException("Bike is in service");
            case PICKUP:
                throw new IllegalStateException("Bike is being picked up");
        }
    }

    public void purchased_in_store(){
        switch (state) {
            case AVAILABLE:
                state = BikeState.PICKUP;
                break;
            case SERVICE:
                throw new IllegalStateException("Bike is in service");
            case PICKUP:
                throw new IllegalStateException("Bike is being picked up");
        }
    }

    public void drop_off(){
        switch (state) {
            case AVAILABLE:
                state = BikeState.SERVICE;
            case SERVICE:
                break;
            case PICKUP:
                throw new IllegalStateException("Bike is being picked up");
        }
    }

}
