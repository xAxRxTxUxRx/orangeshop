package com.artur.orange_backend.exception;

public class EmailTakenException extends RuntimeException {
    public EmailTakenException(String email){
        super(String.format("Email %s already taken", email));
    }
}
