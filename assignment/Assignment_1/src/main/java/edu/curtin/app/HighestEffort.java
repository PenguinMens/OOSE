package edu.curtin.app;

import java.util.Collections;
import java.util.List;

public class HighestEffort extends Reconcilation {
    
    @Override
    protected int hook(List<Integer> effortsEsitmates) {
        
        System.out.println("PERFORMING HIGHEST ESTIMATE!!!\n");
        int effort =  Collections.max(effortsEsitmates);
        System.out.printf("Selecting the highest estimate: %d\n", effort);
        return effort;
    }

}