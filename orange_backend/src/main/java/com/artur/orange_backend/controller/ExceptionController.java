package com.artur.orange_backend.controller;

import com.artur.orange_backend.exception.*;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> handleIllegalStateException(IllegalStateException exc) {
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailTakenException.class)
    public ResponseEntity<?> handleEmailTakenException(EmailTakenException exc) {
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exc) {
        List<String> errors = exc.getBindingResult().getFieldErrors()
                .stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value
            = { EntityNotFoundByIdException.class, UserNotFoundByEmailException.class,
            ConfirmationTokenNotFoundException.class})
    public ResponseEntity<?> handleEntityNotFoundByIdException(EntityNotFoundByIdException exc) {
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value
            = { ConfirmationTokenConfirmedException.class, ConfirmationTokenExpiredException.class})
    public ResponseEntity<?> handleConfirmationTokenExceptions(ConfirmationTokenConfirmedException exc) {
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.CONFLICT);
    }
}
