package com.epam.preprod.pavlov.exception;

public class UnmodifiableException extends RuntimeException {
    public UnmodifiableException(String message) {
        super(message);
    }
}
