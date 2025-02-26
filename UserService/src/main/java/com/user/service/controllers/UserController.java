package com.user.service.controllers;

import com.user.service.enities.User;

import com.user.service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createUser = userService.saveUser(user);
        return new ResponseEntity<>(createUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        List<User> getUsers = userService.getAllUser();
        return new ResponseEntity<>(getUsers, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
   // @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
   // @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable long userId) {
        User getSingleUser = userService.getUser(userId);
        return new ResponseEntity<>(getSingleUser, HttpStatus.OK);
    }

    //creating fall back method for circuit breaker
    public ResponseEntity<User> ratingHotelFallback(long userId,Exception ex){
     // logger.info("Fallback is executed because service is down :",ex.getMessage());
      User user = User.builder()
              .email("dummy@email")
              .name("dummy")
              .about("This user is created dummy because some service is down")
              .userId(12345)
              .build();
      return new ResponseEntity<>(user,HttpStatus.OK);
    }
}
