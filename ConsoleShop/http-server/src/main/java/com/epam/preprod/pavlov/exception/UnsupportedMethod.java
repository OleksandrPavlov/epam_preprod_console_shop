package com.epam.preprod.pavlov.exception;

public class UnsupportedMethod extends RuntimeException {
    public static final String MESSAGE = "Http method is not supported!";

    public UnsupportedMethod() {
        super(MESSAGE);
    }
}
