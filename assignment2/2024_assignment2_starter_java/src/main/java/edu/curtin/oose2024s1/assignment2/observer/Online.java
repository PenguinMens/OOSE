package edu.curtin.oose2024s1.assignment2.observer;

public class Online implements NewMessage{
    
    private String message;

    @Override
    public void update(String message){
        if(message.contains("ONLINE"))
        {
            System.out.println("    Online: " + message);
        }
        
        this.message = message;
    }
}
