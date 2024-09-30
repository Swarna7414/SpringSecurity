package com.Securtiy.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger= LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(alReadyUserPresentException.class)
    public ResponseEntity<ErrorResponse> handlealreadyPresentException(alReadyUserPresentException ex){
        logger.error("User Regestration Failed because"+ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(),HttpStatus.CONFLICT.value());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

}
