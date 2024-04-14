package edu.curtin.app;

import java.util.List;

public class Task implements WBSItem{
    private String id;
    private String description;
    private int effort;

    public Task(String id, String description){
        this.id = id;
        this.description = description;
        effort = -1;
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
        String s;
        if(effort == -1){
            s = indent + id + ": " + description + ", effort = ";
        }
        else
        {
            s = indent + id + ": " + description + ", effort = " + effort;
        }   
        System.out.println(s);
    }   

    @Override
    public void estimateEffort(List<String> tasks, String searchTerm) {
       
        if(this.id.equals(searchTerm)){
            tasks.add(this.id);
        }
    }


    @Override
    public void calcEffort(WBSInfo info) {
        if(effort == -1)
        {
            
            info.addUnknown(1);
        }
        else
        {
            info.addEffort(this.effort);
        }
        
    }


    @Override
    public String saveWBS(List<String> tasks, String saveString, String parent) {
        String temp;
        if(effort == -1){
            temp = parent + ";" + id + ";" + description + ";\n";
        }
        else{
            temp = parent + ";" + id + ";" + description + ";" + effort + "\n";
        }
        saveString  += temp;
      
        return saveString;
    }

  
    
    

}
