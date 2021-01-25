package com.epam.preprod.pavlov.controller.impl;

import com.epam.preprod.Pavlov.entity.PassengerCar;
import com.epam.preprod.Pavlov.entity.Transport;
import com.epam.preprod.Pavlov.entity.Truck;
import com.epam.preprod.Pavlov.util.SerializeTransportUtils;
import com.epam.preprod.pavlov.constants.ControllerConstants;
import com.epam.preprod.pavlov.constants.HttpServerConstants;
import com.epam.preprod.pavlov.constants.ServerStatus;
import com.epam.preprod.pavlov.request.HttpRequest;
import com.epam.preprod.pavlov.response.http.HttpResponse;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

public class GetItemInfoControllerTest {
    public static final String INVALID_ATTRIBUTE_VALUE = "I";
    public static final String UNKNOWABLE_ATTRIBUTE_VALUE = "3";
    public static final String KNOWABLE_ATTRIBUTE_VALUE = "1";
    public String pathToFile;

    public static final Transport TRANSPORT1 = new PassengerCar("GALO", 1, 1, 1, 1, 1, 1, 1);
    public static final Transport TRANSPORT2 = new Truck("VOLA", 2, 2, 2, 2, 2, 2, 2);
    private HttpResponse response;
    private HttpRequest request;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();


    @Before
    public void setUp() throws Exception {
        response = mock(HttpResponse.class);
        request = mock(HttpRequest.class);
        File createdFile = folder.newFile("test.txt");
        pathToFile = createdFile.getPath();
        SerializeTransportUtils.insertList(pathToFile, Stream.of(TRANSPORT1, TRANSPORT2).collect(Collectors.toList()), false);
    }


    @Test
    public void shouldInvokeAllMethodsWithProperParametersWhenDoGetCalledWithValidRequest() throws IOException {
        String transportName = "GALO";
        int price = 1;
        JSONObject object = new JSONObject();
        object.put(ControllerConstants.NAME, transportName);
        object.put(ControllerConstants.PRICE, price);
        response.setContentType(HttpServerConstants.JSON_CONTENT_TYPE);
        expect(request.getAttribute(ControllerConstants.GET_INFO_ATTRIBUTE)).andReturn(KNOWABLE_ATTRIBUTE_VALUE);
        response.addContent(object.toString());
        response.throwOff();
        replay(response, request);
        GetItemInfoController controller = new GetItemInfoController(pathToFile);
        controller.doGet(request, response);
        verify(request, response);
    }


    @Test
    public void shouldInvokeAllMethodsWithProperParametersWhenDoGetCalledWithRequestWithInvalidAttributeValue() throws IOException {
        response.setContentType(HttpServerConstants.JSON_CONTENT_TYPE);
        expect(request.getAttribute(ControllerConstants.GET_INFO_ATTRIBUTE)).andReturn(INVALID_ATTRIBUTE_VALUE);
        response.setStatus(ServerStatus.BAD_REQUEST);
        response.addContent(ControllerConstants.BAD_REQUEST);
        response.throwOff();
        replay(response, request);
        GetItemInfoController controller = new GetItemInfoController(pathToFile);
        controller.doGet(request, response);
        verify(request, response);
    }

    @Test
    public void shouldInvokeAllMethodsWithProperParametersWhenDoGetCalledWithRequestWithUnknowableAttributeValue() throws IOException {
        response.setContentType(HttpServerConstants.JSON_CONTENT_TYPE);
        expect(request.getAttribute(ControllerConstants.GET_INFO_ATTRIBUTE)).andReturn(UNKNOWABLE_ATTRIBUTE_VALUE);
        response.addContent(ControllerConstants.NO_ONE_ITEM);
        response.throwOff();
        replay(response, request);
        GetItemInfoController controller = new GetItemInfoController(pathToFile);
        controller.doGet(request, response);
        verify(request, response);
    }


}