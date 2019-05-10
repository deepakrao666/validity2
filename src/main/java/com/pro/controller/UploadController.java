package com.pro.controller;

import com.pro.exceptions.FileFormatCustomException;
import com.pro.models.FileReport;
import com.pro.services.struct.FileInputService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
public class UploadController  extends ResponseEntityExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(UploadController.class);

    private FileInputService fileInputService;

    public UploadController(FileInputService fileInputService) {
        this.fileInputService = fileInputService;
    }

    /**
     * ConsumeCsv function
     * Consumes a CSV file to generate and return a report for it.
     * @param file as input
     * @return fileReport in te format of a ReST response (JSON in or case)
     */
    @PostMapping("/upload")
    @ExceptionHandler(FileFormatCustomException.class)
    public ResponseEntity<FileReport> consumeCsv(@RequestParam("file") MultipartFile file) {
        logger.info("consuming CSV file");
        HttpHeaders header = new HttpHeaders();
        header.add("Content-type", MediaType.APPLICATION_JSON_VALUE);
        return new ResponseEntity<>(fileInputService.consumeAndReport(file), HttpStatus.OK);
    }


}
