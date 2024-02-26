package com.artur.orange_backend.exception;

public class ConfirmationTokenNotFoundException extends RuntimeException{
    public ConfirmationTokenNotFoundException(String token){
        super(String.format("Confirmation token %s not found", token));
    }
}
