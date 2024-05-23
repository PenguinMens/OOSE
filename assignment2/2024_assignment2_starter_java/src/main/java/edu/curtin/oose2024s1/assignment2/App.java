package edu.curtin.oose2024s1.assignment2;


import java.io.*;

import edu.curtin.oose2024s1.assignment2.StateObserver.BikeInventory;

/**
 * Use this code to get started on Assignment 2. You are free to modify or replace this file as
 * needed (to fulfil the assignment requirements, of course).
 */
public class App
{
    public static void main(String[] args)
    {
        BikeShopInput inp = new BikeShopInput();
        StateManager stateManager = new StateManager();
        BikeInventory bikeInventory = new BikeInventory(stateManager);

        BikeShop shop = new BikeShop(bikeInventory);
        
        try
        {
            int days = 0;
            System.out.println();
            while(System.in.available() == 0)
            {
                // ... ?

                // For illustration purposes -- this just prints out the messages as they come in.
                System.out.println("---");
                String msg = inp.nextMessage();
                bikeInventory.bikeIterate();
                while(msg != null)
                {
                    // System.out.println(msg);
                    
                    shop.newMessage(msg);
                    msg = inp.nextMessage();
                    
                }
                
                System.out.println("END OF DAY: " + days);
                bikeInventory.printStats();
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
        }
        catch(IOException e)
        {
            System.out.println("Error reading user input");
        }
    }
}
