package com.rating.controllers;


import com.rating.entities.Rating;
import com.rating.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    @Autowired
    RatingService ratingService;

    //create rating
    @PostMapping
    public ResponseEntity<Rating> create(@RequestBody Rating rating){
        Rating rating1 =ratingService.create(rating);
        return new ResponseEntity<Rating>(rating1, HttpStatus.CREATED);
    }

    //getAll
    @GetMapping
    public ResponseEntity<List<Rating>> getRatings(){
        List<Rating> ratings = ratingService.getRatings();
        return new ResponseEntity<>(ratings,HttpStatus.OK);
    }

    //getAll
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId){
        List<Rating> ratings = ratingService.getRatingByUserId(userId);
        return new ResponseEntity<>(ratings,HttpStatus.OK);
    }

    //getAll
    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatings(@PathVariable String hotelId){
        List<Rating> ratings = ratingService.getRatingByHotelId(hotelId);
        return new ResponseEntity<>(ratings,HttpStatus.OK);
    }

}
