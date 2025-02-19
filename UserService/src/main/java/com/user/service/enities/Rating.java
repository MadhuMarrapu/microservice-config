package com.user.service.enities;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Rating {
    private int ratingId;
    private long userId;
    private int hotelId;
    private int rating;
    private String feedback;
    private Hotel hotel;
}
