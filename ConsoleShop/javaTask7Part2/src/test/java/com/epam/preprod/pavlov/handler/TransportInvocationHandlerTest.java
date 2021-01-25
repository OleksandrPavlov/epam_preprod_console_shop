package com.epam.preprod.pavlov.handler;

import com.epam.preprod.pavlov.creator.UnmodifiableTransportCreator;
import com.epam.preprod.pavlov.exception.UnmodifiableException;
import com.epam.preprod.pavlov.transport.ITransport;
import com.epam.preprod.pavlov.transport.impl.Transport;
import org.junit.Test;

import java.lang.instrument.UnmodifiableClassException;
import java.lang.reflect.Proxy;

import static org.junit.Assert.assertEquals;

public class TransportInvocationHandlerTest {

    @Test(expected = UnmodifiableException.class)
    public void SetId_AttemptToInvokeUnsupportedMethod_ThrowsUnmodifiableClassException() throws UnmodifiableClassException {
        ITransport transportProxy = UnmodifiableTransportCreator.create();
        transportProxy.setId(1);
    }

    @Test(expected = UnmodifiableException.class)
    public void SetCarrying_AttemptToInvokeUnsupportedMethod_ThrowsUnmodifiableClassException() throws UnmodifiableClassException {
        ITransport transportProxy = UnmodifiableTransportCreator.create();
        transportProxy.setCarrying(1);
    }

    @Test(expected = UnmodifiableException.class)
    public void SetName_AttemptToInvokeUnsupportedMethod_ThrowsUnmodifiableClassException() throws UnmodifiableClassException {
        ITransport transportProxy = UnmodifiableTransportCreator.create();
        transportProxy.setName("Name");
    }

    @Test(expected = UnmodifiableException.class)
    public void SetPrice_AttemptToInvokeUnsupportedMethod_ThrowsUnmodifiableClassException() throws UnmodifiableClassException {
        ITransport transportProxy = UnmodifiableTransportCreator.create();
        transportProxy.setPrice(1);
    }

    @Test
    public void GetPrice_ObtainingPriceFromProxy_AssertToOrigin() throws UnmodifiableClassException {
        Transport transport = new Transport("TRANSPORT", 1, 1, 1);
        ClassLoader classLoader = transport.getClass().getClassLoader();
        Class[] interfaces = transport.getClass().getInterfaces();
        ITransport transportProxy = (ITransport) Proxy.newProxyInstance(classLoader, interfaces, new TransportInvocationHandler(transport));
        assertEquals(1, transportProxy.getPrice(), 0.0);
    }

    @Test
    public void GetName_ObtainingPriceFromProxy_AssertToOrigin() throws UnmodifiableClassException {
        Transport transport = new Transport("TRANSPORT", 1, 1, 1);
        ClassLoader classLoader = transport.getClass().getClassLoader();
        Class[] interfaces = transport.getClass().getInterfaces();
        ITransport transportProxy = (ITransport) Proxy.newProxyInstance(classLoader, interfaces, new TransportInvocationHandler(transport));
        assertEquals("TRANSPORT", transportProxy.getName());
    }

    @Test
    public void GetId_ObtainingPriceFromProxy_AssertToOrigin() throws UnmodifiableClassException {
        Transport transport = new Transport("TRANSPORT", 1, 1, 1);
        ClassLoader classLoader = transport.getClass().getClassLoader();
        Class[] interfaces = transport.getClass().getInterfaces();
        ITransport transportProxy = (ITransport) Proxy.newProxyInstance(classLoader, interfaces, new TransportInvocationHandler(transport));
        assertEquals(1, transportProxy.getId());

    }

    @Test
    public void GetCarrying_ObtainingPriceFromProxy_AssertToOrigin() throws UnmodifiableClassException {
        Transport transport = new Transport("TRANSPORT", 1, 1, 1);
        ClassLoader classLoader = transport.getClass().getClassLoader();
        Class[] interfaces = transport.getClass().getInterfaces();
        ITransport transportProxy = (ITransport) Proxy.newProxyInstance(classLoader, interfaces, new TransportInvocationHandler(transport));
        assertEquals(1, transportProxy.getCarrying(), 0.0);
    }


}