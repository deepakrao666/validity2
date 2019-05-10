package com.validity.controllers;

import com.validity.models.FileReport;
import com.validity.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class BaseController {
    private static Logger logger = LoggerFactory.getLogger(BaseController.class);

    @GetMapping("/")
    public ResponseEntity<String> status() {
        return new ResponseEntity<>("{ status : up }", HttpStatus.OK);
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return new ResponseEntity<>("{ status : up }", HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<User> someUser() {
        User master = new User.UserBuilder()
                .withUserId(0)
                .withFirstName("deepak")
                .withLastName("guntamukalla")
                .withCompany("xxxx")
                .withEmail("xxx@asd.com")
                .withAddressOne("xxx")
                .withAddressTwo("xxx")
                .withCity("xxx")
                .withStateShort("xxx")
                .withStateLong("xx")
                .withZipCode(12345)
                .withPhoneNumber("xxx-xxx-xxx")
                .build();


        return new ResponseEntity<>(master, HttpStatus.OK);
    }



}
