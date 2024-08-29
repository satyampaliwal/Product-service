package com.scaler.productservice.inheritancedemo.mappedsuperclass;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "ms_mentor")
public class Mentor extends User{
    private String company;
    private Float avgRating;
}
