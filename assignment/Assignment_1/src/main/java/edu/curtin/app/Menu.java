package edu.curtin.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Menu {
    private WBSItem rootItem;
    private int developers;
    private EstimationMethod estimationMethod;
    private Map<EstimationMethod, Reconcilation> reconcilations = new HashMap<>();
    private static final Logger logger = Logger.getLogger(Menu.class.getName());
    public static final int MIN_ESTIMATE_VALUE = 0;
    public Menu(WBSItem rootItem)
    {
        this.rootItem = rootItem;
        developers = 3;
        this.estimationMethod = EstimationMethod.DEMOCRACY;
        reconcilations.put(EstimationMethod.DEMOCRACY, new DemocaryEffort());
        reconcilations.put(EstimationMethod.HIGHEST, new HighestEffort());
        reconcilations.put(EstimationMethod.MEDIAN, new MedianEffort());

    }
    public void runMenu() throws IOException
    {
        
        
        Scanner sc = new Scanner(System.in);
        String id;
        boolean done = false;
        while(!done)
        {
            
            System.out.print(
                "\nChoose - (x) exit, (d) display, ,(c) configure, (e) estimate effort: ");
            logger.info("Reading user input for menu option");
            String opt = sc.nextLine();
            final String optString = opt;
            logger.info(() -> "User selected option " + optString);
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

                    for(int i = 0; i < EstimationMethod.values().length; i++){
                        System.out.printf("    (%d). %s\n", i + 1, EstimationMethod.values()[i]);
                    }

                    int methodIndex = readIntLoop(sc, "Enter the number of the method: ", 1, EstimationMethod.values().length);
                    estimationMethod = EstimationMethod.values()[methodIndex - 1];
                    break;

                case "e":
                    
                    System.out.print("Enter the ID of the item to estimate: ");
                    id = sc.nextLine();
                    List<String> tasks = new ArrayList<>();

                    WBSItem item = rootItem.find(id);
                    if (item == null){
                        logger.log(Level.WARNING, "Item not found");
                        System.out.println("Item not found.");
                        break;
                    }

                    item.estimateEffort(tasks, id);
                    if(tasks.size() ==0){
                        final String msgId = id;
                        logger.log(Level.WARNING, "No tasks found with name %s or sub tasks", msgId);
                        System.out.printf("No tasks found with name %s or sub tasks", id);
                        break;
                    }
                    System.out.println("Tasks found: ");
                    for (String task : tasks){
                        System.out.println("    "+task);
                    }
                    for(String task : tasks)
                    {
                        WBSItem currentItem= rootItem.find(task);
                        System.out.print("Performing "+ estimationMethod + " estimation for:");
                        currentItem.display();
                        Reconcilation reconcilation = reconcilations.get(estimationMethod);
                        reconcilation.reconcilation(currentItem, task, developers, sc);
                    }
                    break;

                case "x":
                    List<String> tasks2 = new ArrayList<>();
                    System.out.println("Saving to file");
                    WBSItem currentItem= rootItem.find("");
                    logger.info(() -> "Saving to file " + App.getFilename());
                    try( BufferedWriter writer = new BufferedWriter(new FileWriter(App.getFilename()))){
                        String saveString = currentItem.saveWBS(tasks2, "", "");
                        writer.write(saveString);
                    }
                    logger.info("Saved to file");
                    done = true;
                    break;

                default:
                    logger.log(Level.WARNING, () -> "Unknown option " + optString);
                    System.out.printf("Unknown option '%s'.\n", opt);

            }
        }
  
    }

    public static int readIntLoop(Scanner sc, String prompt, int min)
    {
        return readIntLoop(sc, prompt, min, Integer.MAX_VALUE);
    }
    public static int readIntLoop(Scanner sc, String prompt, int min, int max) 
    {
        if(min > max)
        {

            throw new IllegalArgumentException("Min must be less than max.");
        }
        int value = min - 1;
        while(value < min || value > max)
        {
            System.out.print(prompt);
            try
            {
                logger.log(Level.INFO, "Reading integer value");
                value = Integer.parseInt(sc.nextLine());
                if(value < min || value > max)
                {
                    final int minValue = min;
                    final int maxValue = max;
                    logger.log(Level.WARNING, () -> "Integer Value must be between " +minValue + " and " + maxValue);
                    System.out.printf("Integer Value must be between %d and %d.\n", min, max);
                }
            }
            catch(NumberFormatException e)
            {
                System.out.print("Invalid number format.\n");
                logger.log(Level.WARNING, "Invalid value", e);
            }
            
        }
        return value;
    }
    

}
