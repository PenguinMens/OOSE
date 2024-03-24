package edu.curtin.app;

public class WBSInfo {
    private int effort;
    private int unknown;
    public WBSInfo() {
        effort = 0;
        unknown = 0;
    }
    public void setEffort(int effort) {
        this.effort = effort;
    }
    public void setUnknown(int unknown) {
        this.unknown = unknown;
    }
    public int getEffort() {
        return effort;
    }
    public int getUnknown() {
        return unknown;
    }
    public void addEffort(int effort) {
        this.effort += effort;
    }
    public void addUnknown(int unknown) {
        this.unknown += unknown;
    }

    public void printInfo()
    {
        System.out.println("Total effort: " + effort);
        System.out.println("Unknown effort: " + unknown);
    }

    
}
