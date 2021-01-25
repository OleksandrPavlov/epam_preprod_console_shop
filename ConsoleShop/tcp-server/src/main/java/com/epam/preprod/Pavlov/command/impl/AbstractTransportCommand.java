package com.epam.preprod.Pavlov.command.impl;


import com.epam.preprod.Pavlov.command.TCPCommand;
import com.epam.preprod.Pavlov.entity.Transport;
import com.epam.preprod.Pavlov.util.SerializeTransportUtils;

import java.util.List;

public abstract class AbstractTransportCommand implements TCPCommand {
    private static final String NO_SUITABLE_ELEMENTS = "No one suitable elements in file";
    private String pathName;

    public AbstractTransportCommand(String pathName) {
        this.pathName = pathName;
    }

    protected List<Transport> getEntities() {
        List<Transport> transports = null;
        try {
            transports = SerializeTransportUtils.extractTransports(pathName);
        } catch (ClassNotFoundException e) {
            System.out.println(NO_SUITABLE_ELEMENTS);
        }
        return transports;
    }
}
