package com.bell.BellApi.exception.handler;


import com.bell.BellApi.dto.response.error.ErrorResponse;
import com.bell.BellApi.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.UUID;

@RestControllerAdvice
public class ExHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(Logger.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> unhandledException(Exception e){
        ErrorResponse errorResponse = new ErrorResponse("Internal server error");
        String code = UUID.randomUUID().toString();
        errorResponse.setCode(code);
        LOGGER.error(code, e);
        return ResponseEntity.internalServerError().body(errorResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> notFound(NotFoundException e){
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        String code = UUID.randomUUID().toString();
        errorResponse.setCode(code);
        LOGGER.error(code, e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> badRequest(IllegalStateException e){
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        String code = UUID.randomUUID().toString();
        errorResponse.setCode(code);
        LOGGER.error(code, e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
