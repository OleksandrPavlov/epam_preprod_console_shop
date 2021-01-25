package com.epam.preprod.Pavlov.command.impl;

import com.epam.preprod.Pavlov.constants.CommandConstants;
import com.epam.preprod.Pavlov.constants.ServerConstants;
import com.epam.preprod.Pavlov.entity.Transport;
import com.epam.preprod.Pavlov.io.TCPReader;
import com.epam.preprod.Pavlov.io.TCPWriter;
import com.epam.preprod.Pavlov.util.constants.ErrorMessageConstants;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class GetTransportNameCommand extends AbstractTransportCommand {
    private static final String DESCRIPTION = "This command will output name of item by identifier";

    public GetTransportNameCommand(String pathName) {
        super(pathName);
    }

    @Override
    public void execute(TCPWriter writer, TCPReader reader) {
        try {
            int identifier = getValidId(writer, reader);
            List<Transport> transports = getEntities();
            if (transports == null) {
                writer.writeln(ErrorMessageConstants.EMPTY_REPOSITORY);
            } else if (Objects.isNull(getTransportById(identifier, transports))) {
                writer.writeln(CommandConstants.NO_ONE_BY_ID);
            } else {
                Transport transport = getTransportById(identifier, transports);
                writer.writeln(String.format(CommandConstants.ITEM_NAME_FORMAT, identifier, transport.getName(), transport.getPrice()) + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println(CommandConstants.IO_EXCEPTION_GET_NAME);
        }
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    private int getValidId(TCPWriter writer, TCPReader reader) throws IOException {

        int number;
        while (true) {
            writer.multiWrite(ServerConstants.ENTER_ITEM_ID);
            String input = reader.readLine();
            if (NumberUtils.isDigits(input)) {
                number = Integer.parseInt(input);
                break;
            } else {
                writer.multiWrite(ErrorMessageConstants.INVALID_PRODUCT_ID);
            }
        }
        return number;
    }

    private Transport getTransportById(int id, List<Transport> list) {
        for (Transport transport : list) {
            if (transport.getId() == id) {
                return transport;
            }
        }
        return null;
    }
}
