package edu.curtin.app;

import java.util.ArrayList;
import java.util.List;

public class WorkItem implements WBSItem {
    private String id;
    private String description;
    private List<WBSItem> items;

    public WorkItem(String id, String description){
        this.id = id;
        this.description = description;
        this.items = new ArrayList<WBSItem>();
    }

    @Override
    public WBSItem find(String name) {
        if(this.id.equals(name)) {
            return this;
        }
        for (WBSItem item : items) {
            WBSItem task = item.find(name);
            if (task != null) {
                return task;
            }
        }
        return null;
    }

    @Override
    public void setEffort(String id, int effort) {
        for (WBSItem item : items) {
            item.setEffort(id, effort);
        }
        
        
    }

    @Override
    public void display(String indent) {
        
        System.out.println(indent + this.id+": " + this.description);   
        for (WBSItem item : items) {
            item.display(indent + "  ");
        }
    }

    @Override
    public void estimateEffort(List<String> tasks, String searchTerm) {
        for (WBSItem item : items) {
            item.estimateEffort(tasks, item.getName());
        }
    }

    @Override
    public String getName(){
        return this.id;
    }
    
    public void calcEffort(WBSInfo info){
        info.addUnknown(1);
        for (WBSItem item : items) {
            item.calcEffort(info);
        }
    }

    public void addItem(WBSItem item){
        items.add(item);
    }

    public void removeItem(WBSItem item){
        items.remove(item);
    }

    
}
