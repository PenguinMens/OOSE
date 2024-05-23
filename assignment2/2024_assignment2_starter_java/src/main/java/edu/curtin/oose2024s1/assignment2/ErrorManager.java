package edu.curtin.oose2024s1.assignment2;

import java.util.Map;
import java.util.HashMap;

public class ErrorManager {
    private Map<ErrorType, Integer> errorMap;

    public ErrorManager() {
        errorMap = new HashMap<>();
        for (ErrorType errorType : ErrorType.values()) {
            errorMap.put(errorType, 0);
        }
    }

    public void incrementError(ErrorType errorType) {
        errorMap.put(errorType, errorMap.get(errorType) + 1);
    }

    public void printErrorLog() {
        System.out.println("Error Log:");
        for (Map.Entry<ErrorType, Integer> entry : errorMap.entrySet()) {
            System.out.println("    "  + entry.getKey() + ": " + entry.getValue());
        }
    }
}
