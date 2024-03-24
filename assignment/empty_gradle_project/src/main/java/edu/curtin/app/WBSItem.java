package edu.curtin.app;

import java.util.List;

public interface WBSItem {
    WBSItem find(String id);
    void display(String indent);
    void setEffort(String id,int effort);
    String getName();
    void calcEffort(WBSInfo info);
    
     /**
     * Finds tasks whose names contain a given search term, and whose effort falls between
     * minEffort and maxEffort (inclusive).
     *
     * When a task is found, a String representation must be generated that includes the
     * task's name and effort; e.g.: "@TODO". Such strings are added to
     * the 'tasks' list.
     a
     * @param tasks A list to which to add task descriptions.
     * @param searchTerm A piece of text that must appear in matching tasks' names.
     * @param minEffort The lowest effort of matching tasks.
     * @param maxEffort The highest effort of matching tasks.
     */
    void estimateEffort(List<String> tasks, String searchTerm);
    default void display()
    {
        display("");
    }
}
