package ru.netology.Cloud.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RestControllerAdvice
public class ExceptionApiHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> clientError(HttpClientErrorException exception) {
        return ResponseEntity
                .status(exception.getStatusCode())
                .body(exception.getMessage());
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<String> serverError(HttpServerErrorException exception) {
        return ResponseEntity
                .status(exception.getStatusCode())
                .body(exception.getMessage());
    }
}
