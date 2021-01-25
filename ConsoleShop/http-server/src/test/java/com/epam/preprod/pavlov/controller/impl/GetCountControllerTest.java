package com.epam.preprod.pavlov.controller.impl;

import com.epam.preprod.Pavlov.entity.PassengerCar;
import com.epam.preprod.Pavlov.entity.Transport;
import com.epam.preprod.Pavlov.entity.Truck;
import com.epam.preprod.Pavlov.util.SerializeTransportUtils;
import com.epam.preprod.pavlov.constants.ControllerConstants;
import com.epam.preprod.pavlov.constants.HttpServerConstants;
import com.epam.preprod.pavlov.request.HttpRequest;
import com.epam.preprod.pavlov.response.http.HttpResponse;
import org.json.JSONObject;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

public class GetCountControllerTest {
    public static final String PATH_TO_FILE = "test.txt";
    public static final Transport TRANSPORT1 = new PassengerCar("GALO", 1, 1, 1, 1, 1, 1, 1);
    public static final Transport TRANSPORT2 = new Truck("VOLA", 2, 2, 2, 2, 2, 2, 2);
    private HttpResponse response;
    private HttpRequest request;

    @Before
    public void setUp() throws Exception {
        response = mock(HttpResponse.class);
        request = mock(HttpRequest.class);
        SerializeTransportUtils.insertList(PATH_TO_FILE, Stream.of(TRANSPORT1, TRANSPORT2).collect(Collectors.toList()), false);
    }

    @AfterClass
    public static void afterClass() throws Exception {
        File file = new File(PATH_TO_FILE);
        file.delete();
    }

    @Test
    public void shouldReturnProperResponseWhenDoGetMethodCalledWithRightRequest() throws IOException {
        JSONObject object = new JSONObject();
        object.put(ControllerConstants.COUNT_ATTRIBUTE, 2);
        response.setContentType(HttpServerConstants.JSON_CONTENT_TYPE);
        response.addContent(object.toString());
        response.throwOff();
        replay(response);
        GetCountController controller = new GetCountController(PATH_TO_FILE);
        controller.doGet(null, response);
        verify(response);
    }
}