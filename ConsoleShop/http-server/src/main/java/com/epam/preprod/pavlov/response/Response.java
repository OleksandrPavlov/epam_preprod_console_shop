package com.epam.preprod.pavlov.response;

import java.io.IOException;

public interface Response {
    /**
     * This method will throw its whole interior to client as response.
     * After throwOff method will invoked at least once,  IllegalStateException will be thrown as result of repeatably attempt to invoke.
     */
    void throwOff() throws IOException;
}
