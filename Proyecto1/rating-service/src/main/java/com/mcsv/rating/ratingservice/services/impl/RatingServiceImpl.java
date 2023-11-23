package com.mcsv.rating.ratingservice.services.impl;

import com.mcsv.rating.ratingservice.entities.Rating;
import com.mcsv.rating.ratingservice.repository.RatingRepository;
import com.mcsv.rating.ratingservice.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    RatingRepository ratingRepository;
    @Override
    public Rating create(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getCalifications() {
        return  ratingRepository.findAll();
    }

    @Override
    public List<Rating> getCalificationsByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getCalificationsByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }
}
