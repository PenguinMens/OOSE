package edu.curtin.vehicleapp;

import java.io.*;
import java.util.*;

public class VehicleApp
{
    public static void main(String[] args)
    {
        new VehicleApp().run(new ServicesFileIO(), new RequestsFileIO());
    }

    public void run(ServicesFileIO servicesReader, RequestsFileIO requestsReader)
    {
        try
        {
            List<VehicleService> services
                = servicesReader.readServices(ServicesFileIO.SERVICES_FILE);

            List<CustomerRequest> requests
                = requestsReader.readRequests(RequestsFileIO.REQUESTS_FILE);

            System.out.println("Finding available services for the following customer requests:\n");

            for(var request : requests)
            {
                System.out.println(request.format());
                boolean anyValid = false;
                for(var service : services)
                {
                    if(request.satisfiedBy(service.getDescription()))
                    {
                        anyValid = true;
                        System.out.printf("  $%.2f -- %s\n", request.calcCost(), service.getName());
                    }
                }

                if(!anyValid)
                {
                    System.out.println("  [no available services]");
                }
                System.out.println();
            }
        }
        catch(FileParseException e)
        {
            System.out.println(
                "File format error in services and/or requests file: " + e.getMessage());
        }
        catch(IOException e)
        {
            System.out.println(
                "Could not read services and/or requests file: " + e.getMessage());
        }
    }
}
