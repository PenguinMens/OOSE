package edu.curtin.app;

public class MalformedDataException  extends Exception{
    public MalformedDataException(String message){
        super(message);
    }
    
    public MalformedDataException(String message, Throwable cause){
        super(message, cause);
    }
    
}
