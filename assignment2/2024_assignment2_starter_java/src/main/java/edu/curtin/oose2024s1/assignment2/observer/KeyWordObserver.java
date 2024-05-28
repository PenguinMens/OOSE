package edu.curtin.oose2024s1.assignment2.observer;

import java.util.function.Function;

import edu.curtin.oose2024s1.assignment2.StateObserver.BikeInventory;

public class KeyWordObserver implements NewMessage {
    // changed 5 observers to just one using function genericc
    // I think this is a way to use generics
    private String keyWord;
    private Function<String, String> action;
    public KeyWordObserver(String keyWord, Function<String, String> action) {
        this.keyWord = keyWord;
        this.action = action;
    }

    @Override
    public void update(String message, BikeInventory bikeInventory){
        if(message.contains(keyWord))
        {
            action.apply(message);
    
            // logger.info(() -> "Doing ONLINE stuff: " + message);
            // bikeInventory.sellBikeOnline(message.split(" ")[1]);
        }
        
        // this.message = message;   
    }
}
