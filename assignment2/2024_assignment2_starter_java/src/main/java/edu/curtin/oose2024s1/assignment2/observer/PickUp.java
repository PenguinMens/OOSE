package edu.curtin.oose2024s1.assignment2.observer;

public class PickUp implements NewMessage{

    private String message;

    @Override
    public void update(String message){
        if(message.contains("PICK-UP"))
        {
            System.out.println("    Pick-UP: " + message);
        }
            
        this.message = message;
    }
    
}
