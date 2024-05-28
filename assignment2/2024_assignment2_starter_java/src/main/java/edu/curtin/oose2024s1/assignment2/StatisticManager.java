package edu.curtin.oose2024s1.assignment2;

import java.util.Map;
import java.util.HashMap;

public class StatisticManager {
    private Map<StatisticTypes, Integer> errorMap;

    public StatisticManager() {
        errorMap = new HashMap<>();
        for (StatisticTypes errorType : StatisticTypes.values()) {
            errorMap.put(errorType, 0);
        }
    }

    public void incrementError(StatisticTypes errorType) {
        errorMap.put(errorType, errorMap.get(errorType) + 1);
    }

    public void printErrorLog() {
        System.out.println("Error Log:");
        for (Map.Entry<StatisticTypes, Integer> entry : errorMap.entrySet()) {
            System.out.println("    "  + entry.getKey() + ": " + entry.getValue());
        }
    }
    public String printFinalStats()
    {
 
        int totalErrors = 0;
        for (int errorCount : errorMap.values()) {
            totalErrors += errorCount;
        }
        totalErrors -= errorMap.get(StatisticTypes.TOTAL_MESSAGES);
        // System.out.println("Total Errors: " + totalErrors);
        // System.out.println("Total messages: " + errorMap.get(StatisticTypes.TOTAL_MESSAGES));

        return "Total Errors: " + totalErrors + "\nTotal messages: " + errorMap.get(StatisticTypes.TOTAL_MESSAGES);
    }

}
