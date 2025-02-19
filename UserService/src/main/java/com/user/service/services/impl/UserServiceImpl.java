package com.user.service.services.impl;

import com.user.service.enities.Hotel;
import com.user.service.enities.Rating;
import com.user.service.enities.User;
import com.user.service.exceptions.ResourceNotFoundException;
import com.user.service.external.services.HotelService;
import com.user.service.repositories.UserRepository;
import com.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HotelService hotelService;


    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        //implement RATING SERVICE CALL: USING REST TEMPLATE
        return userRepository.findAll();
    }


    //get single user
    @Override
    public User getUser(long userId) {
        //get single user from database with help of repository
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given id is not found on server !! : " + userId));
       //fetch rating of the above user from RATING-SERVICE
       // http://localhost:8083/api/ratings/users/13
      //  http://localhost:8083/api/ratings/hotels/4

        Rating[] ratingOfUser = restTemplate.getForObject("http://RATINGSERVICE/api/ratings/users/"+user.getUserId(), Rating[].class);
        logger.info("{}",ratingOfUser);
        List<Rating> ratings = Arrays.stream(ratingOfUser).toList();
        List<Rating> ratingList = ratings.stream().map(rating -> {
            //api call to hotel service to get the hotel
            //http://localhost:8082/api/hotels/5
          //  ResponseEntity<Hotel> hotelResponseEntity = restTemplate.getForEntity("http://HOTELSERVICE/api/hotels/"+rating.getHotelId(), Hotel.class);
           // Hotel hotel= hotelResponseEntity.getBody();
            Hotel hotel= hotelService.getHotel(rating.getHotelId());
          //  logger.info("response status code: {}",hotelResponseEntity);
            //set the hotel to rating
            rating.setHotel(hotel);
            // return the rating
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);

        return user;
    }
}
