package com.mcsv.hotel.hotelservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/staffs")
public class StaffController {
    ResponseEntity<List<String>> staffsList(){
        List<String> staffs = Arrays.asList("Pablo", "ALvaro", "Alfonso", "Juan");
        return new ResponseEntity<>(staffs, HttpStatus.OK);
    }
}
