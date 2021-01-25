package com.epam.preprod.pavlov.initialization;

import com.epam.preprod.pavlov.constants.ControllerMapping;
import com.epam.preprod.pavlov.controller.impl.GetCountController;
import com.epam.preprod.pavlov.controller.impl.GetItemInfoController;
import com.epam.preprod.pavlov.handler.HttpHandler;
import com.epam.preprod.pavlov.handler.impl.HttpHandlerImpl;
import com.epam.preprod.pavlov.server.HttpServer;


public class HttpServerContext {
    private final String PATH_TO_FILE = String.format("%s\\%s\\%s\\%s\\%s", "console-app", "src", "main", "files", "transport-repository.txt");
    private HttpHandler handler;
    private int port;

    public HttpServerContext(int port) {
        this.port = port;
    }

    public void start() {
        initHandler();
        HttpServer server = new HttpServer(port, handler);
        server.start();
    }

    private void initHandler() {
        handler = new HttpHandlerImpl();
        handler.addController(ControllerMapping.GET_COUNT_URL, new GetCountController(PATH_TO_FILE));
        handler.addController(ControllerMapping.GET_INFO_URL, new GetItemInfoController(PATH_TO_FILE));
    }
}
