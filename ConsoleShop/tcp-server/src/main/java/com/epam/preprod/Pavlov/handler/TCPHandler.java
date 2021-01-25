package com.epam.preprod.Pavlov.handler;

import com.epam.preprod.Pavlov.command.TCPCommand;

import java.util.List;
import java.util.Map;


public interface TCPHandler {
    TCPCommand findSuitable(String commandKey);

    boolean addTCPCommand(String commandKey, TCPCommand command);

    int commandAmount();

    List<Map.Entry<String, TCPCommand>> getAllCommandEntries();
}
