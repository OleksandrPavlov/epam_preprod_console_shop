package com.epam.preprod.pavlov.request;

public interface HttpRequest {
    /**
     * This method will return method name part of http protocol's start line.
     *
     * @return method name like "GET" or "POST"
     */
    String getMethod();

    /**
     * This method will return url part of http protocol's start line.
     *
     * @return url like "/url1/url2"
     */
    String getUrl();

    /**
     * This method returns value of attribute that was passed in http protocol's start line.
     *
     * @param name of attribute
     * @return some value that located after "=" symbol like "/url?attribute_name=value" so value will returned.
     */
    String getAttribute(String name);

}
