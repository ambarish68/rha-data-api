package com.rha.dataapi.hibernate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {AccessDeniedException.class})
    protected ResponseEntity<Object> handleAccessDeniedException(
            RuntimeException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("The resource you are trying to access is forbidden");
    }

    @ExceptionHandler(value
            = {EntityNotFoundException.class})
    protected ResponseEntity<Object> handleResourceNotFoundException(
            RuntimeException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The resource you are trying to access does not exist");
    }

    @ExceptionHandler(value
            = {IllegalArgumentException.class})
    protected ResponseEntity<Object> handleIllegalArgumentException(
            RuntimeException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid parameters in request");
    }
}