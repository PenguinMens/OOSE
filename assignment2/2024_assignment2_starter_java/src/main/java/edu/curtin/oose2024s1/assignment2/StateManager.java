package edu.curtin.oose2024s1.assignment2;


import edu.curtin.oose2024s1.assignment2.states.BikeState;
import edu.curtin.oose2024s1.assignment2.states.CanBeSoldState;
import edu.curtin.oose2024s1.assignment2.states.EmptyState;
import edu.curtin.oose2024s1.assignment2.states.PickUpState;
import edu.curtin.oose2024s1.assignment2.states.ServiceState;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class StateManager {
    private Map<String, List<Bike>> bikeMap;

    public StateManager() {
        bikeMap = new HashMap<>();
        bikeMap.put("Available", new ArrayList<>());
        bikeMap.put("Ready", new ArrayList<>());
        bikeMap.put("Service", new ArrayList<>());
        bikeMap.put("Empty", new ArrayList<>());
    }

    public void addBike(String state, Bike bike) {
        bikeMap.get(state).add(bike);
    }

    public void removeBike(String state, Bike bike) {
        bikeMap.get(state).remove(bike);
    }

    public List<Bike> getBikes(String state) {
        return bikeMap.get(state);
    }

    public Bike findBikeByEmail(String state, String email) {
        for (Bike bike : bikeMap.get(state)) {
            System.out.printf("trying to find bike %s, %s\n", bike.getEmail(), email);
            if (bike.getEmail().equals(email)) {
                return bike;
            }
        }
        return null;
    }

    public void moveBike(String fromState, String toState, Bike bike) {
        removeBike(fromState, bike);
        bike.setState(getStateInstance(toState));
        addBike(toState, bike);
    }

    public BikeState getStateInstance(String state) {
        switch (state) {
            case "Available":
                return new CanBeSoldState();
            case "Ready":
                return new PickUpState();
            case "Service":
                return new ServiceState();
            case "Empty":
            default:
                return new EmptyState();
        }
    }
}