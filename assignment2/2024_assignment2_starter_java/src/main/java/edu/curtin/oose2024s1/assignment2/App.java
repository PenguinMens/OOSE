package edu.curtin.oose2024s1.assignment2;


import java.io.*;

import edu.curtin.oose2024s1.assignment2.StateObserver.BikeInventory;
import edu.curtin.oose2024s1.assignment2.StateObserver.TransitionObserver;
import edu.curtin.oose2024s1.assignment2.observer.KeyWordObserver;
import edu.curtin.oose2024s1.assignment2.observer.NewMessage;

import java.util.logging.Logger;
/**
 * Use this code to get started on Assignment 2. You are free to modify or replace this file as
 * needed (to fulfil the assignment requirements, of course).
 */
public class App
{
    private static final Logger logger = Logger.getLogger(App.class.getName());
    private Boolean checkValid(String message)
    {
        int numSpaces =  message.split(" ").length-1;
        
        if (message.contains("DELIVERY") ||  message.contains("IN-STORE")&& numSpaces == 0)
        {
            return true;
        }
        else if ((message.contains("ONLINE") || message.contains("DROP-OFF") || message.contains("PICK-UP")) && numSpaces == 1)
        {
            return true;
        }
        
        return false;
    }
    private static BufferedWriter writer = null;
    public static void main(String[] args)
    {
        System.out.println("initialiseing");
       
        try {
            App.writer = new BufferedWriter(new FileWriter("bike_inventory.log", true));
        } catch (IOException e) {
            logger.severe("Failed to initialize file writer: " + e.getMessage());
        }   
        // try {
        //     System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream("output.txt"))));
        // } catch (FileNotFoundException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        //     System.out.println("Error " + e);
        //     return;
        // }
        
        App app = new App();
        BikeShopInput inp = new BikeShopInput();
        StateManager stateManager = new StateManager();
        FinanceManager financeManager = new FinanceManager();
        StatisticManager errorManager = new StatisticManager();
        BikeInventory bikeInventory = new BikeInventory(stateManager,financeManager, errorManager, writer);
        
        BikeShop shop = new BikeShop(bikeInventory);
               // pick up  
       NewMessage pickUp = new KeyWordObserver("PICK-UP", (String message) -> {

            bikeInventory.pickUpBike(message);
            return message;
        });

        // delivery
        NewMessage delivery = new KeyWordObserver("DELIVERY", (String message) -> {
            bikeInventory.newBike();;
            return message;
        });

        // drop off
        NewMessage dropOff = new KeyWordObserver("DROP-OFF", (String message) -> {
            bikeInventory.dropOffBike(message);
            return message;
        });

        //instore
        NewMessage inStore  = new KeyWordObserver("IN-STORE", (String message) -> {
            bikeInventory.sellBike();
            return message;
        });

        // online 
        NewMessage online = new KeyWordObserver("ONLINE", (String message) -> {
            bikeInventory.sellBikeOnline(message);
            return message;
        });
        shop.addNewMessageObserver(online);
        shop.addNewMessageObserver(inStore);
        shop.addNewMessageObserver(dropOff);
        shop.addNewMessageObserver(delivery);
        shop.addNewMessageObserver(pickUp);

        try
        {
            int days = 0;
            
            System.out.println();
            while(System.in.available() == 0)
            {
                // ... ?
                
                // For illustration purposes -- this just prints out the messages as they come in.
                System.out.println("\n---\n");
                String msg = inp.nextMessage();

                bikeInventory.bikeIterate();
                while(msg != null)
                {
                    // System.out.println(msg);
                    //System.out.println(msg);
                    if(app.checkValid(msg))
                    {

                        shop.newMessage(msg);
                        
                        errorManager.incrementError(StatisticTypes.TOTAL_MESSAGES);    
                    }else
                    {
               
                        logger.info("Invalid message");
                        errorManager.incrementError(StatisticTypes.INVALID_MESSAGE_PARSING_ERROR);
                    }
                    
                    msg = inp.nextMessage();
                    
                }
                if(days % 7 == 0)
                {
                    financeManager.payWorker();
                }
                writeToFile("END OF DAY: " + days);
                
                writeToFile(financeManager.printStats());
                writeToFile(bikeInventory.printStats());
                // errorManager.printErrorLog();
                // Wait 1 second
                try
                {
                    Thread.sleep(1000);
                }
                catch(InterruptedException e)
                {
                    throw new AssertionError(e);
                }
                days++;
            }
            writeToFile(errorManager.printFinalStats());
        }
        catch(IOException e)
        {
            System.out.println("Error reading user input");
        }

    }
    public static void writeToFile(String message)
    {

        try {
            
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            logger.severe("Failed to write to file: " + e.getMessage());
        }
    }
}
