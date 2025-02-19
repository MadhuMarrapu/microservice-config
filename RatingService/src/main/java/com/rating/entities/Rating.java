package com.rating.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="user_ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ratingId;
    private String userId;
    private String hotelId;
    private  int rating;
    private  String feedback;
}
