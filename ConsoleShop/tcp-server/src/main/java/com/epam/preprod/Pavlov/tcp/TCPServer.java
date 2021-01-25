package com.epam.preprod.Pavlov.tcp;

import com.epam.preprod.Pavlov.client.TCPClientConnection;
import com.epam.preprod.Pavlov.handler.TCPHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    private static final String SERVER_CLOSE_ERROR = "IO exception was occurs during server closing executing";
    private static final String SERVER_OPEN_ERROR = "IO exception was occurs during server opening executing";

    private int port;
    private TCPHandler handler;
    ServerSocket server;

    public TCPServer(TCPHandler handler, int port) {
        this.handler = handler;
        this.port = port;
    }

    public void start() {
        try {
            server = new ServerSocket(port);
            while (!Thread.interrupted()) {
                Socket socket = server.accept();
                Thread clientThread = new Thread(new TCPClientConnection(handler, socket));
                clientThread.setDaemon(true);
                clientThread.start();
            }
        } catch (IOException e) {
            System.out.println(SERVER_OPEN_ERROR);
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    System.out.println(SERVER_CLOSE_ERROR);
                }
            }
        }
    }
}
