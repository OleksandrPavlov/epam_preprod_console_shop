package com.epam.preprod.Pavlov.creator.impl;

import com.epam.preprod.Pavlov.annotation.Invitation;
import com.epam.preprod.Pavlov.entity.Car;
import com.epam.preprod.Pavlov.entity.Transport;
import com.epam.preprod.Pavlov.maker.IMaker;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class ReflectTransportCreator extends TransportCreator {
    private static final String CREATION_EXCEPTION = "Some underlying method has throw an exception or you try to modify or to get access to field you haven't access to.";
    private IMaker maker;
    private TreeMap<Invitation, Method> carMethods;
    private Method[] makerMethods;
    private ResourceBundle resourceBundle;
    private Class typeToCreate;

    public ReflectTransportCreator(IMaker maker, ResourceBundle resourceBundle, Class<? extends Car> typeToCreate) {
        this.resourceBundle = resourceBundle;
        this.maker = maker;
        Comparator<Invitation> comparator = Comparator.comparingInt(Invitation::order);
        this.carMethods = new TreeMap<>(comparator);
        this.typeToCreate = typeToCreate;
        makerMethods = IMaker.class.getDeclaredMethods();
    }

    @Override
    public Transport create() {
        Object transport = null;
        try {
            Constructor constructor = typeToCreate.getConstructor(null);
            transport = constructor.newInstance(null);
            setFields(transport);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.out.println(CREATION_EXCEPTION);
            return null;
        }
        return (Transport) transport;
    }

    public void setFields(Object transport) throws InvocationTargetException, IllegalAccessException {
        defineMethodsToInvoke();
        invokeProperMakerFunction(transport);
    }

    private void defineMethodsToInvoke() {
        for (Method m : typeToCreate.getMethods()) {
            if (m.isAnnotationPresent(Invitation.class)) {
                Invitation invitation = m.getAnnotation(Invitation.class);
                carMethods.put(invitation, m);
            }
        }
    }

    private Class defineTypeOfParameter(Method method) {
        Class[] typesOfPassedParameters = method.getParameterTypes();
        return typesOfPassedParameters[0];
    }

    private Method chooseProperMethodFromMaker(Class type) {
        for (Method method : makerMethods) {
            if (method.getReturnType().equals(type)) {
                return method;
            }
        }
        return null;
    }

    private void invokeProperMakerFunction(Object transport) throws
            InvocationTargetException, IllegalAccessException {
        for (Map.Entry<Invitation, Method> entry : carMethods.entrySet()) {
            Class typeOfParameter = defineTypeOfParameter(entry.getValue());
            Method methodFromMaker = chooseProperMethodFromMaker(typeOfParameter);
            String str = resourceBundle.getString(entry.getKey().keyName());
            entry.getValue().invoke(transport, methodFromMaker.invoke(maker, resourceBundle.getString(entry.getKey().keyName())));
        }
    }

}
