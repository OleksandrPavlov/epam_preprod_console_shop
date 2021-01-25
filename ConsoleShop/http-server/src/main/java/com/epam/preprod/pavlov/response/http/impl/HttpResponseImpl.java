package com.epam.preprod.pavlov.response.http.impl;

import com.epam.preprod.pavlov.constants.HttpServerConstants;
import com.epam.preprod.pavlov.constants.ServerStatus;
import com.epam.preprod.pavlov.response.http.HttpResponse;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class HttpResponseImpl implements HttpResponse {
    private static final String REPEATABLY_THROW_OFF = "Repeatably attempt of trowing off was accepted!";
    private static final String VERSION = "HTTP/1.1 ";
    private static final String HEADER_CONTENT_TYPE = "Content-Type:";
    private static final String HEADER_CONTENT_LENGTH = "Content-Length:";
    private String contentType;
    private String status;
    private StringBuilder content;
    private BufferedWriter writer;

    boolean isThrown = false;

    public HttpResponseImpl(BufferedWriter writer) {
        this.writer = writer;
        contentType = HttpServerConstants.TEXT_CONTENT_TYPE;
        status = ServerStatus.OK;
        content = new StringBuilder();
    }


    @Override
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void addContent(String content) {
        this.content.append(content + System.lineSeparator());
    }

    @Override
    public void throwOff() throws IOException {
        if (isThrown) {
            throw new IllegalStateException(REPEATABLY_THROW_OFF);
        }
        writer.write(String.format("%s\n", status));
        writer.write(String.format("%s %s\n", HEADER_CONTENT_TYPE, contentType));
        writer.write(String.format("%s %s\n\n", HEADER_CONTENT_LENGTH, content.toString().getBytes(StandardCharsets.UTF_8).length));
        writer.write(content.toString());
        writer.flush();
    }
}
