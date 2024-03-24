package edu.curtin.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WBSIO {
    public static final String CATALOGUE_FILE = "example.txt";

    public WBSItem readCatalogue(String filename) throws 
                                                               IOException
    {
        // TODO: Create the root category
        // ...
        WorkItem root = new WorkItem("","" );
        Map<String, WorkItem> workitems = new HashMap<>();
        workitems.put("", root);
        try(var reader = new BufferedReader(new FileReader(filename)))
        {
            String line = reader.readLine();

            
            while(line != null)
            {
              
                
                line = line.strip();
                if(!line.isEmpty())
                {
                 
                    String[] parts = line.split(";", -1);
                    String name, description, parentWorkItemID ;
                    int effort;
                    
                    parentWorkItemID = parts[0].strip();

                    WorkItem parentWorkItem = workitems.get(parentWorkItemID);
                    name = parts[1].strip();
                    description = parts[2].strip();
                    
                    if(parts.length == 4)   // task
                    {
                 
                        Task task = new Task(name, description);
                        if(!parts[3].equals("")){
                            effort = Integer.parseInt(parts[3].strip());
                            task.setEffort(effort);
                        }
                        // System.out.println("Task: " + name + " " + description);
                        // System.out.println("     TASK");
                        
                        
                        parentWorkItem.addItem(task);


                    }
                    else if(parts.length == 3) // workitem
                    {
          
                        WorkItem category = new WorkItem(name, description);
                        parentWorkItem.addItem(category);
                        workitems.put(name, category);
                    }
      
                    // switch(parts.length)
                    // {
                    //     case 2:  // Category
                    //         name = parts[1];
                            
                    //         WorkItem category = new WorkItem(name, );
                            
                    //         parentCategory.addItem(category);
                    //         categories.put(name, category);
                    //         break;

                    //     case 4:  // Product
                    //         name = parts[1];
                    //         try
                    //         {

                    //             double price = Double.parseDouble(parts[2]);
                    //             int quantityInStock = Integer.parseInt(parts[3]);
                    //             Product product = new Product(price, name, quantityInStock);
                    //             parentCategory.addItem(product);
                               
                    //         }
                    //         catch(NumberFormatException e)
                    //         {
                    //             throw new CatalogueFormatException("Catalogue product: invalid number format", e);
                    //         }
                    //         break;

                    //     default:
                    //         throw new CatalogueFormatException("Unknown line format");
                    // }

                }
                line = reader.readLine();
            }
        }

        // TODO: return the root category
        // return ...
        return root;
    }
}
