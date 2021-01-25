package com.epam.preprod.Pavlov.command.impl;


import com.epam.preprod.Pavlov.constants.CommandConstants;
import com.epam.preprod.Pavlov.entity.Transport;
import com.epam.preprod.Pavlov.io.TCPReader;
import com.epam.preprod.Pavlov.io.TCPWriter;
import com.epam.preprod.Pavlov.util.constants.ErrorMessageConstants;

import java.io.IOException;
import java.util.List;

import static com.epam.preprod.Pavlov.constants.CommandConstants.IO_EXCEPTION_GET_NUMBER;

public class GetTransportNumberCommand extends AbstractTransportCommand {

    private static final String DESCRIPTION = "This command will output amount of items";

    public GetTransportNumberCommand(String pathName) {
        super(pathName);
    }

    @Override
    public void execute(TCPWriter writer, TCPReader reader) {
        try {
            List<Transport> transports = getEntities();
            if (transports.size() == 0) {
                writer.writeln(ErrorMessageConstants.EMPTY_REPOSITORY);
            }
            writer.writeln(CommandConstants.AMOUNT + transports.size() + System.lineSeparator());
        } catch (IOException e) {
            System.out.println(IO_EXCEPTION_GET_NUMBER);
        }
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
