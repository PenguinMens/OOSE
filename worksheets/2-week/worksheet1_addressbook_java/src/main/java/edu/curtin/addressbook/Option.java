package edu.curtin.addressbook;

public interface Option {
    String doOption(String value);
    boolean requiresText();
}