package edu.curtin.app;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.List;
import java.util.Scanner;

public class DemocaryEffort  extends Reconcilation{
        private static final Logger logger = Logger.getLogger(DemocaryEffort.class.getName());
        @Override
        protected int hook(List<Integer> effortsEsitmates) {
                int  effort = -1;
                Scanner sc = new Scanner(System.in);
                System.out.println("PERFORMING DEMOCRACY ESTIMATE!!!\n");
                System.out.println("\nPlease discuss with your team and select a single estimate.\n");
                System.out.print("Enter the selected estimate: ");
                while(!effortsEsitmates.contains(effort)){
                    effort = Menu.readIntLoop(sc, "Enter the estimate: ", Menu.MIN_ESTIMATE_VALUE);
                    if(!effortsEsitmates.contains(effort)){
                        final String loggingMessage = effort + " wasn't in the list of estimates";
                        logger.log(Level.WARNING,() -> loggingMessage);
                        System.out.println("The estimate is not in the list of estimates. Please enter a valid estimate.");
                    }
                }
                System.out.printf("Selecting the democracy estimate: %d\n", effort);
                return effort;
        }
    
}
