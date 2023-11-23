package com.mcsv.rating.ratingservice.repository;

import com.mcsv.rating.ratingservice.entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingRepository extends MongoRepository<Rating, Long> {
    List<Rating> findByUserId(String userId);
    List<Rating> findByHotelId(String hotelId);
}
