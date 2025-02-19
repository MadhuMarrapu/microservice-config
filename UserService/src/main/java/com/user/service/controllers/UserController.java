package com.user.service.controllers;

import com.user.service.enities.User;

import com.user.service.services.UserService;
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
   public ResponseEntity<User> createUser(@RequestBody  User user){
       User createUser= userService.saveUser(user);
       return new ResponseEntity<>(createUser,HttpStatus.CREATED);
   }

   @GetMapping
   public ResponseEntity<List<User>> getAllUser(){
     List<User> getUsers = userService.getAllUser();
     return new ResponseEntity<>(getUsers,HttpStatus.OK);
   }

   @GetMapping("/{userId}")
   public ResponseEntity<User> getSingleUser(@PathVariable long userId){
   User getSingleUser = userService.getUser(userId);
   return new ResponseEntity<>(getSingleUser,HttpStatus.OK);
   }
}
