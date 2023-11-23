package com.mcsv.rating.ratingservice.services;

import com.mcsv.rating.ratingservice.entities.Rating;

import java.util.List;

public interface RatingService {
    Rating create(Rating rating);
    List<Rating> getCalifications();
    List<Rating> getCalificationsByUserId(String userId);
    List<Rating> getCalificationsByHotelId(String hotelId);
}
