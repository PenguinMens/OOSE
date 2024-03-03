package edu.curtin.addressbook;

import java.util.*;
        
/**
 * Represents a single address book entry.
 * 
 * @author ...
 */
public class Entry 
{
    // Insert your code here.
    String name;
    List<String> Emails;

    public Entry(String name, List<String> Emails)
    {
        this.name = name;
        this.Emails = Emails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getEmails() {
        return Emails;
    }

    public void setEmails(List<String> emails) {
        Emails = emails;
    }

    public String toString()
    {
        return this.name+ " " + this.Emails;
    }
    
}
