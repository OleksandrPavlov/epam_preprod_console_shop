package com.epam.preprod.pavlov.controller;

import com.epam.preprod.pavlov.exception.UnsupportedMethod;
import com.epam.preprod.pavlov.request.HttpRequest;
import com.epam.preprod.pavlov.response.http.HttpResponse;

public abstract class GetController implements Controller {
    private static final String GET_METHOD = "GET";

    @Override
    public void execute(HttpRequest request, HttpResponse response) {
        if (GET_METHOD.equals(request.getMethod())) {
            doGet(request, response);
        } else {
            throw new UnsupportedMethod();
        }
    }

    public void doGet(HttpRequest request, HttpResponse response) {
    }
}
