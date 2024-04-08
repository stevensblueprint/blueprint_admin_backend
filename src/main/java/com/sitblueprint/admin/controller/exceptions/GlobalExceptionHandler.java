package com.sitblueprint.admin.controller.exceptions;

import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.NoSuchElementException;

public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity handleServiceException(ServiceException e, WebRequest request) {
        return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity handleNoSuchElementException(NoSuchElementException e, WebRequest request) {
        return new ResponseEntity("Resource not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleGenericException(Exception e, WebRequest request) {
        return new ResponseEntity("An unexpected error has occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
