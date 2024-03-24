package edu.curtin.vehicleapp;

import java.time.*;
import java.util.regex.Pattern;

/**
 * Represents a customer's request for a particular kind of service. These objects are (or should
 * be) created in the RequestsFileIO class.
 *
 * TODO: this interface should form the top of your Decorator Pattern inheritance hierarchy. The
 * rest of the hierarchy is up to you, but you should find you need to create _9_ classes (simple
 * ones!).
 */
public interface CustomerRequest
{
    /**
     * Returns a formatted string containing all the information in the customer request.
     *
     * See Util.formatTime() for a way to convert LocalDateTime objects to user-friendly strings.
     */
    String format();

    /**
     * Checks the customer request against the given service description, and returns true if the
     * given service satisfies the request (or false otherwise).
     *
     * See Util.extractNumbers() for a way of grabbing integers from the serviceDescription.
     */
    boolean satisfiedBy(String serviceDescription);

    /**
     * Calculates the total cost of fulfilling the customer's request (assuming it can be
     * fulfilled).
     */
    double calcCost();
}
