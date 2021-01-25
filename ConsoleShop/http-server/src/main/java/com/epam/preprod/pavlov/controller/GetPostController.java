package com.epam.preprod.pavlov.controller;

import com.epam.preprod.pavlov.request.HttpRequest;
import com.epam.preprod.pavlov.response.http.HttpResponse;


public abstract class GetPostController extends GetController {
    public static final String WRITE_EXCEPTION = "An io exception was occurs during writing to client stream!";
    private static final String POST_METHOD = "POST";

    public void doPost(HttpRequest request, HttpResponse response) {
    }

    @Override
    public void execute(HttpRequest request, HttpResponse response) {
        if (POST_METHOD.equals(request.getMethod())) {
            doPost(request, response);
        }
        super.execute(request, response);
    }

}
