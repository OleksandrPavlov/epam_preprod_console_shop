package com.epam.preprod.Pavlov.tcp;

import com.epam.preprod.Pavlov.command.impl.GetTransportNameCommand;
import com.epam.preprod.Pavlov.command.impl.GetTransportNumberCommand;
import com.epam.preprod.Pavlov.constants.ServerConstants;
import com.epam.preprod.Pavlov.handler.TCPHandler;
import com.epam.preprod.Pavlov.handler.impl.TCPHandlerImpl;

import java.io.File;

public class TcpServerContext {
    private int port;
    private TCPHandler handler;
    private static final String PATH_TO_FILE = "console-app" + File.separator + "src" + File.separator + "main" + File.separator + "files" + File.separator + "file.txt";

    public TcpServerContext(int port) {
        this.port = port;
    }

    private void initHandler() {
        handler = new TCPHandlerImpl();
        handler.addTCPCommand(ServerConstants.GET_NAME_BY_ID, new GetTransportNameCommand(PATH_TO_FILE));
        handler.addTCPCommand(ServerConstants.GET_REPOSITORY_SIZE, new GetTransportNumberCommand(PATH_TO_FILE));
    }

    public void start() {
        initHandler();
        TCPServer server = new TCPServer(handler, port);
        server.start();
    }
}
