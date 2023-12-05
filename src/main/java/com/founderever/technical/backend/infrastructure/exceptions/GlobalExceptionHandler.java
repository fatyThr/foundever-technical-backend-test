package com.founderever.technical.backend.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e) {
        return new ResponseEntity<>("An internal error has occurred : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TechnicalException.class)
    public ResponseEntity<String> handleTechnicalException(TechnicalException e) {
        return new ResponseEntity<>("A specific error has occurred : " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
