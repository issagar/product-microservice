package com.mcsv.rating.ratingservice.controllers;

import com.mcsv.rating.ratingservice.entities.Rating;
import com.mcsv.rating.ratingservice.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<Rating> saveRatings(@RequestBody Rating rating){
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));
    }

    @GetMapping
    public ResponseEntity<List<Rating>> listRating(){
        return ResponseEntity.ok(ratingService.getCalifications());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> lisRatingsByUserId(@PathVariable String userId){
        return ResponseEntity.ok(ratingService.getCalificationsByUserId(userId));
    }
    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> listRatingsByHotelId(@PathVariable String hotelId){
        return ResponseEntity.ok(ratingService.getCalificationsByHotelId(hotelId));
    }
}
