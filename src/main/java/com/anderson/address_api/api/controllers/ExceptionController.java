package com.anderson.address_api.api.controllers;

import com.anderson.address_api.shared.exceptions.AlreadyRegisteredException;
import com.anderson.address_api.shared.exceptions.InvalidDataException;
import com.anderson.address_api.shared.exceptions.NotFoundException;
import com.anderson.address_api.shared.exceptions.StandardException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardException> notFound(NotFoundException exception, HttpServletRequest request) {
        String error = "Not found exception !";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardException e = new StandardException(Instant.now(), status.value(), error, exception.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(e);
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<StandardException> invalidData(InvalidDataException exception, HttpServletRequest request) {
        String error = "Invalid data exception !";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardException e = new StandardException(Instant.now(), status.value(), error, exception.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(e);
    }

    @ExceptionHandler(AlreadyRegisteredException.class)
    public ResponseEntity<StandardException> invalidData(AlreadyRegisteredException exception, HttpServletRequest request) {
        String error = "Already registered exception !";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardException e = new StandardException(Instant.now(), status.value(), error, exception.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(e);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<StandardException> illegalState(IllegalStateException exception, HttpServletRequest request) {
        String error = "Try again later !";
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        StandardException e = new StandardException(Instant.now(), status.value(), error, exception.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(e);
    }
}
