package edu.curtin.vehicleapp;

import java.io.*;
import java.util.*;
import java.time.*;
import java.time.format.*;

/**
 * Parsing logic for the customer requests file.
 *
 * TODO: you will need to insert your own code below to build the Decorator structure. Fortunately,
 * you _don't_ need to write any of the actual parsing code -- that's already done.
 *
 * Just FYI: each line of the customer requests file contains one request, broken up into parts
 * separated by ";". The first part represents the base request; the remaining parts represent
 * add-ons. Each part itself is broken up into fields, separated by ",".
 *
 * Each base request (and hence each line) starts with "taxi", "tour" or "rent". Each add-on starts
 * with "nseats", "wheelchair", "storage_kg", "storage_dim" or "entertainment". These terms
 * determine what the rest of the fields are.
 *
 * Some fields represent times; these are expressed in ISO format 'yyyy-mm-ddThh:mm:ss', because
 * this can be easily parsed into LocalDateTime objects.
 */
public class RequestsFileIO
{
    public static final String REQUESTS_FILE = "requests.txt";

    public List<CustomerRequest> readRequests(String filename) throws FileParseException,
                                                                      IOException
    {
        var requests = new ArrayList<CustomerRequest>();
        try(var reader = new BufferedReader(new FileReader(filename)))
        {
            String line = reader.readLine();
            while(line != null)
            {
                line = line.strip();
                if(!line.isEmpty())
                {
                    requests.add(readRequest(line));
                }
                line = reader.readLine();
            }
        }
        return requests;
    }

    private CustomerRequest readRequest(String line) throws FileParseException, IOException
    {
        String[] parts = line.split(";");

        CustomerRequest request;

        // The first part of the line describes the base service being requested -- taxi, tour or
        // rental -- and associated core info.
        String[] fields = parts[0].strip().split(",");

        switch(fields[0])
        {
            case "taxi":
                check(fields.length == 4);

                String origin = fields[1].strip();
                String dest = fields[2].strip();
                LocalDateTime taxiTime = getTime(fields[3].strip());

                // TODO: Do something with these fields!
                // request = ...;

                break;

            case "tour":
                check(fields.length == 4);

                String pickUpLocation = fields[1].strip();
                LocalDateTime tourTime = getTime(fields[2].strip());
                String[] itinerary = fields[3].strip().split("\\+");

                // TODO: do something with these fields!
                // request = ...;

                break;

            case "rent":
                check(fields.length == 3);
                LocalDateTime from = getTime(fields[1].strip());
                LocalDateTime to = getTime(fields[2].strip());

                // TODO: do something with these fields!
                // request = ...;

                break;

            default:
                throw new FileParseException("'" + fields[0] + "' is not a valid request");
        }

        // The rest of the line contains a range of extra stipulations (add-ons to the request).
        for(int i = 1; i < parts.length; i++)
        {
            fields = parts[i].strip().split(",");

            switch(fields[0])
            {
                case "nseats":
                    check(fields.length == 2);
                    int nSeats = getInt(fields[1].strip());
                    // TODO
                    // ...

                    break;

                case "wheelchair":
                    check(fields.length == 1);
                    // TODO
                    // ...

                    break;

                case "storage_kg":
                    check(fields.length == 2);
                    int nKilos = getInt(fields[1].strip());
                    // TODO
                    // ...

                    break;

                case "storage_dim":
                    check(fields.length == 4);
                    int x = getInt(fields[1].strip());
                    int y = getInt(fields[2].strip());
                    int z = getInt(fields[3].strip());
                    // TODO
                    // ...

                    break;

                case "entertainment":
                    check(fields.length == 1);
                    // TODO
                    // ...

                    break;

                default:
                    throw new FileParseException("'" + fields[0] + "' is not a valid request");
            }
        }

        // Return a CustomerRequest object
        return request;
    }

    private void check(boolean condition) throws FileParseException
    {
        if(!condition)
        {
            throw new FileParseException("Customer request file format error");
        }
    }

    private int getInt(String field) throws FileParseException
    {
        try
        {
            return Integer.parseInt(field);
        }
        catch(NumberFormatException e)
        {
            throw new FileParseException("Customer request file: invalid number", e);
        }
    }

    private LocalDateTime getTime(String field) throws FileParseException
    {
        try
        {
            return LocalDateTime.parse(field);
        }
        catch(DateTimeParseException e)
        {
            throw new FileParseException("Customer request file: invalid date/time format", e);
        }
    }
}
