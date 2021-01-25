package com.epam.preprod.pavlov.constants;

public class HttpServerConstants {
    private HttpServerConstants() {

    }

    public static final String ATTRIBUTE_PATH_PATTERN = "^(?<method>\\w+) ((?<path>(\\/\\w+)+)(\\?((?<attribute>\\w+)=(?<value>.+) ))?)(?<version>.+)$";
    public static final String SERVER_ERROR_MESSAGE = "Some thing going wrong on our side! Try to reload page";
    public static final String NO_ONE_CONTROLLER_MESSAGE = "There is no one answer by this link!";
    public static final String CLOSE_SOCKET_ERROR = "An IO exception was occurs during of closing client socket";
    public static final String TEXT_CONTENT_TYPE = "text/html; charset=\"UTF-8\"";
    public static final String JSON_CONTENT_TYPE = "application/json; charset=\"UTF-8\"";


}
