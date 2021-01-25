package com.epam.preprod.pavlov.creator;

import com.epam.preprod.pavlov.handler.TransportInvocationHandler;
import com.epam.preprod.pavlov.transport.ITransport;
import com.epam.preprod.pavlov.transport.impl.Transport;

import java.lang.reflect.Proxy;

public class UnmodifiableTransportCreator {
    private UnmodifiableTransportCreator() {

    }

    public static final ITransport create() {
        Transport transport = new Transport();
        ClassLoader classLoader = transport.getClass().getClassLoader();
        Class[] interfaces = transport.getClass().getInterfaces();
        return (ITransport) Proxy.newProxyInstance(classLoader, interfaces, new TransportInvocationHandler(transport));
    }
}
