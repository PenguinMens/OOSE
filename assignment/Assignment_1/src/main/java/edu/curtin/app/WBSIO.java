package edu.curtin.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

public class WBSIO {
    public static final String CATALOGUE_FILE = "example.txt";
    private static final Logger logger = Logger.getLogger(WBSIO.class.getName());
    public WBSItem readCatalogue(String filename) throws 
                                                               IOException, MalformedDataException
    {   
        
        int effort;
        WorkItem   root = new WorkItem("","" );
       
        Map<String, WorkItem> workitems = new HashMap<>();
        workitems.put("", root);
        String line;
        try(var reader = new BufferedReader(new FileReader(filename)))
        {
            line = reader.readLine();

            
            while(line != null)
            {
              
                
                line = line.strip();
                if(!line.isEmpty())
                {
                 
                    String parts[] = line.split(";", -1);
                    if(parts.length != 3 && parts.length != 4)
                    {
                        final String loggingMessage = "line not foramted correctly: " + line + " parts: " + parts.length;
                        logger.log(Level.SEVERE,() -> loggingMessage);
                        throw new MalformedDataException("Invalid line: " + line);
                    }

                    
                    
                    
                    String parentWorkItemID = parts[0].strip();
                    
                    WorkItem parentWorkItem = workitems.get(parentWorkItemID);
                    
                    String name = parts[1].strip();
                    String description = parts[2].strip();
                    if(name==null || name.equals(""))
                    {
                        final String loggingMessage = "name is null or empty: " + line;
                        logger.log(Level.SEVERE,() -> loggingMessage);
                        
                    }
                    if(parts.length == 4)   // task
                    {
                 
                        Task task = new Task(name, description);
                        if(!parts[3].equals("")){
                            effort = Integer.parseInt(parts[3].strip());
                            task.setEffort(effort);
                        }

                        
                        parentWorkItem.addItem(task);


                    }
                    else if(parts.length == 3) // workitem
                    {
          
                        WorkItem category = new WorkItem(name, description);
                        parentWorkItem.addItem(category);
                        workitems.put(name, category);
                    }
      
                    
                }
                line = reader.readLine();
            }
        }
        return root;
    }
}
