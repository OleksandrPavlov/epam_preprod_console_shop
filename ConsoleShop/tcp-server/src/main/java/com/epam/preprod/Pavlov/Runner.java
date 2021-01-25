package com.epam.preprod.Pavlov;

import com.epam.preprod.Pavlov.tcp.TcpServerContext;

public class Runner {
    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);
        TcpServerContext tcpServerContext = new TcpServerContext(port);
        tcpServerContext.start();
    }
}
