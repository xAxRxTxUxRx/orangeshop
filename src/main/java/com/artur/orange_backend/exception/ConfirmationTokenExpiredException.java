package com.artur.orange_backend.exception;

public class ConfirmationTokenExpiredException extends RuntimeException{
    public ConfirmationTokenExpiredException(String token){
        super(String.format("Confirmation token %s already expired", token));
    }
}
