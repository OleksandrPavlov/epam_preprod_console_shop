package com.epam.preprod.pavlov.request.impl;


import com.epam.preprod.pavlov.request.HttpRequest;

import java.util.HashMap;

public class HttpRequestImpl implements HttpRequest {

    private String method;
    private String url;
    private HashMap<String, String> attributes;


    public HttpRequestImpl() {
        attributes = new HashMap<>();
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void addAttribute(String name, String value) {
        attributes.put(name, value);
    }

    @Override
    public String getMethod() {
        return method;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getAttribute(String name) {
        return attributes.get(name);
    }


}
