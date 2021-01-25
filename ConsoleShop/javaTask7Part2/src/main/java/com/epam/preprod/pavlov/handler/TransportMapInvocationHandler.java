package com.epam.preprod.pavlov.handler;

import com.epam.preprod.pavlov.constants.Constants;
import com.epam.preprod.pavlov.transport.impl.Transport;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

public class TransportMapInvocationHandler implements InvocationHandler {
    private HashMap<String, Object> map;

    public TransportMapInvocationHandler(Transport transport) throws IllegalAccessException {
        map = new HashMap<>();
        Field[] fields = transport.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(transport));
        }
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        int indexOfRightEdgeOfSetName = Constants.SET_NAME.length() - 1;
        if (method.getName().startsWith(Constants.SET_NAME)) {
            map.put(method.getName().substring(indexOfRightEdgeOfSetName), objects[0]);
        }
        if (method.getName().startsWith(Constants.GET_NAME)) {
            return map.get(method.getName().substring(indexOfRightEdgeOfSetName));
        }
        return null;
    }
}
