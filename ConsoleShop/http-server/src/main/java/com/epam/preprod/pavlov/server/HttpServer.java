package com.epam.preprod.pavlov.server;

import com.epam.preprod.pavlov.handler.HttpHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    private static final String FAILURE_SOCKET = "Failure to create server socket!";
    private int port;
    private HttpHandler handler;

    public HttpServer(int port, HttpHandler handler) {
        this.port = port;
        this.handler = handler;
    }

    public void start() {
        try {
            ServerSocket socket = new ServerSocket(port);
            while (true) {
                Socket clientSocket = socket.accept();
                handler.handle(clientSocket);
            }
        } catch (IOException e) {
            System.out.println(FAILURE_SOCKET + e.getMessage());
        }
    }
}
