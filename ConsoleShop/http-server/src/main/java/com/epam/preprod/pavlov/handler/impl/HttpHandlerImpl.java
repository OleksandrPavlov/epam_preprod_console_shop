package com.epam.preprod.pavlov.handler.impl;

import com.epam.preprod.pavlov.controller.Controller;
import com.epam.preprod.pavlov.handler.HttpHandler;
import com.epam.preprod.pavlov.handler.thread.HttpHandleBranch;
import com.epam.preprod.pavlov.request.HttpRequest;

import java.net.Socket;
import java.util.HashMap;
import java.util.Objects;

public class HttpHandlerImpl implements HttpHandler {
    private HashMap<String, Controller> controllerPack;

    public HttpHandlerImpl() {
        this.controllerPack = new HashMap<>();
    }

    @Override
    public void handle(Socket socket) {
        Thread thread = new Thread(new HttpHandleBranch(socket, this));
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void addController(String key, Controller controller) {
        controllerPack.put(key, controller);
    }

    @Override
    public Controller findSuitable(HttpRequest request) {
        String path = request.getUrl();
        if (Objects.isNull(path)) {
            return null;
        }
        return controllerPack.get(request.getUrl());
    }


}
