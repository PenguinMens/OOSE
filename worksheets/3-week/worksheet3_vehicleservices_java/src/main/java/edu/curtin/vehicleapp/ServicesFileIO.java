package edu.curtin.vehicleapp;

import java.io.*;
import java.util.*;

/**
 * Parsing logic for the services file. Each line of the file contains a name and a description,
 * separated by ":". These go into a list of VehicleService objects.
 */
public class ServicesFileIO
{
    public static final String SERVICES_FILE = "services.txt";

    public List<VehicleService> readServices(String filename) throws FileParseException,
                                                                     IOException
    {
        var services = new ArrayList<VehicleService>();
        try(var reader = new BufferedReader(new FileReader(filename)))
        {
            String line = reader.readLine();
            while(line != null)
            {
                line = line.strip();
                if(!line.isEmpty())
                {
                    var parts = line.split(":", 2);
                    if(parts.length != 2)
                    {
                        throw new FileParseException("Vehicle services file format error");
                    }

                    services.add(new VehicleService(parts[0], parts[1]));
                }
                line = reader.readLine();
            }
        }
        return services;
    }
}
