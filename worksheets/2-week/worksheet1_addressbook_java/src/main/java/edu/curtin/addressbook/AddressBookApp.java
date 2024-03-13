package edu.curtin.addressbook;

import java.io.*;
import java.util.*;

/**
 * A simple address book application.
 * @author Dave and ...
 */
public class AddressBookApp 
{
    /** Used to obtain user input. */
    private static Scanner input = new Scanner(System.in);
    
    // *^* MAKES A MAP OF OPTIONS
    private static Map<Integer, Option> options = new HashMap<Integer, Option>();
    private static SearchByEmail searchByEmail;
    private static SearchByName searchByName;
    public static void main(String[] args)
    {
        String fileName;
        // String entryName;
   
        


        // TODO System.out.print("Enter address book filename :): ");
        //fileName = input.nextLine();
        fileName  = "addressbook.txt";
        try
        {
            AddressBook addressBook = readAddressBook(fileName);
            // *^* CREATE OPTIONS
            searchByName = new SearchByName(addressBook);
            searchByEmail = new SearchByEmail(addressBook);
            // *^* INITIALISE OPTIONS
            options.put(1, searchByName);
            options.put(2, searchByEmail);

            showMenu();
        }
        catch(IOException e)
        {
            System.out.println("Could not read from " + fileName + ": " + e.getMessage());
        }

    }
    
    /**
     * Read the address book file, containing all the names and email addresses.
     *
     * @param fileName The name of the address book file.
     * @return A new AddressBook object containing all the information.
     * @throws IOException If the file cannot be read.
     */
    private static AddressBook readAddressBook(String fileName) throws IOException
    {
        AddressBook addressBook = new AddressBook();
        
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
            String line = reader.readLine();
            
            while(line != null)
            {
                String[] parts = line.split(":");
                                
                // FIXME: Insert your code here to add a new address book entry.
                // Note: 
                // parts[0] contains the person's name.
                // parts[1], parts[2], etc. contain the person's email address(es).

                List<String> emails = new ArrayList<>();
                for(int i = 1; i < parts.length; i++)
                {
                    emails.add(parts[i]);
                }
                addressBook.addEntry(parts[0], emails);
                line = reader.readLine();
            }
        }
        
        return addressBook;
    }
    
    /**
     * Show the main menu, offering the user options to (1)  search entries by 
     * name, (2) search entries by email, or (3) quit.
     *
     * @param addressBook The AddressBook object to search.
     */
    private static void showMenu()
    {
        boolean done = false;
        while(!done)
        {
            System.out.println("Choose an option:");
            for(Map.Entry<Integer, Option> entry : options.entrySet())
            {
                System.out.print("(" + entry.getKey() + ") " + entry.getValue().toString() + " ");
            }
            System.out.println(" (3) Quit");
            // System.out.println("(1) Search by name, (2) Search by email, (3) Quit");
            
            try
            {

                Integer key = Integer.parseInt(AddressBookApp.input.nextLine());
                if(options.get(key) != null && key != 3)
                {
                    System.out.println("Enter the search value:");
                    String value = input.nextLine();
                    System.out.println(options.get(key).doOption(value));
                }
                else if(key == 3)
                {
                    done = true;
                }
                else
                {
                    System.out.println("Invalid option");
                }


            }
            catch(NumberFormatException e)
            {
                // The user entered something non-numerical.
                System.out.println("Enter a number");
            }
        }
    }
    
}
