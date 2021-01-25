package com.epam.preprod.pavlov.constants;

public class ServerStatus {
    private ServerStatus() {
    }
    public static final String NOT_FOUND = "HTTP/1.1 404 NotFound";
    public static final String OK = "HTTP/1.1 200 OK";
    public static final String BAD_REQUEST = "HTTP/1.1 400 BadRequest";
    public static final String SERVER_ERROR = "HTTP/1.1 500 ServerError";
}
