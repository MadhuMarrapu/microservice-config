package com.hotel.service.entities;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name="hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private  int id;
    private  String name;
    private  String location;
    private  String about;

}
