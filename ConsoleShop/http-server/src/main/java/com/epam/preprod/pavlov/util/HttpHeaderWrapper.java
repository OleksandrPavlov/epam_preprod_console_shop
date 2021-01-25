package com.epam.preprod.pavlov.util;

public class HttpHeaderWrapper {
    private static final String CONTENT_TYPE = "Content-Type: text/html; charset=utf-8";

    private HttpHeaderWrapper() {

    }

    public static String wrapStatus(String status, String content) {
        return String.format("%s\n%s\n\n%s", status, CONTENT_TYPE, content);
    }

}
