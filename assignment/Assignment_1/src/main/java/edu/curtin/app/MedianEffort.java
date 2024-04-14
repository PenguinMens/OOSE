package edu.curtin.app;

import java.util.Collections;
import java.util.List;

public class MedianEffort extends Reconcilation{
    
        @Override
        protected int hook(List<Integer> effortsEsitmates) {
            System.out.println("PERFORMING MEDIAN ESTIMATE!!!\n");
            Collections.sort(effortsEsitmates);
            int effort = effortsEsitmates.get(effortsEsitmates.size()/2);
            System.out.printf("Selecting the median estimate: %d\n", effort);
            return effort;
        }
}
