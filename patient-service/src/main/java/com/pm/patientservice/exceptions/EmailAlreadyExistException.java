package com.pm.patientservice.exceptions;

public class EmailAlreadyExistException extends RuntimeException {
    public EmailAlreadyExistException(String patient_email_already_exists) {
        super(patient_email_already_exists);
    }
}
