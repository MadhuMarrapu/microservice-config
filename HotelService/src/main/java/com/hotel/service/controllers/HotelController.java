package com.hotel.service.controllers;

import com.hotel.service.entities.Hotel;
import com.hotel.service.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    @Autowired
    HotelService hotelService;

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        Hotel createhotel = hotelService.create(hotel);
        return new ResponseEntity<Hotel>(createhotel, HttpStatus.CREATED);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Integer hotelId){
        Hotel getHotel = hotelService.get(hotelId);
        return new ResponseEntity<>(getHotel,HttpStatus.OK);
    }

     @GetMapping
     public ResponseEntity<List<Hotel>> getHotels(){
        List<Hotel> hotels = hotelService.getAll();
        return new ResponseEntity<>(hotels,HttpStatus.OK);
     }

}
