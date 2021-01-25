package com.epam.preprod.pavlov.request.parser.impl;

import com.epam.preprod.pavlov.constants.HttpServerConstants;
import com.epam.preprod.pavlov.request.HttpRequest;
import com.epam.preprod.pavlov.request.impl.HttpRequestImpl;
import com.epam.preprod.pavlov.request.parser.HttpRequestParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpRequestParserImpl implements HttpRequestParser {
    private static final String METHOD = "method";
    private static final String PATH = "path";
    private static final String ATTRIBUTE = "attribute";
    private static final String VALUE = "value";

    private BufferedReader reader;
    private String startString;

    public HttpRequestParserImpl(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public HttpRequest getRequest() throws IOException {
        readStartString();
        if (startString == null) {
            return null;
        }
        HttpRequestImpl request = new HttpRequestImpl();
        Pattern pattern = Pattern.compile(HttpServerConstants.ATTRIBUTE_PATH_PATTERN);
        Matcher matcher = pattern.matcher(startString);
        if (matcher.find()) {
            request.setMethod(matcher.group(METHOD));
            request.setUrl(matcher.group(PATH));
            request.addAttribute(matcher.group(ATTRIBUTE), matcher.group(VALUE));
            fillLastAttributes(request, matcher);
            return request;
        } else {
            return null;
        }
    }

    private void fillLastAttributes(HttpRequestImpl request, Matcher matcher) {
        while (matcher.find()) {
            request.addAttribute(matcher.group(ATTRIBUTE), matcher.group(VALUE));
        }
    }

    private void readStartString() throws IOException {
        startString = reader.readLine();
    }
}
