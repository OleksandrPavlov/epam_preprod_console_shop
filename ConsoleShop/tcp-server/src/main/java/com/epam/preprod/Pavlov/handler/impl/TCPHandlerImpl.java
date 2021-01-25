package com.epam.preprod.Pavlov.handler.impl;

import com.epam.preprod.Pavlov.command.TCPCommand;
import com.epam.preprod.Pavlov.handler.TCPHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TCPHandlerImpl implements TCPHandler {
    private HashMap<String, TCPCommand> commandMap;

    public TCPHandlerImpl() {
        commandMap = new HashMap<>();
    }

    @Override
    public TCPCommand findSuitable(String commandKey) {
        return commandMap.get(commandKey);
    }

    @Override
    public boolean addTCPCommand(String commandKey, TCPCommand command) {
        TCPCommand tempCommand = commandMap.put(commandKey, command);
        return tempCommand == null;
    }

    @Override
    public int commandAmount() {
        return commandMap.size();
    }

    @Override
    public List<Map.Entry<String, TCPCommand>> getAllCommandEntries() {
        return new ArrayList<>(commandMap.entrySet());
    }

}
