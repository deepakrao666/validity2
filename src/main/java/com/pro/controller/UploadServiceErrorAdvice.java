package com.pro.controller;


import com.pro.exceptions.FileFormatCustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class UploadServiceErrorAdvice {
    private static Logger logger = LoggerFactory.getLogger(UploadServiceErrorAdvice.class);

    @ExceptionHandler({FileFormatCustomException.class, RuntimeException.class})
    public ResponseEntity<String> fileFormatException(FileFormatCustomException e) {
        return error(BAD_REQUEST, e);
    }

    private ResponseEntity<String> error(HttpStatus status, Exception e) {
        logger.error("Exception : ", e);
        return ResponseEntity.status(status).body(e.getMessage());
    }

}