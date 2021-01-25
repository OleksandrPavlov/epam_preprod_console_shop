package com.epam.preprod.pavlov.handler;

import com.epam.preprod.pavlov.transport.ITransport;
import com.epam.preprod.pavlov.transport.impl.Transport;
import org.junit.Test;

import java.lang.instrument.UnmodifiableClassException;
import java.lang.reflect.Proxy;

import static org.junit.Assert.assertEquals;

public class TransportMapInvocationHandlerTest {

    @Test
    public void Demo_TestingIfProxyHasBehaviorLikeAMap() throws IllegalAccessException, UnmodifiableClassException {
        Transport transport = new Transport("Transport", 1, 1, 1);
        ClassLoader classLoader = transport.getClass().getClassLoader();
        Class[] interfaces = transport.getClass().getInterfaces();
        ITransport transportProxy = (ITransport) Proxy.newProxyInstance(classLoader, interfaces, new TransportMapInvocationHandler(transport));
        transportProxy.setId(2);
        assertEquals(transportProxy.getId(), 2);
        transportProxy.setName("Name");
        assertEquals(transportProxy.getName(), "Name");
        transportProxy.setCarrying(2);
        assertEquals(transportProxy.getCarrying(), 2, 0.0);
        transportProxy.setPrice(2);
        assertEquals(transportProxy.getPrice(), 2, 0.0);
    }
}