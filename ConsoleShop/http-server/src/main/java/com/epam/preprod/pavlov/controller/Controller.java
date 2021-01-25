package com.epam.preprod.pavlov.controller;

import com.epam.preprod.pavlov.request.HttpRequest;
import com.epam.preprod.pavlov.response.http.HttpResponse;

public interface Controller {
    void execute(HttpRequest request, HttpResponse response) ;
}
