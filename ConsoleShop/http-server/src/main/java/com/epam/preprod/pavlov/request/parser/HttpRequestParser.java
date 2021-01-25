package com.epam.preprod.pavlov.request.parser;

import com.epam.preprod.pavlov.request.HttpRequest;

import java.io.IOException;


public interface HttpRequestParser {
    HttpRequest getRequest() throws IOException;
}
