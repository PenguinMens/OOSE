package edu.curtin.app;

import java.io.IOException;

public class App
{
    private static String filename;
    public static void main(String[] args)
    {

        filename ="default.txt";;
        System.out.println(args.length);
        if(args.length >0)
        {
            filename = args[0];
        }
        
        
        WBSIO wbsio = new WBSIO();
        try
        {
            Menu menu = new Menu(wbsio.readCatalogue(filename));
            menu.runMenu();
        }catch(IOException e)
        {
            System.out.println("Error reading file: " + e.getMessage());
        }
        catch(MalformedDataException e)
        {
            System.out.println("file is not formated correctly: " + e.getMessage());
        }

        

        // If you wish to change the name and/or package of the class containing 'main()', you 
        // will also need to update the 'mainClass = ...' line in build.gradle.
    }

    public static String getFilename()
    {
        return filename;
    }

}
