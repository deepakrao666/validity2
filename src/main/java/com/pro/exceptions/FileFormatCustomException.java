package com.pro.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileFormatCustomException extends RuntimeException {
    private static Logger logger = LoggerFactory.getLogger(FileFormatCustomException.class);

    public FileFormatCustomException(String message) {
        super(message);
        logger.warn(message);
    }
}
