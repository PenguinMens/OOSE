package edu.curtin.app;

import java.util.Scanner;

public class App
{
    public static void main(String[] args)
    {
        System.out.println("Hello world");
        String filename = "example.txt";
        WBSIO wbsio = new WBSIO();
        try
        {
        Menu menu = new Menu(wbsio.readCatalogue(filename));
        menu.runMenu();
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace(System.out);
        }
        
        

        // If you wish to change the name and/or package of the class containing 'main()', you 
        // will also need to update the 'mainClass = ...' line in build.gradle.
    }

}
