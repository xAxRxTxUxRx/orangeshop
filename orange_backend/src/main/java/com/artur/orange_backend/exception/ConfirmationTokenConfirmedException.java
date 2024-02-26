package com.artur.orange_backend.exception;

public class ConfirmationTokenConfirmedException extends RuntimeException{
    public ConfirmationTokenConfirmedException(String token){
        super(String.format("Confirmation token %s already confirmed", token));
    }
}
