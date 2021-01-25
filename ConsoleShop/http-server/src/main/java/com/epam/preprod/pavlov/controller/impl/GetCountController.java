package com.epam.preprod.pavlov.controller.impl;

import com.epam.preprod.Pavlov.entity.Transport;
import com.epam.preprod.Pavlov.util.SerializeTransportUtils;
import com.epam.preprod.pavlov.constants.ControllerConstants;
import com.epam.preprod.pavlov.constants.HttpServerConstants;
import com.epam.preprod.pavlov.controller.GetPostController;
import com.epam.preprod.pavlov.request.HttpRequest;
import com.epam.preprod.pavlov.response.http.HttpResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class GetCountController extends GetPostController {
    private String pathToFile;

    public GetCountController(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    @Override
    public void doGet(HttpRequest request, HttpResponse response) {
        response.setContentType(HttpServerConstants.JSON_CONTENT_TYPE);
        try {
            List<Transport> transportList = SerializeTransportUtils.extractTransports(pathToFile);
            JSONObject object = new JSONObject();
            object.put(ControllerConstants.COUNT_ATTRIBUTE, transportList.size());
            response.addContent(object.toString());
            response.throwOff();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(WRITE_EXCEPTION);
        }
    }
}
