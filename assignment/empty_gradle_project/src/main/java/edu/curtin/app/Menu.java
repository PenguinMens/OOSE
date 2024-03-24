package edu.curtin.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private WBSItem rootItem;
    private int developers;
    public EstimationMethod estimationMethod;
    public Menu(WBSItem rootItem)
    {
        this.rootItem = rootItem;
        developers = 3;
        this.estimationMethod = EstimationMethod.DEMOCRACY;
    }
    public void runMenu()
    {
        
        try{
            Scanner sc = new Scanner(System.in);
            String id;
            boolean done = false;
            while(!done)
            {
                
                System.out.print(
                    "\nChoose - (x) exit, (d) display, ,(c) configure, (e) estimate effort: ");
                String opt = sc.nextLine();
                switch(opt)
                {
                    case "d":

                        rootItem.display();
                        WBSInfo eff = new WBSInfo();
                        rootItem.calcEffort(eff);
                        eff.printInfo();
 
                        break;
                        
                    case "c":

                        developers = readIntLoop(sc, "Enter the number of developers: ", 3);
                        System.out.println("Select the estimation method:");
                        int j = 1;
                        for(int i = 0; i < EstimationMethod.values().length; i++)
                        {
                            System.out.printf("    (%d). %s\n", j++, EstimationMethod.values()[i]);
                        }

                        int methodIndex = readIntLoop(sc, "Enter the number of the method: ", 1, EstimationMethod.values().length);
                        estimationMethod = EstimationMethod.values()[methodIndex - 1];
                        // String method = sc.nextLine();
                        break;

                    case "e":
                        System.out.print("Enter the ID of the item to estimate: ");
                        id = sc.nextLine();
                        List<String> tasks = new ArrayList<String>();
                        
                        WBSItem item = rootItem.find(id);
                        if (item == null){
                            System.out.println("Item not found.");
                            break;
                        }

                        item.estimateEffort(tasks, id);
                        if(tasks.size() ==0){
                            System.out.printf("No tasks found with name %s or sub tasks", id);
                            break;
                        }
                        System.out.println("Tasks found: ");
                        for (String task : tasks){
                            System.out.println("    "+task);
                        }
                        for(String task : tasks)
                        {
                            List<Integer> effortsEsitmates = new ArrayList<Integer>();
                            System.out.print("Performing estimation for ");
                            WBSItem currentItem= rootItem.find(task);
                            currentItem.display();
                            for(int i = 0; i < developers; i++)
                            {
                                System.out.printf("Developer %d: ", i + 1);
                                int effort = readIntLoop(sc, "Enter the estimate: ", 0);
                                effortsEsitmates.add(effort);
                            }
                            System.out.println("Esitamte efforts are: ");
                            for(int i = 0; i < developers; i++)
                            {
                                System.out.printf("    %d\n", effortsEsitmates.get(i));
                            }  
                            int effort = 0;
                            if(estimationMethod == EstimationMethod.DEMOCRACY)
                            {
                                System.out.println("\nPlease discuss with your team and select a single estimate.\n");
                                System.out.print("Enter the selected estimate: ");
                                
                                effort = readIntLoop(sc, "Enter the estimate: ", 0);
          
                            }
                            else if(estimationMethod == EstimationMethod.HIGHEST)
                            {
                                System.out.println("Selecting the highest estimate.");
                                effort = Collections.max(effortsEsitmates);
                                
                            }
                            else if(estimationMethod == EstimationMethod.MEDIAN)
                            {
                                System.out.println("Selecting the median estimate.");
                                Collections.sort(effortsEsitmates);
                                effort = effortsEsitmates.get(developers/2);
                     
                            }
                            currentItem.setEffort(task, effort);
                            

                        }

                        break;

                    case "x":
                        done = true;
                        break;

                    default:
                        System.out.printf("Unknown option '%s'.\n", opt);
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static int readIntLoop(Scanner sc, String prompt, int min)
    {
        int value = min - 1;
        while(value < min )
        {
            System.out.print(prompt);
            try
            {
                value = Integer.parseInt(sc.nextLine());
                if(value < min )
                {
                    System.out.print("Value must be geatrer than positive.\n");
                }
            }
            catch(NumberFormatException e)
            {
                System.out.print("Invalid number format.\n");
            }
            
        }
        return value;
    }
    private static int readIntLoop(Scanner sc, String prompt, int min, int max)
    {
        int value = min - 1;
        while(value < min || value > max)
        {
            System.out.print(prompt);
            try
            {
                value = Integer.parseInt(sc.nextLine());
                if(value < min || value > max)
                {
                    System.out.printf("Value must be between %d and %d.\n", min, max);
                }
            }
            catch(NumberFormatException e)
            {
                System.out.print("Invalid number format.\n");
            }
            
        }
        return value;
    }
        /**
     * Reads a double from the console and returns it, or a default value if no valid number is
     * entered.
     */
    private static double readDouble(Scanner sc, double defaultValue)
    {
        try
        {
            return Double.parseDouble(sc.nextLine());
        }
        catch(NumberFormatException e)
        {
            return defaultValue;
        }
    }
    /**
     * Reads an integer from the console and returns it, or a default value if no valid number is
     * entered.
     */
    private static int readInt(Scanner sc, int defaultValue)
    {
        try
        {
            return Integer.parseInt(sc.nextLine());
        }
        catch(NumberFormatException e)
        {
            return defaultValue;
        }
    }

}
