package com.bell.BellApi.exception.handler;


import com.bell.BellApi.dto.error.response.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import java.util.UUID;

@RestControllerAdvice
public class ExHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(Logger.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> unhandledException(Exception e){
        ErrorResponse errorResponse = new ErrorResponse("Internal server error");
        mapError(errorResponse);
        LOGGER.error(errorResponse.getCode(), e);
        return ResponseEntity.internalServerError().body(errorResponse);
    }

    @ExceptionHandler({EntityNotFoundException.class,
                        NoResultException.class})
    public ResponseEntity<ErrorResponse> notFound(Exception e){
        ErrorResponse errorResponse = new ErrorResponse("Not found");
        mapError(errorResponse);
        LOGGER.warn(errorResponse.getCode(), e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> badRequest(IllegalStateException e){
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        mapError(errorResponse);
        LOGGER.warn(errorResponse.getCode(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    private void mapError(ErrorResponse e){
        e.setCode(UUID.randomUUID().toString());
    }
}
