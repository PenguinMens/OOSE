package edu.curtin.addressbook;


public class SearchByEmail implements Option{

    private AddressBook addressBook;
    private boolean requiresText;
    public SearchByEmail(AddressBook addressBook) {
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
        return "Search by email";
    }

}
