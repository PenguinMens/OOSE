package edu.curtin.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public abstract class Reconcilation {

    public void reconcilation(WBSItem currItem, String task, int developers, Scanner sc) {

            List<Integer> effortsEsitmates = new ArrayList<>();
            System.out.print("\n    ");
            currItem.display();
            for(int i = 0; i < developers; i++)
            {
                System.out.printf("Developer %d: ", i + 1);
                int effort = Menu.readIntLoop(sc, "Enter estimate: ", 0);
                effortsEsitmates.add(effort);
            }
            System.out.println("Estimate efforts are: ");
            for(int i = 0; i < developers; i++)
            {
                System.out.printf("    %d\n", effortsEsitmates.get(i));
            }  

            int effort  = hook(effortsEsitmates);

            currItem.setEffort(task, effort);


        }    
    protected abstract int hook(List<Integer> effortsEsitmates);


}