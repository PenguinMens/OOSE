package edu.curtin.oose2024s1.assignment2.observer;

public class InStore implements NewMessage {
    
    private String message;

    @Override
    public void update(String message){
        if(message.contains("IN-STORE"))
        {
            System.out.println("    InStore: " + message);
        }
            
        this.message = message;
    }
}
