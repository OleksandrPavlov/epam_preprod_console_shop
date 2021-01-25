package com.epam.preprod.Pavlov.constants;


public class CommandConstants {
    private CommandConstants(){

    }
    //GetItemNameCommand
    public static final String ITEM_NAME_FORMAT = "Item name by id->%s = %s|%s";
    public static final String IO_EXCEPTION_GET_NAME = "Io exception was occurs during execute method of GetItemName command!";
    public static final String NO_ONE_BY_ID = "No one entity by this id!";

    //GetItemNumberCommand
    public static final String AMOUNT = "Product amount: ";
    public static final String IO_EXCEPTION_GET_NUMBER = "Io exception was occurs during execute method of GetItemNumberCommand command!";
}
