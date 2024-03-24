package edu.curtin.vehicleapp;

/**
 * Simple model class representing one of the services offered to customers. There's just a name
 * and a description. The description contains information that can be extracted by the
 * CustomerRequest subclasses, to determine whether the service is suitable.
 */
public class VehicleService
{
    private final String name;
    private final String description;

    public VehicleService(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }
}
