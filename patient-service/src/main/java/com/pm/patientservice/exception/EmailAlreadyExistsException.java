package com.pm.patientservice.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    String message;

    public EmailAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}
