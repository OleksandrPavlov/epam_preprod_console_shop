package com.epam.preprod.Pavlov.client;

import com.epam.preprod.Pavlov.command.TCPCommand;
import com.epam.preprod.Pavlov.constants.ServerConstants;
import com.epam.preprod.Pavlov.exception.TCPHandleException;
import com.epam.preprod.Pavlov.handler.TCPHandler;
import com.epam.preprod.Pavlov.io.TCPReader;
import com.epam.preprod.Pavlov.io.TCPWriter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Objects;

public class TCPClientConnection implements Runnable {
    private static final String END_MESSAGE_KEY = "end";
    private static final String STOP_SERVER_KEY = "stop";
    private static final String NO_ONE_COMMAND = "Handle exception was occurs during request to empty handle!";
    private static final String UNKNOWN_KEY = "This command does not supported!";
    private static final String SOCKET_CLOSE_ERROR = "An io exception was occurs during socket closing";
    private static final String SOCKET_STREAM_ERROR = "An io exception was occurs during of creation of streams or during write or read executing";
    private TCPHandler handler;
    private Socket socket;


    public TCPClientConnection(TCPHandler handler, Socket socket) {
        this.handler = handler;
        this.socket = socket;
    }

    @Override
    public void run() {
        try (TCPReader reader = new TCPReader(new InputStreamReader(socket.getInputStream()));
             TCPWriter writer = new TCPWriter(new OutputStreamWriter(socket.getOutputStream()), END_MESSAGE_KEY)) {
            sendEndKey(writer);
            String menu = bringOutDescription();
            String input;
            writer.multiWrite(menu);
            while (!STOP_SERVER_KEY.equals(input = reader.readLine())) {
                TCPCommand command = handler.findSuitable(input);
                if (Objects.isNull(command)) {
                    writer.writeln(UNKNOWN_KEY);
                } else {
                    command.execute(writer, reader);
                }
                writer.multiWrite(menu);
            }
        } catch (IOException ex) {
            System.out.println(SOCKET_STREAM_ERROR);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println(SOCKET_CLOSE_ERROR);
            }
        }
    }

    private String bringOutDescription() {
        if (handler.commandAmount() == 0) {
            throw new TCPHandleException(NO_ONE_COMMAND);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%-20s%s\n", ServerConstants.COMMAND, ServerConstants.DESCRIPTION));
        handler.getAllCommandEntries().forEach(value -> stringBuilder.append(String.format("%-20s%s\n", value.getKey(), value.getValue().getDescription())));
        return stringBuilder.toString().substring(0, stringBuilder.length() - 1);
    }

    private void sendEndKey(BufferedWriter writer) throws IOException {
        writer.write(END_MESSAGE_KEY + System.lineSeparator());
        writer.flush();
    }
}
