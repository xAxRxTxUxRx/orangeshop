package com.artur.orange_backend.exception;

public class UserNotFoundByEmailException extends RuntimeException{
    public UserNotFoundByEmailException(String email){
        super(String.format("User not found by email %s", email));
    }
}
