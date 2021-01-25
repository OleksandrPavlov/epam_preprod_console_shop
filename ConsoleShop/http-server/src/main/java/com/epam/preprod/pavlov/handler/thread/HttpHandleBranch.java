package com.epam.preprod.pavlov.handler.thread;

import com.epam.preprod.pavlov.constants.HttpServerConstants;
import com.epam.preprod.pavlov.constants.ServerStatus;
import com.epam.preprod.pavlov.controller.Controller;
import com.epam.preprod.pavlov.handler.HttpHandler;
import com.epam.preprod.pavlov.request.HttpRequest;
import com.epam.preprod.pavlov.request.parser.impl.HttpRequestParserImpl;
import com.epam.preprod.pavlov.response.http.HttpResponse;
import com.epam.preprod.pavlov.response.http.impl.HttpResponseImpl;
import com.epam.preprod.pavlov.util.HttpHeaderWrapper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class HttpHandleBranch implements Runnable {
    private Socket clientSocket;
    private HttpHandler handler;

    public HttpHandleBranch(Socket clientSocket, HttpHandler handler) {
        this.clientSocket = clientSocket;
        this.handler = handler;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {

            HttpResponse response = new HttpResponseImpl(writer);
            HttpRequest request = new HttpRequestParserImpl(reader).getRequest();
            Controller controller = handler.findSuitable(request);
            if (controller == null) {
                writer.write(HttpHeaderWrapper.wrapStatus(ServerStatus.NOT_FOUND, HttpServerConstants.NO_ONE_CONTROLLER_MESSAGE));
                writer.flush();
            } else {
                controller.execute(request, response);
            }
        } catch (IOException ex) {
            System.out.println(HttpServerConstants.SERVER_ERROR_MESSAGE);
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println(HttpServerConstants.CLOSE_SOCKET_ERROR);
            }
        }
    }

}
