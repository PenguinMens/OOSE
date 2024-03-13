package edu.curtin.addressbook;

public class SearchByName implements Option {
     
    private AddressBook addressBook;
    private boolean requiresText;
    public SearchByName(AddressBook addressBook) {
        this.addressBook = addressBook;
    }
    @Override
    public String doOption(String value) {
        return addressBook.getEntry(value).toString();
    }

    @Override
    public boolean requiresText() {
        return !requiresText;
    }

    @Override
    public String toString() {
        return "Search by name";
    }
}
