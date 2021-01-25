package com.epam.preprod.pavlov.handler;

import com.epam.preprod.pavlov.constants.Constants;
import com.epam.preprod.pavlov.exception.UnmodifiableException;
import com.epam.preprod.pavlov.transport.impl.Transport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TransportInvocationHandler implements InvocationHandler {
    private Transport transport;
    private static final String SET_INVOCATION_EXCEPTION_MESSAGE = "You can't to invoke modifiable methods";

    public TransportInvocationHandler(Transport transport) {
        this.transport = transport;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        if (method.getName().startsWith(Constants.SET_NAME)) {
            throw new UnmodifiableException(SET_INVOCATION_EXCEPTION_MESSAGE);
        }
        return method.invoke(transport, objects);
    }
}
