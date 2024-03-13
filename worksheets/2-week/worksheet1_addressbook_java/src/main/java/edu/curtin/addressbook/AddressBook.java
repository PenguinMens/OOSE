package edu.curtin.addressbook;

import java.util.*;

/**
 * Contains all the address book entries.
 * 
 * @author ...
 */
public class AddressBook
{
    // Insert your code here.
    private List<Entry> entries;

    public AddressBook()
    {
        entries = new ArrayList<>();
    }

    public List<Entry> getEntries() {
        return entries;
    }
    public Entry getEntry(int index) {
        return entries.get(index);
    }
    public Entry getEntry(String name) {
        for(Entry entry : entries)
        {
            if(entry.name.equals(name))
            {
                return entry;
            }
            else
            {
                for(String email : entry.emails)
                {
                    if(email.equals(name))
                    {
                        return entry;
                    }
                }
            }
        }
        return null;
    }
    public void addEntry(String name, List<String> emails)
    {
        Entry entry = new Entry(name, emails);
        entries.add(entry);
    }

}
