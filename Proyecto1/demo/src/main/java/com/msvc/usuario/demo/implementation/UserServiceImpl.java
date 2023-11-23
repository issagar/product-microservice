package com.msvc.usuario.demo.implementation;

import com.msvc.usuario.demo.entity.Calification;
import com.msvc.usuario.demo.entity.Hotel;
import com.msvc.usuario.demo.external.services.HotelService;
import com.msvc.usuario.demo.external.services.RatingService;
import com.msvc.usuario.demo.service.UserService;
import com.msvc.usuario.demo.entity.User;
import com.msvc.usuario.demo.exceptions.ResourceNotFoundException;
import com.msvc.usuario.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RatingService ratingService;


    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException ("Not found user with ID: " + userId));
        Calification[] calificationsArray = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+userId, Calification[].class);
        List<Calification> califications = Arrays.stream(calificationsArray).collect(Collectors.toList());

        List<Calification> calificationList = califications.stream().map(calification -> {
            System.out.println(calification.getHotelId());
            //ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+calification.getHotelId(), Hotel.class);
            //Hotel hotel = forEntity.getBody();
            Hotel hotel = hotelService.getHotel(calification.getHotelId());
            calification.setHotel(hotel);
            return calification;
        }).collect(Collectors.toList());

        user.setCalificationList(calificationList);
        return user;
    }
}
