package edu.curtin.oose2024s1.assignment2.observer;

public class DropOff implements NewMessage{
    private String message;

    @Override
    public void update(String message){
        if(message.contains("DROP-OFF"))
        {
            System.out.println("    DropOff: " + message);
        }
            
        this.message = message;
    }
}
