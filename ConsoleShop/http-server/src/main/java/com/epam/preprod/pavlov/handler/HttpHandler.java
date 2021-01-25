package com.epam.preprod.pavlov.handler;

import com.epam.preprod.pavlov.controller.Controller;
import com.epam.preprod.pavlov.request.HttpRequest;

import java.net.Socket;

public interface HttpHandler {

    void addController(String key, Controller controller);

    Controller findSuitable(HttpRequest request);

    void handle(Socket socket);
}
