package com.mcsv.hotel.hotelservice.service;

import com.mcsv.hotel.hotelservice.entity.Hotel;

import java.util.List;

public interface HotelService {
    Hotel create (Hotel hotel);
    List<Hotel> getAllHotels();
    Hotel getHotel(String hotelId);
}
