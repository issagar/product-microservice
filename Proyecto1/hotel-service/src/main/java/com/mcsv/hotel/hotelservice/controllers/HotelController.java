package com.mcsv.hotel.hotelservice.controllers;

import com.mcsv.hotel.hotelservice.entity.Hotel;
import com.mcsv.hotel.hotelservice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<Hotel> saveHotel(@RequestBody Hotel hotelRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotelRequest));
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> listHotels(){
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.getAllHotels());
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId){
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.getHotel(hotelId));
    }
}
