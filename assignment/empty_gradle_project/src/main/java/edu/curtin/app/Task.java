package edu.curtin.app;

import java.util.List;

public class Task implements WBSItem{
    private String id;
    private String description;
    private int effort;

    public Task(String id, String description){
        this.id = id;
        this.description = description;
        
    }


    public void setEffort (int effort){
        this.effort = effort;
    }

    @Override
    public void setEffort (String id, int effort){
        if(this.id.equals(id)){
            this.effort = effort;
        }
    }

    @Override
    public String getName(){
        return this.id;
    }
    

    @Override
    public WBSItem find(String id) {
        if( this.id.equals(id)){
            return this;
        }
        return null;
        
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + id + ": " + description + ", effort = " + effort);
    }   

    @Override
    public void estimateEffort(List<String> tasks, String searchTerm) {
       
        if(this.id.equals(searchTerm)){
            tasks.add(this.id);
        }
    }


    @Override
    public void calcEffort(WBSInfo info) {
        // TODO Auto-generated method stub  
        info.addEffort(this.effort);

    }

  
    
    

}
