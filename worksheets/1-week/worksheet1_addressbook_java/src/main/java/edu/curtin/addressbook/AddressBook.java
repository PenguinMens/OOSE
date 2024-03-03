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

    public void addEntry(String name, List<String> emails)
    {
        Entry entry = new Entry(name, emails);
        entries.add(entry);
    }

}
