package com.epam.preprod.pavlov.response.http;

import com.epam.preprod.pavlov.response.Response;

public interface HttpResponse extends Response {
    void setContentType(String contentType);

    void setStatus(String status);

    void addContent(String content);
    
}
