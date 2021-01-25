package com.epam.preprod.pavlov.controller.impl;

import com.epam.preprod.Pavlov.entity.Transport;
import com.epam.preprod.Pavlov.util.SerializeTransportUtils;
import com.epam.preprod.pavlov.constants.ControllerConstants;
import com.epam.preprod.pavlov.constants.HttpServerConstants;
import com.epam.preprod.pavlov.constants.ServerStatus;
import com.epam.preprod.pavlov.controller.GetPostController;
import com.epam.preprod.pavlov.request.HttpRequest;
import com.epam.preprod.pavlov.response.http.HttpResponse;
import org.apache.commons.lang3.math.NumberUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;


public class GetItemInfoController extends GetPostController {

    private String pathToFile;

    public GetItemInfoController(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        response.setContentType(HttpServerConstants.JSON_CONTENT_TYPE);
        String attributeValue = request.getAttribute(ControllerConstants.GET_INFO_ATTRIBUTE);
        try {
            if (! NumberUtils.isDigits(attributeValue)) {
                response.setStatus(ServerStatus.BAD_REQUEST);
                response.addContent(ControllerConstants.BAD_REQUEST);
                response.throwOff();
                return;
            }

            List<Transport> transports = SerializeTransportUtils.extractTransports(pathToFile);
            int identifier = Integer.parseInt(attributeValue);
            Transport transport = findTransportById(transports, identifier);

            if (transport == null) {
                response.addContent(ControllerConstants.NO_ONE_ITEM);
                response.throwOff();
                return;
            }

            JSONObject object = new JSONObject();
            object.put(ControllerConstants.NAME, transport.getName());
            object.put(ControllerConstants.PRICE, transport.getPrice());
            response.addContent(object.toString());
            response.throwOff();
        } catch (ClassNotFoundException e) {
            response.setStatus(ServerStatus.SERVER_ERROR);
            response.addContent(HttpServerConstants.SERVER_ERROR_MESSAGE);
        } catch (IOException e) {
            System.out.println(HttpServerConstants.SERVER_ERROR_MESSAGE);
        }
    }

    private Transport findTransportById(List<Transport> transports, int id) {
        for (Transport transport : transports) {
            if (transport.getId() == id) {
                return transport;
            }
        }
        return null;
    }


}
